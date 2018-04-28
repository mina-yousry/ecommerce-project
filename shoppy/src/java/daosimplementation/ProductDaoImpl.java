/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosimplementation;

import daosinterfaces.ProductDaoInt;
import dbconnectionfactory.DBConnection;
import dtos.ProductDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mina
 */
public class ProductDaoImpl implements ProductDaoInt {

    @Override
    public boolean create(ProductDto product) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement insertPs = connection.prepareStatement("INSERT INTO product (name,description,price,quantity_stock,vendor,category,image,quantity_cart)  VALUES (?,?,?,?,?,?,?,?)");
            insertPs.setString(1, product.getProductName());
            insertPs.setString(2, product.getDescription());
            insertPs.setDouble(3, product.getPrice());
            insertPs.setInt(4, product.getQuantityStock());
            insertPs.setString(5, product.getVendor());
            insertPs.setString(6, product.getCategory());
            if (product.getProductImage() == null) {
                insertPs.setNull(7, java.sql.Types.BLOB);
            } else {
                insertPs.setBytes(7, product.getProductImage());
            }
            insertPs.setInt(8, 0);
            System.out.println("insert" + insertPs.executeUpdate());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public HashMap<Integer, ProductDto> retrieve(String productName) {

        HashMap<Integer, ProductDto> foundProductDtos = new HashMap<>();
        productName.replaceAll(" ", "%");
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM product WHERE name like '" + productName + "%'");
            ResultSet retSet = retrievePs.executeQuery();
            while (retSet.next()) {
                ProductDto product = new ProductDto();
                product.setProductId(retSet.getInt(1));
                product.setProductName(retSet.getString(2));
                product.setDescription(retSet.getString(3));
                product.setPrice(retSet.getInt(4));
                product.setQuantityStock(retSet.getInt(5));
                product.setVendor(retSet.getString(6));
                product.setCategory(retSet.getString(7));
                product.setProductImage(retSet.getBytes(8));
                product.setQuantityCart(retSet.getInt(9));
                foundProductDtos.put(product.getProductId(), product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            foundProductDtos = null;
        }
        return foundProductDtos;
    }

    @Override
    public boolean update(ProductDto product) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement insertPs = connection.prepareStatement("UPDATE product SET name=?, description=?, "
                    + "price=?, quantity_stock=?, vendor=?, category=?, image=?, quantity_cart=? WHERE product_id=?");
            insertPs.setString(1, product.getProductName());
            insertPs.setString(2, product.getDescription());
            insertPs.setDouble(3, product.getPrice());
            insertPs.setInt(4, product.getQuantityStock());
            insertPs.setString(5, product.getVendor());
            insertPs.setString(6, product.getCategory());
            if (product.getProductImage() == null) {
                insertPs.setNull(7, java.sql.Types.BLOB);
            } else {
                insertPs.setBytes(7, product.getProductImage());
            }

            insertPs.setInt(8, product.getQuantityCart());
            insertPs.setInt(9, product.getProductId());
            return insertPs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(ProductDto product) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement deletePs = connection.prepareStatement("DELETE FROM product WHERE product_id =?");
            deletePs.setInt(1, product.getProductId());
            deletePs.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ProductDto retrieveById(int productId) {

        ProductDto product = null;

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM product WHERE product_id=?");
            retrievePs.setInt(1, productId);
            ResultSet retSet = retrievePs.executeQuery();
            if (retSet.isBeforeFirst()) {
                retSet.next();
                product = new ProductDto();
                product.setProductId(retSet.getInt(1));
                product.setProductName(retSet.getString(2));
                product.setDescription(retSet.getString(3));
                product.setPrice(retSet.getDouble(4));
                product.setQuantityStock(retSet.getInt(5));
                product.setVendor(retSet.getString(6));
                product.setCategory(retSet.getString(7));
                product.setProductImage(retSet.getBytes(8));
                product.setQuantityCart(retSet.getInt(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public HashMap<Integer, ProductDto> getAllProducts() {

        HashMap<Integer, ProductDto> foundProductDtos = new HashMap<>();

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM product");
            ResultSet retSet = retrievePs.executeQuery();
            while (retSet.next()) {
                ProductDto product = new ProductDto();
                product.setProductId(retSet.getInt(1));
                product.setProductName(retSet.getString(2));
                product.setDescription(retSet.getString(3));
                product.setPrice(retSet.getDouble(4));
                product.setQuantityStock(retSet.getInt(5));
                product.setVendor(retSet.getString(6));
                product.setCategory(retSet.getString(7));
                product.setProductImage(retSet.getBytes(8));
                product.setQuantityCart(retSet.getInt(9));
                foundProductDtos.put(product.getProductId(), product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            foundProductDtos = null;
        }
        return foundProductDtos;
    }

    public void updateQty(Integer productId, Integer qty) {
        ProductDto product = retrieveById(productId);
        if (product != null) {
            int tempQty = product.getQuantityCart();
            tempQty += qty;
            product.setQuantityCart(tempQty);
            update(product);
        }
    }

    public int getNewId() {
        int res = -1;
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement st = connection.prepareStatement("SELECT product_id FROM product");) {
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) {
                res = 1;
            } else {
                rs.last();
                res = rs.getInt("product_id") + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("");
        return res;
    }

    public HashMap<Integer, ProductDto> getProductsByCategory(String category) {

        HashMap<Integer, ProductDto> foundProductDtos = new HashMap<>();

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM product WHERE category=?");
            retrievePs.setString(1, category);
            ResultSet retSet = retrievePs.executeQuery();
            while (retSet.next()) {
                ProductDto product = new ProductDto();
                product.setProductId(retSet.getInt(1));
                product.setProductName(retSet.getString(2));
                product.setDescription(retSet.getString(3));
                product.setPrice(retSet.getDouble(4));
                product.setQuantityStock(retSet.getInt(5));
                product.setVendor(retSet.getString(6));
                product.setCategory(retSet.getString(7));
                product.setProductImage(retSet.getBytes(8));
                product.setQuantityCart(retSet.getInt(9));
                foundProductDtos.put(product.getProductId(), product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            foundProductDtos = null;
        }
        return foundProductDtos;
    }

}

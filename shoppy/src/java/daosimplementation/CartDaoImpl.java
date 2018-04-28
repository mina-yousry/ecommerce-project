/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosimplementation;

import daosinterfaces.CartDaoInt;
import dbconnectionfactory.DBConnection;
import dtos.OnlineCartDto;
import dtos.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class CartDaoImpl implements CartDaoInt {

    @Override
    public boolean create(OnlineCartDto cart) {
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement insertCartQuery = connection.prepareStatement("INSERT INTO cart (user_id, total_price) VALUES(?,?)");

            insertCartQuery.setInt(1, cart.getUserId());
            insertCartQuery.setDouble(2, cart.getTotalPrice());

            insertCartQuery.executeUpdate();

            OnlineCartDto newCart = retrieve(cart.getUserId());

            PreparedStatement insertProductQuery = connection.prepareStatement("INSERT INTO cart_products (cart_id, product_id, quantity) VALUES(?,?,?)");

            for (int productId : cart.getProductMap().keySet()) {

                insertProductQuery.setInt(1, newCart.getCartId());
                insertProductQuery.setInt(2, productId);
                insertProductQuery.setInt(3, cart.getProductMap().get(productId));

                insertProductQuery.executeUpdate();

            }

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public OnlineCartDto retrieve(int userId) {

        OnlineCartDto cart = null;
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement selectCartQuery = connection.prepareStatement("SELECT * FROM cart WHERE user_id = ?");

            selectCartQuery.setInt(1, userId);
            //selectCartQuery.setInt(2, userId);

            ResultSet rs = selectCartQuery.executeQuery();

            while (rs.next()) {
                cart = new OnlineCartDto();
                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setTotalPrice(rs.getDouble("total_price"));

                PreparedStatement selectProductQuery = connection.prepareStatement("SELECT * FROM cart_products WHERE cart_id = ?");

                selectProductQuery.setInt(1, cart.getCartId());

                ResultSet rs2 = selectProductQuery.executeQuery();

                while (rs2.next()) {

                    cart.getProductMap().put(rs2.getInt("product_id"), rs2.getInt("quantity"));
                    cart.setTotalItems(cart.getTotalItems() + rs2.getInt("quantity"));

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cart;
    }

    @Override
    public boolean update(OnlineCartDto cart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(OnlineCartDto cart) {
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement deleteCartQuery = connection.prepareStatement("DELETE FROM cart WHERE user_id = ?");

            deleteCartQuery.setInt(1, cart.getUserId());

            deleteCartQuery.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}

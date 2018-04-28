/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Mina
 */
public class ProductDto {
    
    private int productId;
    private String productName;
    private String description;
    private double price;
    private int quantityStock;
    private String vendor;
    private String category;
    private byte[] productImage;
    private int quantityCart;
//    public static final String CATEGORY_FOOD = "FOOD";
//    public static final String CATEGORY_CLOTHES = "CLOTHES";
//    public static final String CATEGORY_DRINKS = "DRINKS";
//    public static final String CATEGORY_ELECTRONICS = "ELECTRONICS";
//    public static final String CATEGORY_FURNITURE = "FURNITURE";
//    public static final String CATEGORY_STATIONARY = "STATIONARY";
//    public static final String CATEGORY_ACCESSORIES = "ACCESSORIES";
//    public static final String CATEGORY_OTHERS = "OTHERS";

    public ProductDto() {
    }

    public ProductDto(int productId, String productName, String description, double price, int quantity_stock, String vendor, String category, byte[] productImage, int quantity_cart) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityStock = quantity_stock;
        this.vendor = vendor;
        this.category = category;
        this.productImage = productImage;
        this.quantityCart = quantity_cart;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantity_stock) {
        this.quantityStock = quantity_stock;
    }

    public int getQuantityCart() {
        return quantityCart;
    }

    public void setQuantityCart(int quantity_cart) {
        this.quantityCart = quantity_cart;
    }
    
    
    
}

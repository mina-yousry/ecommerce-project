/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Omar
 */
public class OnlineCartDto {

    private int cartId;
    private int userId;
    private double totalPrice;
    private int totalItems;
    private Timestamp time;
//product id + Qty
    private Map<Integer, Integer> productMap;

    public OnlineCartDto() {
        productMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    }

    public OnlineCartDto(int cartId, int userId, double totalPrice, int totalItems, Timestamp time, Map<Integer, Integer> productMap) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
        this.time = time;
        this.productMap = productMap;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Map<Integer, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Integer, Integer> productList) {
        this.productMap = productList;
    }

    public void addProduct(ProductDto product, Integer qty) {
        Integer currentProductQty;
        if (productMap.containsKey(product.getProductId())) {
            currentProductQty = productMap.get(product.getProductId());
            currentProductQty += qty;
             productMap.put(product.getProductId(), currentProductQty);
        } else {
            productMap.put(product.getProductId(), qty);
        }
        totalItems += qty;
    }

    public void removeProduct(Integer productId) {
        if (productMap.containsKey(productId)) {
            Integer currentProductQty = productMap.get(productId);
            totalItems -= currentProductQty;
            productMap.remove(productId);
        }
    }

    public void reduceProductQty(ProductDto product, Integer qty) {
        if (productMap.containsKey(product.getProductId())) {
            Integer currentProductQty = productMap.get(product.getProductId());
            totalItems -= qty;
            if (currentProductQty.equals(qty)) {
                productMap.remove(product.getProductId());
            } else {
                currentProductQty -= qty;
                productMap.put(product.getProductId(), currentProductQty);

            }
        }
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.time.LocalDate;

/**
 *
 * @author Mina
 */
public class CartProductsDto {
    
    private int cartId;
    private int productId;
    private int quantity;
    private LocalDate time;

    public CartProductsDto() {
    }

    public CartProductsDto(int cartId, int productId, int quantity, LocalDate time) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.time = time;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
    
    
}

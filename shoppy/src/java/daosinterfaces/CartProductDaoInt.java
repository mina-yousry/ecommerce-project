/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosinterfaces;

import dtos.CartDto;
import dtos.CartProductsDto;
import java.util.ArrayList;

/**
 *
 * @author Mina
 */
public interface CartProductDaoInt {

    public boolean create(CartProductsDto cartProduct);

    public ArrayList<CartProductsDto> retrieve(CartDto Cart);

    public boolean update(CartProductsDto cartProduct);

    public boolean delete(CartProductsDto cartProduct);

}

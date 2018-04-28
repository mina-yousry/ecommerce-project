/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosinterfaces;

import dtos.CartHistoryDto;
import dtos.UserDto;

/**
 *
 * @author Mina
 */
public interface CartHistoryDaoInt {

    public boolean create(CartHistoryDto cart);

    public CartHistoryDto retrieve(UserDto user);

    public boolean update(CartHistoryDto cart);

    public boolean delete(CartHistoryDto cart);

}

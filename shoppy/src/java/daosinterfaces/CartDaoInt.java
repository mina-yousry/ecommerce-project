/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosinterfaces;


import dtos.OnlineCartDto;

/**
 *
 * @author Mina
 */
public interface CartDaoInt {

    public boolean create(OnlineCartDto cart);

    public OnlineCartDto retrieve(int userId);

    public boolean update(OnlineCartDto cart);

    public boolean delete(OnlineCartDto cart);

}

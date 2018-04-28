/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosinterfaces;

import dtos.UserDto;

/**
 *
 * @author Mina
 */
public interface UserDaoInt {

    public boolean create(UserDto user);

    public UserDto retrieve(String mail);

    public boolean update(UserDto user);

    public boolean delete(Integer id);

}

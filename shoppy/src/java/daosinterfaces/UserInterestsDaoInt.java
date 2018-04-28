/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosinterfaces;

import dtos.UserDto;
import java.util.ArrayList;

/**
 *
 * @author Mina
 */
public interface UserInterestsDaoInt {

    public boolean create(UserDto user);

    public  ArrayList<String> retrieve(UserDto user);

    public boolean update(UserDto user, String oldInterest, String newInterest);

    public boolean delete(UserDto user, String deletedInterest);
}

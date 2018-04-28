/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosimplementation;

import daosinterfaces.UserInterestsDaoInt;
import dbconnectionfactory.DBConnection;
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
 * @author Mina
 */
public class UserInterestsDaoImpl implements UserInterestsDaoInt {

    @Override
    public boolean create(UserDto user) {

        ArrayList<String> interests = user.getInterests();

        try (Connection connection = DBConnection.getConnection()) {
            for (int i = 0; i < interests.size(); i++) {
                PreparedStatement insertPs = connection.prepareStatement("INSERT INTO user VALUES(?,?)");
                insertPs.setInt(1, user.getUserId());
                insertPs.setString(2, interests.get(i));
                insertPs.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<String> retrieve(UserDto user) {

        ArrayList<String> interests = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM user_interests WHERE user_id='" + user.getUserId() + "'");
            ResultSet retSet = retrievePs.executeQuery();
            while (retSet.next()) {
                interests.add(retSet.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            interests = null;
        }
        return interests;
    }

    @Override
    public boolean update(UserDto user, String oldInterest, String newInterest) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement updatePs = connection.prepareStatement("UPDATE user_interests SET interest=? "
                    + "WHERE user_id='" + user.getUserId() + "'"
                    + "AND interest = '" + oldInterest + "'");
            updatePs.setString(1,newInterest);
            updatePs.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(UserDto user, String deletedInterest) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement deletePs = connection.prepareStatement("DELETE FROM user_interests "
                    + "WHERE user_id='" + user.getUserId() + "'"
                    + "AND interest = '" + deletedInterest + "'");
            deletePs.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}

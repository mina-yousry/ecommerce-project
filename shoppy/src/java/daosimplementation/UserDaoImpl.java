/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daosimplementation;

import daosinterfaces.UserDaoInt;
import dbconnectionfactory.DBConnection;
import dtos.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mina
 */
public class UserDaoImpl implements UserDaoInt {

    @Override
    public boolean create(UserDto user) {

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM user WHERE email=?");
            retrievePs.setString(1, user.getMail());
            ResultSet retSet = retrievePs.executeQuery();
            if (retSet.isBeforeFirst()) {
                return false;
            }
            PreparedStatement insertPs = connection.prepareStatement("INSERT INTO user (first_name,last_name,phone,dob, job,email, password, balance, address,image)  VALUES(?,?,?,?,?,?,?,?,?,?)");
            System.out.println(user);
            insertPs.setString(1, user.getFirstName());
            insertPs.setString(2, user.getLastName());
            insertPs.setString(3, user.getPhone());

            Date date = new SimpleDateFormat("mm-dd-yyyy").parse(user.getDob());
            java.sql.Date dbDate = new java.sql.Date(date.getTime());
            insertPs.setDate(4, dbDate);
            insertPs.setString(5, user.getJob());
            insertPs.setString(6, user.getMail());
            insertPs.setString(7, user.getPassword());
            insertPs.setDouble(8, user.getBalance());
            insertPs.setString(9, user.getAddress());
            if (user.getProfileImage() == null) {
                insertPs.setNull(10, java.sql.Types.BLOB);
            } else {
                insertPs.setBytes(10, user.getProfileImage());
            }

            System.out.println("DB CONNN " + insertPs.executeUpdate());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public UserDto retrieve(String mail) {

        UserDto user = null;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM user WHERE email='" + mail + "'");
            ResultSet retSet = retrievePs.executeQuery();
            if (!retSet.isBeforeFirst()) {
                return user;
            }
            user = new UserDto();
            while (retSet.next()) {
                user.setUserId(retSet.getInt(1));
                user.setFirstName(retSet.getString(2));
                user.setLastName(retSet.getString(3));
                user.setPhone(retSet.getString(4));
                java.sql.Date dbDate = retSet.getDate(5);
                String userBD = dbDate.toString();
                user.setDob(userBD);
                user.setJob(retSet.getString(6));
                user.setMail(retSet.getString(7));
                user.setPassword(retSet.getString(8));
                user.setBalance(retSet.getDouble(9));
                user.setAddress(retSet.getString(10));
                user.setProfileImage(retSet.getBytes(11));
                user.setType(retSet.getString(12));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            user = null;
        }
        return user;
    }

    public UserDto retrieve(int id) {

        UserDto user = null;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM user WHERE user_id='" + id + "'");
            ResultSet retSet = retrievePs.executeQuery();
            if (!retSet.isBeforeFirst()) {
                return user;
            } else {
                user = new UserDto();
            }
            while (retSet.next()) {
                user.setUserId(retSet.getInt(1));
                user.setFirstName(retSet.getString(2));
                user.setLastName(retSet.getString(3));
                user.setPhone(retSet.getString(4));
                java.sql.Date dbDate = retSet.getDate(5);
                String userBD = dbDate.toString();
                user.setDob(userBD);
                user.setJob(retSet.getString(6));
                user.setMail(retSet.getString(7));
                user.setPassword(retSet.getString(8));
                user.setBalance(retSet.getDouble(9));
                user.setAddress(retSet.getString(10));
                user.setProfileImage(retSet.getBytes(11));
                user.setType(retSet.getString(12));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            user = null;
        }
        return user;
    }

    @Override
    public boolean update(UserDto user) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement updatePs = connection.prepareStatement("UPDATE user SET first_name=?, last_name=?, "
                    + "phone=?, dob=?, job=?, email=?, password=?, balance=?, address=?, image=?,type=? WHERE user_id=?");
            updatePs.setString(1, user.getFirstName());
            updatePs.setString(2, user.getLastName());
            updatePs.setString(3, user.getPhone());
            updatePs.setString(4, user.getDob());
            updatePs.setString(5, user.getJob());
            updatePs.setString(6, user.getMail());
            updatePs.setString(7, user.getPassword());
            updatePs.setDouble(8, user.getBalance());
            updatePs.setString(9, user.getAddress());
            updatePs.setBytes(10, user.getProfileImage());
            updatePs.setString(11, user.getType());
            updatePs.setInt(12, user.getUserId());
            updatePs.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Integer userId) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement deletePs = connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
            deletePs.setInt(1, userId);
            return deletePs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public UserDto retrieveByMailAndPassword(String mail, String password) {

        UserDto user = null;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement retrievePs = connection.prepareStatement("SELECT * FROM user WHERE email='" + mail + "' AND password = '" + password + "'");
            ResultSet retSet = retrievePs.executeQuery();
            if (retSet.isBeforeFirst()) {
                user = new UserDto();
                while (retSet.next()) {

                    user.setUserId(retSet.getInt(1));
                    user.setFirstName(retSet.getString(2));
                    user.setLastName(retSet.getString(3));
                    user.setPhone(retSet.getString(4));
                    java.sql.Date dbDate = retSet.getDate(5);
                    String userBD = dbDate.toString();
                    user.setDob(userBD);
                    user.setJob(retSet.getString(6));
                    user.setMail(retSet.getString(7));
                    user.setPassword(retSet.getString(8));
                    user.setBalance(retSet.getDouble(9));
                    user.setAddress(retSet.getString(10));
                    user.setProfileImage(retSet.getBytes(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public List<UserDto> retrieveall() {

        List<UserDto> usersList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement retrieveall = connection.prepareStatement("SELECT * FROM user");
            ResultSet retSet = retrieveall.executeQuery();
            while (retSet.next()) {
                int id = retSet.getInt("user_id");
                String firstName = retSet.getString("first_name");
                String lastName = retSet.getString("last_name");
                String phone = retSet.getString("phone");
                String dob = retSet.getString("dob");
                String job = retSet.getString("job");
                String email = retSet.getString("email");
                String password = retSet.getString("password");
                double balance = retSet.getDouble("balance");
                String address = retSet.getString("address");
                byte[] image = retSet.getBytes("image");
                if (image == null) {
                    System.out.println("image retreived");
                }
                String type = retSet.getString("type");

                UserDto user = new UserDto(id, firstName, lastName, phone, dob, job, email, password, balance, address, image, null, type);

                usersList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }

}

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
public class UserInterestDto {
    
    private int userId;
    private String interest;

    public UserInterestDto() {
    }

    public UserInterestDto(int userId, String interest) {
        this.userId = userId;
        this.interest = interest;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
    
    
    
}

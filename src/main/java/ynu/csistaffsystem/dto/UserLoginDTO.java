package ynu.csistaffsystem.dto;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class UserLoginDTO {

    String userName;
    String password;

    @Transient
    boolean isRememberMe;

    public UserLoginDTO(){}

    public UserLoginDTO(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}

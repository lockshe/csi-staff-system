package ynu.csistaffsystem.dto.vo;

import lombok.Data;

@Data
public class UserLoginVO {

    Integer id;
    String loginName;
    String userName;
    Integer status;

    public UserLoginVO(Integer id,String loginName, String userName, Integer status){
        this.id = id;
        this.loginName = loginName;
        this.userName = userName;
        this.status = status;
    }
}

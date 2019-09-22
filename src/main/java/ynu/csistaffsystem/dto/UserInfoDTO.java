package ynu.csistaffsystem.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "user_info")
public class UserInfoDTO {

    @Id
    Integer Id;
    String loginName;
    String password;
    Integer status;
    Date createDate;
    Date updateDate;
    String userName;
    String faceUrl;
    String facePath;

}

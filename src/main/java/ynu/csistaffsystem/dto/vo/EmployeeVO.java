package ynu.csistaffsystem.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeVO {

    Integer id;
    String department;
    String job;
    String name;
    String cardId;
    String address;
    String postCode;
    String tel;
    String qqNum;
    String email;
    Integer sex;
    String party;
    Date birthday;
    String race;
    String education;
    String speciality;
    String hobby;
    String remark;
}

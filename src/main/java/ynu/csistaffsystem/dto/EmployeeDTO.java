package ynu.csistaffsystem.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "employee_info")
public class EmployeeDTO {
    @Id
    Integer id;
    Integer deptId;
    Integer jobId;
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
    Date createTime;
    Date updateTime;

}

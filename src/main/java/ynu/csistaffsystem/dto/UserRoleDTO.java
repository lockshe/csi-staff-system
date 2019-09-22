package ynu.csistaffsystem.dto;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user_role")
public class UserRoleDTO {
    @Id
    Integer id;
    String role;
}

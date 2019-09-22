package ynu.csistaffsystem.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "dept_info")
public class DepartmentDTO {

    @Id
    Integer id;
    String name;
    String remark;
}

package ynu.csistaffsystem.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "job_info")
public class JobDTO {

    @Id
    Integer id;
    String name;
    String remark;

}

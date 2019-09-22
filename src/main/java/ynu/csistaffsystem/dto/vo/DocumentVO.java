package ynu.csistaffsystem.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentVO {

    Integer id;
    String title;
    String fileName;
    String remark;
    Date createTime;
    String userName;

}

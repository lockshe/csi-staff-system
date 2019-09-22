package ynu.csistaffsystem.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeVO {

    Integer id;
    String title;
    String content;
    Date createTime;
    String userName;
}

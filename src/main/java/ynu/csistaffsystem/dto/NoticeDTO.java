package ynu.csistaffsystem.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "notice_info")
public class NoticeDTO {
    @Id
    Integer id;
    String title;
    String content;
    Date createTime;
    Integer userId;
}

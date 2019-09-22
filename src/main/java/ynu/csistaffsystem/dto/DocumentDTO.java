package ynu.csistaffsystem.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "document_info")
public class DocumentDTO {
    @Id
    Integer id;
    String title;
    String filename;
    String remark;
    Date createTime;
    Integer userId;
}

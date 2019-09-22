package ynu.csistaffsystem.service;

import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;
import ynu.csistaffsystem.dto.DocumentDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.DocumentVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface DocumentService {

    PageInfo<DocumentVO> queryDocument(PageRequest pageRequest, Map<String, Object> map);

    void uploadDocument(DocumentDTO documentDTO, MultipartFile multipartFile);

    void updateDocumentInfo(DocumentDTO documentDTO);

    void deleteDocumentById(Integer documentId);

    void downloadDocument(Integer documentId, HttpServletResponse response);
}

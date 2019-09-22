package ynu.csistaffsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ynu.csistaffsystem.dto.DocumentDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.dto.vo.DocumentVO;
import ynu.csistaffsystem.exception.MyException;
import ynu.csistaffsystem.mapper.DocumentMapper;
import ynu.csistaffsystem.mapper.UserInfoMapper;
import ynu.csistaffsystem.service.DocumentService;
import ynu.csistaffsystem.utils.FileHandlerUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    DocumentMapper documentMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<DocumentVO> queryDocument(PageRequest pageRequest, Map<String, Object> searchParam) {
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return new PageInfo<>(documentMapper.queryDocument(searchParam));
    }

    @Override
    @Transactional
    public void uploadDocument(DocumentDTO documentDTO, MultipartFile multipartFile) {
        LOGGER.info("upload--->"+multipartFile.getOriginalFilename());
        Subject subject = SecurityUtils.getSubject();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setLoginName((String) subject.getPrincipal());
        userInfoDTO = userInfoMapper.selectOne(userInfoDTO);
        documentDTO.setUserId(userInfoDTO.getId());
        documentDTO.setFilename(FileHandlerUtil.randomPrefix(multipartFile.getOriginalFilename()));
        documentMapper.insertSelective(documentDTO);
        FileHandlerUtil.upload(multipartFile, documentDTO.getFilename());
    }

    @Override
    @Transactional
    public void updateDocumentInfo(DocumentDTO documentDTO) {
        try {
            documentMapper.updateByPrimaryKeySelective(documentDTO);
        }catch (Exception e){
            throw new MyException("文件信息更新异常!");
        }
    }

    @Override
    @Transactional
    public void deleteDocumentById(Integer documentId) {
        DocumentDTO documentDTO = documentMapper.selectByPrimaryKey(documentId);
        documentMapper.deleteByPrimaryKey(documentId);
        FileHandlerUtil.delete(documentDTO.getFilename());
    }

    @Override
    public void downloadDocument(Integer documentId, HttpServletResponse servletResponse) {
        DocumentDTO documentDTO = documentMapper.selectByPrimaryKey(documentId);
        FileHandlerUtil.download(servletResponse,documentDTO.getFilename());
    }
}

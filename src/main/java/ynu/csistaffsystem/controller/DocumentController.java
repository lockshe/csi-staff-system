package ynu.csistaffsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ynu.csistaffsystem.dto.DocumentDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.DocumentVO;
import ynu.csistaffsystem.service.DocumentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping("/query")
    public ResponseEntity<PageInfo<DocumentVO>> queryDocument(PageRequest pageRequest,@RequestBody String searchParam){
        return new ResponseEntity<>(documentService.queryDocument(pageRequest, JSONObject.parseObject(searchParam)), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile multipartFile, DocumentDTO documentDTO){
        documentService.uploadDocument(documentDTO, multipartFile);
        return new ResponseEntity<>("成功上传文件",HttpStatus.OK);
    }

    @GetMapping("/download/{docId}")
    public void downloadDocument(@PathVariable("docId")Integer documentId,HttpServletResponse response){
        documentService.downloadDocument(documentId ,response);
    }


    @RequiresRoles("Admin")
    @DeleteMapping("/deleteDoc/{docId}")
    public ResponseEntity<String> deleteDocument(@PathVariable("docId") Integer docId){
        documentService.deleteDocumentById(docId);
        return new ResponseEntity<>("删除文件成功",HttpStatus.OK);
    }


    @RequiresRoles("Admin")
    @PostMapping("/updateDoc")
    public ResponseEntity<String> updateDocument(@RequestBody DocumentDTO documentDTO){
        documentService.updateDocumentInfo(documentDTO);
        return new ResponseEntity<>("更新成功",HttpStatus.OK);
    }
}

package ynu.csistaffsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynu.csistaffsystem.dto.NoticeDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.NoticeVO;
import ynu.csistaffsystem.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    NoticeService noticeService;

    @PostMapping("/queryNotice")
    public ResponseEntity<PageInfo<NoticeVO>> queryNotice(PageRequest pageRequest,
                                                          @RequestBody(required = false) String searchParam){
        return new ResponseEntity<>(noticeService.queryNotice(JSONObject.parseObject(searchParam),pageRequest), HttpStatus.OK);
    }


    @RequiresRoles("Admin")
    @DeleteMapping("/deleteNoticeById")
    public ResponseEntity<String> deleteNoticeById(@RequestParam("noticeId") Integer noticeId){
        noticeService.deleteNoticeById(noticeId);
        return new ResponseEntity<>("删除公告成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/updateNoticeById")
    public ResponseEntity<String> updateNoticeById(@RequestBody NoticeDTO noticeDTO){
        noticeService.updateNoticeById(noticeDTO);
        return new ResponseEntity<>("更新公告成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/addNotice")
    public ResponseEntity<String> addNotice(@RequestBody NoticeDTO noticeDTO){
        noticeService.addNotice(noticeDTO);
        return new ResponseEntity<>("新增公告成功", HttpStatus.OK);
    }
}

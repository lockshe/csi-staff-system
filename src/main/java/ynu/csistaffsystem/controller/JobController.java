package ynu.csistaffsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynu.csistaffsystem.dto.JobDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @PostMapping("/queryJob")
    public ResponseEntity<PageInfo<JobDTO>> queryNotice(PageRequest pageRequest,
                                                        @RequestBody(required = false) String searchParam){
        return new ResponseEntity<>(jobService.queryJob(JSONObject.parseObject(searchParam),pageRequest), HttpStatus.OK);
    }


    @RequiresRoles("Admin")
    @DeleteMapping("/deleteJobById")
    public ResponseEntity<String> deleteNoticeById(@RequestParam("noticeId") Integer noticeId){
        jobService.deleteJobById(noticeId);
        return new ResponseEntity<>("删除职位成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/updateJobById")
    public ResponseEntity<String> updateJobById(@RequestBody JobDTO jobDTO){
        jobService.updateJobById(jobDTO);
        return new ResponseEntity<>("更新职位成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/addJob")
    public ResponseEntity<String> addNotice(@RequestBody JobDTO jobDTO){
        jobService.addJob(jobDTO);
        return new ResponseEntity<>("新增职位成功", HttpStatus.OK);
    }

}

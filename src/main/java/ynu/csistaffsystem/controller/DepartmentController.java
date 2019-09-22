package ynu.csistaffsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynu.csistaffsystem.dto.DepartmentDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.service.DepartmentService;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/queryDept")
    public ResponseEntity<PageInfo<DepartmentDTO>> queryDept(PageRequest pageRequest,
                                                             @RequestBody String searchParam){
        return new ResponseEntity<>(departmentService.queryDepartment(pageRequest, JSONObject.parseObject(searchParam)), HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @DeleteMapping("/deleteDeptById")
    public ResponseEntity<String> deleteDeptById(@RequestParam("deptId") Integer deptId){
        departmentService.deleteDepartmentById(deptId);
        return new ResponseEntity<>("删除部门信息成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/updateDeptById")
    public ResponseEntity<String> updateDeptById(@RequestBody DepartmentDTO departmentDTO){
        departmentService.updateDepartmentById(departmentDTO);
        return new ResponseEntity<>("更新部门信息成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/addDept")
    public ResponseEntity<String> addDept(@RequestBody DepartmentDTO departmentDTO){
        departmentService.addDepartment(departmentDTO);
        return new ResponseEntity<>("新增部门信息成功", HttpStatus.OK);
    }
}

package ynu.csistaffsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynu.csistaffsystem.dto.EmployeeDTO;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.vo.EmployeeVO;
import ynu.csistaffsystem.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/queryEmploy")
    public ResponseEntity<PageInfo<EmployeeVO>> queryEmploy(PageRequest pageRequest,
                                                            @RequestBody String searchparam, HttpServletResponse response, HttpServletRequest request){
        return new ResponseEntity<>(employeeService.queryEmploy(JSONObject.parseObject(searchparam),pageRequest),HttpStatus.OK);
    }


    @RequiresRoles("Admin")
    @DeleteMapping("/deleteEmployById")
    public ResponseEntity<String> deleteEmployById(@RequestParam("employId") Integer employId){
        employeeService.deleteEmployById(employId);
        return new ResponseEntity<>("删除职员信息成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/updateEmployById")
    public ResponseEntity<String> updateEmployById(@RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployById(employeeDTO);
        return new ResponseEntity<>("更新职员信息成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/addEmploy")
    public ResponseEntity<String> addEmploy(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmploy(employeeDTO);
        return new ResponseEntity<>("新增职员信息成功", HttpStatus.OK);
    }

}

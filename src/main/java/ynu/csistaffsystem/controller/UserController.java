package ynu.csistaffsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.service.UserInfoService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/queryUser")
    public ResponseEntity<PageInfo<UserInfoDTO>> queryDept(PageRequest pageRequest,
                                                           @RequestBody String searchParam){
        return new ResponseEntity<>(userInfoService.queryUser(pageRequest, JSONObject.parseObject(searchParam)), HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @DeleteMapping("/deleteUserById")
    public ResponseEntity<String> deleteDeptById(@RequestParam("userId") Integer userId){
        userInfoService.deleteUserById(userId);
        return new ResponseEntity<>("删除用户成功", HttpStatus.OK);
    }


    @RequiresRoles("Admin")
    @DeleteMapping("/deleteUserByList")
    public ResponseEntity<String> deleteUserByList(@RequestParam("ids[]") List<Integer> ids){
        userInfoService.deleteUserByIdList(ids);
        return new ResponseEntity<>("批量删除成功",HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/updateUserById")
    public ResponseEntity<String> updateUserById(@RequestBody UserInfoDTO userInfoDTO){
        userInfoService.updateUserById(userInfoDTO);
        return new ResponseEntity<>("更新用户信息成功", HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserInfoDTO userInfoDTO){
        userInfoService.addUser(userInfoDTO);
        return new ResponseEntity<>("新增用户信息成功", HttpStatus.OK);
    }

}

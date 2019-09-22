package ynu.csistaffsystem.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynu.csistaffsystem.dto.PwdDTO;
import ynu.csistaffsystem.dto.UserLoginDTO;
import ynu.csistaffsystem.dto.vo.UserLoginVO;
import ynu.csistaffsystem.service.UserInfoService;

import javax.validation.constraints.NotNull;

@RestController
public class CommonController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        return new ResponseEntity<>(userInfoService.login(userLoginDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @NotNull UserLoginDTO userLoginDTO){
        userInfoService.register(userLoginDTO);
        return new ResponseEntity<>("注册成功",HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResponseEntity<>("注销成功",HttpStatus.OK);
    }

    @PutMapping("/reset")
    public ResponseEntity<String> reset(@RequestBody PwdDTO pwdDTO){
        userInfoService.reset(pwdDTO);
        return new ResponseEntity<>("修改成功",HttpStatus.OK);
    }

    @RequiresRoles("Admin")
    @PutMapping("/adminreset")
    public ResponseEntity<String> adminreset(@RequestBody UserLoginDTO userLoginDTO){
        userInfoService.adminSetPwd(userLoginDTO);
        return new ResponseEntity<>("修改用户密码成功",HttpStatus.OK);
    }

    @RequestMapping("/unauth")
    public ResponseEntity<String> unauth(){
        return new ResponseEntity<>("未授权，请登录",HttpStatus.UNAUTHORIZED);
    }

}

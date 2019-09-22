package ynu.csistaffsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.csistaffsystem.dto.PageRequest;
import ynu.csistaffsystem.dto.PwdDTO;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.dto.UserLoginDTO;
import ynu.csistaffsystem.dto.vo.UserLoginVO;
import ynu.csistaffsystem.exception.MyException;
import ynu.csistaffsystem.mapper.UserInfoMapper;
import ynu.csistaffsystem.service.UserInfoService;
import ynu.csistaffsystem.utils.ShiroMd5Util;

import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        LOGGER.info(userLoginDTO.toString());
        userLoginDTO.setPassword(ShiroMd5Util.encrypt(userLoginDTO));
        LOGGER.info(userLoginDTO.toString());
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDTO.getUserName(), userLoginDTO.getPassword(),userLoginDTO.isRememberMe());
        LOGGER.info("login--->"+usernamePasswordToken.toString());
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            throw new MyException("密码错误,请重新输入");
        }
        return (UserLoginVO)session.getAttribute("user");
    }

    /**
     * 注册字段只有账号密码，先暂时用登录的DTO
     * @param userLoginDTO
     */
    @Override
    public void register(UserLoginDTO userLoginDTO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setLoginName(userLoginDTO.getUserName());
        userInfoDTO.setPassword(ShiroMd5Util.encrypt(userLoginDTO));
        LOGGER.info("register----->"+userInfoDTO.toString());
        try {
            // 使用selective 不插入默认值
            userInfoMapper.insertSelective(userInfoDTO);
        }catch (Exception e){
            throw new MyException("用户名已经被注册");
        }
    }

    @Override
    @Transactional
    public void reset(PwdDTO pwdDTO) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UserLoginDTO userLoginDTO = (UserLoginDTO) session.getAttribute("user");
        if(pwdDTO.getOldPassword().equals(userLoginDTO.getPassword())){
            userLoginDTO.setPassword(pwdDTO.getNewPassword());
            userLoginDTO.setPassword(ShiroMd5Util.encrypt(userLoginDTO));
            try {
                userInfoMapper.updatePwdByName(userLoginDTO.getUserName(),userLoginDTO.getPassword());
            }catch (Exception e){
                throw new MyException("修改异常");
            }
            session.setAttribute("user",userLoginDTO);
        }
        throw new MyException("旧密码错误");
    }

    @Override
    public void adminSetPwd(UserLoginDTO userLoginDTO) {
        userLoginDTO.setPassword(ShiroMd5Util.encrypt(userLoginDTO));
        try {
            userInfoMapper.updatePwdByName(userLoginDTO.getUserName(), userLoginDTO.getPassword());
        }catch (Exception e){
            LOGGER.info(e.toString());
            throw new MyException("用户密码修改异常");
        }

    }


    @Override
    public PageInfo<UserInfoDTO> queryUser(PageRequest pageRequest , Map<String, Object> searchParam) {
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPageSize());
        return new PageInfo<>(userInfoMapper.queryUser(searchParam));
    }

    @Override
    public void deleteUserById(Integer userId) {
        try {
            userInfoMapper.deleteByPrimaryKey(userId);
        }catch (Exception e){
            throw new MyException("删除用户失败");
        }

    }

    @Override
    @Transactional
    public void deleteUserByIdList(List<Integer> userIds) {
        try {
            for (Integer id:userIds){
                userInfoMapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e){
            throw new MyException("批量删除用户失败");
        }
    }

    @Override
    public void addUser(UserInfoDTO userInfoDTO) {
        userInfoDTO.setPassword(ShiroMd5Util.encryptPwd(userInfoDTO));
        try {
            userInfoMapper.insertSelective(userInfoDTO);
        }catch (Exception e){
            throw new MyException("新增用户失败");
        }

    }

    @Override
    public void updateUserById(UserInfoDTO userInfoDTO) {
        try {
            userInfoMapper.updateByPrimaryKeySelective(userInfoDTO);
        }catch (Exception e){
            throw new MyException("更新用户失败");
        }
    }
}

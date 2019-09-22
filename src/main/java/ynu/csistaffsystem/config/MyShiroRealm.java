package ynu.csistaffsystem.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.dto.UserRoleDTO;
import ynu.csistaffsystem.dto.vo.UserLoginVO;
import ynu.csistaffsystem.mapper.UserInfoMapper;
import ynu.csistaffsystem.mapper.UserRoleMapper;
import ynu.csistaffsystem.service.impl.UserInfoServiceImpl;

public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     *shiro 登录验证 , UserInfoService.login()调用subject.login()进入到此方法进行验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String loginName = (String)authenticationToken.getPrincipal();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setLoginName(loginName);
        LOGGER.info(userInfoDTO.toString());
        userInfoDTO = userInfoMapper.selectOne(userInfoDTO);
        if(userInfoDTO == null){
            throw new AuthenticationException("账号名不存在");
        }else{
            // 返回AuthenticationInfo和AuthenticationToken进行比对
            UserLoginVO userLoginVO = new UserLoginVO(userInfoDTO.getId(),userInfoDTO.getLoginName(),userInfoDTO.getUserName(),userInfoDTO.getStatus());
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute("user",userLoginVO);
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfoDTO.getLoginName(), userInfoDTO.getPassword(), getName());
            return simpleAuthenticationInfo;
        }

        //fix: return语句放这里报错
    }


    /**
     * shiro 权限验证
     *
     * 角色：普通用户(User)、管理员(Admin)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setLoginName(loginName);
        userInfoDTO = userInfoMapper.selectOne(userInfoDTO);
        UserRoleDTO userRoleDTO = userRoleMapper.selectByPrimaryKey(userInfoDTO.getStatus());
        simpleAuthorizationInfo.addRole(userRoleDTO.getRole());
        return simpleAuthorizationInfo;
    }


}

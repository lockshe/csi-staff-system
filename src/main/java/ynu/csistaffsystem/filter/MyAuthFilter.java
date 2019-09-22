package ynu.csistaffsystem.filter;


import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.csistaffsystem.dto.UserInfoDTO;
import ynu.csistaffsystem.exception.MyException;
import ynu.csistaffsystem.mapper.UserInfoMapper;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * shiro 自带的filterchain 在带有rememberMe功能时，登录进去的用户没有上下文
 * 取不到用户的状态信息 因此需要重写
 */
public class MyAuthFilter extends FormAuthenticationFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyAuthFilter.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        // 和 Security.getSubject() 有啥区别？最终还不是用这个
        Subject subject = getSubject(request, response);
        LOGGER.info(subject.getPrincipal().toString());
        //处理没有登录但是记住我的情况，这种获取反序列化的username，再登录
        //这个判断第一次登录就可以，后面就不用登录了
        if (!subject.isAuthenticated() && subject.isRemembered()){
            try {
                UserInfoDTO userInfoDTO = new UserInfoDTO();
                String username = subject.getPrincipal().toString();
                userInfoDTO.setLoginName(username);
                userInfoDTO = userInfoMapper.selectOne(userInfoDTO);
                UsernamePasswordToken token = new UsernamePasswordToken(userInfoDTO.getLoginName(),userInfoDTO.getPassword());
                subject.login(token);
            }catch (Exception e){
                throw new MyException(e.getMessage());
            }
        }
        // 已经登陆
        return subject.isAuthenticated();
    }
}

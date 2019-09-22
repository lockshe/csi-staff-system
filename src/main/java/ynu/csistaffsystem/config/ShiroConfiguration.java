package ynu.csistaffsystem.config;
import org.apache.shiro.codec.Base64;

import org.apache.shiro.mgt.SecurityManager;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean
    public MyShiroRealm getmyShiroRealm(){
        return new MyShiroRealm();
    }
    /**
     * cookie对象
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * 设置cookie 管理对象
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 权限管理
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getsecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(getmyShiroRealm());
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager());
        return defaultWebSecurityManager;
        // fix :方法名称和 Shiro 的有一个方法冲突，注入失败，换成getsecurityManager
    }

    /**
     * 配置过滤规则
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 加入自定义的Filter
        // Map<String, Filter> filters = new LinkedHashMap<>();
        // filters.put("myAuthFilter",new MyAuthFilter());
        Map<String, String> filterchainMap = new LinkedHashMap<>();
        filterchainMap.put("/login","anon");
        filterchainMap.put("/register","anon");
        filterchainMap.put("/logout","anon");
        filterchainMap.put("/v2/**","anon");// swagger访问路径
        filterchainMap.put("/swagger-ui.html","anon");// swagger-ui访问路径
        filterchainMap.put("/swagger-resources/**","anon");
        filterchainMap.put("/webjars/**", "anon");
        filterchainMap.put("/**","authc");
        // filterchainMap.put("/**","myAuthFilter");
        // user指的是用户认证通过或者配置了Remember Me记住用户登录状态后可访问
        // shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterchainMap);
        //前后端分离，设置登录路由也相当于鉴权失败
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        return shiroFilterFactoryBean;
    }

    /**
     *开启shiro
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

package com.example.demo.config;

import com.example.demo.sys.realm.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {







    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //游客权限接口接口
        filterChainDefinitionMap.put("/user/test", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");

        filterChainDefinitionMap.put("/goods/getAllTypes", "anon");
        filterChainDefinitionMap.put("/goods/goodlist", "anon");
        filterChainDefinitionMap.put("/goods/selectOneById", "anon");
        filterChainDefinitionMap.put("/comment/selectByGoodsId", "anon");
        filterChainDefinitionMap.put("/comment/save", "anon");

        //用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/goods/**", "user");
        filterChainDefinitionMap.put("/menu/selectlist", "user");
        filterChainDefinitionMap.put("/goods/selectMyGoods", "user");
        filterChainDefinitionMap.put("/goods/delectbyid", "user");

        filterChainDefinitionMap.put("/goods/delectpricture", "user");
        filterChainDefinitionMap.put("/guanzhu/**", "user");
        filterChainDefinitionMap.put("/user/isadmin", "anon");

        filterChainDefinitionMap.put("/goods/addGoodsType", "user");
        //管理员，需要角色权限 “admin”
//        filterChainDefinitionMap.put("/goods/**", "superadmin");



        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;


    }





    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(customRealm());
        return securityManager;
    }


    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public Realm customRealm() {
        return new Realm();
    }






}

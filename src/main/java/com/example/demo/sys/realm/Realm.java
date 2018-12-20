package com.example.demo.sys.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserSerivce;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class Realm extends AuthorizingRealm {
    @Autowired
    UserSerivce userSerivce;
    @Autowired
    RoleService roleService;



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.println(username);


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        User user = userSerivce.selectByUserName(username);
        Set<String> set = new HashSet<>();

        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(roleService.selectbyid(user.getRoleid()).getRole());
        //设置该用户拥有的角色
        info.setRoles(set);





        return info;
    }
}

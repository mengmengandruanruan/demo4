package com.example.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.demo.DTO.Kcrbb;
import com.example.demo.DTO.pages;
import com.example.demo.entity.User;
import com.example.demo.service.UserSerivce;
import com.example.demo.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.attribute.standard.PagesPerMinute;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user/")
public class UserController {
    @Resource
    UserSerivce userSerivce;

    @PostMapping("/register")
    public R register(@RequestBody User user)
    {
    //先判断用户名是否已经注册
        EntityWrapper<User> EW = new EntityWrapper<>();
        User u = new User();
        u.setUsername(user.getUsername());
        EW.setEntity(u);
        int x = userSerivce.selectCount(EW);

        //如果没有
        if (x==0){
            userSerivce.insert(user);
            return R.ok().put("msg","success");
        }
        else {
            return R.ok().put("msg","用户名已经存在");
        }
    }

    @PostMapping("/login")
    public R login(@RequestBody User user)
    {

        User u =new User();

        u.setUsername(user.getUsername());

        EntityWrapper<User> E = new EntityWrapper<>();

        E.setEntity(u);

        u = userSerivce.selectOne(E);


        if (u!=null){
            if(user.getPassword().equals(u.getPassword())){

                Subject sb = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(),u.getPassword());
                token.setRememberMe(true);
                sb.login(token);

                return R.ok().put("msg","success");
            }
            else
                {
                    return R.ok().put("msg","密码错误");
                }
        }
        else return R.ok().put("msg","用户不存在");

    }

    @PostMapping("/outlogin")
    public R outlogin()
    {
        SecurityUtils.getSubject().logout();
        return R.ok().put("mag","success");
    }

    @GetMapping("isadmin")
    public  R isadmin()
    {
        String loginName  =(String)  SecurityUtils.getSubject().getPrincipal();
        if ("admin".equals(loginName))
        {
        return  R.ok();
        }
        else return R.error(1,"bad");
    }

    @ResponseBody
    @RequestMapping("test")
    public R K(Kcrbb kcrbb, HttpServletRequest request )
    {
        System.out.println(kcrbb.toString());
        List<Kcrbb> kcrbbs = new ArrayList<Kcrbb>();
        int count = 99;
        int page =Integer.parseInt(request.getParameter("page"));
        int limit =Integer.parseInt(request.getParameter("limit")) ;

        if (count-page*limit>0){
            for (int i=(page-1)*limit;i<limit*page;i++)
            {
                Kcrbb k = new Kcrbb();
                k.setSpmc(""+i);
                kcrbbs.add(k);
            }
        }
        else
            {
                for (int i=(page-1)*limit;i<count;i++){
                    Kcrbb k = new Kcrbb();
                    k.setSpmc(""+i);
                    kcrbbs.add(k);
                }
            }
        return R.ok().put("code",0).put("msg","").put("count",count).put("data",kcrbbs);
    }

}

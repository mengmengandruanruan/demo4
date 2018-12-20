package com.example.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.RoleDao;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao,Role> implements RoleService {
    @Override
    public Role selectbyid(Long roleid) {
        EntityWrapper EW = new EntityWrapper();
        Role role = new Role();
        role.setRoleid(roleid);
        EW.setEntity(role);
        return selectOne(EW);
    }
}

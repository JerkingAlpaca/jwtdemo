package com.example.jwtdemo.Service;

import com.example.jwtdemo.Entity.Role;
import com.example.jwtdemo.Model.RoleModel;
import com.example.jwtdemo.Repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleModel createRole(RoleModel roleModel) {

        Role role = new Role();
        BeanUtils.copyProperties(roleModel, role);

        Role role1 = roleRepository.save(role);
        BeanUtils.copyProperties(role1, roleModel);

        return roleModel;
    }

    @Override
    public List<RoleModel> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleModel> roleModels = new ArrayList<>();
        RoleModel roleModel = new RoleModel();
        for(Role re : roles){
            BeanUtils.copyProperties(re, roleModel);
            roleModels.add(roleModel);
        }
        return roleModels;
    }

    @Override
    public RoleModel getRolesById(Long roleId) {
        Role role = roleRepository.findById(roleId).get();
        RoleModel roleModel = new RoleModel();
        BeanUtils.copyProperties(role, roleModel);
        return roleModel;
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }


}

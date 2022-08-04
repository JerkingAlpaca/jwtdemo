package com.example.jwtdemo.Service;

import com.example.jwtdemo.Entity.Role;
import com.example.jwtdemo.Model.RoleModel;

import java.util.List;

public interface RoleService {

    public RoleModel createRole(RoleModel roleModel);

    public List<RoleModel> getAllRoles();

    public RoleModel getRolesById(Long roleId);

    public void deleteRoleById(Long roleId);
}

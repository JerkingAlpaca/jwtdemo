package com.example.jwtdemo.Controller;

import com.example.jwtdemo.Entity.Role;
import com.example.jwtdemo.Model.RoleModel;
import com.example.jwtdemo.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createRoles")
    public RoleModel createRole(@RequestBody RoleModel roleModel){
        return roleService.createRole(roleModel);
    }

    @GetMapping("/getAllRoles")
    public List<RoleModel> getAllRoles(){
        return roleService.getAllRoles();
    }

    @DeleteMapping("/deleteRoles")
    public void deleteRoles(@PathVariable Long roleId){
        roleService.deleteRoleById(roleId);
    }
}

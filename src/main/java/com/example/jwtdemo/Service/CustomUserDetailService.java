package com.example.jwtdemo.Service;

import com.example.jwtdemo.Entity.Role;
import com.example.jwtdemo.Entity.User;
import com.example.jwtdemo.Model.RoleModel;
import com.example.jwtdemo.Model.UserModel;
import com.example.jwtdemo.Repository.RoleRepository;
import com.example.jwtdemo.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel register(UserModel userModel){
        User user = new User();
        BeanUtils.copyProperties(userModel, user);

        Set<Role> roles = new HashSet<>();
        for(RoleModel rm :userModel.getRoles()){
            Optional<Role> optionalRole = roleRepository.findById(rm.getId());
            if (optionalRole.isPresent()){
                roles.add(optionalRole.get());
            }
;        }

        user.setRoles(roles);
        user.setPassword(this.bCryptPasswordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        BeanUtils.copyProperties(user, userModel);

        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(Role re : user.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }

        userModel.setRoles(roleModels);
        return userModel;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{

        User user = userRepository.findByUsername(userName);

        if(userName == null){
            throw new UsernameNotFoundException("does not exist");
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);

        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(Role re : user.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            rm.setId(re.getId());
            roleModels.add(rm);
        }

        userModel.setRoles(roleModels);

        return userModel;
    }
}

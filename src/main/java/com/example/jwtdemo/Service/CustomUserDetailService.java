package com.example.jwtdemo.Service;

import com.example.jwtdemo.Entity.User;
import com.example.jwtdemo.Filter.JwtAuthenticationFilter;
import com.example.jwtdemo.Model.UserModel;
import com.example.jwtdemo.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel register(UserModel userModel){
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        BeanUtils.copyProperties(user, userModel);
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

        return userModel;
    }
}

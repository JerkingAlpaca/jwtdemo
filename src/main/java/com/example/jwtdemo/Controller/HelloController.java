package com.example.jwtdemo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PreAuthorize("hasRole('ADMIN')") //Only users with role that contains "ADMIN" can access "/hello"
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')" //Both users with role that contains "ADMIN" or "USER" can access "/hello"
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasAnyAuthority('DELETE_AUTHORITY', 'UPDATE_AUTHORITY')")
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from HelloController!";
    }

}

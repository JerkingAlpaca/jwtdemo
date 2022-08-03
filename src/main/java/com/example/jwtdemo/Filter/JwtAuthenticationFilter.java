package com.example.jwtdemo.Filter;

import com.example.jwtdemo.Service.CustomUserDetailService;
import com.example.jwtdemo.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.Filter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        String username = null;
        String token = null;

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            token = bearerToken.substring(7);

            try{

                //extract username from the token
                username = jwtUtil.extractUsername(token);

                //get user details for this user
                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                //security checks
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                    SecurityContextHolder.getContext().setAuthentication(upat);
                }else{
                    System.out.println("Invalid Token");
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("Invalid Bearer Token Format");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

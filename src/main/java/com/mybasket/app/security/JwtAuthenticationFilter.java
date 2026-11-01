package com.mybasket.app.security;


import com.mybasket.app.entity.User;
import com.mybasket.app.exception.ResourceNotFoundException;
import com.mybasket.app.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;// token validate

    private final UserRepository userRepository;// user check

    private final ModelMapper modelMapper;

     private Logger logger=LoggerFactory.getLogger(JwtAuthenticationFilter.class);



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // header Authorization Bearer token


        String header = request.getHeader("Authorization");

      logger.debug(header);

try{


    if(header!=null && header.startsWith("Bearer")){

        String token= header.substring(7);
        if(!jwtService.isAccessToken(token)){

            System.out.println("Token not accessed");
            filterChain.doFilter(request,response);
            return;
        }

          Jws<Claims> parsedData= jwtService.parse(token);
        Claims payload = parsedData.getPayload();
        String userId = payload.getSubject();
        User user = userRepository.findById(Integer.parseInt(userId)).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(user.isEnable()){
            ROLE role = user.getRole();
            List<GrantedAuthority> authorityList=user.getRole()==null? List.of(): List.of(new SimpleGrantedAuthority(role.toString()));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorityList);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            if(SecurityContextHolder.getContext().getAuthentication()==null){
                logger.debug("setting authentication.........");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


    }


}catch (ExpiredJwtException ex){
    ex.printStackTrace();
    ex.printStackTrace();
}catch (MalformedJwtException ex){
    ex.printStackTrace();
}catch (Exception ex){
    ex.printStackTrace();
}


        // forward request
       filterChain.doFilter(request,response);


    }
}

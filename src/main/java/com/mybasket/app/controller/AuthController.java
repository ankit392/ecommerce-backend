package com.mybasket.app.controller;


import com.mybasket.app.dto.LoginRequest;
import com.mybasket.app.dto.TokenResponse;
import com.mybasket.app.dto.UserDto;
import com.mybasket.app.entity.User;
import com.mybasket.app.repository.UserRepository;
import com.mybasket.app.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {



    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    //api for generating token

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> generateToken(


            @RequestBody LoginRequest loginRequest

    ){
        //email & pwd

try{
//
    UsernamePasswordAuthenticationToken uNaUTHusernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

    Authentication authenticatedObject = authenticationManager.authenticate(uNaUTHusernamePasswordAuthenticationToken);

    //fetch user from db
    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
    User actualUser=user.get();

      if(!user.isPresent()){

          throw new DisabledException("user is disabled , content to admin");
      }

  // access token generate
    String acessToken=jwtService.generateAccessToken(actualUser);
    TokenResponse tokenResponse = new TokenResponse();
      tokenResponse.setUser(modelMapper.map(user, UserDto.class));
      tokenResponse.setAccessToken(acessToken);
      return new ResponseEntity<>(tokenResponse, HttpStatus.OK);


}catch (AuthenticationException ex){
    throw  new BadCredentialsException("invalid user or password");
}


    }






}

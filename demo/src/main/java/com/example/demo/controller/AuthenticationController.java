package com.example.demo.controller;



import com.example.demo.config.JwtUtils;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManage;
    private UserDao userDao;
    private final JwtUtils jwtUtils;


    @PostMapping("/authenticate")
    public ResponseEntity<String> autheticate(
            @RequestBody AuthenticationRequest request
    ){
        authenticationManage.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails userDetails= userDao.findUserByEmail(request.getEmail());
        if(userDetails!=null){
            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        }else {
            return ResponseEntity.status(400).body("some error has occored");
        }
    }

}

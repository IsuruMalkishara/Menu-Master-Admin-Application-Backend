package com.oxcentra.menumasteradminapp.controller;

import com.oxcentra.menumasteradminapp.common.JwtUtility;
import com.oxcentra.menumasteradminapp.dto.JwtRequest;
import com.oxcentra.menumasteradminapp.dto.JwtResponse;
import com.oxcentra.menumasteradminapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@Component
@RestController
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AdminService adminService;


    @Autowired
    private AuthenticationManager authenticationManager;

    public AdminController(JwtUtility jwtUtility, AdminService adminService, AuthenticationManager authenticationManager) {
        this.jwtUtility = jwtUtility;
        this.adminService = adminService;
        this.authenticationManager = authenticationManager;
    }

    @CrossOrigin(origins = { "http://localhost:3000"})
    @PostMapping("/login")
    @ResponseBody
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception {

        log.info(jwtRequest.getUserName());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.info("Error");
            throw new Exception("Invalid credential", e);

        }


        final Boolean result = true;
        final String message = "SUCCESS";
        final String token = jwtUtility.generateToken(jwtRequest.getUserName());
        final Date expiresAt = jwtUtility.extractExpiration(token);
        final int expiresIn = jwtUtility.jwtExpirationInMs;

        log.info(token);

        JwtResponse jwtResponse = new JwtResponse();

        jwtResponse.setId(adminService.getAdminByUserName(jwtRequest.getUserName()).getId());
        jwtResponse.setUserType(adminService.getAdminByUserName(jwtRequest.getUserName()).getUserType());
        jwtResponse.setResult(true);
        jwtResponse.setMessage("Success");
        jwtResponse.setAccess_token(jwtUtility.generateToken(jwtRequest.getUserName()));
        jwtResponse.setExpires_at(jwtUtility.extractExpiration(token));
        jwtResponse.setExpires_in(jwtUtility.jwtExpirationInMs);

        return jwtResponse;
    }


}

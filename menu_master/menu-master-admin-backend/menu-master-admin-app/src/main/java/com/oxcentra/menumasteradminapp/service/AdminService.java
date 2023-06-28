package com.oxcentra.menumasteradminapp.service;

import com.oxcentra.menumasteradminapp.model.Admin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService extends UserDetailsService {
    List<Admin> getAllAdmin();

    @Override
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

    Admin getAdminByUserName(String userName);
}

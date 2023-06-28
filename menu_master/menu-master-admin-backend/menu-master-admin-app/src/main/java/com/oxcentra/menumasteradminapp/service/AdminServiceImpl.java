package com.oxcentra.menumasteradminapp.service;

import com.oxcentra.menumasteradminapp.model.Admin;
import com.oxcentra.menumasteradminapp.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;


    List<Admin> adminList = new ArrayList<>();


    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        adminList = getAllAdmin().stream().filter(a ->
                userName.equals(a.getUserName())).collect(Collectors.toList());

        log.info(String.valueOf(adminList));

        if (adminList.size() > 0) {
            return new User(adminList.get(0).getUserName(), adminList.get(0).getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }


    }

    @Override
    public Admin getAdminByUserName(String userName) {
        List<Admin> admin=getAllAdmin().stream().filter(a->
                userName.equals(a.getUserName())).collect(Collectors.toList());
        log.info(String.valueOf(admin));
        return admin.get(0);
    }
}
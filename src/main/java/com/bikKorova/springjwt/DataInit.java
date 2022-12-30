package com.bikKorova.springjwt;

import com.bikKorova.springjwt.models.ERole;
import com.bikKorova.springjwt.models.Role;
import com.bikKorova.springjwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    public void startInit(){
        roleRepository.save(new Role(ERole.ROLE_USER));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startInit();
    }
}

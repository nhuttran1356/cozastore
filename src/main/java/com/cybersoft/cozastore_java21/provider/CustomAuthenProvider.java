package com.cybersoft.cozastore_java21.provider;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy // Khai bao de chay sau cung
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Lay username ma user truyen vao
        String username = authentication.getName();
        //Lay duoc password ma user truyen vao
        String password = authentication.getCredentials().toString();

        UserEntity user = userRepository.findByEmail(username);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            // Dang nhap thanh cong
            return new UsernamePasswordAuthenticationToken(username, user.getPassword(), new ArrayList<>());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Khai bao loai chung thuc su dung de so sanh
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

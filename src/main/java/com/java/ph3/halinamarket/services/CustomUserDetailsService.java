package com.java.ph3.halinamarket.services;

import com.java.ph3.halinamarket.adapter.CustomUserDetails;
import com.java.ph3.halinamarket.models.User;
import com.java.ph3.halinamarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user == null) {
            throw new UsernameNotFoundException("Username Not Found");
        }
        return new CustomUserDetails(user);
    }
}

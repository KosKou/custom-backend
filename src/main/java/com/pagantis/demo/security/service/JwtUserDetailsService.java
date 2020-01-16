package com.pagantis.demo.security.service;

import com.pagantis.demo.entity.Role;
import com.pagantis.demo.entity.User;
import com.pagantis.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Call userRepository to get credentials
        User user = userRepository.findByEmail(email).get();
        //Validates if User exist
        if (user == null){
            throw new UsernameNotFoundException(String.format("No user found with email '%s'. ", email));
        }
        //User Exist So...

        //Simple collection of roles without ID
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        //UserDetails entity needs a simple list of roles, without the ID
        for (Role role: user.getRoles()){
            logger.info("Role: ".concat(role.getRole()));
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        if (authorities.isEmpty()){
            logger.error("Error: User '" + email + "' does not have assigned roles!");
            throw new UsernameNotFoundException("Error: User '" + email + "' does not have assigned roles!");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getEnabled(), true, true, true, authorities);
    }

}

package com.example.HostelManagementSystem_Backend.service.impl;


import com.example.HostelManagementSystem_Backend.entity.impl.UserEntity;
import com.example.HostelManagementSystem_Backend.repository.UserRepository;
import com.example.HostelManagementSystem_Backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsImpl(user);
    }
}
package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.AuthResponseDto;
import com.example.HostelManagementSystem_Backend.dto.impl.OwnerCreateDto;
import com.example.HostelManagementSystem_Backend.dto.impl.UserSignUpDto;
import com.example.HostelManagementSystem_Backend.entity.impl.OwnerEntity;
import com.example.HostelManagementSystem_Backend.entity.impl.UserEntity;
import com.example.HostelManagementSystem_Backend.enums.Role;
import com.example.HostelManagementSystem_Backend.repository.OwnerRepository;
import com.example.HostelManagementSystem_Backend.repository.UserRepository;
import com.example.HostelManagementSystem_Backend.security.JwtUtil;
import com.example.HostelManagementSystem_Backend.service.AuthService;
import com.example.HostelManagementSystem_Backend.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponseDto registerOwner(OwnerCreateDto ownerCreateDto) {

        if (userRepository.existsByUsername(ownerCreateDto.getUser().getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }
        if (ownerRepository.existsByEmail(ownerCreateDto.getEmail())) {
            throw new RuntimeException("Email is already registered!");
        }
        if (ownerRepository.existsByNic(ownerCreateDto.getNic())) {
            throw new RuntimeException("NIC is already registered!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(ownerCreateDto.getUser().getUsername());
        user.setPassword(passwordEncoder.encode(ownerCreateDto.getUser().getPassword()));
        user.setRole(Role.OWNER);

        UserEntity savedUser = userRepository.save(user);

        OwnerEntity owner = new OwnerEntity();
        owner.setOwnerId(idGenerator.generateOwnerId());
        owner.setOwnerName(ownerCreateDto.getOwnerName());
        owner.setNic(ownerCreateDto.getNic());
        owner.setTel(ownerCreateDto.getTel());
        owner.setAddress(ownerCreateDto.getAddress());
        owner.setWorkplace(ownerCreateDto.getWorkplace());
        owner.setJobTitle(ownerCreateDto.getJobTitle());
        owner.setEmail(ownerCreateDto.getEmail());
        owner.setUser(savedUser);

        OwnerEntity savedOwner = ownerRepository.save(owner);

        String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole().name());

        return AuthResponseDto.builder()
                .token(token)
                .id(savedOwner.getOwnerId())
                .username(savedUser.getUsername())
                .email(savedOwner.getEmail())
                .role(savedUser.getRole().name())
                .message("Owner registered successfully!")
                .build();
    }

    @Override
    public AuthResponseDto login(UserSignUpDto userSignUpDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userSignUpDto.getUsername(),
                        userSignUpDto.getPassword()
                )
        );

        UserEntity user = userRepository.findByUsername(userSignUpDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userSignUpDto.getUsername()));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return AuthResponseDto.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .message("Login successful!")
                .build();
    }
}
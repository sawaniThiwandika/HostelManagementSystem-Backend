package com.example.HostelManagementSystem_Backend.service.impl;

import com.example.HostelManagementSystem_Backend.dto.impl.*;
import com.example.HostelManagementSystem_Backend.entity.impl.*;
import com.example.HostelManagementSystem_Backend.enums.Role;
import com.example.HostelManagementSystem_Backend.repository.OwnerRepository;
import com.example.HostelManagementSystem_Backend.repository.RefreshTokenRepository;
import com.example.HostelManagementSystem_Backend.repository.StaffRepository;
import com.example.HostelManagementSystem_Backend.repository.UserRepository;
import com.example.HostelManagementSystem_Backend.security.JwtUtil;
import com.example.HostelManagementSystem_Backend.service.AuthService;
import com.example.HostelManagementSystem_Backend.service.EmailService;
import com.example.HostelManagementSystem_Backend.util.IdGenerator;
import com.example.HostelManagementSystem_Backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final EmailService emailService;
    private final Mapping mapping;

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

        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());
        String accessToken = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole().name());
        String refreshTokenStr = jwtUtil.generateRefreshToken(userDetails);

        saveOrUpdateRefreshToken(savedUser, refreshTokenStr);

        return AuthResponseDto.builder()
                .token(accessToken)
                .refreshToken(refreshTokenStr)
                .id(savedOwner.getOwnerId())
                .username(savedUser.getUsername())
                .email(savedOwner.getEmail())
                .role(savedUser.getRole().name())
                .message("Owner registered successfully!")
                .build();
    }

    @Override
    @Transactional
    public AuthResponseDto login(UserSignUpDto userSignUpDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userSignUpDto.getUsername(),
                        userSignUpDto.getPassword()
                )
        );

        UserEntity user = userRepository.findByUsername(userSignUpDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userSignUpDto.getUsername()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        String refreshTokenStr = jwtUtil.generateRefreshToken(userDetails);

        saveOrUpdateRefreshToken(user, refreshTokenStr);

        return AuthResponseDto.builder()
                .token(accessToken)
                .refreshToken(refreshTokenStr)
                .username(user.getUsername())
                .role(user.getRole().name())
                .message("Login successful!")
                .build();
    }

    @Override
    public TokenRefreshResponseDto refreshToken(TokenRefreshRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();

        if (jwtUtil.validateToken(requestRefreshToken)) {
            String username = jwtUtil.extractUsername(requestRefreshToken);

            UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            refreshTokenRepository.findByToken(requestRefreshToken)
                    .orElseThrow(() -> new RuntimeException("Refresh token not found in database"));

            String newAccessToken = jwtUtil.generateToken(username, user.getRole().name());
            return new TokenRefreshResponseDto(newAccessToken, requestRefreshToken);
        }

        throw new RuntimeException("Refresh token is expired or invalid");
    }

    private void saveOrUpdateRefreshToken(UserEntity user, String tokenStr) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByUser(user)
                .orElse(new RefreshTokenEntity());
        refreshToken.setUser(user);
        refreshToken.setToken(tokenStr);
        refreshTokenRepository.save(refreshToken);
    }
    @Override
    @Transactional
    public String registerStaff(StaffCreateDto staffCreateDto) {
        if (staffRepository.existsByNic(staffCreateDto.getNic())) {
            throw new RuntimeException("NIC is already registered!");
        }

        String generatedStaffId = idGenerator.generateStaffId();
        String generatedUsername = staffCreateDto.getName().toLowerCase().replaceAll("\\s+", "") + "_" + generatedStaffId.toLowerCase();
        String rawPassword = java.util.UUID.randomUUID().toString().substring(0, 8);

        if (userRepository.existsByUsername(generatedUsername)) {
            generatedUsername = generatedUsername + "_" + (int)(Math.random() * 900 + 100);
        }

        // 1. Save User Credentials
        UserEntity user = new UserEntity();
        user.setUsername(generatedUsername);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.STAFF);
        user.setStaffCategory(staffCreateDto.getCategory());
        userRepository.save(user);

        // 2. Save Staff Details
        StaffEntity staffEntity = mapping.toStaffEntity(staffCreateDto);
        staffEntity.setStaffId(generatedStaffId);
        staffEntity.setEmail(staffCreateDto.getEmail());
        staffRepository.save(staffEntity);

        // 3. Send Credentials via Email only
        emailService.sendStaffCredentials(
                staffCreateDto.getEmail(),
                staffCreateDto.getName(),
                generatedUsername,
                rawPassword
        );

        return "Staff member registered successfully. Login credentials have been emailed to " + staffCreateDto.getEmail();
    }
}
package com.example.HostelManagementSystem_Backend.util;


import com.example.HostelManagementSystem_Backend.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdGenerator {

    private final OwnerRepository ownerRepository;

    public String generateOwnerId() {
        return ownerRepository.findLastOwnerId()
                .map(lastId -> {
                    int numericPart = Integer.parseInt(lastId.substring(3));
                    return String.format("OWN%07d", numericPart + 1);
                })
                .orElse("OWN0000001");
    }
}

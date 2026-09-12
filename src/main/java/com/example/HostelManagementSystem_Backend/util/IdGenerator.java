package com.example.HostelManagementSystem_Backend.util;


import com.example.HostelManagementSystem_Backend.repository.HostelRepository;
import com.example.HostelManagementSystem_Backend.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdGenerator {

    private final OwnerRepository ownerRepository;
    private final HostelRepository hostelRepository;

    public String generateOwnerId() {
        return ownerRepository.findLastOwnerId()
                .map(lastId -> {
                    int numericPart = Integer.parseInt(lastId.substring(3));
                    return String.format("OWN%07d", numericPart + 1);
                })
                .orElse("OWN0000001");
    }

    public String generateHostelId() {
        return hostelRepository.findLastHostelId()
                .map(lastId -> {
                    int numericPart = Integer.parseInt(lastId.substring(3));
                    return String.format("HOS%07d", numericPart + 1);
                })
                .orElse("HOS0000001");
    }
}

package com.example.HostelManagementSystem_Backend.dto.impl;

import com.example.HostelManagementSystem_Backend.dto.SuperDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateDto implements SuperDto {
    @NotNull(message = "Room number is required")
    private Integer num;

    @NotNull(message = "Floor number is required")
    private Integer floor;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Room size is required")
    private Integer size;

    private String description;

    @NotNull(message = "Fee is required")
    @Min(value = 0, message = "Fee must be a valid amount")
    private Double fee;

    @NotBlank(message = "Hostel ID is required")
    private String hostelId;
}

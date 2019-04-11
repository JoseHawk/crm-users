package com.joselara.crmusers.models.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CustomerDTO {

    private UUID customerId;
    private String name;
    private String surname;
    private String picturePath;
    private String createdBy;
    private LocalDateTime insertTime;
    private String modifiedBy;
    private LocalDateTime modificationTime;
}

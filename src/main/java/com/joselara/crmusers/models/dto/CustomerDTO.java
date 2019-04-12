package com.joselara.crmusers.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {

    private UUID customerId;
    private String name;
    private String surname;
    private String picturePath;
    private String createdBy;
    private LocalDateTime insertTime;
    private String modifiedBy;
    private LocalDateTime modificationTime;

    @Tolerate
    public CustomerDTO() { }
}

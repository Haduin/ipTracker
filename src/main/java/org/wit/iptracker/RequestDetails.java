package org.wit.iptracker;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestDetails {
    @Id
    private Long id;
    private String ip;
    private String country;
    private String city;
    private LocalDateTime requestTime;
}

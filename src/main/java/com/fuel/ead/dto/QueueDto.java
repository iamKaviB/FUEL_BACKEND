package com.fuel.ead.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueueDto {

    private String id;
    private String tank;
    private String customer;
    private String status;
}

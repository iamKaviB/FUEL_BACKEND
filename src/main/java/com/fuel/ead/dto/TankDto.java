package com.fuel.ead.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TankDto {

    private String tankId;
    private String fuelType;
    private boolean status;
    private String shed;

}

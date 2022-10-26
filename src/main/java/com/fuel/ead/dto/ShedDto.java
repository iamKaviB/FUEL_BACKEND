package com.fuel.ead.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShedDto {

    private String shedId;
    private String name;
    private String location;
    private String shedMaster;
}

package com.fuel.ead.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueueCount {

    private String id;
    private int count;
    private String time;
    private String status;
}

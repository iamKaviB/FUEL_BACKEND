package com.fuel.ead.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Document(collection = "tank")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tank {

    @Id
    @Indexed(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tankId;

    private String fuelType;
    private boolean status;

    @ManyToOne
    private Shed shed;

}

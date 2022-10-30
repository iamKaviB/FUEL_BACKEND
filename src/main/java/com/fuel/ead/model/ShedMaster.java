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

@Document(collection = "shedMaster")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShedMaster {

    @Id
    @Indexed(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @Indexed(unique = true)
    private String nic;

    @Indexed(unique = true)
    private String email;

    private String password;

}

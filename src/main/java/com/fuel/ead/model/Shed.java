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

@Document(collection = "shed")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shed {

    @Id
    @Indexed(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String shedId;

    private String name;
    private String location;

    @ManyToOne
    private ShedMaster shedMaster;
}

package com.fuel.ead.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "queue")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Queue {

    @Id
    @Indexed(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    private Tank tank;

    private String time;
    private String customer;
    private String status;
}

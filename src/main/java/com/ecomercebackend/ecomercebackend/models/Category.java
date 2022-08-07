package com.ecomercebackend.ecomercebackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "id")
    private UUID id;

    @NotBlank
    @Size(max = 120)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;


    //This constructor is created for Unit Test purpose
    public Category(String name) {
        this.name = name;
    }
}


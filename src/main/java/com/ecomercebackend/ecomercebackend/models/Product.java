package com.ecomercebackend.ecomercebackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "id")
    private UUID id;

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    @Size(max = 120)
    private String price;

    @NotBlank
    @Size(max = 120)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String unit_in_stock;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

}
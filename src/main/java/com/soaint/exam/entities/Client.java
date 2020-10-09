package com.soaint.exam.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "idClient", updatable = false, nullable = false)
    private Long idclient;

    @Column(length = 30)
    private String firstname;

    @Column(length = 30)
    private String lastname;

    @Column(length = 8)
    private String dni;

    @Column(length = 20)
    private int telefono;

    @Column(length = 30)
    private String email;

    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
            )
    private List<Sale> saleList;

}

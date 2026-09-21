package com.upc.backendintellijgrupo4.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "niveles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Nivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer orden;
}

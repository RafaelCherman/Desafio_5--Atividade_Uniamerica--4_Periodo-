package com.example.lembretesfinal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoas", schema = "public")
public class Pessoa {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_pessoas_id_seq")
    @SequenceGenerator(name = "tb_pessoas_id_seq", sequenceName = "tb_pessoas_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;

}

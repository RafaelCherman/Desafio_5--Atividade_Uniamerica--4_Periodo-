package com.example.lembretesfinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoas", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
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

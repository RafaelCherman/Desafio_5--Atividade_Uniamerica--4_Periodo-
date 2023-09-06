package com.example.lembretesfinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_lembretes", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Lembrete {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_lembretes_id_seq")
    @SequenceGenerator(name = "tb_lembretes_id_seq", sequenceName = "tb_lembretes_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "recado", nullable = false)
    private String recado;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

}

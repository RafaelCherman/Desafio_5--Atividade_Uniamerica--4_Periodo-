package com.example.lembretesfinal.dto;

import lombok.Getter;
import lombok.Setter;

public class LembreteDto {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String recado;

    @Getter @Setter
    private PessoaDto pessoaDto;

}

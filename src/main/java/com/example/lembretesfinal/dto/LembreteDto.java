package com.example.lembretesfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class LembreteDto {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String recado;

    @Getter @Setter
    private PessoaDto pessoaDto;

}

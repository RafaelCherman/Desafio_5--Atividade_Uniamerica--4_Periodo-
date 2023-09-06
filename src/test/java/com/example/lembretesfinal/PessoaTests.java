package com.example.lembretesfinal;

import com.example.lembretesfinal.controller.PessoaController;
import com.example.lembretesfinal.dto.PessoaDto;
import com.example.lembretesfinal.entity.Pessoa;
import com.example.lembretesfinal.repository.PessoaRepository;
import com.example.lembretesfinal.service.PessoaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PessoaTests {

    @MockBean
    PessoaRepository pessoaRepository;

    @Autowired
    private PessoaController pessoaController;

    @BeforeEach
    void injectData(){
        Optional<Pessoa> pessoa = Optional.of(new Pessoa (1L,"Carlos"));
        Pessoa pessoa1 = new Pessoa(2L, "João");
        Pessoa pessoa2 = new Pessoa(3L, "Maria");
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(pessoa1);
        pessoas.add(pessoa2);
        Long id = 1L;
        Mockito.when(pessoaRepository.findById(id)).thenReturn(pessoa);
        Mockito.when(pessoaRepository.doesExist(id)).thenReturn(true);
        Mockito.when(pessoaRepository.isInLembrete(id)).thenReturn(false);
        Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);
    }

    @Test
    void testControllerPessoaFindById()
    {
        var pessoa = pessoaController.findById(1L);
        Long id = pessoa.getBody().getId().longValue();
        System.out.println(id);
        Assert.assertEquals(1L, id, 0);
    }

    @Test
    void testControllerPessoaFindAll()
    {
        var pessoa = pessoaController.findAll();
        Assert.assertEquals(2, pessoa.getBody().size(), 0);
    }


    @Test
    void testControllerPessoaPost()
    {
        PessoaDto pessoaDto = new PessoaDto(1L, "Carlos");
        var resposta = pessoaController.cadastrar(pessoaDto);
        Assert.assertEquals("Pessoa cadastrada com sucesso", resposta.getBody());
    }

    @Test
    void testControllerPessoaPut()
    {
        PessoaDto pessoaDto = new PessoaDto(1L, "Carlos");
        var resposta = pessoaController.editar(1L, pessoaDto);
        Assert.assertEquals("Pessoa alterada com sucesso", resposta.getBody());
    }

    @Test
    void testControllerPessoaDelete()
    {
        var resposta = pessoaController.deletar(1L);
        Assert.assertEquals("Pessoa deletada com sucesso", resposta.getBody());
    }

}

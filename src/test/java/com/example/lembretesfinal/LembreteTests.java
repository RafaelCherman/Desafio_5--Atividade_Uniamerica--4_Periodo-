package com.example.lembretesfinal;

import com.example.lembretesfinal.controller.LembreteController;
import com.example.lembretesfinal.controller.PessoaController;
import com.example.lembretesfinal.dto.LembreteDto;
import com.example.lembretesfinal.dto.PessoaDto;
import com.example.lembretesfinal.entity.Lembrete;
import com.example.lembretesfinal.entity.Pessoa;
import com.example.lembretesfinal.repository.LembreteRepository;
import com.example.lembretesfinal.repository.PessoaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LembreteTests {

    @MockBean
    LembreteRepository lembreteRepository;

    @Autowired
    private LembreteController lembreteController;

    @BeforeEach
    void injectData(){
        Pessoa pessoa = new Pessoa(1L, "João");
        Lembrete lembrete1 = new Lembrete(2L, "Lavar moto", pessoa);
        Lembrete lembrete2 = new Lembrete(2L, "Lavar calçada", pessoa);
        List<Lembrete> lembretes = new ArrayList<>();
        lembretes.add(lembrete1);
        lembretes.add(lembrete2);
        Optional<Lembrete> lembrete = Optional.of(new Lembrete(1L, "Lavar carro", pessoa));
        Long id = 1L;
        String nome = "João";
        Mockito.when(lembreteRepository.findById(id)).thenReturn(lembrete);
        Mockito.when(lembreteRepository.doesExist(id)).thenReturn(true);
        Mockito.when(lembreteRepository.getPessoaById(id)).thenReturn(pessoa);
        Mockito.when(lembreteRepository.findAll()).thenReturn(lembretes);
        Mockito.when(lembreteRepository.getByNome("João")).thenReturn(lembretes);
    }

    @Test
    void testControllerPessoaFindByNome()
    {
        var lembretes = lembreteController.procuraNome("João");
        for(LembreteDto i : lembretes.getBody())
        {
            Assert.assertEquals("João", i.getPessoaDto().getNome());
        }

        Assert.assertEquals(2, lembretes.getBody().size(), 0);
    }

    @Test
    void testControllerLembreteFindAll()
    {
        var lembretes = lembreteController.findAll();
        Assert.assertEquals(2, lembretes.getBody().size(), 0);
    }


    @Test
    void testControllerLembretePost()
    {
        PessoaDto pessoaDto = new PessoaDto(1L, "Carlos");
        LembreteDto lembreteDto = new LembreteDto(1L, "Lavar carro", pessoaDto);
        var resposta = lembreteController.cadastrar(lembreteDto);
        Assert.assertEquals("Lembrete cadastrado com sucesso", resposta.getBody());
    }

    @Test
    void testControllerPessoaPut()
    {
        PessoaDto pessoaDto = new PessoaDto(1L, "Carlos");
        LembreteDto lembreteDto = new LembreteDto(1L, "Lavar carro", pessoaDto);
        var resposta = lembreteController.editar(1L, lembreteDto);
        Assert.assertEquals("Lembrete alterado com sucesso", resposta.getBody());
    }

    @Test
    void testControllerPessoaDelete()
    {
        var resposta = lembreteController.deletar(1L);
        Assert.assertEquals("Lembrete deletado com sucesso", resposta.getBody());
    }

}

package com.example.lembretesfinal.service;

import com.example.lembretesfinal.dto.LembreteDto;
import com.example.lembretesfinal.dto.PessoaDto;
import com.example.lembretesfinal.entity.Lembrete;
import com.example.lembretesfinal.entity.Pessoa;
import com.example.lembretesfinal.repository.LembreteRepository;
import com.example.lembretesfinal.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;

    public List<LembreteDto> findAll(){
        List<Lembrete> lembretes = this.lembreteRepository.findAll();
        List<LembreteDto> lembretesDto = new ArrayList<>();

        for(Lembrete i : lembretes)
        {
            lembretesDto.add(convertToDTO(i));
        }

        return lembretesDto;
    }

    public List<LembreteDto> achaNome(String nome){
        List<Lembrete> lembretes = this.lembreteRepository.getByNome(nome);
        List<LembreteDto> lembretesDto = new ArrayList<>();

        for(Lembrete i : lembretes)
        {
            lembretesDto.add(convertToDTO(i));
        }

        return lembretesDto.size() < 1
                ? null
                : lembretesDto;

    }

    public void cadastrar(LembreteDto lembreteDto)
    {
        Assert.notNull(lembreteDto.getRecado(), "Recado não pode ser nulo");
        Assert.notNull(lembreteDto.getPessoaDto(), "Pessoa não pode ser nula");
        Lembrete lembrete = convertToEntity(lembreteDto);
        this.lembreteRepository.save(lembrete);
    }

    public void editar(LembreteDto lembreteDto, Long id)
    {
        Assert.isTrue(this.lembreteRepository.doesExist(id), "Lembrete não existe");
        Assert.notNull(lembreteDto.getRecado(), "Recado não pode ser nulo");
        Assert.notNull(lembreteDto.getPessoaDto(), "Pessoa não pode ser nula");
        Lembrete lembrete = convertToEntity(lembreteDto);
        this.lembreteRepository.save(lembrete);
    }

    public void deletar(Long id)
    {
        Lembrete lembrete = this.lembreteRepository.findById(id).orElse(null);
        Assert.notNull(lembrete, "Lembrete não existe");

        this.lembreteRepository.delete(lembrete);
    }


    private LembreteDto convertToDTO(Lembrete lembrete) {
        LembreteDto lembreteDto = new LembreteDto();
        lembreteDto.setId(lembrete.getId());
        lembreteDto.setRecado(lembrete.getRecado());
        lembreteDto.setPessoaDto(convertToDTOPessoa(lembrete.getPessoa()));
        return lembreteDto;
    }

    private Lembrete convertToEntity(LembreteDto lembreteDto) {
        Lembrete lembrete = new Lembrete();
        lembrete.setRecado(lembreteDto.getRecado());
        lembrete.setPessoa(this.lembreteRepository.getPessoaById(lembreteDto.getId()));
        return lembrete;
    }

    private PessoaDto convertToDTOPessoa(Pessoa pessoa) {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        return pessoaDto;
    }


}

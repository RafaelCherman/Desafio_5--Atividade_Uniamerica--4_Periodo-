package com.example.lembretesfinal.service;

import com.example.lembretesfinal.dto.PessoaDto;
import com.example.lembretesfinal.entity.Pessoa;
import com.example.lembretesfinal.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaDto> findAll(){
        List<Pessoa> pessoas = this.pessoaRepository.findAll();
        List<PessoaDto> pessoasDto = new ArrayList<>();

        for(Pessoa i : pessoas)
        {
            pessoasDto.add(convertToDTO(i));
        }

        return pessoasDto;
    }

    public PessoaDto findById(Long id){
        Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);

        return pessoa == null
                ? null
                : convertToDTO(pessoa);
    }

    public void cadastrar(PessoaDto pessoaDto)
    {
        Assert.notNull(pessoaDto.getNome(), "Nome não pode ser nulo");
        Pessoa pessoa = convertToEntity(pessoaDto);
        this.pessoaRepository.save(pessoa);
    }

    public void editar(PessoaDto pessoaDto, Long id)
    {
        Assert.isTrue(this.pessoaRepository.doesExist(id), "Pessoa não existe");
        Assert.isTrue(pessoaDto.getId().equals(id), "Não foi possivel identificar o registro");
        Assert.notNull(pessoaDto.getNome(), "Nome não pode ser nulo");
        Pessoa pessoa = convertToEntity(pessoaDto);
        this.pessoaRepository.save(pessoa);
    }

    public boolean deletar(Long id)
    {
        Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
        Assert.notNull(pessoa, "Pessoa não existe");

        if(this.pessoaRepository.isInLembrete(id))
        {
            return true;
        }
        else
        {
            this.pessoaRepository.delete(pessoa);
            return false;
        }
    }


    private PessoaDto convertToDTO(Pessoa pessoa) {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        return pessoaDto;
    }

    private Pessoa convertToEntity(PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDto.getNome());
        return pessoa;
    }
}

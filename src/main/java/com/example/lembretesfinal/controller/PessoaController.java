package com.example.lembretesfinal.controller;

import com.example.lembretesfinal.dto.PessoaDto;
import com.example.lembretesfinal.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("todos")
    public ResponseEntity<List<PessoaDto>> findAll()
    {
        try{
            return ResponseEntity.ok(this.pessoaService.findAll());
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<PessoaDto> findById(@RequestParam("id") final Long id){

        try{
            return ResponseEntity.ok(this.pessoaService.findById(id));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody final PessoaDto pessoaDto){

        try{
            this.pessoaService.cadastrar(pessoaDto);

            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> editar(
            @RequestParam("id") final Long id,
            @RequestBody final PessoaDto pessoaDto)
    {
        try {
            this.pessoaService.editar(pessoaDto, id);

            return ResponseEntity.ok("Pessoa alterada com sucesso");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id)
    {
        try {
            if(this.pessoaService.deletar(id)){
                return ResponseEntity.ok("Não foi capaz de deletar essa pessoa pois tem lembretes vinculados\n Tente deletar esses lembretes antes");
            }
            else{
                return ResponseEntity.ok("Pessoa deletada com sucesso");
            }
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}

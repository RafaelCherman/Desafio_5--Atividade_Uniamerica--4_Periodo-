package com.example.lembretesfinal.controller;

import com.example.lembretesfinal.dto.LembreteDto;
import com.example.lembretesfinal.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "api/lembrete")
public class LembreteController {

    @Autowired
    private LembreteService lembreteService;

    @GetMapping("todos")
    public ResponseEntity<List<LembreteDto>> findAll()
    {
        try {
            return ResponseEntity.ok(this.lembreteService.findAll());
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<LembreteDto>> procuraNome(@RequestParam("nome") final String nome){

        try {
            return ResponseEntity.ok(this.lembreteService.achaNome(nome));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody final LembreteDto lembreteDto){

        try {
            this.lembreteService.cadastrar(lembreteDto);

            return ResponseEntity.ok("Lembrete cadastrado com sucesso");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping ResponseEntity<String> editar(
            @RequestParam("id") final Long id,
            @RequestBody final LembreteDto lembreteDto)
    {
        try {
            this.lembreteService.editar(lembreteDto, id);

            return ResponseEntity.ok("Lembrete alterado com sucesso");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping ResponseEntity<String> deletar(@RequestParam("id") final Long id)
    {
        try {
            this.lembreteService.deletar(id);
            return ResponseEntity.ok("Lembrete deletado com sucesso");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}

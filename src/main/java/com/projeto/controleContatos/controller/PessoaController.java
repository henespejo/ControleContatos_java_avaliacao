package com.projeto.controleContatos.controller;

import com.projeto.controleContatos.dto.request.PessoaRequestDTO;
import com.projeto.controleContatos.dto.response.PessoaMalaDiretaResponseDTO;
import com.projeto.controleContatos.dto.response.PessoaResponseDTO;
import com.projeto.controleContatos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    @Operation(summary = "Busca a lista de Pessoas.")
    public ResponseEntity<List<PessoaResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscaTodos());
    }


    @GetMapping(path = "/{id}")
    @Operation(summary = "Busca de Pessoa por ID.")
    public ResponseEntity<PessoaResponseDTO> buscaPorId(@PathVariable Long id) {
        PessoaResponseDTO pessoaDTO = pessoaService.buscaPorId(id);
        if (pessoaDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @GetMapping(path = "/maladireta/{id}")
    @Operation(summary = "Busca de Pessoa por ID para Mala Direta.")
    public ResponseEntity<PessoaMalaDiretaResponseDTO> buscaPorIdParaMalaDireta(@PathVariable Long id) {
        PessoaMalaDiretaResponseDTO dto = pessoaService.buscaPorIdParaMalaDireta(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @Operation(summary = "Cadastro de Pessoa.")
    public ResponseEntity<PessoaResponseDTO> salvar(@RequestBody PessoaRequestDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pessoaService.salvar(pessoaDTO, null));
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Atualiza Pessoa por ID.")
    public ResponseEntity<PessoaResponseDTO> atualizar(@PathVariable Long id, @RequestBody PessoaRequestDTO pessoaDTO) {
        if (pessoaService.buscaPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaService.salvar(pessoaDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Exclui Pessoa por ID.")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (pessoaService.buscaPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

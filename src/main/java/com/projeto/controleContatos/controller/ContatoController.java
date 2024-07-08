package com.projeto.controleContatos.controller;

import com.projeto.controleContatos.dto.request.ContatoRequestDTO;
import com.projeto.controleContatos.dto.response.ContatoResponseDTO;
import com.projeto.controleContatos.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    @Operation(summary = "Busca a lista de Contatos.")
    public ResponseEntity<List<ContatoResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.buscaTodos());
    }


    @GetMapping(path = "/{id}")
    @Operation(summary = "Busca Contato por ID.")
    public ResponseEntity<ContatoResponseDTO> buscaPorId(@PathVariable Long id) {
        ContatoResponseDTO contatoDTO = contatoService.buscaPorId(id);
        if (contatoDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contatoDTO);
    }

    @GetMapping(path = "/pessoa/{idPessoa}")
    @Operation(summary = "Busca lista de Contatos por ID de Pessoa.")
    public ResponseEntity<List<ContatoResponseDTO>> buscaPorPessoa(@PathVariable Long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.buscaTodosPorPessoa(idPessoa));
    }

    @PostMapping
    @Operation(summary = "Cadastro de Contato.")
    public ResponseEntity<ContatoResponseDTO> salvar(@RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.salvar(dto, null));
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Atualiza Contato por ID.")
    public ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ContatoRequestDTO dto) {
        if (contatoService.buscaPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.salvar(dto, id));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Exclui Contato por ID.")
    public ResponseEntity<ContatoResponseDTO> remover(@PathVariable Long id) {
        if (contatoService.buscaPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contatoService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

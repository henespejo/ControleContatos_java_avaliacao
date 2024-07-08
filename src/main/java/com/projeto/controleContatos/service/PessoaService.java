package com.projeto.controleContatos.service;

import com.projeto.controleContatos.dto.request.PessoaRequestDTO;
import com.projeto.controleContatos.dto.response.PessoaMalaDiretaResponseDTO;
import com.projeto.controleContatos.dto.response.PessoaResponseDTO;
import com.projeto.controleContatos.model.Pessoa;
import com.projeto.controleContatos.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<PessoaResponseDTO> buscaTodos() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaResponseDTO> pessoaDTOS = new ArrayList<>();
        pessoas.forEach(obj -> {
            pessoaDTOS.add(convertDBParaPessoaDTO(obj));
        });
        return pessoaDTOS;
    }

    public PessoaResponseDTO buscaPorId(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        return optional.map(this::convertDBParaPessoaDTO).orElse(null);
    }

    public PessoaMalaDiretaResponseDTO buscaPorIdParaMalaDireta(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        return optional.map(this::converteDBParaPessoaMalaDiretaDTO).orElse(null);
    }

    public PessoaResponseDTO salvar(PessoaRequestDTO pessoaDTO, Long id) {
        Pessoa pessoa = convertDTOParaDB(pessoaDTO);
        pessoa.setId(id);
        pessoaRepository.save(pessoa);
        return convertDBParaPessoaDTO(pessoa);
    }

    public void remover(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa buscaPessoaPorId(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        return optional.orElse(null);
    }

    private PessoaResponseDTO convertDBParaPessoaDTO(Pessoa pessoa) {
        PessoaResponseDTO pessoaDTO = new PessoaResponseDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setEndereco(pessoa.getEndereco());
        pessoaDTO.setCep(pessoa.getCep());
        pessoaDTO.setCidade(pessoa.getCidade());
        pessoaDTO.setUf(pessoa.getUf());
        return pessoaDTO;
    }

    private Pessoa convertDTOParaDB(PessoaRequestDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setEndereco(pessoaDTO.getEndereco());
        pessoa.setCep(pessoaDTO.getCep());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setUf(pessoaDTO.getUf());
        return  pessoa;
    }

    private PessoaMalaDiretaResponseDTO converteDBParaPessoaMalaDiretaDTO(Pessoa pessoa) {
        PessoaMalaDiretaResponseDTO dto = new PessoaMalaDiretaResponseDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setMalaDireta(pessoa.obtemMalaDireta());
        return dto;
    }
}

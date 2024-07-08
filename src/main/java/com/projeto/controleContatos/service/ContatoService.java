package com.projeto.controleContatos.service;

import com.projeto.controleContatos.dto.request.ContatoRequestDTO;
import com.projeto.controleContatos.dto.response.ContatoResponseDTO;
import com.projeto.controleContatos.model.Contato;
import com.projeto.controleContatos.model.enums.TipoContato;
import com.projeto.controleContatos.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;

    public ContatoService(ContatoRepository contatoRepository, PessoaService pessoaService) {
        this.contatoRepository = contatoRepository;
        this.pessoaService = pessoaService;
    }

    public List<ContatoResponseDTO> buscaTodos() {
        List<Contato> contatos = contatoRepository.findAll();
        List<ContatoResponseDTO> contatoDTOS = new ArrayList<>();
        contatos.forEach(o -> {
            contatoDTOS.add(converteDBParaDTO(o));
        });
        return contatoDTOS;
    }

    public List<ContatoResponseDTO> buscaTodosPorPessoa(Long idPessoa) {
        List<Contato> contatos = contatoRepository.buscaContatoPorPessoa(idPessoa);
        List<ContatoResponseDTO> contatoDTOS = new ArrayList<>();
        contatos.forEach(o -> {
            contatoDTOS.add(converteDBParaDTO(o));
        });
        return contatoDTOS;
    }

    public ContatoResponseDTO buscaPorId(Long id) {
        Optional<Contato> optional = contatoRepository.findById(id);
        return optional.map(this::converteDBParaDTO).orElse(null);
    }

    public ContatoResponseDTO salvar(ContatoRequestDTO dto, Long id) {
        Contato contato = converteDTOParaDB(dto);
        contato.setId(id);
        contatoRepository.save(contato);
        return converteDBParaDTO(contato);
    }

    public void remover(Long id) {
        contatoRepository.deleteById(id);
    }

    private Contato converteDTOParaDB(ContatoRequestDTO dto) {
        Contato contato = new Contato();
        contato.setNumeroContato(dto.getNumeroContato());
        contato.setTipoContato(TipoContato.getTipo(dto.getTipoContato()));
        contato.setPessoa(pessoaService.buscaPessoaPorId(dto.getIdPessoa()));
        return contato;
    }

    private ContatoResponseDTO converteDBParaDTO(Contato contato) {
        ContatoResponseDTO contatoDTO = new ContatoResponseDTO();
        contatoDTO.setId(contato.getId());
        contatoDTO.setNumeroContato(contato.getNumeroContato());
        contatoDTO.setTipoContato(contato.getTipoContato().getValor());
        contatoDTO.setIdPessoa(contato.getPessoa() != null ? contato.getPessoa().getId() : null);
        return contatoDTO;
    }
}

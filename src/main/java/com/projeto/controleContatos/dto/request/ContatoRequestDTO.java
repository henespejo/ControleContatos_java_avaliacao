package com.projeto.controleContatos.dto.request;

import jakarta.validation.constraints.NotNull;

public class ContatoRequestDTO {

    @NotNull(message = "Tipo de Contato não pode ser vazio.")
    private Integer tipoContato;

    @NotNull(message = "Número de Contato não pode ser vazio.")
    private String numeroContato;

    @NotNull(message = "Id Pessoa não pode ser vazio.")
    private Long idPessoa;

    public Integer getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(Integer tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getNumeroContato() {
        return numeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        this.numeroContato = numeroContato;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }
}

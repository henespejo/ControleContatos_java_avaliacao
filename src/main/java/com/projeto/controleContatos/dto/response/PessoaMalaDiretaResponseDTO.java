package com.projeto.controleContatos.dto.response;

public class PessoaMalaDiretaResponseDTO {

    private Long id;
    private String nome;
    private String malaDireta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMalaDireta() {
        return malaDireta;
    }

    public void setMalaDireta(String malaDireta) {
        this.malaDireta = malaDireta;
    }
}

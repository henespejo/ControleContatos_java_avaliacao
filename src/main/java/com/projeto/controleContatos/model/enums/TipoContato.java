package com.projeto.controleContatos.model.enums;

public enum TipoContato {

    TELEFONE(0), CELULAR(1);

    private int valor;

    TipoContato(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public static TipoContato getTipo(int valor) {
        for (TipoContato tipoContato : TipoContato.values()) {
            if (tipoContato.getValor() == valor) {
                return tipoContato;
            }
        }
        return null;
    }
}

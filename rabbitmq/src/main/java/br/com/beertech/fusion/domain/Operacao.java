package br.com.beertech.fusion.domain;

import java.io.Serializable;
import java.util.Objects;


public class Operacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoOperacao;
    private Double valorOperacao;
    private String identificadorContaCorrente;

    public Operacao() { }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Double getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(Double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public String getIdentificadorContaCorrente() {
        return identificadorContaCorrente;
    }

    public void setIdentificadorContaCorrente(String identificadorContaCorrente) {
        this.identificadorContaCorrente = identificadorContaCorrente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoOperacao, valorOperacao);
    }

    @Override
    public String toString() {
        return "Operacao [tipoOperacao=" + tipoOperacao + ", valorOperacao=" + valorOperacao
                + ", identificadorContaCorrente=" + identificadorContaCorrente + "]";
    }

}

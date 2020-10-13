package br.com.beertech.fusion.controller.dto;

public class TransferenciaDto {

    private String identificadorContaOrigem;
    private String identificadorContaDestino;
    private Double valorTransferido;

    public String getIdentificadorContaOrigem() {
        return identificadorContaOrigem;
    }

    public void setIdentificadorContaOrigem(String identificadorContaOrigem) {
        this.identificadorContaOrigem = identificadorContaOrigem;
    }

    public String getIdentificadorContaDestino() {
        return identificadorContaDestino;
    }

    public void setIdentificadorContaDestino(String identificadorContaDestino) {
        this.identificadorContaDestino = identificadorContaDestino;
    }

    public Double getValorTransferido() {
        return valorTransferido;
    }

    public void setValorTransferido(Double valorTransferido) {
        this.valorTransferido = valorTransferido;
    }

}

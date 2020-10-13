package br.com.beertech.fusion.domain;

import java.util.Objects;

public class Transferencia {

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

    @Override
    public int hashCode() {
        return Objects.hash(identificadorContaOrigem, identificadorContaDestino, valorTransferido);
    }

    @Override
    public String toString() {
        return "Transferencia [identificadorContaOrigem=" + identificadorContaOrigem + ", identificadorContaDestino="
                + identificadorContaDestino + ", valorTransferido=" + valorTransferido + "]";
    }

}

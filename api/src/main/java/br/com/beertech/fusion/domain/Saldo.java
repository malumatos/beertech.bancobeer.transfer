package br.com.beertech.fusion.domain;

import java.io.Serializable;
import java.util.Objects;

public class Saldo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Double saldo;

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Saldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(saldo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Saldo))
            return false;

        Saldo other = (Saldo) obj;
        return (getSaldo() == null && other.getSaldo() == null)
                || (getSaldo() != null && getSaldo().equals(other.getSaldo()));
    }
}

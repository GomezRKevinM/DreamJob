package com.talento_tech.BolsaEmpleo.Entities;

import java.util.Arrays;

public class SQLQuery {
    private String sentencia;
    private Object resultado;

    public String getQuery() {
        return sentencia;
    }

    public Object getResultado() {
        return resultado;
    }

    public void setSentencia(String sentencia) {
        this.sentencia = sentencia;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }
}

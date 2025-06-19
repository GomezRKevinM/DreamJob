package com.talento_tech.BolsaEmpleo.Entities;

import java.sql.Timestamp;

public class SystemInfo {
    private Timestamp fecha;
    private String tipo_info;
    private String accion;
    
    public SystemInfo(String tipo,String info) {
        this.fecha = new Timestamp(System.currentTimeMillis());
        this.tipo_info = tipo;
        this.accion = info;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public String getTipoInfo() {
        return tipo_info;
    }

    public String getAccion() {
        return accion;
    }

    @Override
    public String toString() {
        return fecha+" - " + tipo_info + ": " + accion+"\n";
    }


}

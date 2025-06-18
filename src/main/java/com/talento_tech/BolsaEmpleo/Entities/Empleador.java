package com.talento_tech.BolsaEmpleo.Entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Empleador {
    private long id;
    private long empresa_id;
    private long usuario_id;

    public Empleador(long id, long empresa_id, long usuario_id) {
        this.id = id;
        this.empresa_id = empresa_id;
        this.usuario_id = usuario_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(long empresa_id) {
        this.empresa_id = empresa_id;
    }

    public long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(long usuario_id) {
        this.usuario_id = usuario_id;
    }
}

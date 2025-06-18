package com.talento_tech.BolsaEmpleo.Entities;

public class ReferenciaPersonal {
    private long id;
    private String nombre;
    private String telefono;
    private String parentezco;
    private long empleado_id;

    public ReferenciaPersonal(long id, String nombre, String telefono, String parentezco, long empleado_id) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.parentezco = parentezco;
        this.empleado_id = empleado_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    public long getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(long empleado_id) {
        this.empleado_id = empleado_id;
    }
}

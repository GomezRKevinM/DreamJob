package com.talento_tech.BolsaEmpleo.Entities;

public class UserSesion {
    private Usuario user;
    private Empleado rolEmpleado;
    private Empresa rolEmpresa;
    private Empleador rolEmpleador;

    public UserSesion(Usuario user) {
        this.user = user;
        if(user.getRol().equals("empleado")) this.rolEmpleado = new Empleado();
        if(user.getRol().equals("empresa")) this.rolEmpresa = new Empresa();
        if(user.getRol().equals("empleador")) this.rolEmpleador = new Empleador();
    }

    public UserSesion() {
        user = null;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Empleado getRolEmpleado() {
        return rolEmpleado;
    }

    public void setRolEmpleado(Empleado rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public Empresa getRolEmpresa() {
        return rolEmpresa;
    }

    public void setRolEmpresa(Empresa rolEmpresa) {
        this.rolEmpresa = rolEmpresa;
    }

    public Empleador getRolEmpleador() {
        return rolEmpleador;
    }

    public void setRolEmpleador(Empleador rolEmpleador) {
        this.rolEmpleador = rolEmpleador;
    }

}

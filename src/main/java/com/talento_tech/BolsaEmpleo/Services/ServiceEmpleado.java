package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.Empleado;

public class ServiceEmpleado {
    Empleado empleado;

    public ServiceEmpleado() {
        empleado = new Empleado(1, "cv.pdf");
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Object getEmpleadosByEmpresaId(long empresaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmpleadosByEmpresaId'");
    }

    public void setEmpleados(Object empleadosByEmpresaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmpleados'");
    }

}

package com.talento_tech.BolsaEmpleo.Entities;

public class FiltroOferta {
    private String busqueda;
    private String ciudad;
    private String pais;
    private String departamento;
    private String nivelEstudios;
    private String experienciaLaboral;
    private String idiomas;
    private String modalidad;
    private String tipoContrato;

    public FiltroOferta(String busqueda, String pais, String ciudad, String departamento, String nivelEstudios, String experienciaLaboral, String idiomas, String modalidad, String tipoContrato){
        this.busqueda = busqueda;
        this.pais = pais;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.nivelEstudios = nivelEstudios;
        this.experienciaLaboral = experienciaLaboral;
        this.idiomas = idiomas;
        this.modalidad = modalidad;
        this.tipoContrato = tipoContrato;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    
}

package com.talento_tech.BolsaEmpleo.Entities;


import java.util.ArrayList;

public class Empleado {
    private long empleado_id;
    private long usuario_id;
    private String cv;

    private ArrayList<OfertaEmpleo> ofertasAplicadas;
    private ArrayList<OfertaEmpleo> ofertasFavoritas;
    private ArrayList<String> habilidades;
    private ArrayList<String> idiomas;
    private ArrayList<ExperienciaLaboral> experienciaLaboral;
    private ArrayList<Educacion> educacion;
    private ArrayList<ReferenciaPersonal> referenciasPersonales;

    public Empleado(long usuario_id,String cv) {
        this.usuario_id = usuario_id;
        this.cv = cv;
        ofertasAplicadas = new ArrayList<>();
        ofertasFavoritas = new ArrayList<>();
        habilidades = new ArrayList<>();
        idiomas = new ArrayList<>();
        experienciaLaboral = new ArrayList<>();
        educacion = new ArrayList<>();
        referenciasPersonales = new ArrayList<>();
    }

    public Empleado() {
        super();
        ofertasAplicadas = new ArrayList<>();
        ofertasFavoritas = new ArrayList<>();
        habilidades = new ArrayList<>();
    }

    public ArrayList<OfertaEmpleo> getOfertasAplicadas() {
        return ofertasAplicadas;
    }

    public void setOfertasAplicadas(ArrayList<OfertaEmpleo> ofertasAplicadas) {
        this.ofertasAplicadas = ofertasAplicadas;
    }

    public ArrayList<OfertaEmpleo> getOfertasFavoritas() {
        return ofertasFavoritas;
    }

    public void setOfertasFavoritas(ArrayList<OfertaEmpleo> ofertasFavoritas) {
        this.ofertasFavoritas = ofertasFavoritas;
    }

    public ArrayList<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<String> habilidades) {
        this.habilidades = habilidades;
    }

    public void agregarOfertaAplicada(OfertaEmpleo oferta) {
        if (!ofertasAplicadas.contains(oferta)) {
            ofertasAplicadas.add(oferta);
        }
    }

    public void agregarOfertaFavorita(OfertaEmpleo oferta) {
        if (!ofertasFavoritas.contains(oferta)) {
            ofertasFavoritas.add(oferta);
        }
    }

    public void agregarHabilidad(String habilidad) {
        if (!habilidades.contains(habilidad)) {
            habilidades.add(habilidad);
        }
    }
    public void eliminarHabilidad(String habilidad) {
        habilidades.remove(habilidad);
    }

    public void eliminarOfertaAplicada(OfertaEmpleo oferta) {
        ofertasAplicadas.remove(oferta);
    }

    public void eliminarOfertaFavorita(OfertaEmpleo oferta) {
        ofertasFavoritas.remove(oferta);
    }

    public long getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(long empleado_id) {
        this.empleado_id = empleado_id;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(ArrayList<String> idiomas) {
        this.idiomas = idiomas;
    }

    public ArrayList<ExperienciaLaboral> getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(ArrayList<ExperienciaLaboral> experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public ArrayList<Educacion> getEducacion() {
        return educacion;
    }

    public void setEducacion(ArrayList<Educacion> educacion) {
        this.educacion = educacion;
    }

    public ArrayList<ReferenciaPersonal> getReferenciasPersonales() {
        return referenciasPersonales;
    }

    public void setReferenciasPersonales(ArrayList<ReferenciaPersonal> referenciasPersonales) {
        this.referenciasPersonales = referenciasPersonales;
    }
}

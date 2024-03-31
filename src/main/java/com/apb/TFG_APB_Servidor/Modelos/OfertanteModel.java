package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

/**
 * Clase que contiene todos los datos de la base de datos
 */
@Entity
@Table(name = "ofertante")
public class OfertanteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ofertante")
    private int id_ofertante;

    @Column(name = "nombre_ofertante")
    private String nombreOfertante;

    @Column(name = "primer_apellido_ofertante")
    private String primerApellidoOfertante;

    @Column(name = "segundo_apellido_ofertante")
    private String segundoApellidoOfertante;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "email_ofertante")
    private String email_ofertante;

    @Column(name = "is_administrador")
    private boolean is_administrador;

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail_ofertante() {
        return email_ofertante;
    }

    public void setEmail_ofertante(String email_ofertante) {
        this.email_ofertante = email_ofertante;
    }

    public int getId_ofertante() {
        return id_ofertante;
    }

    public void setId_ofertante(int id_ofertante) {
        this.id_ofertante = id_ofertante;
    }

    public String getNombreOfertante() {
        return nombreOfertante;
    }

    public void setNombreOfertante(String nombreOfertante) {
        this.nombreOfertante = nombreOfertante;
    }

    public String getPrimerApellidoOfertante() {
        return primerApellidoOfertante;
    }

    public void setPrimerApellidoOfertante(String primerApellidoOfertante) {
        this.primerApellidoOfertante = primerApellidoOfertante;
    }

    public String getSegundoApellidoOfertante() {
        return segundoApellidoOfertante;
    }

    public void setSegundoApellidoOfertante(String segundoApellidoOfertante) {
        this.segundoApellidoOfertante = segundoApellidoOfertante;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public boolean isIs_administrador() {
        return is_administrador;
    }

    public void setIs_administrador(boolean is_administrador) {
        this.is_administrador = is_administrador;
    }
}

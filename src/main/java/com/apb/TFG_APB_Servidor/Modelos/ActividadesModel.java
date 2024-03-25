package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "actividades")
public class ActividadesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_actividad")
    private int id_actividad;

    @Column(name = "tipo_actividad")
    private String tipoActividad;

    @Column(name = "descripcion_actividad")
    private String descripcionActividad;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "fecha")
    private Date fecha;

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

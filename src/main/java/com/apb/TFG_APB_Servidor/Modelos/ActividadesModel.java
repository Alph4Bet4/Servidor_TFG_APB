package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

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

    @Column(name = "hora_inicio")
    private Time hora_inicio;

    @Column(name = "hora_fin")
    private Time hora_fin;

    @Column(name = "cantidad_max_personas")
    private int cantidad_max_personas;

    @Column(name = "cantidad_actual_personas")
    private int cantidad_actual_personas;

    @ManyToOne
    @JoinColumn(name = "id_creador_ofertante")
    private OfertanteModel Creador_ofertante;

    public OfertanteModel getCreador_ofertante() {
        return Creador_ofertante;
    }

    public void setCreador_ofertante(OfertanteModel creador_ofertante) {
        Creador_ofertante = creador_ofertante;
    }

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

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getCantidad_max_personas() {
        return cantidad_max_personas;
    }

    public void setCantidad_max_personas(int cantidad_max_personas) {
        this.cantidad_max_personas = cantidad_max_personas;
    }

    public int getCantidad_actual_personas() {
        return cantidad_actual_personas;
    }

    public void setCantidad_actual_personas(int cantidad_actual_personas) {
        this.cantidad_actual_personas = cantidad_actual_personas;
    }
}

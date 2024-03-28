package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "recursos")
public class RecursosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Recurso")
    private int id_recurso;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    private int id_actividad;

    @Column(name = "Nombre_recurso")
    private String nombre_recurso;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Cantidad")
    private int cantidad;

    @Column(name = "isOfertadaPorOfertante")
    private boolean is_ofertada_por_ofertante;

    //TODO hacer repositorio, servicio y controlador

    public int getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(int id_recurso) {
        this.id_recurso = id_recurso;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getNombre_recurso() {
        return nombre_recurso;
    }

    public void setNombre_recurso(String nombre_recurso) {
        this.nombre_recurso = nombre_recurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isIs_ofertada_por_ofertante() {
        return is_ofertada_por_ofertante;
    }

    public void setIs_ofertada_por_ofertante(boolean is_ofertada_por_ofertante) {
        this.is_ofertada_por_ofertante = is_ofertada_por_ofertante;
    }
}

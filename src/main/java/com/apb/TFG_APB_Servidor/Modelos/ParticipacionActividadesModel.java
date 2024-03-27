package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "participacion_actividades")
public class ParticipacionActividadesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participacion_actividades")
    private int id_participacion_actividades;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    private ActividadesModel actividad;

    @ManyToOne
    @JoinColumn(name = "id_consumidor")
    private ConsumidorModel consumidor;

    public int getId_participacion_actividades() {
        return id_participacion_actividades;
    }

    public void setId_participacion_actividades(int id_participacion_actividades) {
        this.id_participacion_actividades = id_participacion_actividades;
    }

    public ActividadesModel getActividad() {
        return actividad;
    }

    public void setActividad(ActividadesModel actividad) {
        this.actividad = actividad;
    }

    public ConsumidorModel getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(ConsumidorModel consumidor) {
        this.consumidor = consumidor;
    }
}

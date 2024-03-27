package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "sugerencia_actividades")
public class SugerenciaActividadesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sugerencia")
    private int id_sugerencia;

    @ManyToOne
    @JoinColumn(name = "id_consumidor")
    private ConsumidorModel consumidor;

    @OneToOne
    @JoinColumn(name = "id_actividad_sugerida")
    private ActividadesModel actividad;

    public int getId_sugerencia() {
        return id_sugerencia;
    }

    public void setId_sugerencia(int id_sugerencia) {
        this.id_sugerencia = id_sugerencia;
    }

    public ConsumidorModel getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(ConsumidorModel consumidor) {
        this.consumidor = consumidor;
    }

    public ActividadesModel getActividad() {
        return actividad;
    }

    public void setActividad(ActividadesModel actividad) {
        this.actividad = actividad;
    }
}

package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "consumidor")
public class ConsumidorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumidor")
    private int id_consumidor;

    @Column(name = "nombre_consumidor")
    private String nombreConsumidor;

    @Column(name = "primer_apellido_consumidor")
    private String primerApellidoConsumidor;

    @Column(name = "segundo_apellido_consumidor")
    private String segundoApellidoConsumidor;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "email_consumidor")
    private String email_consumidor;

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail_consumidor() {
        return email_consumidor;
    }

    public void setEmail_consumidor(String email_consumidor) {
        this.email_consumidor = email_consumidor;
    }

    public int getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(int id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public String getNombreConsumidor() {
        return nombreConsumidor;
    }

    public void setNombreConsumidor(String nombreConsumidor) {
        this.nombreConsumidor = nombreConsumidor;
    }

    public String getPrimerApellidoConsumidor() {
        return primerApellidoConsumidor;
    }

    public void setPrimerApellidoConsumidor(String primerApellidoConsumidor) {
        this.primerApellidoConsumidor = primerApellidoConsumidor;
    }

    public String getSegundoApellidoConsumidor() {
        return segundoApellidoConsumidor;
    }

    public void setSegundoApellidoConsumidor(String segundoApellidoConsumidor) {
        this.segundoApellidoConsumidor = segundoApellidoConsumidor;
    }
}

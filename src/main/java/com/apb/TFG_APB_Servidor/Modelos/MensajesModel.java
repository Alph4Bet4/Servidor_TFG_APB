package com.apb.TFG_APB_Servidor.Modelos;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "mensajechat")
public class MensajesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private int id_mensaje;

    @Column(name = "id_consumidor")
    //TODO Clave foranea
    private String id_consumidor;

    @Column(name = "id_ofertante")
    //TODO Clave foranea
    private String id_ofertante;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha")
    private Date fecha;


}

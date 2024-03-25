package com.apb.TFG_APB_Servidor.Repositorios;

import com.apb.TFG_APB_Servidor.Modelos.OfertanteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase que permite hacer consultas a la tabla de ofertante
 */
@Repository
public interface IOfertanteRepositorio extends JpaRepository<OfertanteModel, Integer> {
}

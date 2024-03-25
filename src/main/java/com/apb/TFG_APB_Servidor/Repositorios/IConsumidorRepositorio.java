package com.apb.TFG_APB_Servidor.Repositorios;

import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase que accede a la tabla de consumidor
 */
@Repository
public interface IConsumidorRepositorio extends JpaRepository<ConsumidorModel, Integer> {
}

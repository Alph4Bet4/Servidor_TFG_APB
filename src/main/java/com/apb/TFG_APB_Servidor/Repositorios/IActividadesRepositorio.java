package com.apb.TFG_APB_Servidor.Repositorios;

import com.apb.TFG_APB_Servidor.Modelos.ActividadesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase que hace consultas a la base de datos a la tabla de Actividades
 */
@Repository
public interface IActividadesRepositorio extends JpaRepository<ActividadesModel, Integer> {
}

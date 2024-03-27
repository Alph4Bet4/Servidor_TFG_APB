package com.apb.TFG_APB_Servidor.Repositorios;

import com.apb.TFG_APB_Servidor.Modelos.ParticipacionActividadesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParticipacionActividadesRepositorio extends JpaRepository<ParticipacionActividadesModel, Integer> {
}

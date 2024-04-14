package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import com.apb.TFG_APB_Servidor.Modelos.SugerenciaActividadesModel;
import com.apb.TFG_APB_Servidor.Repositorios.ISugerenciaActividadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SugerenciaActividadesServicio {

    @Autowired
    ISugerenciaActividadesRepositorio sugerenciaActividadesRepositorio;

    public ArrayList<SugerenciaActividadesModel> getSugerencias() {

        ArrayList<SugerenciaActividadesModel> sugerenciaActividadesModel = (ArrayList<SugerenciaActividadesModel>) sugerenciaActividadesRepositorio.findAll();

        for (SugerenciaActividadesModel sugerencia : sugerenciaActividadesModel) {
            //Capturamos al consumidor que queremos ocultarle la contrasenia
            ConsumidorModel consumidorOcultarContrasenia = sugerencia.getConsumidor();
            if (consumidorOcultarContrasenia != null) {
                //Le ocultamos la contrasenia
                consumidorOcultarContrasenia.setContrasenia("vacio");
                sugerencia.setConsumidor(consumidorOcultarContrasenia);
            }

        }

        return sugerenciaActividadesModel;
    }

    public Optional<SugerenciaActividadesModel> getSugerenciaPorId(int id) {
        Optional<SugerenciaActividadesModel> sugerencia = sugerenciaActividadesRepositorio.findById(id);

        //Ocultamos las contrasenias
        sugerencia.get().getActividad().getCreador_ofertante().setContrasenia("vacio");
        sugerencia.get().getConsumidor().setContrasenia("vacio");

        return sugerencia;
    }

    public SugerenciaActividadesModel guardarSugerencia(SugerenciaActividadesModel sugerencia) {
        return sugerenciaActividadesRepositorio.save(sugerencia);
    }

    public SugerenciaActividadesModel actualizarSugerenciaPorId(SugerenciaActividadesModel sugerenciaActualizar, int id) {
        SugerenciaActividadesModel sugerenciaNueva = sugerenciaActividadesRepositorio.findById(id).get();

        sugerenciaNueva.setConsumidor(sugerenciaActualizar.getConsumidor());
        sugerenciaNueva.setActividad(sugerenciaActualizar.getActividad());

        sugerenciaActividadesRepositorio.save(sugerenciaNueva);

        return sugerenciaNueva;
    }

    public boolean borrarSugerencia(int id) {
        try {
            sugerenciaActividadesRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Modelos.ActividadesModel;
import com.apb.TFG_APB_Servidor.Repositorios.IActividadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ActividadesServicio {

    @Autowired
    IActividadesRepositorio actividadesRepositorio;

    public ArrayList<ActividadesModel> getActividades() {
        return (ArrayList<ActividadesModel>) actividadesRepositorio.findAll();
    }

    public ActividadesModel guardarActividad(ActividadesModel actividad) {
        return actividadesRepositorio.save(actividad);
    }

    public Optional<ActividadesModel> getActividadPorId(int id) {
        return actividadesRepositorio.findById(id);
    }

    public ActividadesModel actualizarActividadPorId(ActividadesModel actividadNueva, int id) {
        ActividadesModel actividadAActualizar = actividadesRepositorio.findById(id).get();

        actividadAActualizar.setTipoActividad(actividadNueva.getTipoActividad());
        actividadAActualizar.setDescripcionActividad(actividadNueva.getDescripcionActividad());
        actividadAActualizar.setDireccion(actividadNueva.getDireccion());
        actividadAActualizar.setFecha(actividadNueva.getFecha());

        return actividadAActualizar;
    }

    public Boolean borrarActividadPorId(int id) {
        try {
            actividadesRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Controladores.ConsumidorController;
import com.apb.TFG_APB_Servidor.Controladores.OfertanteController;
import com.apb.TFG_APB_Servidor.Modelos.ActividadesModel;
import com.apb.TFG_APB_Servidor.Modelos.OfertanteModel;
import com.apb.TFG_APB_Servidor.Repositorios.IActividadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ActividadesServicio {

    @Autowired
    IActividadesRepositorio actividadesRepositorio;

    /**
     * Un get de actividades sencillo que captura las actividades sin que se muestren las constrasenias
     *
     * @return
     */
    public ArrayList<ActividadesModel> getActividades() {
        ArrayList<ActividadesModel> listaActividades = (ArrayList<ActividadesModel>) actividadesRepositorio.findAll();

        for (ActividadesModel actividad : listaActividades) {
            //Capturamos al ofertante
            OfertanteModel ofertanteOcultarContrasenia = actividad.getCreador_ofertante();
            //Tenemos en cuenta de que puede ser null para diferentes actividades
            if (ofertanteOcultarContrasenia != null) {
                //Ocultamos la contrasenia
                ofertanteOcultarContrasenia.setContrasenia("vacio");

                //Lo agregamos a la actividad para que no se vea la contrasenia
                actividad.setCreador_ofertante(ofertanteOcultarContrasenia);
            }
        }
        return listaActividades;
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
        actividadAActualizar.setCreador_ofertante(actividadNueva.getCreador_ofertante());

        return actividadNueva;
    }

    public boolean borrarActividadPorId(int id) {
        try {
            actividadesRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

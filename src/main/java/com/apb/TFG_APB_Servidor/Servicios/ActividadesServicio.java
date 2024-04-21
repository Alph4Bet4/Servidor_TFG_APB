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

    public synchronized ActividadesModel guardarActividad(ActividadesModel actividad) {
        return actividadesRepositorio.save(actividad);
    }

    public Optional<ActividadesModel> getActividadPorId(int id) {
        Optional<ActividadesModel> actividades = actividadesRepositorio.findById(id);

        if (actividades.get().getCreador_ofertante() != null) {
            actividades.get().getCreador_ofertante().setContrasenia("vacio");
        }


        return actividades;
    }

    public synchronized ActividadesModel actualizarActividadPorId(ActividadesModel actividadNueva, int id) {
        ActividadesModel actividadAActualizar = actividadesRepositorio.findById(id).get();

        actividadAActualizar.setTipoActividad(actividadNueva.getTipoActividad());
        actividadAActualizar.setDescripcionActividad(actividadNueva.getDescripcionActividad());
        actividadAActualizar.setDireccion(actividadNueva.getDireccion());
        actividadAActualizar.setFecha(actividadNueva.getFecha());
        actividadAActualizar.setHora_inicio(actividadNueva.getHora_inicio());
        actividadAActualizar.setHora_fin(actividadNueva.getHora_fin());
        actividadAActualizar.setCantidad_max_personas(actividadNueva.getCantidad_max_personas());
        actividadAActualizar.setCantidad_actual_personas(actividadNueva.getCantidad_actual_personas());
        actividadAActualizar.setEstadoActividad(actividadNueva.getEstadoActividad());
        actividadAActualizar.setCreador_ofertante(actividadNueva.getCreador_ofertante());

        actividadesRepositorio.save(actividadAActualizar);

        return actividadAActualizar;
    }

    public synchronized boolean borrarActividadPorId(int id) {
        try {
            actividadesRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

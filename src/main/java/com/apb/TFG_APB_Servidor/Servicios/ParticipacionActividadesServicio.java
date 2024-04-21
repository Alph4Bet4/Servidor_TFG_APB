package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Modelos.ActividadesModel;
import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import com.apb.TFG_APB_Servidor.Modelos.OfertanteModel;
import com.apb.TFG_APB_Servidor.Modelos.ParticipacionActividadesModel;
import com.apb.TFG_APB_Servidor.Repositorios.IParticipacionActividadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ParticipacionActividadesServicio {

    @Autowired
    IParticipacionActividadesRepositorio participacionActividadesRepositorio;

    public ArrayList<ParticipacionActividadesModel> getParticipaciones() {
        ArrayList<ParticipacionActividadesModel> listaParticipacion = (ArrayList<ParticipacionActividadesModel>) participacionActividadesRepositorio.findAll();

        for (ParticipacionActividadesModel participacion : listaParticipacion) {
            //Capturamos al consumidor
            ConsumidorModel consumidorOcultarContrasenia = participacion.getConsumidor();

            if (consumidorOcultarContrasenia != null) {
                //Cambiamos la contrasenia a vacia y la guardamos
                consumidorOcultarContrasenia.setContrasenia("vacio");
                participacion.setConsumidor(consumidorOcultarContrasenia);
            }
            //Capturamos al ofertante de la actividad
            OfertanteModel ofertanteOcultarContrasenia = participacion.getActividad().getCreador_ofertante();

            if (ofertanteOcultarContrasenia != null) {
                //Ocultamos la contrasenia del ofertante
                ofertanteOcultarContrasenia.setContrasenia("vacio");

                //Modificamos la actividad con la contrasenia oculta
                ActividadesModel actividadOcultarContraseniaOfertante = participacion.getActividad();
                actividadOcultarContraseniaOfertante.setCreador_ofertante(ofertanteOcultarContrasenia);

                //Guardamos la actividad
                participacion.setActividad(actividadOcultarContraseniaOfertante);
            }

        }

        return listaParticipacion;
    }

    public Optional<ParticipacionActividadesModel> getParticipacionPorId(int id) {
        Optional<ParticipacionActividadesModel> participacion = participacionActividadesRepositorio.findById(id);

        //Ocultamos las contrasenias
        participacion.get().getActividad().getCreador_ofertante().setContrasenia("vacio");
        participacion.get().getConsumidor().setContrasenia("vacio");


        return participacion;
    }

    public ArrayList<ParticipacionActividadesModel> getParticipacionPorIdActividad(ActividadesModel actividad) {
        ArrayList<ParticipacionActividadesModel> listaParticipacionesCompleta = (ArrayList<ParticipacionActividadesModel>) participacionActividadesRepositorio.findAll();
        ArrayList<ParticipacionActividadesModel> listaParticipacionesADevolver = new ArrayList<>();

        //Recorremos la lista para capturar las participaciones segun la actividad
        for (ParticipacionActividadesModel participacion : listaParticipacionesCompleta) {
            //Comparamos con la actividad pasada
            if (participacion.getActividad().getId_actividad() == actividad.getId_actividad()) {
                //Ocultamos la pass al usuario
                participacion.getConsumidor().setContrasenia("vacio");

                //Ocultamos la pass al creador por si tiene
                if (participacion.getActividad().getCreador_ofertante() != null) {
                    participacion.getActividad().getCreador_ofertante().setContrasenia("vacio");
                }

                listaParticipacionesADevolver.add(participacion);
            }
        }


        return listaParticipacionesADevolver;
    }


    public synchronized ParticipacionActividadesModel guardarParticipacion(ParticipacionActividadesModel participacion) {
        try {
            return participacionActividadesRepositorio.save(participacion);
        } catch (Exception e) {
            System.out.println("- Alguien intentó unirse a una actividad imposible o con un consumidor inexistente");
            return null;
        }

    }

    public synchronized ParticipacionActividadesModel actualizarParticipacionPorId(ParticipacionActividadesModel participacionActualizar, int id) {
        ParticipacionActividadesModel participacionNueva = participacionActividadesRepositorio.findById(id).get();

        participacionNueva.setActividad(participacionActualizar.getActividad());
        participacionNueva.setConsumidor(participacionActualizar.getConsumidor());

        participacionActividadesRepositorio.save(participacionNueva);

        return participacionNueva;
    }

    public synchronized boolean borrarParticipacionPorId(int id) {
        try {
            participacionActividadesRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

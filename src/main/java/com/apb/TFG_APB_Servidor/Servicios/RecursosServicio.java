package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Modelos.OfertanteModel;
import com.apb.TFG_APB_Servidor.Modelos.RecursosModel;
import com.apb.TFG_APB_Servidor.Repositorios.IRecursosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RecursosServicio {

    @Autowired
    IRecursosRepositorio recursosRepositorio;

    public ArrayList<RecursosModel> getRecursos(){
        ArrayList<RecursosModel> listaRecursos = (ArrayList<RecursosModel>) recursosRepositorio.findAll();

        for (RecursosModel recurso : listaRecursos) {
            OfertanteModel ofertanteOcultarContrasenia = recurso.getActividad().getCreador_ofertante();
                //Controlamos esto ya que la actividad puede ser sugerida
            if (ofertanteOcultarContrasenia != null) {
                ofertanteOcultarContrasenia.setContrasenia("vacio");
            }

        }

        return listaRecursos;
    }

    public Optional<RecursosModel> getRecursoPorId(int id) {
        Optional<RecursosModel> recurso = recursosRepositorio.findById(id);

        //Ocultamos la contrasenia
        recurso.get().getActividad().getCreador_ofertante().setContrasenia("vacio");

        return recurso;
    }

    public ArrayList<RecursosModel> getRecursosPorIdActividad() {
        //TODO
        return null;
    }

    public RecursosModel guardarRecurso(RecursosModel recurso) {
        return recursosRepositorio.save(recurso);
    }

    public RecursosModel actualizarRecurso(RecursosModel recursoNuevo, int id) {
        RecursosModel recursoAActualizar = recursosRepositorio.findById(id).get();

        recursoAActualizar.setNombre_recurso(recursoNuevo.getNombre_recurso());
        recursoAActualizar.setDescripcion(recursoNuevo.getDescripcion());
        recursoAActualizar.setCantidad(recursoNuevo.getCantidad());
        recursoAActualizar.setIs_ofertada_por_ofertante(recursoNuevo.isIs_ofertada_por_ofertante());

        recursosRepositorio.save(recursoAActualizar);

        return recursoAActualizar;
    }

    public boolean borrarRecursoPorId(int id) {
        try {
            recursosRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

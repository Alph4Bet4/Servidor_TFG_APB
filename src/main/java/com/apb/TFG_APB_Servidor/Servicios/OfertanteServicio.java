package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Modelos.OfertanteModel;
import com.apb.TFG_APB_Servidor.Repositorios.IOfertanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Clase encargada de relizar acciones recibidas por el controlador
 */
@Service
public class OfertanteServicio {
    @Autowired
    IOfertanteRepositorio ofertanteRepositorio;

    public ArrayList<OfertanteModel> getOfertantes() {
        return (ArrayList<OfertanteModel>) ofertanteRepositorio.findAll();
    }

    public OfertanteModel guardarOfertante(OfertanteModel ofertante) {
        return ofertanteRepositorio.save(ofertante);
    }

    public Optional<OfertanteModel> getOfertantePorId(int id) {
        return ofertanteRepositorio.findById(id);
    }

    public OfertanteModel actualizarOfertantePorId(OfertanteModel ofertante, int id) {
        OfertanteModel ofertanteUsuario = ofertanteRepositorio.findById(id).get();

        ofertanteUsuario.setNombreOfertante(ofertante.getNombreOfertante());
        ofertanteUsuario.setPrimerApellidoOfertante(ofertante.getPrimerApellidoOfertante());
        ofertanteUsuario.setSegundoApellidoOfertante(ofertante.getSegundoApellidoOfertante());
        ofertanteUsuario.setNombreEmpresa(ofertante.getNombreEmpresa());

        return ofertanteUsuario;
    }


    public Boolean borrarOfertante(int id) {
        try {
            ofertanteRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

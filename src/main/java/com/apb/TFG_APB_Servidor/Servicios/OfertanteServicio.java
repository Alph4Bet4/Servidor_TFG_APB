package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Controladores.EmailController;
import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
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
        ArrayList<OfertanteModel> listaOfertantes = (ArrayList<OfertanteModel>) ofertanteRepositorio.findAll();

        //Tiene en cuenta la seguridad y oculta las contrasenias
        for (OfertanteModel ofertante : listaOfertantes) {
            ofertante.setContrasenia("vacio");
        }

        return listaOfertantes;
    }

    public OfertanteModel guardarOfertante(OfertanteModel ofertante) {
        EmailController controladorEmail = new EmailController();
        if (controladorEmail.validar(ofertante.getEmail_ofertante())) {
            return ofertanteRepositorio.save(ofertante);
        }
        return null;
    }

    public Optional<OfertanteModel> getOfertantePorId(int id) {
        Optional<OfertanteModel> ofertante = ofertanteRepositorio.findById(id);

        ofertante.get().setContrasenia("vacio");

        return ofertante;
    }

    public OfertanteModel actualizarOfertantePorId(OfertanteModel ofertante, int id) {
        OfertanteModel ofertanteAActualizar = ofertanteRepositorio.findById(id).get();

        ofertanteAActualizar.setNombreOfertante(ofertante.getNombreOfertante());
        ofertanteAActualizar.setPrimerApellidoOfertante(ofertante.getPrimerApellidoOfertante());
        ofertanteAActualizar.setSegundoApellidoOfertante(ofertante.getSegundoApellidoOfertante());
        //TODO hashear contraseñas
        ofertanteAActualizar.setContrasenia(ofertante.getContrasenia());
        ofertanteAActualizar.setNombreEmpresa(ofertante.getNombreEmpresa());
        ofertanteAActualizar.setEmail_ofertante(ofertante.getEmail_ofertante());
        ofertanteAActualizar.setIs_administrador(ofertante.isIs_administrador());

        ofertanteRepositorio.save(ofertanteAActualizar);

        return ofertanteAActualizar;
    }


    public boolean borrarOfertante(int id) {
        try {
            ofertanteRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

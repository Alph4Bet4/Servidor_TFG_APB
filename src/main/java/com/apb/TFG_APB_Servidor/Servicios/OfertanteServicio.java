package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Controladores.EmailController;
import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import com.apb.TFG_APB_Servidor.Modelos.OfertanteModel;
import com.apb.TFG_APB_Servidor.Repositorios.IOfertanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pbkdf2.PBKDF2Encriptacion;

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
            if (!comprobarEmailExistente(ofertante.getEmail_ofertante())) {
                //Encriptamos la pass
                ofertante.setContrasenia(new PBKDF2Encriptacion().encriptarPass(ofertante.getContrasenia()));
                return ofertanteRepositorio.save(ofertante);
            }
        }
        return null;
    }

    /**
     * Método que devuelve false si no existe un email, de otra forma devuelve verdadero
     * @param email
     * @return
     */
    public boolean comprobarEmailExistente(String email) {
        ArrayList<OfertanteModel> listaOfertantes = (ArrayList<OfertanteModel>) ofertanteRepositorio.findAll();

        for (OfertanteModel ofertante : listaOfertantes) {
            if (ofertante.getEmail_ofertante().equals(email)) {
                return true;
            }
        }

        return false;
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
        // Hasheamos la contrasenia
        ofertanteAActualizar.setContrasenia(new PBKDF2Encriptacion().encriptarPass(ofertante.getContrasenia()));
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

    public Optional<OfertanteModel> getOfertantePorDatos(OfertanteModel ofertante) {
        ArrayList<OfertanteModel> listaOfertantes = (ArrayList<OfertanteModel>) ofertanteRepositorio.findAll();
        Optional<OfertanteModel> ofertanteDevolver = null;

        for (OfertanteModel ofertanteModel : listaOfertantes) {

            //Comprueba primeramente los usuarios para saber si alguno coincide
            if (ofertanteModel.getNombreOfertante().equals(ofertante.getNombreOfertante())) {
                //Si coinciden pasa a comprobar el email que es único
                if (ofertanteModel.getEmail_ofertante().equals(ofertante.getEmail_ofertante())) {
                    //Comprobamos las contraseñas
                    PBKDF2Encriptacion pbkdf2Encriptacion = new PBKDF2Encriptacion();

                    if (pbkdf2Encriptacion.verificarPass(ofertante.getContrasenia(), ofertanteModel.getContrasenia())) {
                        ofertanteDevolver = Optional.of(ofertanteModel);
                    }
                }
            }
        }

        return ofertanteDevolver;
    }

}

package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Controladores.EmailController;
import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import com.apb.TFG_APB_Servidor.Repositorios.IConsumidorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pbkdf2.PBKDF2Encriptacion;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ConsumidorServicio {

    @Autowired
    IConsumidorRepositorio consumidorRepositorio;

    public ArrayList<ConsumidorModel> getConsumidores() {
        ArrayList<ConsumidorModel> listaConsumidores = (ArrayList<ConsumidorModel>) consumidorRepositorio.findAll();

        //Tiene en cuenta la seguridad y oculta las contrasenias
        for (ConsumidorModel consumidor : listaConsumidores) {
            consumidor.setContrasenia("vacio");
        }

        return listaConsumidores;
    }

    public ConsumidorModel guardarConsumidor(ConsumidorModel nuevoConsumidor) {
        EmailController controladorEmail = new EmailController();
        if (controladorEmail.validar(nuevoConsumidor.getEmail_consumidor())) {
            if (!comprobarEmailExistente(nuevoConsumidor.getEmail_consumidor())) {
                //Encriptamos la pass
                nuevoConsumidor.setContrasenia(new PBKDF2Encriptacion().encriptarPass(nuevoConsumidor.getContrasenia()));
                return consumidorRepositorio.save(nuevoConsumidor);
            }

        }
        return null;
    }

    /**
     * Método que devuelve false si no existe un email, de otra forma devuelve verdadero
     *
     * @param email
     * @return
     */
    public boolean comprobarEmailExistente(String email) {
        ArrayList<ConsumidorModel> listaConsumidor = (ArrayList<ConsumidorModel>) consumidorRepositorio.findAll();

        for (ConsumidorModel consumidor : listaConsumidor) {
            if (consumidor.getEmail_consumidor().equals(email)) {
                return true;
            }
        }

        return false;
    }

    public Optional<ConsumidorModel> getConsumidorPorId(int id) {
        Optional<ConsumidorModel> consumidor = consumidorRepositorio.findById(id);

        consumidor.get().setContrasenia("vacio");

        return consumidor;
    }

    public ConsumidorModel actualizarConsumidorPorId(ConsumidorModel consumidor, int id) {
        ConsumidorModel consumidorAActualizar = consumidorRepositorio.findById(id).get();

        consumidorAActualizar.setNombreConsumidor(consumidor.getNombreConsumidor());
        consumidorAActualizar.setPrimerApellidoConsumidor(consumidor.getPrimerApellidoConsumidor());
        consumidorAActualizar.setSegundoApellidoConsumidor(consumidor.getSegundoApellidoConsumidor());
        //Hasheamos la contrasenia
        consumidorAActualizar.setContrasenia(new PBKDF2Encriptacion().encriptarPass(consumidor.getContrasenia()));
        consumidorAActualizar.setEmail_consumidor(consumidor.getEmail_consumidor());

        consumidorRepositorio.save(consumidorAActualizar);

        return consumidorAActualizar;
    }

    public boolean borrarConsumidorPorId(int id) {
        try {
            consumidorRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<ConsumidorModel> getConsumidorPorDatos(ConsumidorModel consumidor) {
        ArrayList<ConsumidorModel> listaConsumidores = (ArrayList<ConsumidorModel>) consumidorRepositorio.findAll();
        Optional<ConsumidorModel> consumidorDevolver = null;

        for (ConsumidorModel consumidorModel : listaConsumidores) {

            //Comprueba primeramente los usuarios para saber si alguno coincide
            if (consumidorModel.getNombreConsumidor().equals(consumidor.getNombreConsumidor())) {
                //Si coinciden pasa a comprobar el email que es único
                if (consumidorModel.getEmail_consumidor().equals(consumidor.getEmail_consumidor())) {
                    //Comprobamos las contraseñas
                    PBKDF2Encriptacion pbkdf2Encriptacion = new PBKDF2Encriptacion();

                    if (pbkdf2Encriptacion.verificarPass(consumidor.getContrasenia(), consumidorModel.getContrasenia())) {
                        consumidorDevolver = Optional.of(consumidorModel);
                    }

                }
            }
        }

        return consumidorDevolver;
    }
}

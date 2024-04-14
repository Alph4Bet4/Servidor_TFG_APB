package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Controladores.EmailController;
import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import com.apb.TFG_APB_Servidor.Repositorios.IConsumidorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
            return consumidorRepositorio.save(nuevoConsumidor);
        }
        return null;
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
        //TODO hashear contraseñas
        consumidorAActualizar.setContrasenia(consumidor.getContrasenia());
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
}

package com.apb.TFG_APB_Servidor.Servicios;

import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import com.apb.TFG_APB_Servidor.Repositorios.IConsumidorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ConsumidorServicio {

    @Autowired
    IConsumidorRepositorio consumidorRepositorio;

    public ArrayList<ConsumidorModel> getConsumidores() {
        return (ArrayList<ConsumidorModel>) consumidorRepositorio.findAll();
    }

    public ConsumidorModel guardarConsumidor(ConsumidorModel nuevoConsumidor) {
        return consumidorRepositorio.save(nuevoConsumidor);
    }

    public Optional<ConsumidorModel> getConsumidorPorId(int id) {
        return consumidorRepositorio.findById(id);
    }

    public ConsumidorModel actualizarConsumidorPorId(ConsumidorModel consumidor, int id) {
        ConsumidorModel consumidorUsuario = consumidorRepositorio.findById(id).get();

        consumidorUsuario.setNombreConsumidor(consumidor.getNombreConsumidor());
        consumidorUsuario.setPrimerApellidoConsumidor(consumidor.getPrimerApellidoConsumidor());
        consumidorUsuario.setSegundoApellidoConsumidor(consumidor.getSegundoApellidoConsumidor());

        return consumidorUsuario;
    }

    public Boolean borrarConsumidorPorId(int id) {
        try {
            consumidorRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

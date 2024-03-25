package com.apb.TFG_APB_Servidor.Controladores;

import com.apb.TFG_APB_Servidor.Modelos.ConsumidorModel;
import com.apb.TFG_APB_Servidor.Servicios.ConsumidorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Clase encargada de controlar las peticiones de Consumidor
 */
@RestController
@RequestMapping("/consumidor")
public class ConsumidorController {

    @Autowired
    ConsumidorServicio consumidorServicio;

    @GetMapping
    public ArrayList<ConsumidorModel> getConsumidor() {
        return consumidorServicio.getConsumidores();
    }

    @PostMapping
    public ConsumidorModel guardarConsumidor(@RequestBody ConsumidorModel consumidor) {
        return consumidorServicio.guardarConsumidor(consumidor);
    }

    @GetMapping(path = "/{id}")
    public Optional<ConsumidorModel> getConsumidorPorId(@PathVariable("id") int id) {
        return consumidorServicio.getConsumidorPorId(id);
    }

    @PutMapping(path = "/{id}")
    public ConsumidorModel actualizarConsumidorPorId(@RequestBody ConsumidorModel consumidor, @PathVariable("id") int id) {
        return consumidorServicio.actualizarConsumidorPorId(consumidor, id);
    }

    @DeleteMapping(path = "/{id}")
    public String borrarConsumidorPorId(@PathVariable("id") int id) {
        boolean isBorrado = consumidorServicio.borrarConsumidorPorId(id);

        if (isBorrado) {
            return "Se ha borrado el consumidor con id: " + id;
        } else {
            return "No se ha podido borrar el consumidor con id: " + id;
        }
    }


}

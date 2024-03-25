package com.apb.TFG_APB_Servidor.Controladores;

import com.apb.TFG_APB_Servidor.Modelos.ActividadesModel;
import com.apb.TFG_APB_Servidor.Servicios.ActividadesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/actividades")
public class ActividadesController {

    @Autowired
    ActividadesServicio actividadesServicio;

    @GetMapping
    public ArrayList<ActividadesModel> getActividades() {
        return actividadesServicio.getActividades();
    }

    @PostMapping
    public ActividadesModel guardarActividades(@RequestBody ActividadesModel actividad) {
        return actividadesServicio.guardarActividad(actividad);
    }

    @GetMapping(path = "/{id}")
    public Optional<ActividadesModel> getActividadPorId(@PathVariable("id") int id) {
        return actividadesServicio.getActividadPorId(id);
    }

    @PutMapping(path = "/{id}")
    public ActividadesModel actualizarActividadPorId(@RequestBody ActividadesModel actividad, @PathVariable("id") int id) {
        return actividadesServicio.actualizarActividadPorId(actividad, id);
    }

    @DeleteMapping(path = "/{id}")
    public String borrarActividadPorId(@PathVariable("id") int id) {
        boolean isBorrado = actividadesServicio.borrarActividadPorId(id);

        if (isBorrado) {
            return "Se ha borrado la actividad con id: " + id;
        } else {
            return "No se ha borrado la actividad con id: " + id;
        }

    }

}

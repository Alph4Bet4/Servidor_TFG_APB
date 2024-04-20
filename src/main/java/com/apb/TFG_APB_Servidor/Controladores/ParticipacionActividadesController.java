package com.apb.TFG_APB_Servidor.Controladores;

import com.apb.TFG_APB_Servidor.Modelos.ActividadesModel;
import com.apb.TFG_APB_Servidor.Modelos.ParticipacionActividadesModel;
import com.apb.TFG_APB_Servidor.Servicios.ParticipacionActividadesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/participacion")
public class ParticipacionActividadesController {

    @Autowired
    ParticipacionActividadesServicio participacionActividadesServicio;

    @GetMapping
    public ArrayList<ParticipacionActividadesModel> getParticipaciones() {
        return participacionActividadesServicio.getParticipaciones();
    }

    @GetMapping(path = "/{id}")
    public Optional<ParticipacionActividadesModel> getParticipacionPorId(@PathVariable("id") int id) {
        return participacionActividadesServicio.getParticipacionPorId(id);
    }

    @PostMapping(path = "/porIdActividad")
    public ArrayList<ParticipacionActividadesModel> getParticipacionPorIdActividad(@RequestBody ActividadesModel actividad) {
        return participacionActividadesServicio.getParticipacionPorIdActividad(actividad);
    }

    @PostMapping
    public ParticipacionActividadesModel guardarParticipacion(@RequestBody ParticipacionActividadesModel participacion) {
        return participacionActividadesServicio.guardarParticipacion(participacion);
    }

    @PutMapping(path = "/{id}")
    public ParticipacionActividadesModel actualizarParticipacion(@RequestBody ParticipacionActividadesModel participacion, @PathVariable("id") int id) {
        return participacionActividadesServicio.actualizarParticipacionPorId(participacion, id);
    }

    @DeleteMapping(path = "/{id}")
    public String borrarParticipacionPorId(@PathVariable("id") int id) {
        boolean isBorrado = participacionActividadesServicio.borrarParticipacionPorId(id);

        if (isBorrado) {
            return "Se ha borrado la participacion con id: " + id;
        } else {
            return "No se ha borrado la participacion con id: " + id;
        }

    }

}

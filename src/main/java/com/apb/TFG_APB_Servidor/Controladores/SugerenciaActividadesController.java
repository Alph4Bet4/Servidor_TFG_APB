package com.apb.TFG_APB_Servidor.Controladores;

import com.apb.TFG_APB_Servidor.Modelos.SugerenciaActividadesModel;
import com.apb.TFG_APB_Servidor.Servicios.SugerenciaActividadesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/sugerencia")
public class SugerenciaActividadesController {

    @Autowired
    SugerenciaActividadesServicio sugerenciaActividadesServicio;

    @GetMapping
    public ArrayList<SugerenciaActividadesModel> getSugerencias() {
        return  sugerenciaActividadesServicio.getSugerencias();
    }

    @GetMapping(path = "/{id}")
    public Optional<SugerenciaActividadesModel> getSugerenciaPorId(@PathVariable("id") int id) {
        return sugerenciaActividadesServicio.getSugerenciaPorId(id);
    }

    @PostMapping
    public SugerenciaActividadesModel guardarSugerencia(@RequestBody SugerenciaActividadesModel sugerencia) {
        return sugerenciaActividadesServicio.guardarSugerencia(sugerencia);
    }

    @PutMapping(path = "/{id}")
    public SugerenciaActividadesModel actualizarSugerencia(@RequestBody SugerenciaActividadesModel sugerencia, @PathVariable("id") int id) {
        return sugerenciaActividadesServicio.actualizarSugerenciaPorId(sugerencia, id);
    }

    @DeleteMapping(path = "/{id}")
    public String borrarSugerenciaPorId(@PathVariable("id") int id) {
        boolean isBorrado = sugerenciaActividadesServicio.borrarSugerencia(id);

        if (isBorrado) {
            return "Ha sido borrada la sugerencia con id: " + id;
        } else {
            return "No ha sido borrada la sugerencia con id: " + id;
        }
    }

}

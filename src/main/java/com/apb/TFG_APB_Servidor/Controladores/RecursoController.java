package com.apb.TFG_APB_Servidor.Controladores;

import com.apb.TFG_APB_Servidor.Modelos.ActividadesModel;
import com.apb.TFG_APB_Servidor.Modelos.RecursosModel;
import com.apb.TFG_APB_Servidor.Servicios.RecursosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/recurso")
public class RecursoController {

    @Autowired
    RecursosServicio recursosServicio;

    @GetMapping
    public ArrayList<RecursosModel> getRecursos() {
        return recursosServicio.getRecursos();
    }

    @GetMapping(path = "/{id}")
    public Optional<RecursosModel> getRecursoPorId(@PathVariable("id") int id) {
        return recursosServicio.getRecursoPorId(id);
    }

    @PostMapping(path = "/porIdActividad")
    public ArrayList<RecursosModel> getRecursosPorIdActividad(@RequestBody ActividadesModel actividad){
        return recursosServicio.getRecursosPorIdActividad(actividad);
    }

    @PostMapping
    public RecursosModel guardarRecurso(@RequestBody RecursosModel recurso) {
        return recursosServicio.guardarRecurso(recurso);
    }

    @PutMapping(path = "/{id}")
    public RecursosModel actualizarRecursoPorId(@RequestBody RecursosModel recurso, @PathVariable("id") int id) {
        return recursosServicio.actualizarRecurso(recurso, id);
    }

    @DeleteMapping(path = "/{id}")
    public String borrarRecursoPorId(@PathVariable("id") int id) {
        boolean isBorrado = recursosServicio.borrarRecursoPorId(id);

        if (isBorrado) {
            return "Se ha borrado el recurso con id: " + id;
        } else {
            return "No se ha podido borrar el recurso con id: " + id;
        }

    }


}

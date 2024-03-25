package com.apb.TFG_APB_Servidor.Controladores;

import com.apb.TFG_APB_Servidor.Modelos.OfertanteModel;
import com.apb.TFG_APB_Servidor.Servicios.OfertanteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Clase encargada de controlar las peticiones de ofertante
 */
@RestController
@RequestMapping("/ofertante")
public class OfertanteController {

    @Autowired
    private OfertanteServicio ofertanteServicio;

    @GetMapping
    public ArrayList<OfertanteModel> getOfertantes() {
        return this.ofertanteServicio.getOfertantes();
    }

    @PostMapping
    public OfertanteModel guardarOfertante(@RequestBody OfertanteModel ofertante) {
        return this.ofertanteServicio.guardarOfertante(ofertante);
    }

    //Las llaves representan algo no definido
    @GetMapping(path = "/{id}")
    public Optional<OfertanteModel> getOfertantesPorId(@PathVariable("id") int id) {
        return this.ofertanteServicio.getOfertantePorId(id);
    }

    @PutMapping(path = "/{id}")
    public OfertanteModel actualizarOfertantePorId(@RequestBody OfertanteModel ofertante, @PathVariable("id") int id) {
        return this.ofertanteServicio.actualizarOfertantePorId(ofertante, id);
    }


    @DeleteMapping(path = "/{id}")
    public String borrarOfertantePorId(@PathVariable("id") int id) {
        boolean isBorrado = this.ofertanteServicio.borrarOfertante(id);

        if (isBorrado) {
            return "Ofertante con id " + id + " ha sido borrado";
        } else {
            return "Ha ocurrido un error borrando un ofertante con id: " + id;
        }
    }
}

-- SET GLOBAL event_scheduler = ON;
-- DROP EVENT comprobar_fecha_hora_para_finalizar;
-- DROP EVENT comprobar_fecha_hora_para_en_curso;
-- DROP TRIGGER actualizar_cantidad_participantes;
-- DROP TRIGGER disminuir_cantidad_participantes;
-- SHOW EVENTS;
-- SHOW TRIGGERS;
-- DROP PROCEDURE actualizar_cantidad_inscritos;

-- SELECT CURDATE() as fecha_actual, CURTIME() as hora_actual


/* EVENTS

DELIMITER ;
CREATE EVENT comprobar_fecha_hora_para_finalizar
ON SCHEDULE EVERY 1 MINUTE
DO
	UPDATE actividades as ac
    SET ac.estado = 'Finalizado'
    WHERE CURDATE() >= DATE(ac.fecha) 
    AND CURTIME() >= TIME(ac.hora_inicio) 
    AND CURTIME() >= TIME(ac.hora_fin);
END 
DELIMITER ;



CREATE EVENT comprobar_fecha_hora_para_en_curso
ON SCHEDULE EVERY 1 MINUTE
DO
	UPDATE actividades as ac
    SET ac.estado = 'EnCurso'
    WHERE ac.fecha = CURDATE() 
    AND ac.hora_inicio <= CURTIME()
    AND ac.hora_fin >= CURTIME()
    AND ac.estado = 'Disponible';
END //
DELIMITER ;
*/
/* PROCEDURE
DELIMITER //

CREATE PROCEDURE actualizar_cantidad_inscritos(
    IN actividad_id INT
)
BEGIN
    -- Incrementa la cantidad de personas inscritas en la actividad
    UPDATE actividades as ac
    SET ac.cantidad_actual_personas = ac.cantidad_actual_personas + 1
    WHERE ac.id_actividad = actividad_id
    AND ac.cantidad_actual_personas < ac.cantidad_max_personas;
END //
*/
/* TRIGGERS
DELIMITER //
CREATE TRIGGER actualizar_cantidad_participantes
AFTER INSERT ON participacion_actividades
FOR EACH ROW
BEGIN
    -- Obtener el ID de actividad de la nueva participación
    DECLARE actividad_id INT;
    
    SELECT id_actividad INTO actividad_id 
    FROM participacion_actividades 
    WHERE id_participacion_actividades = NEW.id_participacion_actividades;
    
    -- Actualizamos la cantidad de personas y su estado posteriormente
	UPDATE actividades as ac
    SET ac.cantidad_actual_personas = ac.cantidad_actual_personas + 1
    WHERE ac.id_actividad = actividad_id
    AND ac.cantidad_actual_personas < ac.cantidad_max_personas;
    
    -- Actualizamos el estado
    UPDATE actividades as ac
    SET ac.estado = 'Completo'
    WHERE ac.cantidad_actual_personas >= ac.cantidad_max_personas
    AND ac.id_actividad = actividad_id;
    
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER disminuir_cantidad_participantes
BEFORE DELETE ON participacion_actividades
FOR EACH ROW
BEGIN
    -- Obtener el ID de actividad de la antigua participación
    DECLARE actividad_id INT;
    
    SELECT id_actividad INTO actividad_id 
    FROM participacion_actividades 
    WHERE id_participacion_actividades = OLD.id_participacion_actividades;

    -- Actualizamos la cantidad de personas y su estado posteriormente
	UPDATE actividades as ac
    SET ac.cantidad_actual_personas = ac.cantidad_actual_personas - 1
    WHERE ac.id_actividad = actividad_id;
    
    -- Actualizamos el estado
    UPDATE actividades as ac
    SET ac.estado = 'Disponible'
    WHERE ac.cantidad_actual_personas < ac.cantidad_max_personas
    AND ac.id_actividad = actividad_id;
    
END //
DELIMITER ;
*/

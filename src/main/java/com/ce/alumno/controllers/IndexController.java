package com.ce.alumno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ce.alumno.dao.IDaoAlumnos;
import com.ce.alumno.entity.Alumnos;
import com.google.gson.Gson;

@RestController
@RequestMapping(value ="/api")
public class IndexController {
	@Autowired
	private IDaoAlumnos dAlumnos;

	@GetMapping("/saludo")
	public String saludo() {
		return"soy un saludo";
	}
	@GetMapping(value = "/getAlumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAlumnos() {
		List<Alumnos> lista = dAlumnos.findAll();
		Gson gson = new Gson();
		return gson.toJson(lista);
	}
	
	@PostMapping(value = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Alumnos saveAlumno(@RequestBody Alumnos alumno) {
		return dAlumnos.save(alumno);
	}
	
	@PutMapping(value = "/alumnos/{idalumno}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Alumnos updateAlumno(@PathVariable int idalumno, @RequestBody Alumnos alumno) {
	    Alumnos alumnoToUpdate = dAlumnos.findById(idalumno);
	    if (alumnoToUpdate != null) {
	        alumnoToUpdate.setNombre(alumno.getNombre());
	        alumnoToUpdate.setApellido(alumno.getApellido());
	        alumnoToUpdate.setSexo(alumno.getSexo());
	        alumnoToUpdate.setFechanac(alumno.getFechanac());
	        alumnoToUpdate.setTipopersona(alumno.getTipopersona());
	        alumnoToUpdate.setSueldo(alumno.getSueldo());
	        alumnoToUpdate.setFechacreacion(alumno.getFechacreacion());
	        alumnoToUpdate.setEstatus(alumno.getEstatus());
	        return dAlumnos.update(alumnoToUpdate);
	    } else {
	        return null;
	    }
	}

}

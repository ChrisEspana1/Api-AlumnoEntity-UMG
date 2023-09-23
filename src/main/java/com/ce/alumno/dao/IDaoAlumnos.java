package com.ce.alumno.dao;

import java.util.List;


import com.ce.alumno.entity.Alumnos;

public interface IDaoAlumnos {
	public List<Alumnos> findAll();
	public Alumnos save(Alumnos alumno);
	public Alumnos update (Alumnos alumno);
	public Alumnos findById(int idalumno);
}
package com.ce.alumno.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.ce.alumno.dao.IDaoAlumnos;
import com.ce.alumno.entity.Alumnos;


import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j

public class DaoAlumnos implements IDaoAlumnos {
	@PersistenceContext
	private EntityManager manager;
	@Transactional (readOnly = true)
	@Override
	public List<Alumnos> findAll(){
	try {
		@SuppressWarnings("unchecked")
		List<Alumnos> listaAlumnos = manager.createQuery("from Alumnos").getResultList();
		return listaAlumnos;
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	
	};

	@Override
	public Alumnos save(Alumnos alumno) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(alumno);
			//log.info("Data enviada:   " + json);
			manager.persist(alumno);
			return alumno;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("Error al procesar tecnico " + tecnico.toString());
			return null;
		}
	}

	@Override
	public Alumnos update(Alumnos alumno) {
		try {
			manager.merge(alumno);
			return alumno;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("Error al procesar tecnico " + tecnico.toString());
			return null;
		}
	}

	@Override
	public Alumnos findById(int idalumno) {
		Alumnos a = manager.find(Alumnos.class, idalumno);
		if (a == null) {
            throw new EntityNotFoundException("Can't find Artist for ID "
                    + idalumno);
        }
		return a;
	}


		
	
	}
	

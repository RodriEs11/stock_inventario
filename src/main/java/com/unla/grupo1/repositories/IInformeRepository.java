package com.unla.grupo1.repositories;

import org.springframework.stereotype.Repository;
import com.unla.grupo1.entities.Informe;
import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository("informeRepository")
public interface IInformeRepository extends JpaRepository<Informe, Serializable>{
	
	public abstract Optional<Informe> findById(int id);
	
}

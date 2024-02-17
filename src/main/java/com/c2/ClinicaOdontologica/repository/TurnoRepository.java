package com.c2.ClinicaOdontologica.repository;

import com.c2.ClinicaOdontologica.entities.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface TurnoRepository extends JpaRepository<Turno,Long> {
}

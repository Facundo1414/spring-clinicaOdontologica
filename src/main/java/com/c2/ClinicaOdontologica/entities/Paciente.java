package com.c2.ClinicaOdontologica.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")

@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String cedula;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(cascade =  CascadeType.ALL) // si se elimina una se elimina la otra.. al igual que con el resto de operaciones
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio; //relacion de asociacion.
    @Column(unique = true) // no se pueden repetir los mails
    private String email;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    

    public Paciente() {
    }
    public Paciente(String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente(Long id, String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }


    public Paciente(Long pacienteId) {
    }
}

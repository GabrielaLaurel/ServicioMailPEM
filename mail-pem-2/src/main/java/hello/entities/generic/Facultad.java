package hello.entities.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "facultad")
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idfacultad;
    private String nombre;
    private String descripcion;
    @Column(nullable = false)
    private Boolean estado;

    @JsonIgnore
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL)
    private Set<Carrera> carreras;

    public Integer getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(Integer idfacultad) {
        this.idfacultad = idfacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Set<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(Set<Carrera> carreras) {
        this.carreras = carreras;
    }
}

package hello.entities.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "carrera")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private float idcarrera;
    @Column(name = "pre_id")
    private Integer preId;
    private String nombre;
    private String descripcion;
    private Integer activo;

    @ManyToOne
    @JoinColumn(name = "idfacultad")
    private Facultad facultad;

    @JsonIgnore
    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private Set<Alumno> alumnos;

    @JsonIgnore
    @ManyToMany(mappedBy = "carreras")
    private Set<Empleo> empleos;

    public float getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(float idcarrera) {
        this.idcarrera = idcarrera;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
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

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Set<Empleo> getEmpleos() {
        return empleos;
    }

    public void setEmpleos(Set<Empleo> empleos) {
        this.empleos = empleos;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}

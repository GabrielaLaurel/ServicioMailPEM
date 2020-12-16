package hello.entities.generic;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "empleo")
public class Empleo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idempleo;
    private String nombre;
    private String descripcion;
    private String grado;
    private Double sueldo;
    private Integer verificado;
    private String verificador;
    private String ubicacion;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @Column(name = "ciclo_min")
    private Integer cicloMin;
    @Column(name = "tipo_empleo")
    private String tipo;
    private String departamento;

    @ManyToOne
    @JoinColumn
    private Empleador empleador;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "empleo_carrera",
        joinColumns = @JoinColumn(name = "idempleo", referencedColumnName = "idempleo"),
        inverseJoinColumns = @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera"))
    private Set<Carrera> carreras;

    public Integer getIdempleo() {
        return idempleo;
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

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getVerificado() {
        return verificado;
    }

    public void setVerificado(Integer verificado) {
        this.verificado = verificado;
    }

    public String getVerificador() {
        return verificador;
    }

    public void setVerificador(String verificador) {
        this.verificador = verificador;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public Set<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(Set<Carrera> carreras) {
        this.carreras = carreras;
    }

    public void setIdempleo(Integer idempleo) {
        this.idempleo = idempleo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getCicloMin() {
        return cicloMin;
    }

    public void setCicloMin(Integer cicloMin) {
        this.cicloMin = cicloMin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

package hello.entities.generic;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @Column(name = "codigo_matricula")
    private Integer codigoMatricula;
    @Column(name = "nombre_programa")
    private String nombrePrograma;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    private String nombres;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    private String telefono;
    private String direccion;
    private String distrito;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    private String sexo;
    private String tcred;
    @Column(name = "situacion_academica")
    private String situacionAcademica;
    @Column(name = "estado_permanencia")
    private String estadoPermanencia;
    private Double promedio;
    private String email;
    @Column(name = "ciclo_estudios")
    private Integer cicloEstudios;
    @Column(name = "ultimo_periodo_mat")
    private String ultimoPeriodoMat;

    @ManyToOne
    @JoinColumn(name = "codigo_carrera")
    private Carrera carrera;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    private Set<AlumnoPortalEmpl> alumnoPortalEmpls;

    public Integer getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(Integer codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTcred() {
        return tcred;
    }

    public void setTcred(String tcred) {
        this.tcred = tcred;
    }

    public String getSituacionAcademica() {
        return situacionAcademica;
    }

    public void setSituacionAcademica(String situacionAcademica) {
        this.situacionAcademica = situacionAcademica;
    }

    public String getEstadoPermanencia() {
        return estadoPermanencia;
    }

    public void setEstadoPermanencia(String estadoPermanencia) {
        this.estadoPermanencia = estadoPermanencia;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCicloEstudios() {
        return cicloEstudios;
    }

    public void setCicloEstudios(Integer cicloEstudios) {
        this.cicloEstudios = cicloEstudios;
    }

    public String getUltimoPeriodoMat() {
        return ultimoPeriodoMat;
    }

    public void setUltimoPeriodoMat(String ultimoPeriodoMat) {
        this.ultimoPeriodoMat = ultimoPeriodoMat;
    }

    public Set<AlumnoPortalEmpl> getAlumnoPortalEmpls() {
        return alumnoPortalEmpls;
    }

    public void setAlumnoPortalEmpls(Set<AlumnoPortalEmpl> alumnoPortalEmpls) {
        this.alumnoPortalEmpls = alumnoPortalEmpls;
    }
}

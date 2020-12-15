package hello.entities.generic;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alumno_portal_empl")
public class AlumnoPortalEmpl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idalumno_portal_empl")
    private Integer idalumnoPortalEmpl;
    @Column(name = "correo_alumno")
    private String correoAlumno;
    @Column(name = "correo_personal")
    private String correoPersonal;
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    private String ciclo;
    @Column(name = "foto_perfil")
    private String fotoPerfil;
    @Column(name = "primer_ingreso")
    private Integer primerIngreso;
    private String distrito;
    private String departamento;

    @ManyToOne
    @JoinColumn
    private Alumno alumno;




    public Integer getIdalumnoPortalEmpl() {
        return idalumnoPortalEmpl;
    }

    public void setIdalumnoPortalEmpl(Integer idalumnoPortalEmpl) {
        this.idalumnoPortalEmpl = idalumnoPortalEmpl;
    }

    public String getCorreoAlumno() {
        return correoAlumno;
    }

    public void setCorreoAlumno(String correoAlumno) {
        this.correoAlumno = correoAlumno;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getPrimerIngreso() {
        return primerIngreso;
    }

    public void setPrimerIngreso(Integer primerIngreso) {
        this.primerIngreso = primerIngreso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

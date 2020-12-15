package hello.entities.generic;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "empleador")
public class Empleador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idempleador;
    private String ruc;
    private String sector;
    @Column(name = "razon_social")
    private String razonSocial;
    private String institucion;
    @Column(name = "descripcion_empresa")
    private String descripcionEmpresa;
    @Column(name = "logo_empresa")
    private String logoEmpresa;
    private String responsable;
    private String telefono;
    private String correo;
    @Column(name = "foto_responsable")
    private String fotoResponsable;
    private boolean publicar;
    private String pais;
    private String departamento;
    private String direccion;
    private Integer jerarquia;

    @ManyToOne
    @JoinColumn(name = "web_user_id")
    private WebUser webUser;

    @OneToMany(mappedBy = "empleador", cascade = CascadeType.ALL)
    private Set<Empleo> empleos;

    public Integer getIdempleador() {
        return idempleador;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDescripcionEmpresa() {
        return descripcionEmpresa;
    }

    public void setDescripcionEmpresa(String descripcionEmpresa) {
        this.descripcionEmpresa = descripcionEmpresa;
    }

    public String  getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresa(String logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public Set<Empleo> getEmpleos() {
        return empleos;
    }

    public void setEmpleos(Set<Empleo> empleos) {
        this.empleos = empleos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFotoResponsable() {
        return fotoResponsable;
    }

    public void setFotoResponsable(String fotoResponsable) {
        this.fotoResponsable = fotoResponsable;
    }

    public boolean isPublicar() {
        return publicar;
    }

    public void setPublicar(boolean publicar) {
        this.publicar = publicar;
    }

    public void setIdempleador(Integer idempleador) {
        this.idempleador = idempleador;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(Integer jerarquia) {
        this.jerarquia = jerarquia;
    }
}

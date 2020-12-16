package hello.viewmodel;

public class EmpleoSemanalModel {
    private String oid;
    private byte [] logo;
    private String puesto;
    private String empleador;
    private String direccion;
    private String cierre;
    private String tipo;

    public byte[] getLogo() {
        return logo;
    }
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    public String getEmpleador() {
        return empleador;
    }
    public void setEmpleador(String empleador) {
        this.empleador = empleador;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getCierre() {
        return cierre;
    }
    public void setCierre(String cierre) {
        this.cierre = cierre;
    }
    public String getOid() {
        return oid;
    }
    public void setOid(String oid) {
        this.oid = oid;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

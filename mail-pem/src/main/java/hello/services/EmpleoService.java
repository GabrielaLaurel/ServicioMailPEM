package hello.services;

import hello.entities.generic.AlumnoPortalEmpl;
import hello.entities.generic.Empleo;
import hello.viewmodel.EmpleoSemanalModel;

import java.util.List;

public interface EmpleoService {


    public List<EmpleoSemanalModel> getEmpleoSemanal(List<Empleo>  idsEmpleo);


    public byte[] imagen(String direccion);

    public List<Empleo>  idsEmpleoByAlumno(AlumnoPortalEmpl alumno);

    public void MailSemanalGrupo(List<AlumnoPortalEmpl> alumno,int inicio, int fin);

    public void MailSemanal();

    public void MailSemanalAlumno(AlumnoPortalEmpl alumno);
}

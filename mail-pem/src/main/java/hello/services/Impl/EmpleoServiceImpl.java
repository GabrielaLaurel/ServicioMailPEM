package hello.services.Impl;

import hello.accessingdata.generic.*;
import hello.commons.util.Metodo;
import hello.entities.generic.*;

import hello.ftp.exceptions.FTPErrors;
import hello.ftp.ftpclient.FTPService;
import hello.services.EmpleoService;
import hello.viewmodel.EmpleoSemanalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;


@Service
public class EmpleoServiceImpl implements EmpleoService{

    private static final Logger LOGGER = Logger.getLogger("EmpleoServiceImpl");

    @Autowired
    private EmpleoRepository empleoRepository;

    @Autowired
    private Metodo metodoUtil;
    @Autowired
    private AlumnoPortalEmplRepository alumnoPortalRepository;

    @Autowired
    private FTPService ftpService;

    @Autowired
    private MailService mailService;


    @Override
    public byte[] imagen(String direccion){
        byte[] foto= null;
        try {
            foto =ftpService.downloadFileFromFTP(direccion);
            ftpService.disconnectFTP();
        } catch (FTPErrors ftpErrors) {
            ftpErrors.printStackTrace();
        }
        return foto;
    }
    @Override
    public List<EmpleoSemanalModel> getEmpleoSemanal(List<Empleo> empleos) {
        LocalDateTime hoy=LocalDateTime.now();
      //  List<Empleo> empleos=empleoRepository.findByIds(idsEmpleo);
        List<EmpleoSemanalModel> models=new ArrayList<EmpleoSemanalModel>(0);
        for(Empleo empleo: empleos) {


            LocalDateTime fechaFin=metodoUtil.ToLocalDateTime(empleo.getFechaFin()) ;
            long dias = hoy.until(fechaFin, ChronoUnit.DAYS );
            if(dias>-1) {
                EmpleoSemanalModel model=new EmpleoSemanalModel();
                model.setCierre("Cierra en "+dias+" "+ (dias==1L?"día":"días"));

                Empleador empleador = empleo.getEmpleador();
                model.setOid("empleo_" + empleos.indexOf(empleo));
                System.out.println("***************************************");
                System.out.println(model.getOid());
                model.setPuesto(empleo.getNombre());
                model.setEmpleador(empleador.getRazonSocial());
                model.setTipo(empleo.getTipo());
                byte[] Logo = null;
                if (empleador.getLogoEmpresa() != null && empleador.getLogoEmpresa() != "") {
                    try {
                        Logo = ftpService.downloadFileFromFTP(empleador.getLogoEmpresa());
                        ftpService.disconnectFTP();
                    } catch (FTPErrors ftpErrors) {
                        ftpErrors.printStackTrace();
                    }
                }
                model.setLogo(Logo);
                //model.setLogo_base64(empleador.getLogo_base64());
                model.setDireccion(metodoUtil.isEmpty(empleo.getUbicacion()) ? "-" : empleo.getUbicacion());

                models.add(model);
                if(models.size()==6){
                    break;
                }
            }
        }
        return models;
    }


    @Override
    public List<Empleo> idsEmpleoByAlumno(AlumnoPortalEmpl alumno){
        List<Empleo> responseAlum=new ArrayList<>();
        List<Carrera> carreras = new ArrayList<>();
        carreras.add(alumno.getAlumno().getCarrera());
        List<Empleo> empleos = empleoRepository.findByCarrerasAndVerificado(carreras, 1);



        if(empleos.size()>0){
            for(Integer y=10; y>0;y--) {
                for (int i = empleos.size()-1; i >=0; i--) {
                    if ( empleos.get(0).getEmpleador().getJerarquia()==y ) {
                        responseAlum.add(empleos.get(i));
                    }
                }
            }
        }
        return responseAlum;
    }

    @Override
    public void MailSemanalGrupo(List<AlumnoPortalEmpl> alumno,int inicio, int fin){
        int tope ;
        if(inicio < alumno.size()) {
            if (fin > alumno.size()) {
                tope = alumno.size();
            } else
                tope = fin;

            for (int i = inicio; i < tope; i++) {
                LOGGER.info("Envio de correo al alumno "+i);
                MailSemanalAlumno(alumno.get(i));
            }
        }
    }

    @Override
    public void MailSemanal(){
        List<AlumnoPortalEmpl> alumno = (List<AlumnoPortalEmpl>) alumnoPortalRepository.findAll();
        for(int i=0;i<alumno.size();i++) {
            MailSemanalAlumno(alumno.get(i));
        }




    }
    @Override
    public void MailSemanalAlumno(AlumnoPortalEmpl alumno){

        String correo =alumno.getAlumno().getEmail();

        List<Empleo> idempleos = idsEmpleoByAlumno(alumno);


        List<EmpleoSemanalModel> empleos = getEmpleoSemanal(idempleos);

        if (empleos.size()>0) {
            Map<String,Object> atributos= new LinkedHashMap<String,Object>();
            atributos.put("empleos",empleos);


            Map<String,byte []> mapImagesBase64=new LinkedHashMap<String,byte []>(0);
            for(int i=0;empleos.size()>i;i++){
                System.out.println("**************************************iod");
                System.out.println(empleos.get(i).getOid());
                mapImagesBase64.put(empleos.get(i).getOid(),empleos.get(i).getLogo());
            }

            mailService.sendMailHTML(
                    correo,
                    "Empleos esperando por ti en UNMSM Portal empleo ",
                    "semanal",
                    atributos,
                    mapImagesBase64);

        }
    }

}

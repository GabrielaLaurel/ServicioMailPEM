package hello.task;

import hello.accessingdata.generic.AlumnoPortalEmplRepository;
import hello.configuration.MailConfig;
import hello.entities.generic.AlumnoPortalEmpl;
import hello.services.EmpleoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Component
public class Taks {
    private static final Logger LOG =  LoggerFactory.getLogger(Taks.class);
    @Autowired
    private EmpleoService empleoService;

    @Autowired
    private AlumnoPortalEmplRepository alumnoPortalRepository;

    @Autowired
    private MailConfig mailConfig;

    private float grupos = -2;
    private int  grupo=0;
    private int  tamañoGrupo=500;
    private List<AlumnoPortalEmpl> alumno;

    /**
     * fixedRate toma encuenta el ultimo  instante de inicio
     * 5000 = 5 segundos
     * 60 000 = 1 min
     * 180 000= 3 min
     * 86400000 = 24 horas
     * 3600000= 1 hora
     */




    @Scheduled(fixedRate = 86400000)
    public void LogCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        LOG.info("La hora actual es {}",dateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        LocalDate day = LocalDate.now();
        LOG.info("Es {}", day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase());



            if(grupos == -2||day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase().equals("LUNES")) {
                alumno = (List<AlumnoPortalEmpl>) alumnoPortalRepository.findAll();
                for(int i=0 ; i<alumno.size();i++){
                    System.out.println(alumno.get(i).getCorreoAlumno()+" - "+alumno.get(i).getAlumno().getEmail());
                }
                grupos = alumno.size()/tamañoGrupo;
                grupo = 1;
            }

                LOG.info("correostotales: {} ",alumno.size());


            if(grupos > 0) {

                if (day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase().equals("MARTES")) {
                    grupo = 2;
                    if (alumno.size() > valor()) {
                        
                    LOG.info("INICIO DE ENVIO DE CORREOS DE EMPLEOS DISPONIBLE DEL DIA {} grupo {}", day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase(), grupo);
                    empleoService.MailSemanalGrupo(alumno, 0, tamañoGrupo);
                    LOG.info("FINAL DE ENVIO DE CORREOS {}", day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase());
                    } else {
                        grupos = -1;
                    }
                }
                if (day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase().equals("JUEVES")) {
                    grupo = 4;

                    if (alumno.size() > valor()) {
                       
                        LOG.info("INICIO DE ENVIO DE CORREOS DE EMPLEOS DISPONIBLE DEL DIA {} grupo {}", day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase(), grupo);
                        empleoService.MailSemanalGrupo(alumno, tamañoGrupo * (grupo - 1), tamañoGrupo * grupo);
                        LOG.info("FINAL DE ENVIO DE CORREOS {} grupo {}", day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ES")).toUpperCase(), grupo);
                        grupo++;
                    } else {
                        grupos = -1;
                    }

                }



            }
        }

        public float valor(){
            return   (grupo - 1) * (tamañoGrupo);
        }
}

package hello.commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Component
public class Metodo {
    public boolean isEmpty(String string) {
        return string==null || string.isEmpty();
    }

    public boolean isEmpty(Collection<?> lista) {
        return lista==null || lista.isEmpty();
    }

    public boolean isEmpty(Map<?,?> map) {
        return map==null || map.isEmpty();
    }

    public String toString(Object o) {
        try {
            return o.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public Long toLong(Object o) {
        try {
            return new Long(o.toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    public Integer toInteger(Object o) {
        try {
            return new Integer(o.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public String quitarCeros(String numeroFormato) {
        return toString(toLong(numeroFormato));
    }

    public String toStringJSON(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(o);
            return json;
        } catch (Exception e) {
            return "";
        }
    }

    public String formatoFecha(LocalDateTime fecha) {
        DateTimeFormatter  hourFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return fecha.format(hourFormat);
        } catch (Exception e) {
            return "";
        }
    }

    public String formatoHora(LocalDateTime fecha) {
        DateTimeFormatter  hourFormat =  DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            return fecha.format(hourFormat);
        } catch (Exception e) {
            return "";
        }
    }

    public String unirConEspacios(Object ... objs) {
        try {
            return concatenar(" ",objs);
        } catch (Exception e) {
            return "";
        }
    }

    public String unirConBarra(Object ... objs) {
        try {
            return concatenar("|",objs);
        } catch (Exception e) {
            return "";
        }
    }

    public String concatenar(String separador,Object ... objs) {
        try {
            StringBuffer  union= new StringBuffer();
            for (Object o : objs) {
                union.append(o.toString());
                union.append(separador);
            }
            return union.toString().substring(0, union.length()-1);
        } catch (Exception e) {
            return "";
        }
    }

    public String formatoNumComprobante(Object o) {
        try {
            String numeroComprobante=String.format("%08d",toLong(o));
            return numeroComprobante;
        } catch (Exception e) {
            return "00000001";
        }
    }

    public String siguienteNumeroComprobante(String numero) {
        return formatoNumComprobante(toLong(numero)+1L);
    }

    public LocalDateTime restarDias(LocalDateTime fecha,Integer dias) {
        return fecha.minus(toLong(dias), ChronoUnit.DAYS);
    }

    public LocalDateTime anteriorDia(LocalDateTime fecha) {
        return restarDias(fecha,1);

    }

    public LocalDateTime truncarDia(LocalDateTime fecha) {
        return fecha.truncatedTo(ChronoUnit.DAYS);

    }

    public LocalDateTime ToLocalDateTime(Date fecha) {
        LocalDateTime ldt = LocalDateTime.ofInstant(fecha.toInstant(),
                ZoneId.systemDefault());
        return ldt;
    }


    public void pausa(int segundos) {
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(toLong(segundos));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String formatoMonto(BigDecimal numero) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat formateador = (DecimalFormat)nf;
        formateador.applyPattern("###,###,###.00");
        return formateador.format (numero);
    }
}

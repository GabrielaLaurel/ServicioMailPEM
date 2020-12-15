package hello.accessingdata.generic;

import hello.entities.generic.Alumno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlumnoRepository extends CrudRepository<Alumno, Integer> {


}

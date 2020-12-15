package hello.accessingdata.generic;

import hello.entities.generic.AlumnoPortalEmpl;
import hello.entities.generic.Carrera;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlumnoPortalEmplRepository extends CrudRepository<AlumnoPortalEmpl, Integer> {

}

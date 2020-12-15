package hello.accessingdata.generic;

import hello.entities.generic.Carrera;
import org.springframework.data.repository.CrudRepository;



public interface CarreraRepository extends CrudRepository<Carrera, Integer> {

}

package hello.accessingdata.generic;

import hello.entities.generic.Empleador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmpleadorRepository extends CrudRepository<Empleador, Integer> {

}

package hello.accessingdata.generic;

import hello.entities.generic.Carrera;
import hello.entities.generic.Empleo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmpleoRepository extends CrudRepository<Empleo, Integer> {
    List<Empleo> findByCarrerasAndVerificado(List<Carrera> carreras, Integer verificado);

}

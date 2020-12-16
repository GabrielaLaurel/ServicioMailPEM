package hello.accessingdata.generic;

import hello.entities.generic.Facultad;
import org.springframework.data.repository.CrudRepository;

public interface FacultadRepository extends CrudRepository<Facultad, Integer> {
}

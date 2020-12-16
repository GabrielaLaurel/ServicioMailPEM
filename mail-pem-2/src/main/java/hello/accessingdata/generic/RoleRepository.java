package hello.accessingdata.generic;

import hello.entities.generic.WebRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<WebRole, Integer> {

}

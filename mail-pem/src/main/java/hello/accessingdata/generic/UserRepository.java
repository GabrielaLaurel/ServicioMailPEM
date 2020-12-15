package hello.accessingdata.generic;

import hello.entities.generic.WebUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<WebUser, Integer> {

}

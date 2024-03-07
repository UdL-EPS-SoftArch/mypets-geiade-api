package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationRepository extends CrudRepository<Location, String>, PagingAndSortingRepository<Location, String> {


}

package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DogRepository extends CrudRepository<Dog, String>, PagingAndSortingRepository<Dog, String> {
}

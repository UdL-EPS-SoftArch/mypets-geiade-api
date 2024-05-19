package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DogRepository extends CrudRepository<Dog, Long>, PagingAndSortingRepository<Dog, Long> {

    List<Dog> findByName(@Param("name") String name);
    List<Dog> findByChip(@Param("chip") String chip);

}

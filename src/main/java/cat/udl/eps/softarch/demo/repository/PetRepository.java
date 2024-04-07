package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PetRepository extends CrudRepository<Pet, Long>, PagingAndSortingRepository<Pet, Long> {

    Pet findByName(@Param("name") String name);

    Pet findByChip(@Param("chip") String name);

}

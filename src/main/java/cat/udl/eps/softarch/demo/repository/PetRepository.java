package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetRepository extends CrudRepository<Pet, String>, PagingAndSortingRepository<Pet, String> {
}

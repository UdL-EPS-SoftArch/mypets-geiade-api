package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Shelter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShelterRepository extends CrudRepository<Shelter, String>, PagingAndSortingRepository<Shelter, String> {
}
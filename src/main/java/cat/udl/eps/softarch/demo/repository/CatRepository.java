package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Cat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CatRepository extends CrudRepository<Cat, String>, PagingAndSortingRepository<Cat, String> {
}

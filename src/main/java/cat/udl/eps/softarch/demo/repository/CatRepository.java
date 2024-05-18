package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Cat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CatRepository extends CrudRepository<Cat, Long>, PagingAndSortingRepository<Cat, Long> {

    List<Cat> findByName(@Param("name") String name);
    List<Cat> findByChip(@Param("chip") String name);

}

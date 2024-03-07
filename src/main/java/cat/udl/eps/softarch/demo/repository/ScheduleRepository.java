package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScheduleRepository extends CrudRepository<Schedule, Long>, PagingAndSortingRepository<Schedule, Long> {
    
}

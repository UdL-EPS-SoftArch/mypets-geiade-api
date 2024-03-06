package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, String>, PagingAndSortingRepository<Schedule, String> {
    
}

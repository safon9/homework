package kz.greetgo.greetgo.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepo extends JpaRepository<PersonData, Long> {

    PersonData findByName(String name);

    PersonData deleteByName(String name);

    @Query(nativeQuery = true, value = "select p from PersonData p order by p.id limit :#{#flt.getLimit()} offset :#{#flt.getOffset()}")
    List<PersonData> findLimited(@Param("flt")Filter flt);
}

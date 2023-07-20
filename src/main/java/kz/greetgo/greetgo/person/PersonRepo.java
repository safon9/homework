package kz.greetgo.greetgo.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends JpaRepository<PersonData, Long> {

    PersonData findByPhoneNumber(String number);

    Optional<PersonData> findById(Long id);

    void deleteByPhoneNumber(String number);

    void deleteById(Long id);

//    @Query(nativeQuery = true, value = "select p from PersonData p order by p.id limit :#{#flt.getLimit()} offset :#{#flt.getOffset()}")
//    List<PersonData> findLimited(@Param("flt")Filter flt);
}

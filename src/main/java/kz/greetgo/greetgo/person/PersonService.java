package kz.greetgo.greetgo.person;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PersonService {

    private final PersonRepo repo;

    public PersonService(PersonRepo repo) {
        this.repo = repo;
    }

    public PersonData add(PersonData personData) {
        return repo.save(personData);
    }

    public PersonData findByNumber(String number) {
        return repo.findByPhoneNumber(number);
    }

    public PersonData findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public PersonData updateById(PersonData personData) {
        return repo.save(personData);
    }

    public List<PersonData> findFiltered(Filter filter) {
        return repo.findAll();
    }

    public void deleteByNumber(String number) {
        repo.deleteByPhoneNumber(number);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}

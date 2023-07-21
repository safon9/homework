package kz.greetgo.greetgo.person;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/person")
public class Controller {

    private final PersonService service;

    public Controller(PersonService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<PersonData> add(@RequestBody PersonData personData) {

        if (personData.getId() != null && personData.getId() != 0) {
            return new ResponseEntity("redundant param id must not be null", HttpStatus.NOT_ACCEPTABLE);
        }

        personData.setBirthDate(new Date());

        return ResponseEntity.ok(service.add(personData));
    }


    @PostMapping("/id")
    public ResponseEntity<PersonData> findById(@RequestBody Long id) {

        PersonData personData = null;

        try {
            personData = service.findById(id);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return new ResponseEntity("finding by id - " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(personData);
    }

    @PostMapping("/number")
    public ResponseEntity<PersonData> findByNumber(@RequestBody String number) {

        PersonData personData = null;

        try {
            personData = service.findByNumber(number);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return new ResponseEntity("finding by number - " + number + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(personData);
    }

    @PutMapping("/update")
    public ResponseEntity<PersonData> update(@RequestBody PersonData personData) {

        if (personData.getId() == null || personData.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (personData.getPhoneNumber() == null || personData.getPhoneNumber().trim().length() == 0) {
            return new ResponseEntity("missed param: number", HttpStatus.NOT_ACCEPTABLE);
        }

        service.updateById(personData);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("id/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {

        try {
            service.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity("deleting by id - " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("number/delete/{number}")
    public ResponseEntity deleteById(@PathVariable("number") String number) {

        try {
            service.deleteByNumber(number);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity("deleting by number - " + number + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PersonData>> filter(Filter filter) {

        List<PersonData> list = service.findAll();

        return ResponseEntity.ok(list.subList(filter.getOffset() - 1,
                filter.getLimit() >= list.size() ? (list.size() - 1) : (filter.getLimit() - 1)));
    }


}

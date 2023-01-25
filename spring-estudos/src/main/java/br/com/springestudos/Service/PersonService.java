package br.com.springestudos.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import br.com.springestudos.Controller.PersonController;
import br.com.springestudos.Exception.RequiredObjectIsNullException;
import br.com.springestudos.Exception.ResourceNotFoundException;
import br.com.springestudos.Mapper.DozerMapper;
import br.com.springestudos.Model.Person;
import br.com.springestudos.Repository.PersonRepository;
import br.com.springestudos.data.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;



@Service
public class PersonService {

    @Autowired
    PersonRepository repository;
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

       var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
       persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
       return persons;
    }

    public PersonVO findById(Long id) {



        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        PersonVO vo =  DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {

        if (person == null)
            throw new RequiredObjectIsNullException();

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public PersonVO update(PersonVO person) {

        if (person == null)
            throw new RequiredObjectIsNullException();

        logger.info("Updating one person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        repository.delete(entity);
    }

    private PersonVO mockPerson(int i) {

        PersonVO person = new PersonVO();
        person.setKey(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Some address in Brasil " + i);
        person.setGender("Male");
        return person;
    }
}

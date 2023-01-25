//package br.com.springestudos.mockito;
//
//import br.com.springestudos.Exception.RequiredObjectIsNullException;
//import br.com.springestudos.Model.Person;
//import br.com.springestudos.Repository.PersonRepository;
//import br.com.springestudos.Service.PersonService;
//import br.com.springestudos.data.vo.v1.PersonVO;
//import br.com.springestudos.mapper.MockPerson;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//public class PersonServiceTest {
//
//    MockPerson input;
//
//    @InjectMocks
//    private PersonService service;
//
//    @Mock
//    PersonRepository repository;
//
//    @BeforeEach
//    void setUpMocks() throws Exception{
//
//        input = new MockPerson();
//        MockitoAnnotations.openMocks(this);
//    }
//
//    //////////////////END POINTS TESTS//////////////////////////
//
//    @Test
//    void testFindById(){
//
//        Person entity = input.mockEntity(1);
//        entity.setId(1L);
//        when(repository.findById(1L)).thenReturn(Optional.of(entity));
//
//        var result = service.findById(1L);
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        assertNotNull(result.getLinks());
//        System.out.println(result.toString());
//        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
//        assertEquals("Addres Test1", result.getAddress());
//        assertEquals("First Name Test1", result.getFirstName());
//        assertEquals("Last Name Test1", result.getLastName());
//        assertEquals("Female", result.getGender());
//
//
//    }
//
//    @Test
//    void TestFidnAll(){
//        List<Person> list = input.mockEntityList();
//        when(repository.findAll()).thenReturn(list);
//
//        var people = service.findAll();
//        assertNotNull(people);
//        assertEquals(14, people.size());
//
//        var PersonOne = people.get(1);
//
//        assertNotNull(PersonOne);
//        assertNotNull(PersonOne.getKey());
//        assertNotNull(PersonOne.getLinks());
//
//        assertTrue(PersonOne.toString().contains("links: [</person/1>;rel=\"self\"]"));
//        assertEquals("Addres Test1", PersonOne.getAddress());
//        assertEquals("First Name Test1", PersonOne.getFirstName());
//        assertEquals("Last Name Test1", PersonOne.getLastName());
//        assertEquals("Female", PersonOne.getGender());
//    }
//
//    @Test
//    void TestCreate(){
//        Person entity = input.mockEntity(1);
//
//        Person persisted = entity;
//        persisted.setId(1L);
//
//        PersonVO vo = input.mockVO(1);
//        vo.setKey(1L);
//
//        when(repository.save(entity)).thenReturn(persisted);
//
//        var result = service.create(vo);
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        assertNotNull(result.getLinks());
//        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
//        assertEquals("Addres Test1", result.getAddress());
//        assertEquals("First Name Test1", result.getFirstName());
//        assertEquals("Last Name Test1", result.getLastName());
//        assertEquals("Female", result.getGender());
//    }
//
//    @Test
//    void TestUpdate(){
//        Person entity = input.mockEntity(1);
//        entity.setId(1L);
//
//        Person persisted = entity;
//        persisted.setId(1L);
//
//        PersonVO vo = input.mockVO(1);
//        vo.setKey(1L);
//
//        when(repository.findById(1L)).thenReturn(Optional.of(entity));
//        when(repository.save(entity)).thenReturn(persisted);
//
//        var result = service.update(vo);
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        assertNotNull(result.getLinks());
//        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
//        assertEquals("Addres Test1", result.getAddress());
//        assertEquals("First Name Test1", result.getFirstName());
//        assertEquals("Last Name Test1", result.getLastName());
//        assertEquals("Female", result.getGender());
//    }
//
//    @Test
//    void TestDelete(){
//        Person entity = input.mockEntity(1);
//        entity.setId(1L);
//        when(repository.findById(1L)).thenReturn(Optional.of(entity));
//
//         service.delete(1L);
//    }
//
//    ////////////////////NULL OBJECTS VALIDATION TESTS///////////////////////
//
//    @Test
//    void TestCreateWithNullPerson(){
//
//        Exception exception = assertThrows(RequiredObjectIsNullException.class,()->{
//            service.create(null);
//        });
//
//        String expectedMessage = "It is not allowed to persist a null object";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void TestUpdateWithNullPerson(){
//
//        Exception exception = assertThrows(RequiredObjectIsNullException.class,()->{
//            service.update(null);
//        });
//
//        String expectedMessage = "It is not allowed to persist a null object";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//}

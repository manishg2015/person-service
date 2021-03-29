package net.demo.myjava.microservices.personservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.demo.myjava.microservices.personservice.model.Person;
import net.demo.myjava.microservices.personservice.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    final PersonService personService;

    @GetMapping(value = "/{personId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Person> getPersonByPersonId(@PathVariable("personId")String personId){
        log.info("**getPersonByPersonId:" + personId);
        return personService.getPersonDetailsByNameId(personId);
    }
}

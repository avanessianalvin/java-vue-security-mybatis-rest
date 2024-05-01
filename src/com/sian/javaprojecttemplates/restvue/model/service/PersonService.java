package com.sian.javaprojecttemplates.restvue.model.service;

import com.sian.javaprojecttemplates.restvue.model.entity.Person;
import com.sian.javaprojecttemplates.restvue.model.repository.PersonDA;

import java.util.List;

public class PersonService {
    private static final PersonService personService = new PersonService();

    public static PersonService getInstance() {
        return personService;
    }

    private PersonService() {}

    public List<Person> findAllPersons() throws Exception {
        PersonDA personDA = new PersonDA();
        return personDA.findAll();
    }

    public Person findPersonById(Long id) throws Exception {
        PersonDA personDA = new PersonDA();
        return  personDA.findById(id);
    }

    public void addPerson(Person person) throws Exception {
        PersonDA personDA = new PersonDA();
        personDA.insert(person);
    }

    public void updatePerson(Person person) throws Exception {
        PersonDA personDA = new PersonDA();
        personDA.update(person);
    }

    public void removePerson(long id) throws Exception {
        PersonDA personDA = new PersonDA();
        personDA.delete(id);
    }

}
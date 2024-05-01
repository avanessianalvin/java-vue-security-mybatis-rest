package com.sian.javaprojecttemplates.restvue.rest;

import com.sian.javaprojecttemplates.restvue.model.entity.Person;
import com.sian.javaprojecttemplates.restvue.model.service.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/person")
public class PersonApi {

    @Path("findAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllPerson(){
        try {
            List<Person> personList = PersonService.getInstance().findAllPersons();
            return Response.ok(personList).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("findById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdPerson(@QueryParam("id") Long id){
        try {
            Person person = PersonService.getInstance().findPersonById(id);
            return Response.ok(person).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person){

        try {
            PersonService.getInstance().addPerson(person);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person){

        try {
            PersonService.getInstance().updatePerson(person);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("/remove")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(@QueryParam("id") Long id){

        try {
            PersonService.getInstance().removePerson(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


}

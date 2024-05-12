package com.sian.javaprojecttemplates.restvue.rest;

import com.sian.javaprojecttemplates.restvue.model.entity.Person;
import com.sian.javaprojecttemplates.restvue.model.service.PersonService;
import com.sian.javaprojecttemplates.restvue.util.Validator;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.connector.Request;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;

@Path("/person")
public class PersonApi {

    @Context
    HttpServletRequest req;


    @POST
    @Path("/login.auth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAuth(@HeaderParam("Authorization") String header) {
        if (header == null) {
            System.out.println("NO HEADDER");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String authorization = header.substring("Basic ".length());
        byte[] decodedPass = Base64.getDecoder().decode(authorization);
        String userpass = new String(decodedPass);
        String[] token = userpass.split(":");

        try {
            //req.login(token[0], token[1]);
            BasicAuthenticator basicAuthenticator = new BasicAuthenticator();
            System.out.println(req.getClass().getName());
            basicAuthenticator.login(token[0],token[1], (Request) req);


            return Response.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


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

        String errorsMessage = Validator.getErrorsMessage(person);
        if (errorsMessage.length()>0){
            return Response.status(Response.Status.BAD_REQUEST).entity(errorsMessage).build();
        }

        try {
            PersonService.getInstance().addPerson(person);
            return Response.ok().header("msg",MessageStore.PersonAdded).build();
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
        String errorsMessage = Validator.getErrorsMessage(person);
        if (errorsMessage.length()>0){
            return Response.status(Response.Status.BAD_REQUEST).entity(errorsMessage).build();
        }

        try {
            PersonService.getInstance().updatePerson(person);
            return Response.ok().header("msg",MessageStore.PersonUpdated).build();
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
            return Response.ok().header("msg",MessageStore.PersonRemoved).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}

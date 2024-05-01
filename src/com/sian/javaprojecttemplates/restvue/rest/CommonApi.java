package com.sian.javaprojecttemplates.restvue.rest;

import com.sian.javaprojecttemplates.restvue.model.entity.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/common")
public class CommonApi {

    @Context
    HttpServletRequest req;

    @GET
    @Path("/app-name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppName() {
        Map<String,String> map = new HashMap<>();
        map.put("appName","My App");
        return Response.ok(map).build();
    }

    @POST
    @Path("/login.do")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doLogin(@FormParam("username") String username, @FormParam("password") String password){

        System.out.println(username);
        Person person = new Person().setUsername(username);

        try {
            req.login(username,password);
            System.out.println("Log in ok");
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return Response.ok(person).build();
    }
}

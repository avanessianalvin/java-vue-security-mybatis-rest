package com.sian.javaprojecttemplates.rest.vue.rest;

import com.sian.javaprojecttemplates.rest.vue.model.entity.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        Person person = new Person();
        person.setName("Alvin");
        return Response.ok(person).build();
    }

    @GET
    @Path("/login.do")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doLogin(){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        try {
            req.login(username,password);
            System.out.println("login");
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}

package com.sian.javaprojecttemplates.restvue.rest;

import com.sian.javaprojecttemplates.restvue.model.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Path("/common")
public class CommonApi {

    private final static Logger logger = LogManager.getLogger(CommonApi.class);

    @Context
    HttpServletRequest req;

    @Context
    SecurityContext securityContext;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doLogin(String content){
        ObjectMapper objectMapper = new ObjectMapper();
        String username = null;
        String password = null;

        try {
            JsonNode jsonNode = objectMapper.readTree(content);
            username = jsonNode.findValue("username").asText().toLowerCase();
            password = jsonNode.findValue("password").asText();
        }catch (Exception e){
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Person person = new Person().setUsername(username);

        try {
            Principal userPrincipal = req.getUserPrincipal();
            System.out.println(userPrincipal);

            if (userPrincipal==null) {
                req.login(username, password);
                HttpSession session = req.getSession(true);
                session.setAttribute("a", "a");
                logger.info(username + " is logged in");
                return Response.ok(person).build();
            }else {
                if (userPrincipal.getName().equals(username)) {
                    logger.info(username + "already logged in" );
                    return Response.ok("Already logged in.").build();
                }else {
                    req.logout();
                    logger.info(userPrincipal.getName() + " is logged out due to new login by " + username);
                    //req.getSession().invalidate(); // when invalidating login is ok but jsessionid id changed and the rest requests will not work!
                    // another way is to invalidate, then another blank request to server to get new jsessionid then login again.

                    req.login(username, password);
                    HttpSession session = req.getSession(true);
                    session.setAttribute("a", "a");

                    logger.info(username + " is logged in");
                    return Response.ok(person).build();
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/logout.do")
    public Response doLogout(){

        try {
            req.logout();
            req.getSession().invalidate();
            System.out.println("is logged OUT!");
            return Response.ok().build();
        } catch (ServletException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/is-logged")
    public Response isUserLogged(){
        Principal userPrincipal = req.getUserPrincipal();
        if (userPrincipal!=null){
            System.out.println("is logged");
            return Response.ok().build();
        }else {
            System.out.println("is not logged");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}

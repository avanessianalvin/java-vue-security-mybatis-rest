package com.sian.javaprojecttemplates.rest.vue.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/admin")
public class AdminApi {

    @GET
    @Path("/is-logged")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isAdminLogged(){
        Map<String, String> map = new HashMap<>();
        map.put("logged","true");
        return Response.ok(map).build();
    }

}

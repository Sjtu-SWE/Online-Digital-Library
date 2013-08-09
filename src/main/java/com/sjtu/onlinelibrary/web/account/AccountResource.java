package com.sjtu.onlinelibrary.web.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjtu.onlinelibrary.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author Huabei Yin <a href="mailto:huabei.yin@autodesk.com">huabei.yin@autodesk.com</a>
 */
@Component
@Path("account")
@Scope("request")
public class AccountResource {
    private static final ObjectMapper OM = new ObjectMapper();

    @GET
    @Path("/{id}")
    public Response getUserInfo(@Context final HttpServletRequest servletRequest, @PathParam("id") final String id) throws JsonProcessingException {
        return Response.ok(OM.writeValueAsString(new User())).build();
    }

    @GET
    @Path("/test")
    public Response test() {
        return Response.ok("the rest api is working.").build();
    }
}

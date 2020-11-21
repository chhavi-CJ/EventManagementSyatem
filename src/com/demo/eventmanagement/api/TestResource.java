package com.demo.eventmanagement.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
public class TestResource {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getMessage() {

    //TODO add logger
    System.out.println("testing api");
    return "pong";
  }
}

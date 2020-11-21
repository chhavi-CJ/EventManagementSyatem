package com.demo.eventmanagement.api;

import com.demo.eventmanagement.api.request.EventRequest;
import com.demo.eventmanagement.db.dto.Event;
import com.demo.eventmanagement.service.EventService;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/events")
public class EventResource {

  @GET
  @Produces("application/json")
  public Map<String, List<Event>> getEvents(EventRequest eventRequest) {
    EventService eventService = new EventService();
    Map<String, List<Event>> events = eventService.getEventsByDate(eventRequest);
    System.out.println(events);
    return events;
  }

  @POST
  @Path("/register")
  @Produces("application/json")
  @Consumes("application/json")
  public Response addEvent(Event event) {
    EventService eventService = new EventService();
    boolean result = eventService.registerEvent(event);

    System.out.println(result);

    if (result) {
      return Response.ok(event).build();
    } else {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }

  @POST
  @Path("/{event_id}/users/register")
  @Produces("application/json")
  @Consumes("application/json")
  public Response registerUser(@PathParam("event_id") String eventId, String[] users) {
    EventService eventService = new EventService();
    Event event = eventService.registerUserForEvent(eventId, users);

    System.out.println(event);

    if (event == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    } else {
      return Response.ok(event).build();
    }
  }

  @PUT
  @Path("/{event_id}")
  @Consumes("application/json")
  public Response updateEvent(@PathParam("event_id") long eventId, Event event) {

    EventService eventService = new EventService();
    int count = eventService.updateEvent(eventId, event);
    System.out.println(count);

    if (count == 0) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    return Response.ok(count).build();
  }

  @DELETE
  @Path("/{event_id}")
  @Consumes("application/json")
  public Response deleteEvent(@PathParam("event_id") long eventId) {

    EventService eventService = new EventService();
    int count = eventService.deleteEvent(eventId);
    System.out.println(count);

    if (count == 0) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    return Response.ok(count).build();
  }
}

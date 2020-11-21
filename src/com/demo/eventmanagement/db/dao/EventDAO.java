package com.demo.eventmanagement.db.dao;

import com.demo.eventmanagement.api.request.EventRequest;
import com.demo.eventmanagement.db.connector.H2DatabaseClient;
import com.demo.eventmanagement.db.dto.Event;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.h2.util.StringUtils;

public class EventDAO {

  //TODO replace field names
  private static final String EVENTS_TABLE = "events";
  private static final String EVENT_ID = "EventID";
  private static final String EVENT_TITLE = "Title";
  private static final String EVENT_DESCRIPTION = "Description";
  private static final String EVENT_START_DATE = "StartDate";
  private static final String EVENT_COST = "Cost";
  private static final String LOCATION_ID = "LocationID";

  public List<Event> getEvent(String eventIdReq) {
    String query = "Select * from EVENT where event_id = " + eventIdReq;
    return getAllEvents(query);
  }

  public List<Event> getAllEvents() {
    String query = "Select * from EVENT";
    return getAllEvents(query);
  }


  public List<Event> getAllEvents(EventRequest eventRequest) {
    String whereQuery = getWhereQuery(eventRequest);
    String query = "Select * from EVENT " + whereQuery;
    return getAllEvents(query);
  }

  private String getWhereQuery(EventRequest eventRequest) {
    boolean isWhereQueryEmpty = true;
    StringBuilder query = new StringBuilder();
    String startDate = eventRequest.getStartDate();
    String endDate = eventRequest.getEndDate();

    if (!(StringUtils.isNullOrEmpty(startDate) || StringUtils.isNullOrEmpty(endDate))) {
      query.append(" event_date BETWEEN '").append(startDate).append("' AND '").append(endDate).append("' ");
      isWhereQueryEmpty = false;
    }

    String venue = eventRequest.getVenue();
    if (!StringUtils.isNullOrEmpty(venue)) {
      if (!isWhereQueryEmpty) {
        query.append(" AND ");
      }
      query.append(" venue = '").append(venue).append("' ");
      isWhereQueryEmpty = false;
    }

    String queryString = query.toString();
    if (!isWhereQueryEmpty) {
      return " WHERE " + queryString;
    } else {
      return "";
    }

  }

  public List<Event> getAllEvents(String query) {
    H2DatabaseClient databaseClient = new H2DatabaseClient();
    ResultSet resultSet = databaseClient.executeSelect(query);

    List<Event> eventList = new ArrayList<>();
    try {
      while (resultSet.next()) {
        long eventId = resultSet.getLong("event_id");
        String eventName = resultSet.getString("event_name");
        String speaker = resultSet.getString("speaker");
        String eventDate = resultSet.getString("event_date");
        String venue = resultSet.getString("venue");

        Event obj = new Event(eventId, eventName, speaker, eventDate, venue);

        Event eventWithUsers = addUsers(obj);
        eventList.add(eventWithUsers);
      }
      System.out.println("eventList: ");
      System.out.println(eventList);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return eventList;
  }

  private Event addUsers(Event obj) {
    UserDAO userDAO = new UserDAO();
    List<String> users = userDAO.getUsers(obj.getEventId());
    for (String user : users) {
      obj.addUser(user);
    }
    return obj;
  }

  public Event addEvent(Event event) {
    H2DatabaseClient databaseClient = new H2DatabaseClient();
    try {

      PreparedStatement ps = databaseClient.getPreparedStatement("INSERT INTO event (event_name, speaker, event_date, venue) VALUES(?,?,?,?)");

      java.sql.Date eventDate = Date.valueOf(event.getEventDate());
      ps.setString(1, event.getEventName());
      ps.setString(2, event.getSpeaker());
      ps.setDate(3, eventDate);
      ps.setString(4, event.getVenue());

      int count = ps.executeUpdate();
      System.out.println("Event inserted: " + count);

    } catch (Exception e) {
      System.out.print("Unable to insert Event in db: " + e.getMessage());
      e.printStackTrace();
      return null;
    }

    return event;
  }

}

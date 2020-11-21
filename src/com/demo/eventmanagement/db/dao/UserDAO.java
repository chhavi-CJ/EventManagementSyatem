package com.demo.eventmanagement.db.dao;

import com.demo.eventmanagement.db.connector.H2DatabaseClient;
import com.demo.eventmanagement.db.dto.Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

  public List<String> getUsers(long eventId) {
    H2DatabaseClient databaseClient = new H2DatabaseClient();

    String query = "Select username from event_user where event_id = " + eventId;
    ResultSet resultSet = databaseClient.executeSelect(query);

    List<String> users = new ArrayList<>();
    try {
      while (resultSet.next()) {
        String username = resultSet.getString("username");
        users.add(username);
      }
      System.out.println("users: ");
      System.out.println(users);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return users;
  }

  public Event addUserToEvent(String eventId, String[] users) {
    EventDAO eventDAO = new EventDAO();
    List<Event> eventList = eventDAO.getEvent(eventId);

    if (eventList == null || eventList.size() == 0) {
      System.out.println("Event with eventId: " + eventId + " doesn't exists.");
      return null;
    }

    for (String user : users) {
      String username = addUser(eventId, user);
      eventList.get(0).addUser(username);
      System.out.println("Username: " + username + " added successfully in eventId: " + eventId);
    }
    return eventList.get(0);
  }

  private String addUser(String eventId, String username) {
    H2DatabaseClient databaseClient = new H2DatabaseClient();
    try {

      PreparedStatement ps = databaseClient.getPreparedStatement("INSERT INTO event_user (event_id, username) VALUES(?,?)");

      ps.setLong(1, Long.parseLong(eventId));
      ps.setString(2, username);

      int count = ps.executeUpdate();
      System.out.println("username inserted: " + count);

    } catch (Exception e) {
      System.out.print("Unable to insert userName in db: " + e.getMessage());
      e.printStackTrace();
      return null;
    }

    return username;
  }

}

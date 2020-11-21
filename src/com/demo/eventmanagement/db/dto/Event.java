package com.demo.eventmanagement.db.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

  private long eventId;
  private String eventName;
  private String speaker;
  private String eventDate;
  private String venue;
  private List<String> users = new ArrayList<>();

  public Event() {
  }

  public Event(long eventId, String eventName, String speaker, String eventDate, String venue) {
    this.eventId = eventId;
    this.eventName = eventName;
    this.speaker = speaker;
    this.eventDate = eventDate;
    this.venue = venue;
  }

  public long getEventId() {
    return eventId;
  }

  public void setEventId(long eventId) {
    this.eventId = eventId;
  }

  public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getSpeaker() {
    return speaker;
  }

  public void setSpeaker(String speaker) {
    this.speaker = speaker;
  }

  public void addUser(String user) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(user);
  }

  @Override
  public String toString() {
    return "Event{" +
        "eventId=" + eventId +
        ", eventName='" + eventName + '\'' +
        ", speaker='" + speaker + '\'' +
        ", eventDate='" + eventDate + '\'' +
        ", venue='" + venue + '\'' +
        ", users=" + users +
        '}';
  }
}

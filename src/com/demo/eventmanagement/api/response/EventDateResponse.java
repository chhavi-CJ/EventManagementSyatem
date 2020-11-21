package com.demo.eventmanagement.api.response;


import com.demo.eventmanagement.db.dto.Event;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "eventsByDate")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDateResponse {
  String date;
  List<Event> eventList;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<Event> getEventList() {
    return eventList;
  }

  public void setEventList(List<Event> eventList) {
    this.eventList = eventList;
  }

  @Override
  public String toString() {
    return "EventsByDateResponse{" +
        "date='" + date + '\'' +
        ", eventList=" + eventList +
        '}';
  }
}

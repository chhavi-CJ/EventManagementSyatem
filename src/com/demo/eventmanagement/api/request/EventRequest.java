package com.demo.eventmanagement.api.request;

public class EventRequest {
  String startDate;
  String endDate;
  String venue;

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  @Override
  public String toString() {
    return "EventRequest{" +
        "startDate='" + startDate + '\'' +
        ", endDate='" + endDate + '\'' +
        ", venue='" + venue + '\'' +
        '}';
  }
}


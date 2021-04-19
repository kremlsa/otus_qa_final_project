package finalProject.pages;

import java.util.ArrayList;

public class EventCard {

    private String place = "Not defined";
    private String eventName = "Not defined";
    private String date = "Not defined";
    private String registration = "Not defined";
    private final ArrayList<Speaker> speakers = new ArrayList<>();

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getSpeakers() {
        return speakers.toString();
    }

    public void addSpeakers(Speaker speaker) {
        this.speakers.add(speaker);
    }
}

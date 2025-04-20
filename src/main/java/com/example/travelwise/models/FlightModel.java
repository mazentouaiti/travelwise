package com.example.travelwise.models;

import java.util.Date;



public class FlightModel {
    private int flight_id;
    private String airline, origin, destination, classType, status,flight_number;
    private java.sql.Date departureDate, returnDate;
    private double price;

    public FlightModel(int flight_id, String airline, String origin, String destination, String classType, String status, String flight_number,
                       java.sql.Date departureDate, java.sql.Date returnDate, double price) {
        this.flight_id = flight_id;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.classType = classType;
        this.status = status;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.flight_number = flight_number;

    }
    public FlightModel(String airline, String origin, String destination, String classType, String status,String flight_number,
                       java.sql.Date departureDate, java.sql.Date returnDate, double price) {
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.classType = classType;
        this.status = status;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.flight_number = flight_number;

    }

    public FlightModel() {}
    public int getFlight_id() {
        return flight_id;
    }
    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }
    public String getAirline() {
        return airline;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getClassType() {
        return classType;
    }
    public void setClassType(String classType) {
        this.classType = classType;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFlightNumber() {
        return flight_number;
    }
    public void setFlightNumber(String flight_number) {
        this.flight_number = flight_number;
    }
    public java.sql.Date getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(Date departureDate) {
        this.departureDate = (java.sql.Date) departureDate;
    }

    public java.sql.Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = (java.sql.Date) returnDate;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight {" +
                "ID=" + flight_id +
                ", Flight Number='" + flight_number + '\'' +
                ", Airline='" + airline + '\'' +
                ", Origin='" + origin + '\'' +
                ", Destination='" + destination + '\'' +
                ", Class Type='" + classType + '\'' +
                ", Status='" + status + '\'' +
                ", Departure Date=" + departureDate +
                ", Return Date=" + returnDate +
                ", Price=" + price +
                '}';
    }

}

package com.example.travelwise.Services;
import com.example.travelwise.models.FlightModel;
import java.util.List;
public interface Services {
    void addFlight(FlightModel flight);
    void updateFlight(FlightModel flight);
    void deleteFlight(int id);
    List<FlightModel> getAllFlights();
}

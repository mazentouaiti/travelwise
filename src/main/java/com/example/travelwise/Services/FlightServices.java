package com.example.travelwise.Services;

import com.example.travelwise.Utiles.DBConnection;
import com.example.travelwise.models.FlightModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FlightServices implements Services{
    private Connection connection;
    public FlightServices() {connection=DBConnection.getInstance().getConnection();}
    // ✅ CREATE
    public void addFlight(FlightModel flight) {
        String sql = "INSERT INTO flights (flight_number, airline, origin, destination, departure_date, return_date, class_type, status, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getAirline());
            ps.setString(3, flight.getOrigin());
            ps.setString(4, flight.getDestination());
            ps.setDate(5, flight.getDepartureDate());
            ps.setDate(6, flight.getReturnDate());
            ps.setString(7, flight.getClassType());
            ps.setString(8, flight.getStatus());
            ps.setDouble(9, flight.getPrice());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // ✅ READ
    public  List<FlightModel> getAllFlights()  {
        List<FlightModel> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                FlightModel f = new FlightModel();
                f.setFlight_id(rs.getInt("id"));
                f.setFlightNumber(rs.getString("flight_number"));
                f.setAirline(rs.getString("airline"));
                f.setOrigin(rs.getString("origin"));
                f.setDestination(rs.getString("destination"));
                f.setDepartureDate(rs.getDate("departure_date"));
                f.setReturnDate(rs.getDate("return_date"));
                f.setClassType(rs.getString("class_type"));
                f.setStatus(rs.getString("status"));
                f.setPrice(rs.getDouble("price"));
                flights.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }

    // ✅ UPDATE
    public void updateFlight(FlightModel flight)  {
        String sql = "UPDATE flights SET flight_number=?, airline=?, origin=?, destination=?, departure_date=?, return_date=?, class_type=?, status=?, price=? WHERE id=?";
        try  {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getAirline());
            ps.setString(3, flight.getOrigin());
            ps.setString(4, flight.getDestination());
            ps.setDate(5, flight.getDepartureDate());
            ps.setDate(6, flight.getReturnDate());
            ps.setString(7, flight.getClassType());
            ps.setString(8, flight.getStatus());
            ps.setDouble(9, flight.getPrice());
            ps.setInt(10, flight.getFlight_id());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // ✅ DELETE
    public void deleteFlight(int id)  {
        String sql = "DELETE FROM flights WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

package com.example.travelwise.Services;

import com.example.travelwise.Utiles.DBConnection;
import com.example.travelwise.models.FlightModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FlightServices {
    // ✅ CREATE
    public void addFlight(FlightModel flight) throws SQLException {
        String sql = "INSERT INTO flights (flight_number, airline, origin, destination, departure_date, return_date, class_type, status, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
        }
    }

    // ✅ READ
    public static List<FlightModel> getAllFlights() throws SQLException {
        List<FlightModel> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";

        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
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
        }

        return flights;
    }

    // ✅ UPDATE
    public void updateFlight(FlightModel flight) throws SQLException {
        String sql = "UPDATE flights SET flight_number=?, airline=?, origin=?, destination=?, departure_date=?, return_date=?, class_type=?, status=?, price=? WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
        }
    }

    // ✅ DELETE
    public void deleteFlight(int id) throws SQLException {
        String sql = "DELETE FROM flights WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

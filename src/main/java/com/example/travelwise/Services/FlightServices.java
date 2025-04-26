package com.example.travelwise.Services;

import com.example.travelwise.Utiles.DBConnection;
import com.example.travelwise.models.FlightModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FlightServices implements Services{
    private Connection connection;
    public FlightServices() {connection=DBConnection.getInstance().getConnection();}
    // ✅ CREATE
    public void addFlight(FlightModel flight) {
        String sql = "INSERT INTO flights (flight_number, airline, origin, destination, departureDate, return_date, class_type, status, price,capacity ,admin_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
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
            ps.setInt(10,flight.getCapacity());
            ps.setString(11, "pending");
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
                f.setFlight_id(rs.getInt("flight_id"));
                f.setFlightNumber(rs.getString("flight_number"));
                f.setAirline(rs.getString("airline"));
                f.setOrigin(rs.getString("origin"));
                f.setDestination(rs.getString("destination"));
                f.setDepartureDate(rs.getDate("departureDate"));
                f.setReturnDate(rs.getDate("return_date"));
                f.setClassType(rs.getString("class_type"));
                f.setStatus(rs.getString("status"));
                f.setPrice(rs.getDouble("price"));
                f.setCapacity(rs.getInt("capacity"));
                f.setAdminStatus(rs.getString("admin_status"));
                flights.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }

    // ✅ UPDATE
    public void updateFlight(FlightModel flight)  {
        String sql = "UPDATE flights SET flight_number=?, airline=?, origin=?, destination=?, departureDate=?, return_date=?, class_type=?, status=?, price=? , capacity=? WHERE flight_id=?";
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
            ps.setInt(10, flight.getCapacity());
            ps.setInt(11, flight.getFlight_id());

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // ✅ DELETE
    public void deleteFlight(int id)  {
        String sql = "DELETE FROM flights WHERE flight_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<FlightModel> searchFlights(String departure, String destination,
                                           Date departureDate,
                                           String priceFilter) {
        List<FlightModel> flights = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM flights WHERE admin_status = 'approved'");

        // Add conditions based on parameters
        if (departure != null) {
            sql.append(" AND origin LIKE ?");
        }
        if (destination != null) {
            sql.append(" AND destination LIKE ?");
        }
        if (departureDate != null) {
            sql.append(" AND departureDate = ?");
        }

        if (priceFilter != null && !priceFilter.equals("All")) {
            switch (priceFilter) {
                case "Under 100€":
                    sql.append(" AND price < 100");
                    break;
                case "100–300€":
                    sql.append(" AND price BETWEEN 100 AND 300");
                    break;
                case "Above 300€":
                    sql.append(" AND price > 300");
                    break;
            }
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            if (departure != null) {
                ps.setString(paramIndex++, "%" + departure + "%");
            }
            if (destination != null) {
                ps.setString(paramIndex++, "%" + destination + "%");
            }
            if (departureDate != null) {
                ps.setDate(paramIndex++, departureDate);
            }


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlightModel flight = new FlightModel();
                flight.setFlight_id(rs.getInt("flight_id"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setAirline(rs.getString("airline"));
                flight.setOrigin(rs.getString("origin"));
                flight.setDestination(rs.getString("destination"));
                flight.setDepartureDate(rs.getDate("departureDate"));
                flight.setReturnDate(rs.getDate("return_date"));
                flight.setClassType(rs.getString("class_type"));
                flight.setStatus(rs.getString("status"));
                flight.setPrice(rs.getDouble("price"));
                flight.setCapacity(rs.getInt("capacity"));
                flight.setAdminStatus(rs.getString("admin_status"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            System.out.println("Error searching flights: " + e.getMessage());
        }
        return flights;
    }
    public void approveFlight(int flightId) {
        String sql = "UPDATE flights SET admin_status = 'approved' WHERE flight_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, flightId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error approving flight: " + e.getMessage());
        }
    }

    public void rejectFlight(int flightId) {
        String sql = "UPDATE flights SET admin_status = 'rejected' WHERE flight_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, flightId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error rejecting flight: " + e.getMessage());
        }
    }

}

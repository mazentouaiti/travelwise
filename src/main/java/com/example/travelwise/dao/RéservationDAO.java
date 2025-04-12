package com.example.travelwise.dao;
import com.example.travelwise.database.DatabaseConnection;
import com.example.travelwise.models.Réservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RéservationDAO {

    public void ajouterRéservation(Réservation reservation) {
        String sql = "INSERT INTO Réservation (utilisateur_id, voyage_id, statut, dateReservation, montantTotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getUtilisateurId());
            stmt.setInt(2, reservation.getVoyageId());
            stmt.setString(3, reservation.getStatut());
            stmt.setTimestamp(4, new Timestamp(reservation.getDateReservation().getTime()));
            stmt.setDouble(5, reservation.getMontantTotal());
            stmt.executeUpdate();
            System.out.println("Réservation ajoutée !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Réservation> getToutesLesRéservations() {
        List<Réservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Réservation";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservations.add(new Réservation(
                        rs.getInt("id"),
                        rs.getInt("utilisateur_id"),
                        rs.getInt("voyage_id"),
                        rs.getString("statut"),
                        rs.getTimestamp("dateReservation"),
                        rs.getDouble("montantTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}

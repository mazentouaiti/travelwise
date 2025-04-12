package com.example.travelwise.dao;
import com.example.travelwise.models.Réservation;
import java.util.Date;

public class TestRéservationDAO {
    public static void main(String[] args) {
        RéservationDAO reservationDAO = new RéservationDAO();

        Réservation reservation = new Réservation(0, 1, 2, "confirmé", new Date(), 500.00);
        reservationDAO.ajouterRéservation(reservation);

        for (Réservation r : reservationDAO.getToutesLesRéservations()) {
            System.out.println(r.getId() + " - " + r.getStatut() + " - " + r.getMontantTotal());
        }
    }
}

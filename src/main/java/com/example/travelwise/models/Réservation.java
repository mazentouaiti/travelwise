package com.example.travelwise.models;
import java.util.Date;

public class Réservation {
    private int id;
    private int utilisateurId;
    private int voyageId;
    private String statut;
    private Date dateReservation;
    private double montantTotal;

    public Réservation(int id, int utilisateurId, int voyageId, String statut, Date dateReservation, double montantTotal) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.voyageId = voyageId;
        this.statut = statut;
        this.dateReservation = dateReservation;
        this.montantTotal = montantTotal;
    }

    // Getters & Setters...
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUtilisateurId() {
        return utilisateurId;
    }
    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    public int getVoyageId() {
        return voyageId;
    }
    public void setVoyageId(int voyageId) {
        this.voyageId = voyageId;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public Date getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }
    public double getMontantTotal() {
        return montantTotal;
    }
    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

}

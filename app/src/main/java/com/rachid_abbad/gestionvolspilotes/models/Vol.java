package com.rachid_abbad.gestionvolspilotes.models;

public class Vol {
    String numVol, societe, date, numMatricule;

    public Vol() {
    }

    public Vol(String numVol, String societe, String date) {
        this.numVol = numVol;
        this.societe = societe;
        this.date = date;
    }

    public Vol(String numVol, String societe, String date, String numMatricule) {
        this.numVol = numVol;
        this.societe = societe;
        this.date = date;
        this.numMatricule = numMatricule;
    }

    public String getNumVol() {
        return numVol;
    }

    public void setNumVol(String numVol) {
        this.numVol = numVol;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumMatricule() {
        return numMatricule;
    }

    public void setNumMatricule(String numMatricule) {
        this.numMatricule = numMatricule;
    }
}

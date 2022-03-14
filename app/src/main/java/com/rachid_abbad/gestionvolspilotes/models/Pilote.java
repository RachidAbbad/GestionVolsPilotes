package com.rachid_abbad.gestionvolspilotes.models;

public class Pilote {
    private String matricule,name;

    public Pilote(String matricule, String name) {
        this.matricule = matricule;
        this.name = name;
    }

    public Pilote() {
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

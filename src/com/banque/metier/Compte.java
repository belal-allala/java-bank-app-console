package com.banque.metier;

import java.util.ArrayList;

public abstract class Compte {

    private final String code;
    protected double solde;
    protected ArrayList<Operation> listeOperations;

    public Compte(String code, double solde){
        this.code=code;
        this.solde=solde;
        this.listeOperations= new ArrayList<>();
    }

    public void verser(double montant, String source){
        this.solde+=montant;
        Versement v = new Versement(montant, source);
        this.listeOperations.add(v);


    }

    public abstract void retirer(double montant, String destination);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    public String getCode(){
        return code ;
    }

    public double getSolde(){
        return solde;
    }

    public ArrayList<Operation> getListeOperations() {
        return listeOperations;
    }
}

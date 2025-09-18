package com.banque.metier;

public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant(String code, double soldeInitial, double decouvert){
        super(code, soldeInitial);
        this.decouvert=decouvert;
    }

    public void retirer(double montant, String destination){
        if (this.solde - montant >= -this.decouvert){
            this.solde -= montant;
            Retrait r = new Retrait(montant, destination);
            this.listeOperations.add(r);
        } else {
            System.out.println("Opération impossible : Solde insuffisant et découvert dépassé.");
        }
    }

    public double calculerInteret(){
        return 0;
    }
    
    public double getDecouvert(){
        return decouvert;
    }

    public void afficherDetails() {
        System.out.println("--- Détails du Compte Courant ---");
        System.out.println("Code du compte : " + this.getCode());
        System.out.println("Solde actuel : " + String.format("%.2f", this.getSolde()) + " EUR");
        System.out.println("Découvert autorisé : " + String.format("%.2f", this.decouvert) + " EUR");
        System.out.println("---------------------------------");
    }
}

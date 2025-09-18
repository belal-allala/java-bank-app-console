package com.banque.metier;

public class CompteEpargne extends Compte {
    
    private double tauxInteret;

    public CompteEpargne(String code, double soldeInitial, double tauxInteret){
        super(code, soldeInitial);
        this.tauxInteret= tauxInteret;
    }

    public void retirer(double montant, String destination){

        if(this.solde>=montant){
            this.solde-=montant;
            Retrait r = new Retrait(montant, destination);
            this.listeOperations.add(r);
        }else{
            System.out.println("Opération impossible : Solde insuffisant.");
        }
    }

    public double calculerInteret(){
        return this.solde*this.tauxInteret;
    }

    public void afficherDetails(){
        System.out.println("--- Détails du Compte Épargne ---");
        System.out.println("Code du compte : " + this.getCode());
        System.out.println("Solde actuel : " + String.format("%.2f", this.getSolde()) + " EUR");
        System.out.println("Taux d'intérêt : " + (this.tauxInteret * 100) + " %");
        System.out.println("Intérêts calculés : " + String.format("%.2f", this.calculerInteret()) + " EUR");
        System.out.println("----------------------------------");
    }

    public double getTauxInteret(){
        return tauxInteret;
    }
}

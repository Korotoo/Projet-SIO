package fr.pgah.Model;

import javafx.scene.control.TextField;

//import java.sql.Date;

public class FicheDeFrais {
    private int nuitees;
    private int repas;
    private int km;
    private double montant1;
    private String libellé1;

    // private Date Date;

    public void setNuitees(int nuitees) {
        this.nuitees = nuitees;
    }

    public void setRepas(int repas) {
        this.repas = repas;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public void setMontant1(double montant1) {
        this.montant1 = montant1;
    }

    public void setLibellé1(String libellé1) {
        this.libellé1 = libellé1;
    }

    public FicheDeFrais(int nuitees, int repas, int km, double montant1, String libellé1) {
        this.nuitees = nuitees;
        this.repas = repas;
        this.km = km;
        this.montant1 = montant1;
        this.libellé1 = libellé1;

    }

    public double getMontant1() {

        return montant1;
    }

    public String getLibellé1() {
        return libellé1;
    }

    public int getNuitees() {

        return nuitees;

    }

    public int getRepas() {

        return repas;

    }

    public int getkm() {

        return km;
    }

}

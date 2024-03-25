package fr.pgah.Model;

public class FicheDeFrais {
    private int nuitees;
    private int repas;
    private int km;

    public FicheDeFrais(int nuitees, int repas, int km) {
        this.nuitees = nuitees;
        this.repas = repas;
        this.km = km;
    }

    public String getNuitees() {
        String nuiteesStr = Integer.toString(nuitees);
        return nuiteesStr;

    }

    public String getRepas() {
        String repasStr = Integer.toString(repas);
        return repasStr;

    }

    public String getkm() {
        String kmStr = Integer.toString(km);
        return kmStr;
    }
}

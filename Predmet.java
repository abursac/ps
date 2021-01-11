/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Aleksandar
 */
public class Predmet implements Serializable 
{
    private int predmetID;
    private String naziv;
    private String problem;
    private Date datum;
    private Advokat advokat;
    private VrstaPostupka vp;
    private Klijent klijent;

    public Predmet() {
    }

    public Predmet(int predmetID, String naziv, String problem, Date datum, Advokat advokat, VrstaPostupka vp, Klijent klijent) {
        this.predmetID = predmetID;
        this.naziv = naziv;
        this.problem = problem;
        this.datum = datum;
        this.advokat = advokat;
        this.vp = vp;
        this.klijent = klijent;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public int getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(int predmetID) {
        this.predmetID = predmetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Advokat getAdvokat() {
        return advokat;
    }

    public void setAdvokat(Advokat advokat) {
        this.advokat = advokat;
    }

    public VrstaPostupka getVp() {
        return vp;
    }

    public void setVp(VrstaPostupka vp) {
        this.vp = vp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Predmet other = (Predmet) obj;
        if (this.predmetID != other.predmetID) {
            return false;
        }
        return true;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Aleksandar
 */
public class Advokat implements Serializable
{
    private int advokatID;
    private String ime;
    private String prezime;
    private VrstaPostupka specijalnost;

    public Advokat() {
    }

    public Advokat(int advokatID, String ime, String prezime, VrstaPostupka specijalnost) {
        this.advokatID = advokatID;
        this.ime = ime;
        this.prezime = prezime;
        this.specijalnost = specijalnost;
    }

    public VrstaPostupka getSpecijalnost() {
        return specijalnost;
    }

    public void setSpecijalnost(VrstaPostupka specijalnost) {
        this.specijalnost = specijalnost;
    }

    public int getAdvokatID() {
        return advokatID;
    }

    public void setAdvokatID(int advokatID) {
        this.advokatID = advokatID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Advokat other = (Advokat) obj;
        if (this.advokatID != other.advokatID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
}

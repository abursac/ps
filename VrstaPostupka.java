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
public class VrstaPostupka implements Serializable 
{
    private int vrstaPostupkaID;
    private String naziv;

    public VrstaPostupka() {
    }

    public VrstaPostupka(int vrstaPostupkaID, String naziv) {
        this.vrstaPostupkaID = vrstaPostupkaID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrstaPostupkaID() {
        return vrstaPostupkaID;
    }

    public void setVrstaPostupkaID(int vrstaPostupkaID) {
        this.vrstaPostupkaID = vrstaPostupkaID;
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
        final VrstaPostupka other = (VrstaPostupka) obj;
        if (this.vrstaPostupkaID != other.vrstaPostupkaID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        return naziv;
    }
    
    
}

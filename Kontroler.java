/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Advokat;
import domen.Klijent;
import domen.Predmet;
import domen.VrstaPostupka;
import java.sql.SQLException;
import java.util.ArrayList;
import pomocne.PretragaKlasa;

/**
 *
 * @author Aleksandar
 */
public class Kontroler 
{
    private static Kontroler instance;
    DBBroker db;

    private Kontroler() 
    {
        db = new DBBroker();
    }

    public static Kontroler getInstance() 
    {
        if(instance == null)
            instance = new Kontroler();
        return instance;
    }

    public ArrayList<Klijent> vratiKlijente() 
    {
        db.otvoriKonekciju();
        ArrayList<Klijent> lista = db.vratiListu();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<VrstaPostupka> vratiVrste() 
    {
        db.otvoriKonekciju();
        ArrayList<VrstaPostupka> lista = db.vratiVrstePostupka();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Advokat> vratiAdvokate() 
    {
        db.otvoriKonekciju();
        ArrayList<Advokat> lista = db.vratiAdvokate();
        db.zatvoriKonekciju();
        return lista;
    }

    public boolean sacuvajPredmete(ArrayList<Predmet> listaPredmeta) 
    {
        boolean sacuvano = false;
        db.otvoriKonekciju();
        try{
            for(Predmet predmet : listaPredmeta)
            {
                int predmetID = db.vratiIDPredmeta();
                predmet.setPredmetID(predmetID);
                db.sacuvajPredmet(predmet);
            }
            db.commit();
            sacuvano = true;
        }catch(SQLException ex){
            db.rollback();
        }
        db.zatvoriKonekciju();
        return sacuvano;
    }

    public ArrayList<PretragaKlasa> vratiPretragu(String pretraga) 
    {
        db.otvoriKonekciju();
        ArrayList<PretragaKlasa> lista = db.vratiPretragu(pretraga);
        db.zatvoriKonekciju();
        return lista;
    }
    
    
}

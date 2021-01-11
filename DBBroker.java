/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Advokat;
import domen.Klijent;
import domen.Predmet;
import domen.VrstaPostupka;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pomocne.PretragaKlasa;

/**
 *
 * @author Aleksandar
 */
public class DBBroker 
{
    Connection konekcija;
    
    public void ucitajDrajver()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void otvoriKonekciju()
    {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/prosoftmart19", "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void zatvoriKonekciju()
    {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit()
    {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback()
    {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Klijent> vratiListu() 
    {
        ArrayList<Klijent> lista = new ArrayList<>();
        String sql = "SELECT * FROM klijent";
        
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next())
            {
                Klijent k = new Klijent(rs.getInt("KlijentID"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("Telefon"), rs.getString("ElPosta"), rs.getString("Adresa"));
                lista.add(k);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<VrstaPostupka> vratiVrstePostupka() 
    {
        ArrayList<VrstaPostupka> lista = new ArrayList<>();
        String sql = "SELECT * FROM vrstapostuka";
        
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next())
            {
                VrstaPostupka vp = new VrstaPostupka(rs.getInt("VrstaPostukaID"), rs.getString("Naziv"));
                lista.add(vp);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Advokat> vratiAdvokate() 
    {
        ArrayList<Advokat> lista = new ArrayList<>();
        String sql = "SELECT * FROM advokat a JOIN vrstapostuka vp ON a.SpecijalnostZaVrstuPostupka = vp.VrstaPostukaID";
        
        Statement s;
        try {
            s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next())
            {
                VrstaPostupka vp = new VrstaPostupka(rs.getInt("VrstaPostukaID"), rs.getString("Naziv"));
                Advokat a = new Advokat(rs.getInt("AdvokatID"), rs.getString("Ime"), rs.getString("Prezime"), vp);
                lista.add(a);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void sacuvajPredmet(Predmet predmet) throws SQLException 
    {
        String sql = "INSERT INTO predmet VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = konekcija.prepareStatement(sql);
        ps.setInt(1, predmet.getPredmetID());
        ps.setString(2, predmet.getNaziv());
        ps.setString(3, predmet.getProblem());
        ps.setDate(4, new Date(predmet.getDatum().getTime()));
        ps.setInt(5, predmet.getAdvokat().getAdvokatID());
        ps.setInt(7, predmet.getVp().getVrstaPostupkaID());
        ps.setInt(6, predmet.getKlijent().getKlijentID());
        ps.executeUpdate();
        
    }

    public int vratiIDPredmeta() 
    {
        int max = 0;
        String sql = "SELECT max(PredmetID) as max FROM predmet";
        
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next())
            {
                max = rs.getInt("max");
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++max;
    }

    public ArrayList<PretragaKlasa> vratiPretragu(String pretraga) 
    {
        ArrayList<PretragaKlasa> lista = new ArrayList<>();
        String sql = "SELECT a.Ime, a.Prezime, count(p.PredmetID) as brojPredmeta FROM advokat a JOIN predmet p ON a.AdvokatID = p.AdvokatID WHERE a.Ime LIKE '%"+pretraga+"%' OR a.Prezime LIKE '%"+pretraga+"%' GROUP BY a.AdvokatID ORDER BY brojPredmeta DESC";
        
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next())
            {
                String ime = rs.getString("Ime") + " " + rs.getString("Prezime");
                int brojPredmeta = rs.getInt("brojPredmeta");
                PretragaKlasa pk = new PretragaKlasa(brojPredmeta, ime);
                lista.add(pk);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}

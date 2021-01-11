/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Advokat;
import domen.Klijent;
import domen.Predmet;
import domen.VrstaPostupka;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kons.Konstante;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Aleksandar
 */
public class ObradaKlijentskogZahteva extends Thread
{
    Socket klijentskiSoket;
    boolean kraj = false;

    public ObradaKlijentskogZahteva(Socket klijentskiSoket) {
        this.klijentskiSoket = klijentskiSoket;
    }

    @Override
    public void run() 
    {
        while(!kraj)
        {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch(kz.getOperacija())
            {
                case Konstante.VRATI_KLIJENTE:
                    ArrayList<Klijent> listaKlijenata = Kontroler.getInstance().vratiKlijente();
                    so.setOdgovor(listaKlijenata);
                    break;
                case Konstante.VRATI_VRSTE:
                    ArrayList<VrstaPostupka> listaVrsta = Kontroler.getInstance().vratiVrste();
                    so.setOdgovor(listaVrsta);
                    break;
                case Konstante.VRATI_ADVOKATE:
                    ArrayList<Advokat> listaAdvokata = Kontroler.getInstance().vratiAdvokate();
                    so.setOdgovor(listaAdvokata);
                    break;
                case Konstante.SACUVAJ:
                    ArrayList<Predmet> listaPredmeta = (ArrayList<Predmet>) kz.getParametar();
                    boolean uspesno = Kontroler.getInstance().sacuvajPredmete(listaPredmeta);
                    if(uspesno)
                    {
                        so.setPoruka("Uspesno sacuvano");
                    }
                    else
                    {
                        so.setPoruka("Neuspesno sacuvano");
                    }
                    so.setOdgovor(uspesno);
                    break;
            }
            
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() 
    {
        try {
            ObjectInputStream ois = new ObjectInputStream(klijentskiSoket.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) 
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(klijentskiSoket.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

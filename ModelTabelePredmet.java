/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Predmet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksandar
 */
public class ModelTabelePredmet extends AbstractTableModel
{
    ArrayList<Predmet> listaPredmeta;
    String[] kolone = new String[]{"Advokat", "Klijent", "Datum", "Naziv predmeta"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ModelTabelePredmet() 
    {
        listaPredmeta = new ArrayList<>();
    }
    
    
    
    @Override
    public int getRowCount() 
    {
        return listaPredmeta.size();
    }

    @Override
    public int getColumnCount() 
    {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        Predmet p = listaPredmeta.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return p.getAdvokat().toString();
            case 1: return p.getKlijent().toString();
            case 2: return sdf.format(p.getDatum());
            case 3: return p.getNaziv();
            default: return "Error";
        }
    }

    @Override
    public String getColumnName(int column) 
    {
        return kolone[column];
    }
    
    public void dodajPredmet(Predmet p)
    {
        listaPredmeta.add(p);
        fireTableDataChanged();
    }
    
    public void obrisiPredmet(int red)
    {
        listaPredmeta.remove(red);
        fireTableDataChanged();
    }

    public ArrayList<Predmet> getListaPredmeta() {
        return listaPredmeta;
    }

    public void ocistiTabelu() 
    {
        listaPredmeta = new ArrayList<>();
        fireTableDataChanged();
    }
    
    
}

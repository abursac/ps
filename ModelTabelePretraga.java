/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocne.PretragaKlasa;

/**
 *
 * @author Aleksandar
 */
public class ModelTabelePretraga extends AbstractTableModel
{
    private ArrayList<PretragaKlasa> lista;

    public ModelTabelePretraga() 
    {
        lista = new ArrayList<>();
    }
    
    

    @Override
    public int getRowCount() 
    {
        return lista.size();
    }

    @Override
    public int getColumnCount() 
    {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        PretragaKlasa pk = lista.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return pk.getImePrezime();
            case 1: return pk.getBrojPredmeta();
            default: return "Error";
        }
    }

    @Override
    public String getColumnName(int column) 
    {
        switch(column)
        {
            case 0: return "Ime i prezime";
            case 1: return "Broj predmeta";
            default: return "Error";
        }
    }

    
    
    public void setLista(ArrayList<PretragaKlasa> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
}

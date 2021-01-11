/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksandar
 */
public class PokreniServer extends Thread
{

    @Override
    public void run() 
    {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server se pokrenuo.");
            while(true)
            {
                Socket s = ss.accept();
                System.out.println("Klijent se povezao.");
                ObradaKlijentskogZahteva okz = new ObradaKlijentskogZahteva(s);
                okz.start();
            }
            
        } catch (IOException ex) {
            System.out.println("Server down");
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astronomiasc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojosastronomia.Excepciones;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class AstronomiaSC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int puertoServidor = 30500;
            ServerSocket socketServidor = new ServerSocket(puertoServidor);

            while (true) {

                Socket clienteConectado = socketServidor.accept();
                ManejadorPeticion mp = new ManejadorPeticion(clienteConectado);
                mp.start(); // lanza el hilo sin terminar el hilo principal, que sería el main

            }
//            clienteConectado.close();
//            socketServidor.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}

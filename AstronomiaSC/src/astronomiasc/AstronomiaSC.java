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
 * @author usuario
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

//                 System.out.println("Servidor.Consola - El servidor se queda a la espera de algún cliente establezca conexión con el servidor");
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

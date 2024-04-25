/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astronomiacc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author jinet
 */
public class Backup {
     public static void main(String[] args) {
        
        try {
            System.out.println("Estableciendo conexion con el servidor");
            String equipoServidor = "172.16.69.69";
            int puertoServidor = 30500;
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);
            gestionarComunicacion(socketCliente);
            
            socketCliente.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Método que implementa el diálogo con el cliente
     * @param socketCliente Socket que se usa para realizar la comunicación con el servidor
    */
    public static void gestionarComunicacion (Socket socketCliente) {
        
        try {
            System.out.println("Enviando nombre al servidor");
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject("Cristina");
            
            System.out.println("Recibiendo el hola");
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            String saludo = (String) ois.readObject();
            System.out.println(saludo);
            
            System.out.println("Recibiendo el adios");
            ObjectInputStream ois2 = new ObjectInputStream(socketCliente.getInputStream());
            String despedida = (String) ois2.readObject();
            System.out.println(despedida);
            
            ois.close();
            ois2.close();
            oos.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

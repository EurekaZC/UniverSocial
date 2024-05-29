package com.example.universocialui.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pojosastronomia.Excepciones;

public class Password {
    private static final String SALT = "h3liC0pt3r"; // Usamos salt para hacer el hasheo más seguro

    public static String passCodificada(String pass) throws Excepciones {
        byte[] arrayCodificado = null;
        String passFinal;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            pass = pass + SALT; // Añadir la sal a la contraseña
            arrayCodificado = md.digest(pass.getBytes());
            // Convertir el array de bytes a una representación hexadecimal
            passFinal = bytesToHex(arrayCodificado);
        } catch (NoSuchAlgorithmException e) {
            Excepciones ex = new Excepciones();
            ex.setMensajeUsuario("La contraseña no es valida");
            throw ex;
        }
        return passFinal;
    }

    // Método para convertir un array de bytes a una representación hexadecimal
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b)); // Formatea cada byte como un número hexadecimal de dos dígitos
        }
        return result.toString(); // Retorna la cadena hexadecimal resultante
    }
}

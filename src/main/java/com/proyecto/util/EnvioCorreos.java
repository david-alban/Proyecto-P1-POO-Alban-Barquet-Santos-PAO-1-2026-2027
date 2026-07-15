package com.proyecto.util;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EnvioCorreos{

    /**
     * 
     * Las variables empleadas en el Jframe, ahora como parámetros fijos
     */
    private static final String emailFrom = "padilladavidjosue15@gmail.com"; //COrreo emisor
    private static final String passwordFrom = "fnrinmhmfwluqqxf"; //Contraseña de aplicación

    public static void enviarCorreoReal(String destinatario, String asunto, String contenido) {
        
        /**
         * Configurar las propiedades
         */
        Properties mProperties = new Properties();
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        /**
         * Crear la sesión utilizando la API de Jakarta
         */
        Session mSession = Session.getInstance(mProperties);

        try {
            /** 
             * Crear el contenedor del correo electrónico 
             */ 
            MimeMessage mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mCorreo.setSubject(asunto);
            
            /**
             * "html" indica que puedes meter etiquetas HTML en el texto si quieres
             */
            mCorreo.setContent(contenido, "text/html; charset=utf-8"); 

            /**
             * Conectarse al servidor y enviar
             */
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            System.out.println("[Sistema] Correo enviado exitosamente a: " + destinatario);

        } catch (MessagingException ex) {
            System.out.println("[Error] Falló el envío del correo electrónico.");
            ex.printStackTrace(); 
        }
    }
}
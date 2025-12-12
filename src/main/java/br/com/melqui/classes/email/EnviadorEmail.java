package br.com.melqui.classes.email;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EnviadorEmail {

    private final ConfiguracaoEmail config;

    public EnviadorEmail(ConfiguracaoEmail config) {
        this.config = config;
    }

    private class EmailAuthenticator extends Authenticator {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(config.getUsername(), config.getPassword());
        }
    }

    public void enviar(String destinatario, String assunto, String conteudo) throws MessagingException {


        Properties props = config.getJavaMailProperties();

        Session sessao = Session.getInstance(props, new EmailAuthenticator());

        try {
            Message mensagem = new MimeMessage(sessao);
            mensagem.setFrom(new InternetAddress(this.config.getUsername()));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensagem.setSubject(assunto);
            mensagem.setText(conteudo);

            Transport.send(mensagem);

        } catch (MessagingException e) {
            throw e;
        }
    }
}

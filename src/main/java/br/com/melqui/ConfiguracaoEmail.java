package br.com.melqui;

import java.util.Properties;

public class ConfiguracaoEmail {

    // Configurações do Servidor SMTP
    private final String host;
    private final String port;
    private final boolean useSsl;

    // Credenciais (Autenticação)
    private final String username;
    private final String password;

    public ConfiguracaoEmail(String host, String port, String username, String password, boolean useSsl) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.useSsl = useSsl;
    }

    // Getters para uso pelo Authenticator
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Gera e retorna o objeto Properties do JavaMail com base nas configurações
     * de conexão (host, port, segurança).
     *
     * @return O objeto Properties configurado.
     */
    public Properties getJavaMailProperties() {
        Properties props = new Properties();

        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);
        props.put("mail.smtp.auth", "true");

        // Lógica de Segurança (STARTTLS vs SSL)
        if (this.useSsl) {
            // Configuração para SSL (geralmente porta 465)
            props.put("mail.smtp.socketFactory.port", this.port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.ssl.enable", "true");
        } else {
            // Configuração para STARTTLS (geralmente porta 587)
            props.put("mail.smtp.starttls.enable", "true");
        }

        return props;
    }
}

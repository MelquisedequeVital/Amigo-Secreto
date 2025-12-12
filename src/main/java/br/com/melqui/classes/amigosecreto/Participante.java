package br.com.melqui.classes.amigosecreto;

public class Participante {

    private String nome;
    private String email;

    public Participante(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object outraPessoa) {
        if (this == outraPessoa) {
            return true;
        }

        if (outraPessoa == null || !(outraPessoa instanceof Participante)) {
            return false;
        }

        Participante outroParticipante = (Participante) outraPessoa;

        return this.nome.equals(outroParticipante.getNome());
    }

    @Override
    public String toString() {
        return nome;
    }

}

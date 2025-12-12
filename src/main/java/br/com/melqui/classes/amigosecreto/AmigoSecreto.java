package br.com.melqui.classes.amigosecreto;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

public class AmigoSecreto {
    private Participante[] participantes;
    private ArrayList<Participante> participanteSorteados;
    private Hashtable<Participante,Participante> amigoSecreto;
    private Random randomizador = new Random();

    public AmigoSecreto(Participante[] pessoas) {
        this.participantes = pessoas;
        this.participanteSorteados = new ArrayList<>();
        this.amigoSecreto = new Hashtable<>();
    }


    public void sorteiaAmigoSecreto(){
        for (Participante pessoa : participantes) {
            int numeroSorteado = randomizador.nextInt(participantes.length);
            Participante participanteSorteado = participantes[numeroSorteado];

            while(pessoa.equals(participanteSorteado) || isRepetido(participanteSorteado)){
                numeroSorteado = randomizador.nextInt(participantes.length);
                participanteSorteado = participantes[numeroSorteado];
            }

            amigoSecreto.put(pessoa, participanteSorteado);
            participanteSorteados.add(participanteSorteado);
            
        }
    }

    public String getAmigoSecreto(Participante pessoa){
        if(!amigoSecreto.contains(pessoa)){
            throw new IllegalArgumentException("Pessoa não identificada");
        }
        return amigoSecreto.get(pessoa).toString();
    }

    private boolean isRepetido(Participante pessoa){
        boolean repete = false;
        if(this.participanteSorteados.isEmpty()){
                return repete;
            }
        if(participanteSorteados.contains(pessoa)){
            repete = true;
        }
        return repete;
    }

    @Override
    public String toString(){
        String sorteadoString = "";
        Set<Participante> keys = amigoSecreto.keySet();
        for (Participante key: keys) {
            sorteadoString = sorteadoString + key.toString() + ":" + amigoSecreto.get(key).toString() + "\n";
        }
        return sorteadoString;
    }

    
}

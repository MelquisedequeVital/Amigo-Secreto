package br.com.melqui.classes.amigosecreto;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

public class AmigoSecreto {
    private String[] participantes;
    private ArrayList<String> participanteSorteados;
    private Hashtable<String,String> amigoSecreto;
    private Random randomizador = new Random();

    public AmigoSecreto(String[] pessoas) {
        this.participantes = pessoas;
        this.participanteSorteados = new ArrayList<>();
        this.amigoSecreto = new Hashtable<>();
    }


    public void sorteiaAmigoSecreto(){
        for (String pessoa : participantes) {
            int numeroSorteado = randomizador.nextInt(participantes.length);
            String participanteSorteado = participantes[numeroSorteado];

            while(pessoa.equals(participanteSorteado) || isRepetido(participanteSorteado)){
                numeroSorteado = randomizador.nextInt(participantes.length);
                participanteSorteado = participantes[numeroSorteado];
            }

            amigoSecreto.put(pessoa, participanteSorteado);
            participanteSorteados.add(participanteSorteado);
            
        }
    }

    public String getAmigoSecreto(String pessoa){
        if(!amigoSecreto.contains(pessoa)){
            throw new IllegalArgumentException("Pessoa não identificada");
        }
        return amigoSecreto.get(pessoa);
    }

    private boolean isRepetido(String pessoa){
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
        Set<String> keys = amigoSecreto.keySet();
        for (String key: keys) {
            sorteadoString = sorteadoString + key + ":" + amigoSecreto.get(key) + "\n";
        }
        return sorteadoString;
    }

    
}

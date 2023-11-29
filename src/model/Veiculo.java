package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Veiculo {
    @Id
    private String placa;
    private int capacidade;

    @OneToMany(mappedBy = "veiculo", cascade={CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<Viagem> viagens = new ArrayList<>();
    
    public Veiculo() {}

    public Veiculo(String placa, int capacidade) {
        this.placa = placa;
        this.capacidade = capacidade;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void adicionarViagem(Viagem viagem) {
        viagens.add(viagem);
        viagem.setVeiculo(this);
    }

    public void removerViagem(Viagem viagem) {
        viagens.remove(viagem);
        viagem.setVeiculo(null);
    }
    
    @Override
	public String toString() {
		String texto = "Veiculo [placa=" + placa + ", Capacidade = " + capacidade + "] Viagens = " ;
		if (viagens.isEmpty())
			texto += " ";
		else 	
			for(Viagem v: viagens) 
				texto += v.getDestino() + " - " ; 
		
	    return texto;
	}

}
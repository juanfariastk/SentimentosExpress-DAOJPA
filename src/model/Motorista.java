package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Motorista {
    @Id
    private String cnh;
    private String nome;

    @OneToMany(mappedBy = "motorista", cascade={CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<Viagem> viagens = new ArrayList<>();

    public Motorista() {}
    
    public Motorista(String cnh, String nome) {
        this.cnh = cnh;
        this.nome = nome;
    }
    

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void adicionarViagem(Viagem viagem) {
        viagens.add(viagem);
        viagem.setMotorista(this);
    }

    public void removerViagem(Viagem viagem) {
        viagens.remove(viagem);
        viagem.setMotorista(null);
    }

    @Override
	public String toString() {
		String texto = "Motorista [cnh=" + cnh + ", nome=" + nome + "] Viagens = ";
			if (viagens.isEmpty())
				texto += " ";
			else 	
				for(Viagem v: viagens) 
					texto += v.getDestino() + " - " ; 
		    return texto;
	}
}

package model;

import jakarta.persistence.*;

@Entity
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    private String destino;

    @ManyToOne
    @JoinColumn(name = "motorista_cnh")
    private Motorista motorista;

    @ManyToOne
    @JoinColumn(name = "veiculo_placa")
    private Veiculo veiculo;

    private int quantidade;
    
    public Viagem() {}
    
    public Viagem(String data, Veiculo veiculo, Motorista motorista, String destino, int quantidade) {
        this.data = data;
        this.veiculo = veiculo;
        this.motorista = motorista;
        this.destino = destino;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
	public String toString() {
		return "Viagem [id=" + id + ", data=" + data + ", veiculo=" + veiculo + ", motorista=" + motorista
				+ ", destino=" + destino + ", quantidade=" + quantidade + "]";
	}

}

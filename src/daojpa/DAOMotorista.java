package daojpa;

import jakarta.persistence.TypedQuery;
import model.Motorista;
import model.Viagem;

import java.util.List;

public class DAOMotorista extends DAO<Motorista> {

    public Motorista read(Object chave) {
        String cnh = (String) chave;
        return manager.find(Motorista.class, cnh);
    }
    
    public List<Motorista> readAll(){
		TypedQuery<Motorista> query = manager.createQuery("select m from Motorista m LEFT JOIN FETCH m.viagens order by m.cnh",Motorista.class);
		return  query.getResultList();
	}

    public List<Viagem> viagensMotorista(String nomeMotorista) {
        TypedQuery<Motorista> query = manager.createQuery(
                "SELECT m FROM Motorista m WHERE m.nome = :nomeMotorista", Motorista.class)
                .setParameter("nomeMotorista", nomeMotorista);
        Motorista motorista;
        try {
            motorista = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return motorista.getViagens();
    }

    public Viagem viagemMotoristaPorNome(String nomeMotorista, String nomeViagem) {
        TypedQuery<Motorista> query = manager.createQuery(
                "SELECT m FROM Motorista m WHERE m.nome = :nomeMotorista", Motorista.class)
                .setParameter("nomeMotorista", nomeMotorista);
        Motorista motorista;
        try {
            motorista = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        for (Viagem viagem : motorista.getViagens()) {
            if (viagem.getDestino().equals(nomeViagem)) {
                return viagem;
            }
        }
        return null;
    }

    public Motorista motoristaPorPlacaVeiculo(String placaVeiculo) {
        TypedQuery<Motorista> query = manager.createQuery(
                "SELECT m FROM Motorista m WHERE m.veiculo.placa = :placaVeiculo", Motorista.class)
                .setParameter("placaVeiculo", placaVeiculo);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Motorista motoristaPorNome(String nome) {
        TypedQuery<Motorista> query = manager.createQuery(
                "SELECT m FROM Motorista m WHERE m.nome = :nomeMotorista", Motorista.class)
                .setParameter("nomeMotorista", nome);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Motorista> motoristasComMaisDeDuasViagens() {
        TypedQuery<Motorista> query = manager.createQuery(
                "SELECT m FROM Motorista m WHERE SIZE(m.viagens) > 2", Motorista.class);
        return query.getResultList();
    }

    public Motorista motoristaPorCNH(String cnh) {
        TypedQuery<Motorista> query = manager.createQuery(
                "SELECT m FROM Motorista m WHERE m.cnh = :cnh", Motorista.class)
                .setParameter("cnh", cnh);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

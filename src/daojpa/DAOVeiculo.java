package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Veiculo;
import model.Viagem;

import java.util.List;

public class DAOVeiculo extends DAO<Veiculo> {

    public Veiculo read(Object chave) {
        String placa = (String) chave;
        return manager.find(Veiculo.class, placa);
    }
    
    public List<Veiculo> readAll(){
		TypedQuery<Veiculo> query = manager.createQuery("select v from Veiculo v LEFT JOIN FETCH v.viagens order by v.placa",Veiculo.class);
		return  query.getResultList();
	}

    public Veiculo veiculoPorMotorista(String nomeMotorista) {
        TypedQuery<Veiculo> query = manager.createQuery(
                "SELECT v FROM Veiculo v JOIN fetch v.viagens viagem WHERE viagem.motorista.nome = :nomeMotorista", Veiculo.class)
                .setParameter("nomeMotorista", nomeMotorista);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Viagem> listaViagensVeiculo(String placaVeiculo) {
        TypedQuery<Veiculo> query = manager.createQuery(
                "SELECT v FROM Veiculo v LEFT JOIN FETCH v.viagens WHERE v.placa = :placaVeiculo", Veiculo.class)
                .setParameter("placaVeiculo", placaVeiculo);
        Veiculo veiculo;
        try {
            veiculo = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return veiculo.getViagens();
    }

    public Veiculo veiculoPorPlaca(String placa) {
        try {
            TypedQuery<Veiculo> query = manager.createQuery(
                    "SELECT v FROM Veiculo v LEFT JOIN FETCH v.viagens viagem LEFT JOIN FETCH viagem.motorista WHERE v.placa = :placa", Veiculo.class)
                    .setParameter("placa", placa);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void atualizarAtributosVeiculo(Veiculo veiculo) {
        try {
            begin();
            update(veiculo);
            commit();
        } catch (Exception e) {
            rollback();
            throw new RuntimeException("Erro ao atualizar o veículo: " + e.getMessage());
        } finally {
            close();
        }
    }
}

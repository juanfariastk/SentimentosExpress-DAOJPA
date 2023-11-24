package daojpa;

import model.Viagem;

import java.util.List;

public class DAOViagem extends DAO<Viagem> {

    public Viagem read(Object chave) {
        int id = (int) chave;
        return manager.find(Viagem.class, id);
    }

    public void create(Viagem obj) {
        begin();
        manager.persist(obj);
        commit();
    }

    public List<Viagem> viagemMotorista(String nomeMotorista) {
        return manager.createQuery(
                "SELECT v FROM Viagem v WHERE v.motorista.nome = :nomeMotorista", Viagem.class)
                .setParameter("nomeMotorista", nomeMotorista)
                .getResultList();
    }

    public List<Viagem> veiculoViagem(String placaVeiculo) {
        return manager.createQuery(
                "SELECT v FROM Viagem v WHERE v.veiculo.placa = :placaVeiculo", Viagem.class)
                .setParameter("placaVeiculo", placaVeiculo)
                .getResultList();
    }

    public List<Viagem> viagensPorDestino(String destino) {
        return manager.createQuery(
                "SELECT v FROM Viagem v WHERE v.destino = :destino", Viagem.class)
                .setParameter("destino", destino)
                .getResultList();
    }

    public Viagem viagemPorId(int id) {
        return manager.find(Viagem.class, id);
    }

    public List<Viagem> viagensPorData(String data) {
        return manager.createQuery(
                "SELECT v FROM Viagem v WHERE v.data = :data", Viagem.class)
                .setParameter("data", data)
                .getResultList();
    }

    public void atualizarAtributosViagem(Viagem viagem) {
        try {
            begin();
            manager.merge(viagem);
            commit();
        } catch (Exception e) {
            rollback();
            throw new RuntimeException("Erro ao atualizar a viagem: " + e.getMessage());
        } finally {
            close();
        }
    }
}

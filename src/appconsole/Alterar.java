package appconsole;

import java.util.List;

import model.Viagem;
import regras_negocio.Fachada;

public class Alterar {
	
	public Alterar() {
		try {
			Fachada.inicializar();
			// Atualizando capacidade da viagem
            Fachada.atualizarCapacidadeDaViagemPorDestino("Recife", 6);
            List<Viagem> viagemAtualizada = Fachada.localizarViagemPorDestino("Recife");
            System.out.println("A viagem de ID: " + viagemAtualizada.get(0).getId() + " e Destino " + viagemAtualizada.get(0).getDestino() + " foi atualizada com sucesso!"); 
            
		
		} catch (Exception e) {
			System.out.println(e);
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}


package appconsole;


import regras_negocio.Fachada;

public class Deletar {

	public Deletar() {
		try {
            Fachada.inicializar();
            Fachada.excluirViagemPorDestino("Augusta");
            System.out.println("Viagens para Augusta excluídas com sucesso!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Fachada.finalizar();
        System.out.println("\nfim do programa !");
    }
	public static void main(String[] args) {
		new Deletar();
	}
}

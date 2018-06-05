package Memoria;

public class MenVirtual {

	private Pagina[] paginaVirtual;
	
	
	public MenVirtual(int tamanho) {
	
		this.paginaVirtual = new Pagina[tamanho];
		for (int i = 0; i < tamanho; i++) {
			this.paginaVirtual[i] = new Pagina();
		}
		
	}
	
	public Pagina getPagina(int posicao) {
		return paginaVirtual[posicao];
	}
	

}

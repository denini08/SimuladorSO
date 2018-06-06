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
	
	public void mostrarTudoMenVirutal() {
		System.out.println("/----/TUDO DA MenVirutal /---/");
		for(int i = 0; i < this.paginaVirtual.length ; i++) {
			System.out.print("posicao: " + i + " = "); 
			if(paginaVirtual[i] == null) {
				System.out.println(" null");
				continue;
			}
			paginaVirtual[i].printPagina();	
			System.out.println();
			
		}
		System.out.println();
	}

}

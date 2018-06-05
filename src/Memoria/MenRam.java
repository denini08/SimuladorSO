package Memoria;

public class MenRam {
	private Integer[] memoriaRam;
	
	public MenRam(int tamanho) {
		memoriaRam = new Integer[tamanho];
	}
	
	public Integer getValor(int indece) {
		return this.memoriaRam[indece];
	}
	
	public void setValor(int indice, Integer valor) {
		this.memoriaRam[indice] = valor;
	}
	
	
	public Integer getIndiceLivre() {
		for(int i = 0; i < memoriaRam.length; i++){
			if(memoriaRam[i] == null){
				System.out.println("Conseguiu espaco na RAM, espaco: 0x" + i);
				return i;
			}
		}
		System.out.println("NAO conseguiu espaco na RAM");
		return null;
	}
	
	public void mostrarTudoRam() {
		System.out.println("/----/TUDO DA RAM /---/");
		for(int i = 0; i < this.memoriaRam.length ; i++) {
			System.out.println("posicao: " + i + " = " + memoriaRam[i]);	
			
		}
		System.out.println();
	}
}

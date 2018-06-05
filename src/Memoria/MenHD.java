package Memoria;

public class MenHD {
	private Integer[] memoriaHD;
	private final int tamanhoHD = 10;
	
	public MenHD() {
		memoriaHD =new Integer[tamanhoHD];
	}
	
	public Integer getValorHD(int indece) {
		return this.memoriaHD[indece];
	}
	
	public void setValorHD(int indice, Integer valor) {
		this.memoriaHD[indice] = valor;
	}
	
	public Integer getIndiceLivreHD() {
		for(int i = 0; i < memoriaHD.length; i++){
			if(memoriaHD[i] == null){
				System.out.println("Conseguiu espaco no HD, espaco: 0y" + i);
				return i;
			}
		}
		System.out.println("NAO conseguiu espaco no HD");
		return null;
	}


}

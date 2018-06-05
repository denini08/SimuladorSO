package Memoria;

public class Pagina {
	private boolean referenciada;
	private boolean modificada;
	private boolean presente;
	private Integer molduraPagina;
	
	
	public Pagina() {
		this.setReferenciada(false);
		this.setModificada(false);
		this.setMolduraPagina(null);
		this.setPresente(false);
		
	}
	

	
	public Integer getMolduraPagina() {
		return molduraPagina;
	}
	
	public void setMolduraPagina(Integer molduraPagina) {
		this.molduraPagina = molduraPagina;
	}
	
	
	
	public void setReferenciada(boolean status) {
		this.referenciada =status ;
	}


	public boolean isReferenciada() {
		return referenciada;
	}



	public boolean isModificada() {
		return modificada;
	}



	public void setModificada(boolean modificada) {
		this.modificada = modificada;
	}



	public boolean isPresente() {
		return presente;
	}



	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	public boolean existe() {
		if(this.molduraPagina == null) {
			return false;
		}
		return true;
	}
	
	public void printPagina() {
		System.out.println("referenciada: " + referenciada + "\n" 
						+  "modificada: " + modificada  + "\n"
						+   "presente: " + presente + "\n"
						+ 	"molduraPagina: "+ molduraPagina);
	}
	
}

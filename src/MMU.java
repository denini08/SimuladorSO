import Memoria.MenHD;
import Memoria.MenRam;
import Memoria.MenVirtual;
import Memoria.Pagina;

public class MMU {
	private MenRam MemoriaFisica;
	private MenVirtual MemoriaVirtual;
	private MenHD HD;
	private Lista LRU = new Lista();
	
	public MMU(MenVirtual MV_,MenRam Menram_,MenHD HD_) {
		this.MemoriaFisica = Menram_;
		this.MemoriaVirtual = MV_;
		this.HD =HD_;
		
	}
	
	public synchronized void  receberComando(String s, int id) {
		System.out.println("Requisição Processo: " + id);
		String[] t = s.split("-");
		
		if(t[1].contains("R")) {
			leitura( Integer.parseInt(t[0]));
		}else {
			escrita(Integer.parseInt(t[0]), Integer.parseInt(t[2]));
		}
		
		//PARA TESTE:
		//MemoriaVirtual.mostrarTudoMenVirutal();
		//MemoriaFisica.mostrarTudoRam();
		//HD.mostrarTudoHD();
		
	}

	private synchronized void escrita(int indiceVirtual, int escrita) {
		Integer posicaoNaRam = null;
		System.out.println("escrevendo:" + escrita + " na posicao: " + indiceVirtual);
		
		
		if(MemoriaVirtual.getPagina(indiceVirtual).existe()) {	//CASO A PAGINA JA EXISTA, FAZENDO UMA ATUALIZACAO			//se a pagina exite
			System.out.println(" A PAGINA JA EXISTA, FAZENDO UMA ATUALIZACAO");
			
			if(MemoriaVirtual.getPagina(indiceVirtual).isPresente()) {		//se a pagina ja esta na Ram
				System.out.println("a variavel ja esta na Ram");
				posicaoNaRam = MemoriaVirtual.getPagina(indiceVirtual).getMolduraPagina();	//pega a posicao q o valor esta na ram
				MemoriaVirtual.getPagina(indiceVirtual).setModificada(true); 				//AINDA EXISTE DUVIDA AQUI,
				MemoriaFisica.setValor(posicaoNaRam, escrita);
				LRU.adicionarRecente(indiceVirtual);		//ALGORITMO, AQUI FOI REFERENCIADO - IR PARA O FINAL DA LISTA
			}else { //CASO O VALOR NAO ESTEJA NA MEMORIA RAM
				System.out.println("O VALOR NAO ESTA NA MEMORIA RAM");
				this.liberarEspacoRam();
				this.HDparaRAM(indiceVirtual);
	
				LRU.adicionarRecente(indiceVirtual);
				
			}
			
			
		}else { //CASO A PAGINA AINDA NAO EXISTA, FAZENDO UMA NOVA INSERCAO
			System.out.println(" PAGINA AINDA NAO EXISTA, FAZENDO UMA NOVA INSERCAO");
			posicaoNaRam = MemoriaFisica.getIndiceLivre();
			
			if(posicaoNaRam != null) { 		// EXISTE ESPACO LIVRE NA RAM!!!
				MemoriaVirtual.getPagina(indiceVirtual).setMolduraPagina(posicaoNaRam);
				MemoriaVirtual.getPagina(indiceVirtual).setPresente(true);
				MemoriaFisica.setValor(posicaoNaRam, escrita);
				LRU.adicionarRecente(indiceVirtual);	
				
			} else { //CASO NAO EXISTA MEMORIA LIVRE PARA FAZER UMA INSERCAO
				System.out.println("NAO EXISTE MEMORIA LIVRE PARA FAZER UMA INSERCAO");
				this.liberarEspacoRam();
				posicaoNaRam = MemoriaFisica.getIndiceLivre();
				MemoriaVirtual.getPagina(indiceVirtual).setMolduraPagina(posicaoNaRam);
				MemoriaVirtual.getPagina(indiceVirtual).setPresente(true);
				MemoriaFisica.setValor(posicaoNaRam, escrita);
				
				LRU.adicionarRecente(indiceVirtual);	
				
			}
						
		}
		
		//posicaoNaRam = MemoriaFisica.getIndiceLivre();
		//MemoriaFisica.setValor(i, escrita);
		
		System.out.println("ESCRITA FEITA COM SUCESSO");
		//LRU.mostrarTudoArray();
	}

	



	private void leitura(int indiceVirtual) {
		System.out.println("Leitura em :" + indiceVirtual );
		Pagina leitura = this.MemoriaVirtual.getPagina(indiceVirtual);
		
		
		if(leitura.getMolduraPagina() == null) {
			System.out.println("LEITURA SENDO REALIZADA EM UMA PAGINA QUE NAO EXISTE ");
			return;
		}
		if(leitura != null) {
			if(leitura.isPresente()) {
				System.out.println("Indece:" + indiceVirtual + " valor: " + this.MemoriaFisica.getValor(leitura.getMolduraPagina()));
				leitura.setReferenciada(true);
				LRU.adicionarRecente(indiceVirtual);
			}else { //caso estaja ausente (esta no HD).
				this.liberarEspacoRam();
				this.HDparaRAM(indiceVirtual);
				this.leitura(indiceVirtual);
				
				
			}
		}
		
	}
	
	private void liberarEspacoRam() {
		Integer IndicePagina, IndiceLivreHD, IndiceRam, valorRam ;
		IndicePagina = LRU.removerUm();
		System.out.println("foi escolhido:" + IndicePagina);
		IndiceLivreHD = HD.getIndiceLivreHD();
		
		IndiceRam = MemoriaVirtual.getPagina(IndicePagina).getMolduraPagina();
		MemoriaVirtual.getPagina(IndicePagina).setMolduraPagina(IndiceLivreHD);
		MemoriaVirtual.getPagina(IndicePagina).setPresente(false);
		valorRam = MemoriaFisica.getValor(IndiceRam);
		MemoriaFisica.setValor(IndiceRam, null);
		HD.setValorHD(IndiceLivreHD, valorRam);
		
		System.out.println("A variavel que estava na posicao: "+ IndiceRam +  " na pagina " + IndicePagina + " foi para o HD na posicao oy" + IndiceLivreHD);
	}
	
	
	private void HDparaRAM(int indiceVirtual) {
		Integer molduraHD, valorHD, indiceLivreRAM;

		molduraHD = MemoriaVirtual.getPagina(indiceVirtual).getMolduraPagina();
		valorHD = HD.getValorHD(molduraHD);
		HD.setValorHD(molduraHD, null);
		
		indiceLivreRAM = MemoriaFisica.getIndiceLivre();
		MemoriaFisica.setValor(indiceLivreRAM, valorHD);
		MemoriaVirtual.getPagina(indiceVirtual).setMolduraPagina(indiceLivreRAM);
		MemoriaVirtual.getPagina(indiceVirtual).setPresente(true);
		
	}

}

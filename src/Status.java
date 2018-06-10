import Memoria.MenHD;
import Memoria.MenRam;
import Memoria.MenVirtual;

public class Status {
	private MenRam MenFisica;
	private MenVirtual MV;
	private MenHD HD;
	private int contador = 0;
	
	public Status(MenRam MenFisica_,MenVirtual MV_ ,MenHD HD_) {
		this.MenFisica = MenFisica_;
		this.MV = MV_;
		this.HD = HD_;
	}
	
	public  void status_fim() {
		this.contador++;
		if(contador == Main.QUANTIDADE_THREADS) {
			System.out.println("Status FInal papai");
			MV.mostrarTudoMenVirutal();
			MenFisica.mostrarTudoRam();
			HD.mostrarTudoHD();
		}
	}
}

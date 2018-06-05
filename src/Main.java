import Memoria.MenHD;
import Memoria.MenRam;
import Memoria.MenVirtual;

public class Main {

	public static void main(String[] args) {
		
		final int QUANTIDADE_THREADS = 2;
		final int TAMANHO_RAM = 8;
		
	
		MenRam MenFisica = new MenRam(TAMANHO_RAM/2);
		MenVirtual MV = new MenVirtual(TAMANHO_RAM);
		MenHD HD = new MenHD();
	
		MMU mmu = new MMU(MV,MenFisica,HD);
		try {
			for(int i=1; i <= QUANTIDADE_THREADS; i++) {
				new Processo(i, mmu).start();
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
	}

}



/*String s = "4-R, 5-R, 0-R, 4-W-2, 5-R, 7-W-18, 4-W-12";
String[] c = s.split(", ");

for(String g : c) {
	String[] t = g.split("-");
	for(String g1 : t) {
		System.out.println(g1);
	}
	System.out.println();
}*/
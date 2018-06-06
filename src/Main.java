import Memoria.MenHD;
import Memoria.MenRam;
import Memoria.MenVirtual;

public class Main {

	public static void main(String[] args) {
		
		final int QUANTIDADE_THREADS = 1;
		final int TAMANHO_RAM = 8;
		
	
		MenRam MenFisica = new MenRam(TAMANHO_RAM/2);
		MenVirtual MV = new MenVirtual(TAMANHO_RAM);
		MenHD HD = new MenHD();
	
		MMU mmu = new MMU(MV,MenFisica,HD);
		
		String[] s = {"1-W-7", "2-W-1", "1-R","6-W-55", "2-R", "5-W-77", "4-W-75", "3-W-58","6-R"};
		String [] d = {"1-W-88", "2-W-77", "3-W-91", "4-W-12", "2-R", "2-R", "1-R", "2-W-11", "2-R", "5-W-14"};
		String[] p = {"3-W-12","1-W-7","1-R","2-W-5","3-W-23", "2-R","1-W-22", "1-R", "3-W-17", "3-R"};
		String[] e = {"5-W-67", "7-W-3", "6-W-42", "5-R", "7-W-10", "6-R", "5-W-24", "7-R", "7-W-33", "5-R", "4-W-5", "6-W-30", "3-W-20", "7-R"};
		String[] m = {"6-W-1","3-W-2", "1-W-3", "4-W-4", "7-R", "6-R", "2-W-7", "5-W-9", "4-R", "7-W-8", "1-W-3"}; 
		try {
			//for(int i=1; i <= QUANTIDADE_THREADS; i++) {
				//new Processo(0, mmu, d).start();
				//new Processo(1, mmu, p).start();
				new Processo(2, mmu, m).start();
			//}
			
			//MV.mostrarTudoMenVirutal();
			//MenFisica.mostrarTudoRam();
			 
			
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
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
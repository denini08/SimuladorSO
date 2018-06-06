

public class Processo extends Thread{
	private int id;
	private MMU mmu;
	private String[] comandos;
	
	public Processo(int id_, MMU mmu_, String [] comando) {
		this.id = id_;
		this.mmu = mmu_;
		this.comandos = comando;
	}
	
	public void enviar() {
		for(String g : comandos) {
			mmu.receberComando(g,this.id);
			try {
				System.out.println("Processo: " + this.id + " Dormiu\n\n");
				Thread.sleep(this.id * 1500l);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Processo: " + this.id + " ACORDOU!@##@!!");
		}
	}
	
	public void run() {
		
		//String[] s = {"4-R", "5-R", "0-R", "4-W-2", "5-R", "7-W-18", "4-W-12"};
		
		enviar();
		
		
	}
}

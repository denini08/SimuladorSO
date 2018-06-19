

public class Processo extends Thread{
	private int id;
	private MMU mmu;
	private String[] comandos;
	private Status status;
	
	public Processo(int id_, MMU mmu_, String [] comando, Status s) {
		this.id = id_;
		this.mmu = mmu_;
		this.comandos = comando;
		this.status = s;
	}
	
	public void enviar() {
		for(String g : comandos) {
			//System.out.println("Processo: " + this.id + " ACORDOU!@##@!!");
			mmu.receberComando(g,this.id);
			System.out.println(g);
			try {
				System.out.println("Processo: " + this.id + " Dormiu\n\n");
				Thread.sleep(this.id ); //* 1500l
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
		}
		System.out.println("Processo: " + this.id + " Acabou\n");
		status.status_fim();
		
	}
	
	public void run() {
		
		//String[] s = {"4-R", "5-R", "0-R", "4-W-2", "5-R", "7-W-18", "4-W-12"};
		
		enviar();
		
		
	}
}

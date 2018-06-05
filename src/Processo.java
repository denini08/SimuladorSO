

public class Processo extends Thread{
	private int id;
	private MMU mmu;
	
	public Processo(int id_, MMU mmu_) {
		this.id = id_;
		this.mmu = mmu_;
	}
	
	public void enviar(String[] s) {
		for(String g : s) {
			mmu.receberComando(g,this.id);
			try {
				System.out.println("Processo: " + this.id + " Dormiu\n\n");
				Thread.sleep(this.id * 1500l);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void run() {
		
		//String[] s = {"4-R", "5-R", "0-R", "4-W-2", "5-R", "7-W-18", "4-W-12"};
		String[] s = {"1-W-7", "2-W-1", "1-R","6-W-55", "2-R", "5-W-77"};
		enviar(s);
	}
}

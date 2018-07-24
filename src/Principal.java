

public class Principal {

	public static void main(String[] args) {
		CrimeRepo crimes = CrimeRepo.getInstance();
		crimes.setCentros(10);
		crimes.setCentrosCrimes();
		//crimes.printCentrosN();
		
		do {
			crimes.setNovosCentros();
			crimes.setCentrosCrimes();
		}while(crimes.getNMudanças()!=0);
		
		for(int i=0;i<crimes.getCrimes().size();i++){
			System.out.println(crimes.getCrimes().get(i)+","+crimes.getCrimes().get(i).getCentro().getIndex());
		}
		crimes.printCentrosN();
	}
	
	
	//imprime os centros e sua quantidade de crimes 
	//imprime os crimes e seus centros

}
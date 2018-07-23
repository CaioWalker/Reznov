

public class Principal {

	public static void main(String[] args) {
		CrimeRepo crimes = CrimeRepo.getInstance();
		crimes.setCentros(3);
		crimes.setCentrosCrimes();
		
		do {
			crimes.setNovosCentros();
			crimes.setCentrosCrimes();
		}while(crimes.getNMudanças()!=0);
	}

}
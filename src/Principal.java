

public class Principal {

	public static void main(String[] args) {
		int cont = 0;
		CrimeRepo crimes = CrimeRepo.getInstance();
		crimes.setCentros(2);
		crimes.setCentrosCrimes();
		//crimes.printCentrosN();
		
		do {
			crimes.setNovosCentros();
			crimes.setCentrosCrimes();
			cont++;
		}while(crimes.getNMudanças()!=0);
		
		
//		for(int i=0;i<crimes.getCrimes().size();i++){
//			System.out.println(crimes.getCrimes().get(i)+","+crimes.getCrimes().get(i).getCentro().getIndex());
//		}
		crimes.indiceDunn();
		crimes.printCentrosN();
		System.out.println(cont);
		crimes.salvarArquivo();
	}

}
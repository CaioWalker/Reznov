

public class Principal {

	public static void main(String[] args) {
		int cont = 0;
		CrimeRepo crimes = new CrimeRepo();
		CrimeRepo temp = new CrimeRepo();;
		double indice = 0.0;
		double indTemp;
		
		for(int i=0;i<10;i++) {
			
			crimes.setCentros(10);
			crimes.setCentrosCrimes();
			
			//crimes.printCentrosN();
			
			do {
				crimes.setNovosCentros();
				crimes.setCentrosCrimes();
				cont++;
			}while(crimes.getNMudanças()!=0);
						
			indTemp=crimes.indiceDunn();
			if(indTemp>indice) {
				temp=crimes;
				indice = indTemp;
				crimes = new CrimeRepo();
				
			}else {
				crimes = new CrimeRepo();
			}
			
		}
		
		
		temp.printCentrosN();
		System.out.println(cont+"   "+ indice);
		temp.salvarArquivo();
	}

}
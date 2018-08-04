

public class Principal {

	public static void main(String[] args) {
		int cont = 0;
		CrimeRepo crimes = new CrimeRepo();
		CrimeRepo temp = new CrimeRepo();;
		double indice = 0.0;
		double indTemp;
		int k = 2;
		
		for(int i=0;i<10;i++) {
			
			while(k < 11) {
				crimes.setCentros(k);
				crimes.setCentrosCrimes();
				
				//crimes.printCentrosN();
				
				do {
					crimes.setNovosCentros();
					crimes.setCentrosCrimes();
					cont++;
				}while(crimes.getNMudanças()!=0);
							
				indTemp=crimes.indiceDunn();
				if((indTemp>indice) && (indTemp < 1.0)) {
					temp=crimes;
					indice = indTemp;
					crimes = new CrimeRepo();
					
				}else {
					crimes = new CrimeRepo();
				}
				
				k++;
			}
			
			
		}
		
		
		temp.printCentrosN();
		System.out.println(cont+"   "+ indice);
		temp.salvarArquivo();
	}

}
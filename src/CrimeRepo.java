import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class CrimeRepo implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -8635603547323350634L;

private static CrimeRepo instance;
	
	private ArrayList<Crime> crimes = new ArrayList<>();
	private ArrayList<Crime> centros = new ArrayList<>();
	private static String path = "C:\\Users\\Caio\\workspace\\Reznov\\src\\crimes.txt"; 
	
	public static CrimeRepo getInstance(){
		if(instance == null){
			instance = lerDoArquivo();
		}
		return instance;
	}
	
	private CrimeRepo(){
	}
	
	private static CrimeRepo lerDoArquivo(){
		CrimeRepo instanciaLocal = new CrimeRepo();
		int index = 0;
		try {

			FileReader arq = new FileReader(path);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha;
			String[] valores;
			Crime atual;

			
			
			while ((linha = lerArq.readLine()) != null) {
				valores = linha.split(",");
				atual = new Crime(Integer.parseInt(valores[0]),
									Integer.parseInt(valores[1]),
									Integer.parseInt(valores[2]),
									Integer.parseInt(valores[3]),
									Integer.parseInt(valores[4]),
									Integer.parseInt(valores[5]),
									Integer.parseInt(valores[6]),
									Double.parseDouble(valores[7]),
									Double.parseDouble(valores[8]),
									index
									);
				instanciaLocal.cadastrar(atual);
				//System.out.println(index);
				index++;
			}
			
			arq.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return instanciaLocal;
	}
	
	public void salvarArquivo(){
		
		File outCrimes = new File("crimes.dat");
		File outCentros = new File("centros.dat");
		
		try{
			// Se o arquivo de centros nao existir, ele gera
			if (!outCentros.exists()) {
				outCentros.createNewFile();
			}
			// Prepara para escrever no arquivo de centros
			FileWriter fwCentros = new FileWriter(outCentros.getAbsoluteFile(), true);
			BufferedWriter bwCentros = new BufferedWriter(fwCentros);
			PrintWriter saidaCentros = new PrintWriter(bwCentros);
			
			//itera sobre o array de centros para salvar cada linha no arquivo
			
			int[] nCentro= new int[this.centros.size()];
			
			for(int i=0;i<this.centros.size();i++){
				nCentro[i]=0;
			}
			
			
			for(int j=0;j<this.crimes.size();j++){
				for(int i=0;i<this.centros.size();i++){
					if(this.crimes.get(j).getCentro()==this.centros.get(i)){
						nCentro[i]++;
					}
				}
			}
			
			for(int i=0;i<this.centros.size();i++){
				saidaCentros.println("centro "+(i+1)+": "+nCentro[i]);
			}
			
			saidaCentros.close();
			
			
			// Se o arquivo de crimes nao existir, ele gera
			if (!outCrimes.exists()) {
				outCrimes.createNewFile();
			}
			// Prepara para escrever no arquivo de crimes
			FileWriter fwCrimes = new FileWriter(outCrimes.getAbsoluteFile(), true);
			BufferedWriter bwCrimes = new BufferedWriter(fwCrimes);
			PrintWriter saidaCrimes = new PrintWriter(bwCrimes);
			
			
			for(int i=0;i<crimes.size();i++){
				saidaCrimes.println(crimes.get(i)+","+crimes.get(i).getCentro().getIndex());
			}
			
			saidaCrimes.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cadastrar(Crime c){
		this.crimes.add(c);
	}
	
	public ArrayList<Crime> getCrimes(){
		return this.crimes;
	}
	
	public void setCentros(int k) {
		//criando os primeiros centros aleatorios
		System.out.println("criando os primeiros centros aleatorios");
		Random nAleatorio = new Random();
		Crime centro;
		int index;
		for(int i=0;i<k;i++) {
			index = nAleatorio.nextInt(this.crimes.size());
			centro = this.crimes.get(index);
			this.centros.add(centro);
		}
	}
	
	
	public void setCentrosCrimes() {
		double distancia;
		double distTemp;
				
		Crime centro = this.centros.get(1);
		
		//iterando sobre crimes
		for(int i=0;i<this.crimes.size();i++) {
			distancia = 100000000;
			
			if(this.centros.contains(this.crimes.get(i))) {
				this.crimes.get(i).setCentro(this.crimes.get(i));
			}
			
			else {
				//iterando sobre centros para setar o novo centro de cada crime
				for(int j=0;j<this.centros.size();j++) {
					distTemp = this.crimes.get(i).calcDist(this.centros.get(j));
					if(distTemp<distancia) {
						distancia=distTemp;
						centro=this.centros.get(j);
					}
				}
				this.crimes.get(i).setCentro(centro);
				this.crimes.get(i).setDistCentro(distancia);
			}
		}
				
	}
	
	public void printCentrosN(){
		int[] nCentro= new int[this.centros.size()];
		
		for(int i=0;i<this.centros.size();i++){
			nCentro[i]=0;
		}
		
		
		for(int j=0;j<this.crimes.size();j++){
			for(int i=0;i<this.centros.size();i++){
				if(this.crimes.get(j).getCentro()==this.centros.get(i)){
					nCentro[i]++;
				}
			}
		}
		
		for(int i=0;i<this.centros.size();i++){
			System.out.println("centro "+(i+1)+": "+nCentro[i]);
		}
		
		System.out.println(this.crimes.size());
		
	}
	
	public int getNMudan�as() {
		int mudan�as = 0;
		for(int i=0;i<this.crimes.size();i++) {
			if(this.crimes.get(i).getCentro()!=this.crimes.get(i).getCentroAnterio()) {
				mudan�as++;
			}
		}
		return mudan�as;
	}
	
	public void setNovosCentros(){
		ArrayList<Crime> novosCentros = new ArrayList<>();
		Crime centro=null;
		Crime novoCentro = this.crimes.get(0);
		int ocorrencias;
		double distancia=100000000;
		double distNova=100000000;
		
		System.out.println("criando os novos centros");
		
		
		double dia;
		double mes;
		double ano;
		double sexo;
		double tArma;
		double idade;
		double cvli;
		double latitude;
		double longitude;
		
		for(int i=0;i<this.centros.size();i++) {
			
			centro=this.centros.get(i);
			ocorrencias=0;
			distancia=100000000;
			distNova=100000000;
			
			dia=0;
			mes=0;
			ano=0;
			sexo=0;
			tArma=0;
			idade=0;
			cvli=0;
			latitude=0;
			longitude=0;
			
			//somando os valores de cada crime deste grupo
			
			for(int j=0;j<this.crimes.size();j++) {
				if(this.crimes.get(j).getCentro()==centro) {
					this.crimes.get(j).setCentroAnterio(centro);
					ocorrencias++;
					dia=dia+this.crimes.get(j).getDia();
					mes=mes+this.crimes.get(j).getMes();
					ano=ano+this.crimes.get(j).getAno();
					sexo=sexo+this.crimes.get(j).getSexo();
					tArma=tArma+this.crimes.get(j).gettArma();
					idade=idade+this.crimes.get(j).getIdade();
					cvli=cvli+this.crimes.get(j).getCvli();
					latitude=latitude+this.crimes.get(j).getLatitude();
					longitude=longitude+this.crimes.get(j).getLongitude();		
				}
			}
			
			//tirando a media para o novo centroide
			//System.out.println("tirando a media para o novo centroide");
			
			dia=dia/ocorrencias;
			mes=mes/ocorrencias;
			ano=ano/ocorrencias;
			sexo=sexo/ocorrencias;
			tArma=tArma/ocorrencias;
			idade=idade/ocorrencias;
			cvli=cvli/ocorrencias;
			latitude=latitude/ocorrencias;
			longitude=longitude/ocorrencias;

			//calculando a distancia do novo centroide para os crimes
			
			for(int j=0;j<this.crimes.size();j++) {
				if(this.crimes.get(j).getCentro()==centro){
					distNova=this.crimes.get(j).calcDist(dia, mes, ano, sexo, tArma, idade, cvli, latitude, longitude);
					//System.out.println(distNova+" "+distancia);
					if(distNova<distancia) {
						distancia=distNova;
						novoCentro=this.crimes.get(j);
					}
				}
			}
			

			novosCentros.add(novoCentro);
			distancia = 10000000;
			
		}
		//substituindo novos centros
		
		this.centros=novosCentros;
	}
	
	
	
}


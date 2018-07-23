import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
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
									Double.parseDouble(valores[8])
									);
				instanciaLocal.cadastrar(atual);
				linha = lerArq.readLine();
				System.out.println(atual.toString());
				
			}
			
			arq.close();
		}catch(Exception e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return instanciaLocal;
	}
	
	public void salvarArquivo(){
		
		File out = new File("curso.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try{
			fos = new FileOutputStream(out);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(oos!=null){
				try{
					oos.close();
				}
				catch(Exception e){
//					não faz nada
				}
			}
		}
	}
	
	public void cadastrar(Crime c){
		this.crimes.add(c);
	}
	
	public void setCentros(int k) {
		//criando os primeiros centros aleatorios
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
					if(distTemp>distancia) {
						distancia=distTemp;
						centro=this.centros.get(j);
					}
				}
				this.crimes.get(i).setCentro(centro);
				this.crimes.get(i).setDistCentro(distancia);
			}
		}
	}
	
	public int getNMudanças() {
		int mudanças = 0;
		for(int i=0;i<this.crimes.size();i++) {
			if(this.crimes.get(i).getCentro()!=this.crimes.get(i).getCentroAnterio()) {
				mudanças++;
			}
		}
		return mudanças;
	}
	
	public void setNovosCentros(){
		ArrayList<Crime> novosCentros = new ArrayList<>();
		Crime centro=null;
		Crime novoCentro = this.crimes.get(0);
		int ocorrencias;
		double distancia=100000000;
		double distNova=100000000;
		
		
		int dia;
		int mes;
		int ano;
		int sexo;
		int tArma;
		int idade;
		int cvli;
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
				distNova=this.crimes.get(j).calcDist(dia, mes, ano, sexo, tArma, idade, cvli, latitude, longitude);
				if(distNova>distancia) {
					novoCentro=this.crimes.get(j);
				}
			}
			
			//adicionando o novo centro ao arraylist novo
			
			novosCentros.add(novoCentro);
						
		}
		//substituindo novos centros
		
		this.centros=novosCentros;
	}
	
	
	
}


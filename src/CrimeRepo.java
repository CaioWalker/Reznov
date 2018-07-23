import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class CrimeRepo implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -8635603547323350634L;

private static CrimeRepo instance;
	
	private ArrayList<Crime> crimes = new ArrayList<>();
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
	
}


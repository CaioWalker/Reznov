
public class Crime {
	private int dia;
	private int mes;
	private int ano;
	private int sexo;
	private int tArma;
	private int idade;
	private int cvli;
	private double latitude;
	private double longitude;
	
	private Crime centro;
	private Crime centroAnterio;
	private double distCentro;
	private int index;
	
	public Crime(int dia, int mes, int ano, int sexo, int tArma, int idade, int cvli, double latitude, double longitude, int index) {
		this.setDia(dia);
		this.mes = mes;
		this.ano = ano;
		this.sexo = sexo;
		this.tArma = tArma;
		this.idade = idade;
		this.cvli = cvli;
		this.latitude = latitude;
		this.longitude = longitude;
		this.index = index;
	}
	
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int gettArma() {
		return tArma;
	}
	public void settArma(int tArma) {
		this.tArma = tArma;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public int getCvli() {
		return cvli;
	}
	public void setCvli(int cvli) {
		this.cvli = cvli;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	
	public String toString(){
		String resultado =this.dia+","
							+this.mes+","
							+this.ano+","
							+this.sexo+","
							+this.tArma+","
							+this.idade+","
							+this.cvli+","
							+this.latitude+","
							+this.longitude;
		return resultado;
	}

	public Crime getCentro() {
		return centro;
	}

	public void setCentro(Crime centro) {
		this.centro = centro;
	}

	public Crime getCentroAnterio() {
		return centroAnterio;
	}

	public void setCentroAnterio(Crime centroAnterio) {
		this.centroAnterio = centroAnterio;
	}

	public double getDistCentro() {
		return distCentro;
	}

	public void setDistCentro(double distCentro) {
		this.distCentro = distCentro;
	}
	
	public double calcDist(Crime c) {
		double distCidades =6371*Math.acos(Math.cos(Math.PI*(90-c.getLatitude())/180)
				*Math.cos((90-this.latitude)*Math.PI/180)+Math.sin((90-c.getLatitude())
						*Math.PI/180)*Math.sin((90-this.latitude)*Math.PI/180)
						*Math.cos((this.longitude-c.getLongitude())*Math.PI/180));
		
		double dist = Math.sqrt( Math.pow(((this.dia-c.getDia())/30.0), 2)
				+Math.pow(((this.mes-c.getMes())/11.0), 2)
				+Math.pow(((this.ano-c.getAno())/9.0), 2)
				+Math.pow(((this.sexo-c.getSexo())/2.0), 2)
				+Math.pow(((this.tArma-c.gettArma())/2.0), 2)
				+Math.pow(((this.idade-c.getIdade())/99.0), 2)
				+Math.pow(((this.cvli-c.getCvli())/2.0), 2)
				+Math.pow(distCidades/700.0, 2)
				);
						
		return dist;
	}
	
	public double calcDist(double dia, double mes, double ano, double sexo, double tArma, double idade, double cvli, double latitude, double longitude) {
		double distCidades =6371*Math.acos(Math.cos(Math.PI*(90-latitude)/180)
				*Math.cos((90-this.latitude)*Math.PI/180)+Math.sin((90-latitude)
						*Math.PI/180)*Math.sin((90-this.latitude)*Math.PI/180)
						*Math.cos((this.longitude-longitude)*Math.PI/180));
		
		double dist = Math.sqrt( Math.pow(((this.dia-dia)/30.0), 2)
				+Math.pow(((this.mes-mes)/11.0), 2)
				+Math.pow(((this.ano-ano)/9.0), 2)
				+Math.pow(((this.sexo-sexo)/2.0), 2)
				+Math.pow(((this.tArma-tArma)/2.0), 2)
				+Math.pow(((this.idade-idade)/99.0), 2)
				+Math.pow(((this.cvli-cvli)/2.0), 2)
				+Math.pow(distCidades/700.0, 2)
				);			
		return dist;
	}

	public int getIndex() {
		return index;
	}

		
}

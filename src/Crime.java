
public class Crime {
	private int dia;
	private int m�s;
	private int ano;
	private int sexo;
	private int tArma;
	private int idade;
	private int cvli;
	private double latitude;
	private double longitude;
	public Crime(int dia, int m�s, int ano, int sexo, int tArma, int idade, int cvli, double latitude, double longitude) {
		super();
		this.setDia(dia);
		this.m�s = m�s;
		this.ano = ano;
		this.sexo = sexo;
		this.tArma = tArma;
		this.idade = idade;
		this.cvli = cvli;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getM�s() {
		return m�s;
	}
	public void setM�s(int m�s) {
		this.m�s = m�s;
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
	public Double soma(){
		return this.latitude + this.longitude;
	}
	public String toString(){
		String resultado =this.dia+" "+this.m�s+" "+this.ano+" "+this.sexo+" "+this.tArma+" "+this.idade+" "+this.cvli+" "+this.latitude+" "+this.longitude+" Soma:"+this.soma();
		return resultado;
	}
	
}

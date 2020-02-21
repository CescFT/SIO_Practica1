package Estudi;

public class Probabilitat {

	private String probNum;
	
	private double probabilitat;
	
	public Probabilitat() {}
	
	public Probabilitat(String titol, double probabilitat) {
		this.probNum = titol;
		this.probabilitat = probabilitat;
	}

	public String getProbNum() {
		return probNum;
	}

	public void setProbNum(String probNum) {
		this.probNum = probNum;
	}

	public double getProbabilitat() {
		return probabilitat;
	}

	public void setProbabilitat(double probabilitat) {
		this.probabilitat = probabilitat;
	}

	@Override
	public String toString() {
		return "Probabilitat [probNum=" + probNum + ", probabilitat=" + probabilitat + "]";
	}
	
	
	
}

package Estudi;

/**
 * 
 * @author Cesc
 *
 */
public class Probabilitat {

	private String probNum;
	
	private double probabilitat;
	
	/**
	 * constructor
	 */
	public Probabilitat() {}
	
	/**
	 * constructor
	 * @param titol titol 
	 * @param probabilitat probabilitat
	 */
	public Probabilitat(String titol, double probabilitat) {
		this.probNum = titol;
		this.probabilitat = probabilitat;
	}

	/**
	 * getter
	 * @return probabilitat
	 */
	public String getProbNum() {
		return probNum;
	}

	/**
	 * setter
	 * @param probNum probabilitat
	 */
	public void setProbNum(String probNum) {
		this.probNum = probNum;
	}

	/**
	 * getter
	 * @return probabilitat
	 */
	public double getProbabilitat() {
		return probabilitat;
	}

	/**
	 * setter 
	 * @param probabilitat probabilitat
	 */
	public void setProbabilitat(double probabilitat) {
		this.probabilitat = probabilitat;
	}

	@Override
	public String toString() {
		return "Probabilitat [probNum=" + probNum + ", probabilitat=" + probabilitat + "]";
	}
	
	
	
}

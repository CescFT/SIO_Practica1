package Estudi;

/**
 * Classe que permet coneixer el numero de gent q va en cada restaurant
 * @author Cesc
 *
 */
public class InfluenciaGent implements Comparable<InfluenciaGent>{
	
	private int idRestaurant;
	
	private int numPuntuacions;
	
	/**
	 * constructor
	 */
	public InfluenciaGent() {}
	
	/**
	 * constructor
	 * @param idRestaurant idrestaurant
	 * @param numPuntuacions puntuacions
	 */
	public InfluenciaGent(int idRestaurant, int numPuntuacions) {
		this.idRestaurant=idRestaurant;
		this.numPuntuacions=numPuntuacions;
	}

	/**
	 * getter
	 * @return idrestaurant
	 */
	public int getIdRestaurant() {
		return idRestaurant;
	}

	/**
	 * setter
	 * @param idRestaurant idrestaurant
	 */
	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	/**
	 * getter
	 * @return numero puntuacions
	 */
	public int getNumPuntuacions() {
		return numPuntuacions;
	}

	/**
	 * setter 
	 * @param numPuntuacions numpuntuacions
	 */
	public void setNumPuntuacions(int numPuntuacions) {
		this.numPuntuacions = numPuntuacions;
	}

	@Override
	public String toString() {
		return "InfluenciaGent [idRestaurant=" + idRestaurant + ", numPuntuacions=" + numPuntuacions + "]";
	}

	@Override
	public int compareTo(InfluenciaGent o) {
		if(this.numPuntuacions>o.getNumPuntuacions())
			return 1;
		else if (this.numPuntuacions<o.getNumPuntuacions())
			return -1;
		else return 0;
	}

}

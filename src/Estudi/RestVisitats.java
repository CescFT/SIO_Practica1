package Estudi;

/**
 * Classe que té la relació de restaurants visitats
 * @author Cesc
 *
 */
public class RestVisitats implements Comparable<RestVisitats>{
	private int idUsuari;
	private int numRestaurants;
	
	/**
	 * constructor
	 */
	public RestVisitats() {}
	
	/**
	 * constructor
	 * @param idUsuari id
	 * @param numRestaurants restaurants
	 */
	public RestVisitats(int idUsuari, int numRestaurants) {
		this.idUsuari=idUsuari;
		this.numRestaurants=numRestaurants;
	}

	/**
	 * getter
	 * @return id
	 */
	public int getIdUsuari() {
		return idUsuari;
	}

	/**
	 * setter 
	 * @param idUsuari id
	 */
	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	/**
	 * getter
	 * @return numrestaurants
	 */
	public int getNumRestaurants() {
		return numRestaurants;
	}

	/**
	 * setter
	 * @param numRestaurants numero restaurants
	 */
	public void setNumRestaurants(int numRestaurants) {
		this.numRestaurants = numRestaurants;
	}

	@Override
	public String toString() {
		return "RestVisitats [idUsuari=" + idUsuari + ", numRestaurants=" + numRestaurants + "]";
	}

	@Override
	public int compareTo(RestVisitats o) {
		if(this.numRestaurants > o.getNumRestaurants())
			return 1;
		else if (this.numRestaurants < o.getNumRestaurants())
			return -1;
		else return 0;
	}
	
}

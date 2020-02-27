package Estudi;

/**
 * Classe que conté la desviació mitjana per restaurant
 * @author Cesc
 *
 */
public class DesvMitjanaRestaurant {
	
	private int idRestaurant;
	
	private double desvMitjana;
	
	/**
	 * constructor
	 */
	public DesvMitjanaRestaurant() {}
	
	/**
	 *  constructor
	 * @param idRestaurant id restaurant
	 * @param desvMitjana desviacio
	 */
	public DesvMitjanaRestaurant(int idRestaurant, double desvMitjana) {
		this.idRestaurant = idRestaurant;
		
		this.desvMitjana = desvMitjana;
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
	 * @param idRestaurant id restaurant
	 */
	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	/**
	 * getter
	 * @return desviacio
	 */
	public double getDesvMitjana() {
		return desvMitjana;
	}

	/**
	 * setter
	 * @param desvMitjana desviacio
	 */
	public void setDesvMitjana(double desvMitjana) {
		this.desvMitjana = desvMitjana;
	}

	@Override
	public String toString() {
		return "DesvMitjanaRestaurant [idRestaurant=" + idRestaurant + ", desvMitjana=" + desvMitjana + "]";
	}
	
	

}

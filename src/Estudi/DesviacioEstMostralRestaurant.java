package Estudi;

/**
 * Classe que emmagatzema la desviació estandard mostral per restaurant
 * @author Cesc
 *
 */
public class DesviacioEstMostralRestaurant {
	
	private int idRestaurant;
	
	private double desviacioEstandardMostral;
	
	/**
	 * constructor
	 */
	public DesviacioEstMostralRestaurant() {}
	
	/**
	 * constructor
	 * @param idRestaurant id restaurant
	 * @param desviacioEstandardMostral desviacio 
	 */
	public DesviacioEstMostralRestaurant(int idRestaurant, double desviacioEstandardMostral) {
		this.idRestaurant=idRestaurant;
		this.desviacioEstandardMostral=desviacioEstandardMostral;
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
	 * getter desviacio
	 * @return desviacio
	 */
	public double getDesviacioEstandardMostral() {
		return desviacioEstandardMostral;
	}

	/**
	 * setter
	 * @param desviacioEstandardMostral desviacio
	 */
	public void setDesviacioEstandardMostral(double desviacioEstandardMostral) {
		this.desviacioEstandardMostral = desviacioEstandardMostral;
	}

	@Override
	public String toString() {
		return "DesviacioEstMostralRestaurant [idRestaurant=" + idRestaurant + ", desviacioEstandardMostral="
				+ desviacioEstandardMostral + "]";
	}
	
	
	

}

package Estudi;
/**
 * Classe que guarda la desviació absoluta per restaurant
 * @author Cesc
 *
 */
public class DesviacioAbsRestaurant {
	
	private int idRestaurant;
	
	private double desviacioAbsMitjana;
	
	/**
	 * constructor buit
	 */
	public DesviacioAbsRestaurant() {}
	
	/**
	 * constructor
	 * @param idRestaurant id restaurant
	 * @param desviacioAbsMitjana desviacio
	 */
	public DesviacioAbsRestaurant(int idRestaurant, double desviacioAbsMitjana) {
		this.idRestaurant=idRestaurant;
		this.desviacioAbsMitjana=desviacioAbsMitjana;
	}

	/**
	 * Getter
	 * @return id restaurant
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
	 * @return desviacio absoluta
	 */
	public double getDesviacioAbsMitjana() {
		return desviacioAbsMitjana;
	}

	/**
	 * setter desviacio absoluta mitjana
	 * @param desviacioAbsMitjana desviacio absoluta mitjana
	 */
	public void setDesviacioAbsMitjana(double desviacioAbsMitjana) {
		this.desviacioAbsMitjana = desviacioAbsMitjana;
	}

	@Override
	public String toString() {
		return "DesviacioAbsRestaurant [idRestaurant=" + idRestaurant + ", desviacioAbsMitjana=" + desviacioAbsMitjana
				+ "]";
	}
	
	
	

}

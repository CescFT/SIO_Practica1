package Estudi;

/**
 * Classe que conte la mitjana de cada restaurant
 * @author Cesc
 *
 */
public class MitjRestaurant {
	
	private int idRestaurant;
	
	private double mitjana;
	
	/**
	 * constructor
	 */
	public MitjRestaurant() {}
	
	/**
	 * constructor
	 * @param idRestaurant idrestaurant
	 * @param mitjana mitjana
	 */
	public MitjRestaurant(int idRestaurant, double mitjana) {
		this.idRestaurant=idRestaurant;
		this.mitjana=mitjana;
	}

	/**
	 * getter
	 * @return id
	 */
	public int getIdRestaurant() {
		return idRestaurant;
	}

	/**
	 * setter
	 * @param idRestaurant id
	 */
	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	/**
	 * getter
	 * @return mitjana
	 */
	public double getMitjana() {
		return mitjana;
	}

	/**
	 * setter
	 * @param mitjana mitjana
	 */
	public void setMitjana(double mitjana) {
		this.mitjana = mitjana;
	}

	@Override
	public String toString() {
		return "MitjRestaurant [idRestaurant=" + idRestaurant + ", mitjana=" + mitjana + "]";
	}
	
	
	

}

package Estudi;

/**
 * Classe que emmagatzema la desviacio estandard poblacional per restaurant
 * @author Cesc
 *
 */
public class DesviacioEstandardPoblacionalPerRestaurant {
	
	private int idRestaurant;
	
	private double desvEstPob;
	
	/**
	 * constructor
	 */
	public DesviacioEstandardPoblacionalPerRestaurant() {}
	
	/**
	 * constructor amb parametres
	 * @param idRestaurant id restaurant
	 * @param desviacio desviacio
	 */
	public DesviacioEstandardPoblacionalPerRestaurant(int idRestaurant, double desviacio) {
		this.idRestaurant=idRestaurant;
		this.desvEstPob=desviacio;
	}

	/**
	 * getter del id del restaurant
	 * @return id restaurant
	 */
	public int getIdRestaurant() {
		return idRestaurant;
	}

	/**
	 * setter del id del restaurant
	 * @param idRestaurant id
	 */
	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	/**
	 * getter
	 * @return desviació
	 */
	public double getDesvEstPob() {
		return desvEstPob;
	}

	/**
	 * setter desviació estandard poblacional
	 * @param desvEstPob desviació
	 */
	public void setDesvEstPob(double desvEstPob) {
		this.desvEstPob = desvEstPob;
	}

	@Override
	public String toString() {
		return "DesviacioEstandardPoblacionalPerRestaurant [idRestaurant=" + idRestaurant + ", desvEstPob=" + desvEstPob
				+ "]";
	}
	
	

}

package Estudi;

/**
 * Classe que conté la relació entre el usuari i el restaurant
 * @author Cesc
 *
 */
public class RelUsrRest {
	
	private int idUsuari;
	private int idRestaurant;
	
	/**
	 * constructor
	 */
	public RelUsrRest() {}
	
	/**
	 * constructor
	 * @param idUsuari id usr
	 * @param idRestaurant id restaurant
	 */
	public RelUsrRest(int idUsuari, int idRestaurant) {
		this.idUsuari=idUsuari;
		this.idRestaurant=idRestaurant;
	}

	/**
	 * getter 
	 * @return id usuari
	 */
	public int getIdUsuari() {
		return idUsuari;
	}

	/**
	 * setter
	 * @param idUsuari idusuari
	 */
	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
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

	@Override
	public String toString() {
		return "RelUsrRest [idUsuari=" + idUsuari + ", idRestaurant=" + idRestaurant + "]";
	}

	
	
}

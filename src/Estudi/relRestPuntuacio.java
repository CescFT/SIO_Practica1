package Estudi;

import java.util.*;

/**
 * 
 * @author Cesc
 *
 */
public class relRestPuntuacio {
	private int idRestaurant;
	private List<Double> llistatPuntuacions;
	
	/**
	 * constructor
	 */
	public relRestPuntuacio() { llistatPuntuacions = new ArrayList<Double>();}
	
	/**
	 * constructor
	 * @param idRestaurant id
	 * @param llistat llista
	 */
	public relRestPuntuacio(int idRestaurant, List<Double> llistat) {
		this.idRestaurant = idRestaurant;
		this.llistatPuntuacions = llistat;
	}
	
	/**
	 * Mètode que permet afegir una puntuacio
	 * @param puntuacio puntuacio
	 */ 
	public void afegirPuntuacio(double puntuacio) {
		llistatPuntuacions.add(puntuacio);
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
	 * @return llistat puntuacio
	 */
	public List<Double> getLlistatPuntuacions() {
		return llistatPuntuacions;
	}

	/**
	 * setter 
	 * @param llistatPuntuacions llistat puntuacio
	 */
	public void setLlistatPuntuacions(List<Double> llistatPuntuacions) {
		this.llistatPuntuacions = llistatPuntuacions;
	}
	
	/**
	 * Mètode que retorn el llistat en forma de array
	 * @return llistat puntuacio
	 */
	public String puntuacions() {
		String res="[";
		for(Double d:llistatPuntuacions) {
			if(llistatPuntuacions.indexOf(d) == llistatPuntuacions.size()-1) {
				res+=String.valueOf(d);
			}else {
				res+=String.valueOf(d)+",";
			}
			
		}
		res+="]";
		return res;
	}

	@Override
	public String toString() {
		return "relRestPuntuacio [idRestaurant=" + idRestaurant + ", llistatPuntuacions=" + llistatPuntuacions + "]";
	}
	
}

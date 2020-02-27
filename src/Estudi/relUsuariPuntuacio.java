package Estudi;

import java.util.*;

/**
 * Classe que conté la relació entre l'usuari i la puntuació
 * @author Cesc
 *
 */
public class relUsuariPuntuacio {
	
	private int idUsuari;
	private List<Double> llistatPuntuacions;
	
	/**
	 * constructor
	 */
	public relUsuariPuntuacio() { llistatPuntuacions = new ArrayList<Double>();}
	
	/**
	 * constructor
	 * @param idUsuari id
	 * @param llistat llista de puntuacions
	 */
	public relUsuariPuntuacio(int idUsuari, List<Double> llistat) {
		this.idUsuari =  idUsuari;
		this.llistatPuntuacions = llistat;
	}
	
	/**
	 * Mètode que permet afegir puntuacions
	 * @param puntuacio puntuacio
	 */
	public void afegirPuntuacio(double puntuacio) {
		llistatPuntuacions.add(puntuacio);
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
	 * @return llistat de puntuacions
	 */
	public List<Double> getLlistatPuntuacions() {
		return llistatPuntuacions;
	}

	/**
	 * setter
	 * @param llistatPuntuacions llistat de puntuacions
	 */
	public void setLlistatPuntuacions(List<Double> llistatPuntuacions) {
		this.llistatPuntuacions = llistatPuntuacions;
	}

	/**
	 * Mètode que retorna un string de puntuacions
	 * @return llistat de puntuacions
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
		return "relUsuariPuntuacio [idUsuari=" + idUsuari + ", llistatPuntuacions=" + llistatPuntuacions + "]";
	}

}

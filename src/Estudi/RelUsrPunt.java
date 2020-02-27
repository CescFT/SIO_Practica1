package Estudi;

/**
 * Classe que conte la relacio entre els usuaris i els punts
 * @author Cesc
 *
 */
public class RelUsrPunt implements Comparable<RelUsrPunt>{
	
	private int idUsuari;
	private double puntuacio;
	
	/**
	 * constructor
	 */
	public RelUsrPunt() {}
	
	/**
	 * constructor
	 * @param idUsuari id
	 * @param puntuacio puntuacio
	 */
	public RelUsrPunt(int idUsuari, double puntuacio) {
		this.idUsuari = idUsuari;
		this.puntuacio = puntuacio;
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
	 * @return puntuacio
	 */
	public double getPuntuacio() {
		return puntuacio;
	}

	/**
	 * setter puntuacio
	 * @param puntuacio
	 */
	public void setPuntuacio(double puntuacio) {
		this.puntuacio = puntuacio;
	}

	@Override
	public String toString() {
		return "RelUsrPunt [idUsuari=" + idUsuari + ", puntuacio=" + puntuacio + "]";
	}
	

	@Override
	public int compareTo(RelUsrPunt o) {
		if (this.puntuacio > o.getPuntuacio())
			return 1;
		else if (this.puntuacio<o.getPuntuacio())
			return -1;
		else return 0;
	}

}

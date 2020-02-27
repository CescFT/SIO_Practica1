package Estudi;

/**
 * Classe que emmagatzema la desviació estandard mostral x usuari
 * @author Cesc
 *
 */
public class DesviacioEstMostralUsuari {
	
	private int idUsuari;
	
	private double desvestm;
	
	/**
	 * constructor
	 */
	public DesviacioEstMostralUsuari() {}
	
	/**
	 * constructor
	 * @param idUsuari id usuari
	 * @param desviacio desviacio
	 */
	public DesviacioEstMostralUsuari(int idUsuari, double desviacio) {
		this.idUsuari=idUsuari;
		this.desvestm = desviacio;
	}

	/**
	 * getter
	 * @return id usuari
	 */
	public int getIdUsuari() {
		return idUsuari;
	}

	/**
	 * setter id usuari
	 * @param idUsuari id usuari
	 */
	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	/**
	 * getter
	 * @return desviacio
	 */
	public double getDesvestm() {
		return desvestm;
	}

	/**
	 * setter 
	 * @param desvestm desviacio
	 */
	public void setDesvestm(double desvestm) {
		this.desvestm = desvestm;
	}

	@Override
	public String toString() {
		return "DesviacioEstMostralUsuari [idUsuari=" + idUsuari + ", desvestm=" + desvestm + "]";
	}

	
	
}

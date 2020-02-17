package model;

public class Usuari {
	
	private int id;
	
	public Usuari() {}
	
	public Usuari(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	@Override
	public String toString() {
		return "Usuari [id=" + id + "]";
	}
	
	
	

}

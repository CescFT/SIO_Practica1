package Cluster;

public class Point {
	
	private int ID;
	private int idCluster;
	private double score;
	

	public Point(int iD, double score) {
		this.idCluster=-1;
		this.ID = iD;
		this.score=score;
	}
	
	public Point(int iD, double score, int idCluster) {
		ID = iD;
		this.idCluster = idCluster;
		this.score = score;
	}

	
	public int getIdCluster() {
		return idCluster;
	}


	public void setIdCluster(int idCluster) {
		this.idCluster = idCluster;
	}


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public double distance(Point centroid) {
        return Math.abs(centroid.getScore()-this.getScore());
    }


	@Override
	public String toString() {
		return "Point [ID=" + ID + ", idCluster=" + idCluster + ", score=" + score + "]";
	}

	
	
	

}

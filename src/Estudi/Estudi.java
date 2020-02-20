package Estudi;

import java.sql.*;
import java.util.*;


public class Estudi {
	
	private Statement statement;
	
	
	public Estudi(Statement stmt) {
		this.statement=stmt;
	}
	
	
	public int comptarRestaurants() throws Exception {
		String sql="SELECT count(*) AS rowcount FROM restaurant";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
		
	}
	
	
	public int comptarUsuaris() throws Exception{
		String sql = "SELECT count(*) AS rowcount FROM usuari";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	
	public int comptarNoVotats() throws Exception{
		String sql = "SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio=99.00";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	public int comptarVotats() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio!=99.00";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	public int comptarVotsNegatius() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio>=-10 AND puntuacio<=-0.01)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	public int comptarVotsZero() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio=0.00)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	public int comptarVotsPositius() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio>0.00 AND puntuacio<=10)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	public double mitjanaTotesPuntuacions() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio IN (SELECT puntuacio FROM relusrrest WHERE puntuacio!=99.00)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		
		int totalEntrades=rs.getInt("rowcount");
		
		double totalEntry=Double.valueOf(totalEntrades);
		
		sql="SELECT SUM(puntuacio) AS rowcount FROM relusrrest WHERE puntuacio IN (SELECT puntuacio FROM relusrrest WHERE puntuacio!=99.00)";
		rs=statement.executeQuery(sql);
		rs.next();
		double suma=rs.getFloat("rowcount");
		
		return suma/totalEntry;
		
	}
	
	public List<Double> mitjanaPuntuacioCadaRestaurant() throws Exception{
		
		List<Double> mitjanes = new ArrayList<Double>();
		ResultSet rs;
		String sql;
		for(int i=1; i<=100; i++) {
			sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (restaurant="+i+" AND puntuacio!=99.00)";
			rs=statement.executeQuery(sql);
			rs.next();
			int totalP=rs.getInt("rowcount");
			double totalPuntuacions=Double.valueOf(totalP);
			sql= "SELECT SUM(puntuacio) AS rowcount FROM relusrrest WHERE (restaurant="+i+" AND puntuacio!=99.00)";
			rs=statement.executeQuery(sql);
			rs.next();
			double suma=rs.getFloat("rowcount");
			mitjanes.add(suma/totalPuntuacions);
			System.out.println(i+"de 100");
		}
		return mitjanes;
	}
	
	
	public float puntuacioMesAlta() throws Exception{
		String sql="SELECT MAX(puntuacio) AS maxValue"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio!=99.00)";
		ResultSet rs;
		rs = statement.executeQuery(sql);
		rs.next();
		return rs.getFloat("maxValue");
		
	}
	
	public float puntuacioMesBaixa() throws Exception{
		String sql="SELECT MIN(puntuacio) AS minValue"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio!=99.00)";
		ResultSet rs;
		rs = statement.executeQuery(sql);
		rs.next();
		return rs.getFloat("minValue");
	}
	
	
	public List<RelUsrRest> relacioUsuariPuntuacio10Restaurant() throws Exception{
		String sql="SELECT usuari,restaurant"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio=(SELECT MAX(puntuacio)"
								  + " FROM relusrrest"
								  + " WHERE (puntuacio!=99.00))"
				+ ")";
		ResultSet rs;
		List<RelUsrRest> rel = new ArrayList<RelUsrRest>();
		rs = statement.executeQuery(sql);
		while(rs.next()) {
			rel.add(new RelUsrRest(rs.getInt("usuari"),rs.getInt("restaurant")));
		}
		
		return rel;
	}
	
	
	public List<RelUsrRest> relacioUsuariPuntuacioMesBaixaRestaurant() throws Exception{
		String sql="SELECT usuari,restaurant"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio=(SELECT MIN(puntuacio)"
								  + " FROM relusrrest"
								  + " WHERE (puntuacio!=99.00))"
						+ ")";
		ResultSet rs;
		List<RelUsrRest> rel = new ArrayList<RelUsrRest>();
		rs = statement.executeQuery(sql);
		while(rs.next()) {
			rel.add(new RelUsrRest(rs.getInt("usuari"), rs.getInt("restaurant")));
		}
		return rel;
	}
	
	
	
	public Double percentatgeRestaurantsAprobats() throws Exception{
		int numRestaurants=this.comptarRestaurants();
		List<Double> mitjanes = this.mitjanaPuntuacioCadaRestaurant();
		
		int aprobats=0;
		
		for(Double d:mitjanes) {
			if(d>0) 
				aprobats++;
		}
		double numAprobats=Double.valueOf(aprobats);
		double numRestaurantsT = Double.valueOf(numRestaurants);
		double percentatge=numAprobats/numRestaurantsT;
		
		return percentatge;
	}
	
	public Double percentatgeRestaurantsSuspesos() throws Exception{
		int numRestaurants=this.comptarRestaurants();
		List<Double> mitjanes = this.mitjanaPuntuacioCadaRestaurant();
		
		int suspesos=0;
		
		for(Double d:mitjanes) {
			if(d<0) 
				suspesos++;
		}
		double numSuspesos=Double.valueOf(suspesos);
		double numRestaurantsT = Double.valueOf(numRestaurants);
		double percentatge=numSuspesos/numRestaurantsT;
		
		return percentatge;
	}
	
	
	
	

}

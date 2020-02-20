package Estudi;

import java.sql.*;
import java.util.*;


public class Estudi {
	
	private int NUMUSUARIS=73421;
	private int NUMRESTAURANTS=100;
	
	private Statement statement;
	
	
	public Estudi(Statement stmt) {
		this.statement=stmt;
	}
	
	/*
	 * RECOMPTES
	 */
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de restaurants
	 * @return numero de restaurants
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarRestaurants() throws Exception {
		String sql="SELECT count(*) AS rowcount FROM restaurant";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
		
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero d'usuaris
	 * @return numero d'usuaris
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarUsuaris() throws Exception{
		String sql = "SELECT count(*) AS rowcount FROM usuari";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots nuls en tota la mostra.
	 * @return n�mero de espais no votats.
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarNoVotats() throws Exception{
		String sql = "SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio=99.00";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots no nuls en tota la mostra.
	 * @return n�mero de vots no nuls
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotats() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio!=99.00";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots negatius en tota la mostra.
	 * @return n�mero de vots negatius
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotsNegatius() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio>=-10 AND puntuacio<=-0.01)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero igual a zero en tota la mostra.
	 * @return n�mero de vots equivalents a zero.
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotsZero() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio=0.00)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots positius en tota la mostra.
	 * @return vots positius
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotsPositius() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio>0.00 AND puntuacio<=10)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que retorna els usuaris que han visitat els restaurants (Influ�ncia de gent en cadascun dels restaurants).
	 * @return llistat amb cadascun dels restaurants i la influ�ncia de gent.
	 * @throws Exception SQL EXCEPTION
	 */
	public List<InfluenciaGent> numeroDeVisitesXRestaurant() throws Exception{
		List<InfluenciaGent> influencia = new ArrayList<InfluenciaGent>();
		ResultSet rs;
		String sql="";
		
		for(int i = 1; i<=NUMRESTAURANTS; i++) {
			sql="SELECT count(*) AS vegades FROM relusrrest WHERE (restaurant="+i+" AND puntuacio !=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			influencia.add(new InfluenciaGent(i,rs.getInt("vegades")));
		}
		
		Collections.sort(influencia);
		return influencia;
	}
	
	/**
	 * M�tode de recompte que retorna quants restaurants han visitat cadascun dels usuaris. 
	 * @return llistat amb cadascun dels usuaris quants han visitat.
	 * @throws Exception SQL EXCEPTION
	 */
	public List<RestVisitats> numeroDeRestaurantsVisitats()throws Exception{
		List<RestVisitats> numVisitats = new ArrayList<RestVisitats>();
		String sql;
		ResultSet rs;
		
		for(int i = 1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs=statement.executeQuery(sql);
			rs.next();
			numVisitats.add(new RestVisitats(i,rs.getInt("visitats")));
		}
		
		Collections.sort(numVisitats);
		
		return numVisitats;
	}
	
	/**
	 * M�tode que retorna el percentatge de restaurants aprobats.
	 * @return Percentatge de restaurants aprobats de la mostra.
	 * @throws Exception SQL EXCEPTION
	 */
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
		
		return percentatge*100;
	}
	
	/**
	 * M�tode que retorna el percentatge de restaurants suspesos.
	 * @return Percentatge de restaurants suspesos en la mostra.
	 * @throws Exception SQL EXCEPTION
	 */
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
		
		return percentatge*100;
	}
	
	
	/*
	 * MITJANES i DIAGRAMES DE DISPERSI�
	 * 
	 */
	
	/**
	 * M�tode que retorna la mitjana de totes les puntuacions de la mostra.
	 * @return mitjana
	 * @throws Exception SQL EXCEPTION
	 */
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
	
	/**
	 * M�tode que retorna la mitjana de puntacions de cada restaurant.  (tamb� servir� per a realitzar una grafica de dispersi�!!)
	 * @return Mitjana de cada restaurant.
	 * @throws Exception SQL EXEPTION
	 */
	public List<Double> mitjanaPuntuacioCadaRestaurant() throws Exception{
		
		List<Double> mitjanes = new ArrayList<Double>();
		ResultSet rs;
		String sql;
		for(int i=1; i<=NUMRESTAURANTS; i++) {
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
	
	/**
	 * M�tode que retorna la mitjana de puntacions de cada usuari.  (tamb� servir� per a realitzar una grafica de dispersi�!!)
	 * @return llistat de mitjanes i usuaris
	 * @throws Exception SQL EXCEPTION
	 */
	public List<RelUsrPunt> mitjanaPuntuacioCadaUsuari() throws Exception{
		List<RelUsrPunt> mitjanesUsuaris = new ArrayList<RelUsrPunt>();
		ResultSet rs;
		String sql;
		for(int i = 1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			int compt=rs.getInt("visitats");
			float comptF=Float.valueOf(compt);
			sql="SELECT SUM(puntuacio) AS suma FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			float sum=rs.getFloat("suma");
			mitjanesUsuaris.add(new RelUsrPunt(i,sum/comptF));
		}
		Collections.sort(mitjanesUsuaris);
		return mitjanesUsuaris;
	}
	
	/*
	 * ALTRES
	 */
	
	/**
	 * M�tode que retorna l'usuari i el restaurant el qual ha puntuat amb un 10 (m�xima nota).
	 * @return llistat amb tots i cadascun dels usuaris que han puntuat amb un 10 algun dels restaurants.
	 * @throws Exception SQL EXCEPTION
	 */
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
	
	/**
	 * M�tode que retorna l'usuari i el restaurant el qual ha puntuat amb la pitjor nota.
	 * @return llistat amb tots i cadascun dels usuaris amb el restaurant el qual han puntuat amb la pitjor nota.
	 * @throws Exception SQL EXCEPTION
	 */
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
	
	/**
	 * M�tode que retorna el ID del usuari que ha visitat m�s restaurants.
	 * @return id del usuari
	 * @throws Exception SQL EXCEPTION
	 */
	public String usuariAmbMesRestaurantsVisitats() throws Exception{
		String resultat="", sql;
		int resultatMax=-9999;
		ResultSet rs;
		for(int i=1; i<=NUMUSUARIS; i++) {
				sql="SELECT count(*) AS restaurantsVisitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
				rs = statement.executeQuery(sql);
				rs.next();
				int compt=rs.getInt("restaurantsVisitats");
				if(resultatMax < compt) {
					resultat="L'usuari "+i+" ha visitat: "+compt+" vegades.";
					resultatMax=compt;
				}
		}
		
		return resultat;
	}
	
	/**
	 * M�tode retorna l'usuari que ha votat pitjor. 
	 * @return id del usuari
	 * @throws Exception SQL EXCEPTION
	 */
	public int usuariQueVotaPitjor() throws Exception{
		List<RelUsrPunt> mitjanesUsuaris = new ArrayList<RelUsrPunt>();
		ResultSet rs;
		String sql;
		for(int i=1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			int compt=rs.getInt("visitats");
			float comptF=Float.valueOf(compt);
			sql="SELECT SUM(puntuacio) AS suma FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			float sum=rs.getFloat("suma");
			mitjanesUsuaris.add(new RelUsrPunt(i,sum/comptF));
		}
		
		Collections.sort(mitjanesUsuaris);
		Collections.reverse(mitjanesUsuaris);
		
		return mitjanesUsuaris.get(0).getIdUsuari();
	}
	
	/**
	 * M�tode que retorna l'usuari que ha votat millor.
	 * @return id del usuari que ha votat millor
	 * @throws Exception SQL EXCEPTION
	 */
	public int usuariQueVotaMillor() throws Exception{
		List<RelUsrPunt> mitjanesPuntsXUsr = new ArrayList<RelUsrPunt>();
		ResultSet rs;
		String sql;
		for(int i = 1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			int compt=rs.getInt("visitats");
			float comptF=Float.valueOf(compt);
			sql="SELECT SUM(puntuacio) AS suma FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			float sum=rs.getFloat("suma");
			mitjanesPuntsXUsr.add(new RelUsrPunt(i,sum/comptF));
		}
		
		Collections.sort(mitjanesPuntsXUsr);
		
		return mitjanesPuntsXUsr.get(0).getIdUsuari();
	}
	
	
	/**
	 * M�tode que retorna la puntuaci� m�s alta de totes les presents.
	 * @return puntuaci� m�s alta.
	 * @throws Exception SQL EXCEPTION
	 */
	public float puntuacioMesAlta() throws Exception{
		String sql="SELECT MAX(puntuacio) AS maxValue"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio!=99.00)";
		ResultSet rs;
		rs = statement.executeQuery(sql);
		rs.next();
		return rs.getFloat("maxValue");
		
	}
	
	/**
	 * M�tode que retorna la puntuaci� m�s baixa de totes de la mostra.
	 * @return puntuaci� m�s baixa
	 * @throws Exception SQL EXCEPTION
	 */ 
	public float puntuacioMesBaixa() throws Exception{
		String sql="SELECT MIN(puntuacio) AS minValue"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio!=99.00)";
		ResultSet rs;
		rs = statement.executeQuery(sql);
		rs.next();
		return rs.getFloat("minValue");
	}

}

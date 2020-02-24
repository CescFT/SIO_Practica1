package ConnectionToDB;

import java.io.*;
import java.sql.*;
import Estudi.*;
import java.util.*;

import java.io.FileOutputStream;
import java.io.IOException;
 

public class ConnexioDB {
	
	private static Scanner teclat = new Scanner(System.in);
	
	private static Connection connexioDB() {
		System.out.println("Establint la connexió amb la base de dades...");
		try {
			Class.forName("org.mariadb.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/SIO", "root", "");
			System.out.println("Connexió establerta amb la base de dades!");
			return conn;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
	private static void crearTaules(Statement sentenciaSQL, Connection conn) throws Exception {
		sentenciaSQL = conn.createStatement();
		String taulaUsuari = "CREATE TABLE USUARI "
                			+ "(id int(5) not NULL UNIQUE, "
                			+ "constraint pkusuari primary key (id)"
                			+")engine=innodb;";
		
		System.out.println(taulaUsuari);
		
		String taulaRestaurant = "CREATE TABLE RESTAURANT"
								+"(id int(3) not NULL UNIQUE,"
								+"constraint pkrestaurant primary key (id)"
								+")engine=innodb;";
		
		System.out.println(taulaRestaurant);
		
		
		String taulaRelacio = "CREATE TABLE RELUSRREST"
							  +"(usuari int(5) not NULL,"
							  +"restaurant int(3) not NULL,"
							  + "puntuacio decimal(5,2) not NULL,"
							  +"constraint fk_usuari foreign key (usuari) references usuari(id),"
							  +"constraint fk_restaurant foreign key (restaurant) references restaurant(id)"
							  +")engine=innodb;";
		
		
		System.out.println(taulaRelacio);
		
		
		sentenciaSQL.executeUpdate(taulaUsuari);
		sentenciaSQL.executeUpdate(taulaRestaurant);
		sentenciaSQL.executeUpdate(taulaRelacio);
		
	}
	
	
	
	private static void creacioTaules(Statement stmt, Connection connexio) {
		System.out.println("Vols crear taules [S/n]?");
		String op = teclat.nextLine();
		while(!op.toLowerCase().equals("s") && !op.toLowerCase().equals("n")) {
			System.out.println("Siusplau, posa una opció correcta. [S/n]");
			op = teclat.nextLine();
		}
		
		if (op.toLowerCase().equals("s")) {
			System.out.println("Creant les taules...");
			
			try {
				crearTaules(stmt, connexio);
				System.out.println("Taules creades correctament.");
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
	
	private static void guardarDadesADB(String nomF, Connection c){
		System.out.println("Vols emmagatzemar les dades?[S/n]");
		String op = teclat.nextLine();
		while(!op.toLowerCase().equals("s") && !op.toLowerCase().equals("n")) {
			System.out.println("Siusplau, posa una opció correcta. [S/n]");
			op=teclat.nextLine();
		}
		
		if(op.toLowerCase().equals("s")) {
			System.out.println("Quin fitxer vols generar?");
			System.out.println("1. Usuaris");
			System.out.println("2. Restaurants");
			System.out.println("3. Relació usuaris - restaurants");
			
			int op1 = Integer.valueOf(teclat.nextLine());
			
			while(op1!=1 && op1!=2 && op1!=3) {
				System.out.println("Siusplau, posa una opció correcta.");
				System.out.println("1. Usuaris");
				System.out.println("2. Restaurants");
				System.out.println("3. Relació usuaris - restaurants");
				op1=Integer.valueOf(teclat.nextLine());
			}
			
			
			try {
				switch(op1) {
				case 1:
					fitxerUsuaris();
					System.out.println("EXECUTA AL SERVIDOR SQL LA SEGÜENT SENTÈNCIA:");
					System.out.println("LOAD DATA LOCAL INFILE 'abspath/usuaris.txt' INTO TABLE usuari;");
					break;
				case 2:
					fitxerRestaurants();
					System.out.println("EXECUTA AL SERVIDOR SQL LA SEGÜENT SENTÈNCIA:");
					System.out.println("LOAD DATA LOCAL INFILE 'abspath/restaurants.txt' INTO TABLE restaurant;");
					break;
				case 3:
					processarFitxerIGenerarFitxerRelacio(nomF);
					System.out.println("EXECUTA AL SERVIDOR SQL LA SEGÜENT SENTÈNCIA:");
					System.out.println("LOAD DATA LOCAL INFILE 'abspath/relacio.txt' INTO TABLE relusrrest CHARACTER SET UTF8 FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';");
					break;
				}
				
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}
		
	}
	
	private static void fitxerRestaurants() throws IOException{
		BufferedWriter bOut = new BufferedWriter(new FileWriter("restaurants.txt"));
		
		for(int i=1; i<=100; i++) {
			bOut.write(String.valueOf(i));
			bOut.newLine();
		}
		
		bOut.close();
	}
	
	private static void fitxerUsuaris() throws IOException{
		BufferedWriter bOut = new BufferedWriter(new FileWriter("usuaris.txt"));
		
		for(int i=1; i<=73421; i++) {
			bOut.write(String.valueOf(i));
			bOut.newLine();
		}
		bOut.close();
	}
	
	
	
	private static void processarFitxerIGenerarFitxerRelacio(String nomFitxer) throws IOException{
		BufferedReader bIn = new BufferedReader(new FileReader(nomFitxer));
		BufferedWriter bOut = new BufferedWriter(new FileWriter("relacio.txt"));
		String linia=bIn.readLine();
		int usuari = 1;
		int restaurant = 1;
		while(linia!=null) {
			String[] liniaT = linia.split(";");
			if(!liniaT[0].equals("DATASET")) {
					String sortida=String.valueOf(usuari)+",";
					for(restaurant=1; restaurant<=100; restaurant++) {
						if(restaurant==1) {
							sortida+=String.valueOf(restaurant)+",";
							sortida+=String.valueOf(liniaT[restaurant]);
							System.out.println(sortida);
							bOut.write(sortida);
						}
						
						if(restaurant>1) {
							sortida=String.valueOf(usuari)+","+String.valueOf(restaurant)+","+String.valueOf(liniaT[restaurant]);
							System.out.println(sortida);
							bOut.write(sortida);
						}
						bOut.newLine();
					}
					restaurant=1;
					usuari++;
			}
			
			linia=bIn.readLine();
		}
		
		bIn.close();
		bOut.close();
	}
	
	
	private static void mitjanesCadaRestaurant(Estudi e) throws Exception{
		List<Double> mitjanes = e.mitjanaPuntuacioCadaRestaurant();
		System.out.println("RESTAURANT\t\tPUNTUACIO");
		int i=1;
		
		for(Double d:mitjanes) {
			System.out.println(i+"\t\t"+d);
			i++;
		}
		
		
	}
	
		
	
	
	public static void main (String[] args) {
		
		Connection connexio=null;
		Statement stmt=null;
		connexio = connexioDB();
		System.out.println("Vols fer les inicialitzacions de la base de dades? (crear taules i generar fitxers auxiliars que permetin inserir informació en la bd) [S/n]");
		String op=teclat.nextLine();
		while(!op.toLowerCase().equals("s") && !op.toLowerCase().equals("n")) {
			System.out.println("Siusplau, posa una opció correcta.[S/n]");
			op=teclat.nextLine();
		}
		
		
		if(op.toLowerCase().equals("s"))
		{
			creacioTaules(stmt, connexio);
			guardarDadesADB("dataset.csv", connexio);
		}
		
		System.out.println("ESTUDI:");
		try {
			stmt = connexio.createStatement();
			Estudi estudi = new Estudi(stmt);
			
			//System.out.println("Nombre de restaurants: "+estudi.comptarRestaurants()+" restaurants."); //100
			
			//System.out.println("Nombre d'usuaris: "+estudi.comptarUsuaris()+" usuaris.");//73421
			
			//System.out.println("Nombre de puntuacions buides(99.00): "+estudi.comptarNoVotats()+" restaurants no votats.");//3245740
			
			//System.out.println("Nombre de puntuacions no buides: "+estudi.comptarVotats()+" restaurants votats.");//4096360
			
			//System.out.println("Nombre de vots negatius [-10,-0.01]: "+estudi.comptarVotsNegatius()+" vots negatius.");//16889608
			
			//System.out.println("Nombre de vots neutres (0.00): "+estudi.comptarVotsZero()+" vots zero."); //12482
			
			//System.out.println("Nombre de vots positius (0,10]: "+estudi.comptarVotsPositius()+" vots positius."); //2394970
			
			//System.out.println("Puntuació mitjana que els usuaris de la mostra han donat (sumaTotalPuntuacions/totalPuntuacions):"+estudi.mitjanaTotesPuntuacions()+" de mitjana."); //0.7418716250524856
			
			//mitjanesCadaRestaurant(estudi);
			
			//System.out.println(estudi.relacioUsuariPuntuacio10Restaurant());
			
			//System.out.println(estudi.relacioUsuariPuntuacioMesBaixaRestaurant());
			
			//System.out.println(estudi.usuariAmbMesRestaurantsVisitats());
			
			//System.out.println(estudi.usuariQueVotaMillor());
			
			//System.out.println(estudi.probabilitatDeCadaPuntuacio());
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		
		
		
		
	}

}

package ConnectionToDB;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

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
			System.out.println("Emmagatzemant les files a la base de dades...");
			try {
				processarFitxerIGuardarDadesDB(nomF, c);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
		
	}
	
	
	
	private static void processarFitxerIGuardarDadesDB(String nomFitxer, Connection conn) throws SQLException, IOException{
		BufferedReader bIn = new BufferedReader(new FileReader(nomFitxer));
		String linia=bIn.readLine();
		int usuari = 1;
		int restaurant = 1;
		boolean primerCop=true;
		while(linia!=null) {
			String[] liniaT = linia.split(";");
			if(!liniaT[0].equals("DATASET")) {
				String emmagatzemarUsuari = "INSERT INTO USUARI(id) VALUES("+usuari+");";
				PreparedStatement preparedStmt = conn.prepareStatement(emmagatzemarUsuari);
				preparedStmt.execute();
				System.out.println(emmagatzemarUsuari);
				for(int i=1; i<=100; i++) {
					if (primerCop) {
						String emmagatzemarRestaurant="INSERT INTO RESTAURANT(id) VALUES("+restaurant+");";
						PreparedStatement pStmt = conn.prepareStatement(emmagatzemarRestaurant);
						pStmt.execute();
						System.out.println(emmagatzemarRestaurant);
					}	
					String emmagatzemarRelacio="INSERT INTO RELUSRREST(usuari,restaurant,puntuacio) VALUES ("+usuari+","+restaurant+","+Float.valueOf(liniaT[i])+");";
					PreparedStatement prepStmt = conn.prepareStatement(emmagatzemarRelacio);
					prepStmt.execute();
					System.out.println(emmagatzemarRelacio);
					restaurant++;
				}
				usuari++;
				restaurant=1;
				primerCop=false;
			}
			linia=bIn.readLine();
		}
		
		bIn.close();
	}
	
	
	public static void main (String[] args) {
		
		Connection connexio=null;
		Statement stmt=null;
		ResultSet rs;
		
		connexio = connexioDB();
		creacioTaules(stmt, connexio);
		
		guardarDadesADB("dataset.csv", connexio);
		
		
		
		
	}

}

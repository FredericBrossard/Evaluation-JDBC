package EvalJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AppliCRUD {

	/*
	 * pouvoir faire les actions ajouter, supprimer, lister dans un programme Java
	 * en mode console et de se servir d'une base de données pour le stockage des
	 * informations.
	 */
	// List of food attributes
	/*
	 * private static final String[] COLONN_NAME = { "Nom de l'aliment",
	 * "Taux NRJ(kcal)", "Taux Protéines (g/100g)", "Taux Glucides (g/100g)",
	 * "Taux Lipides (g/100g)" };
	 */

	public static void main(String[] args) {
		// Connexion Keys
		String url = "jdbc:postgresql://horton.elephantsql.com:5432/jpgnovjv";
		String user = "jpgnovjv";
		String pass = "xXqtnZW-wiE-VgiQ-qjAbmd5LLAMPDw1";

		String userChoice;
		Scanner sc = new Scanner(System.in);

		do {
			// Gestion du sommaire et options proposées à l'utilisateur
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!-----  Hello and Weclome in  'CRUD application'  ------!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("! 1) Add an aliment in database                         !");
			System.out.println("! 2) Delete an aliment in database                      !");
			System.out.println("! 3) Show me all aliment in database                    !");
			System.out.println("! 0) Quit the 'CRUD Application'. Bye                   !");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			// Récupération de la saisie clavier de l'utilisateur
			userChoice = sc.nextLine();				
			
			try {
				// 1-creer la connection ==> onnection conn = DriverManager.getConnection(url,user, pass)
				// on créé un objet Connection
				Connection conn = DriverManager.getConnection(url, user, pass);
				// Gestion avec "switch" suite à la saisie utilisateur
				switch (userChoice) {
				case "1":
					// -------Debut Questionnaire interactif avec l'utilisateur pour ajouter un
					// aliment
					System.out.println("Saisir l'aliment");
					String answerAlimName = sc.nextLine();
					System.out.println("Saisir la catégorie");
					String answerCatName = sc.nextLine();
					System.out.println(answerCatName);
					// Controle si la catégorie est connue en database
					Categories verifyCat = new Categories(answerCatName);
					selDataBaseCat(conn, verifyCat);
					
					/*
					 * if (stop == false) { System.out.println("sortir"); }
					 */
					System.out.println("Saisir lipide");
					int answerLipiName = sc.nextInt();
					System.out.println("Saisir proteine");
					int answerProteName = sc.nextInt();
					System.out.println("Saisir glucide");
					int answerGluciName = sc.nextInt();
					// ------Fin Questionnaire interactif avec l'utilisateur pour ajouter un aliment

					int txnrjcalcul = answerProteName * answerLipiName;

					Long id = verifyCat.id_categories;

					Aliment insert = new Aliment(answerAlimName, txnrjcalcul, answerLipiName, answerProteName,
							answerGluciName, id);
					addDataBase(conn, insert);
					break;
				case "2":
					System.out.println("Input delete aliment, you want ");
					String delAlimName = sc.nextLine();
					
					deleteDataBase(conn, delAlimName);
					break;
				case "3":
					// Afficher le contenu de la data base
					showDataBase(conn);
					break;
				case "0":
					System.out.println("Bye. See you soon.");
					break;
				default:
					System.out.println("Invalide choice ' " + userChoice + " '. Please restart again. ");
				}
				// Fermeture de la connection
				conn.close();
				
			} catch (Exception e) {
				System.out.println("Exception captutée : " + e);
			}
		} while (!userChoice.equals("0"));

		// Fermeture Scanner (clavier)
		sc.close();
	}

	private static void addDataBase(Connection c, Aliment aliment) throws SQLException {
		// 2 on créé un objet Statement avec l'objet Connection
		Statement stmt = c.createStatement();

		// 3 creer la requete / sqlCmd, passe une chaine de caratere qui est LA requete
		// sql
		String sqlCmd = "Insert INTO \"EVALJDBC\".aliment (name, txnrj, txproteine, txlipide, txglucide, foreign_key) VALUES ('"
				+ aliment.getName() + "'," + aliment.getTxnrj() + "," + aliment.getTxproteine() + ","
				+ aliment.getTxlipide() + "," + aliment.getTxglucide() + "," + aliment.getForeignKey() + ");";
		// 3..executer requete
		stmt.execute(sqlCmd);
	}

	private static void selDataBaseCat(Connection c, Categories cat) throws SQLException {
		/*
		 * Pour exécuter des instructions SQL sur une base de données, il y a besoin
		 * d'une instance Statement ou PreparedStatement. Ensuite lancer une requête.
		 * Elle renvoie une instance ResultSet contenant le résultat complet
		 */
		// 2..creer la requete
		Statement stmt = c.createStatement();
		// sqlCmd, passe une chaine de caratere qui est LA requete sql
		String sqlCmd = "Select \"name\", \"id_categories\" FROM \"EVALJDBC\".categories WHERE \"name\" = '" + cat.name
				+ "';";
		// 3..executer requete : stmt.executeQuery(sqlCmd)
		stmt.execute(sqlCmd);
		// 4 Récupère le résultat en tant qu'objet ResultSet.
		ResultSet rs = stmt.getResultSet();	
		
		//Exploite le Resultat, soit charge la clef primaire de de Categorie dans la clef externe de Aliment via l'objet cat
		if (rs.next()) {
			
	//		System.out.println(rs.getString(1));
	//		System.out.println(rs.getLong(2));		
			cat.id_categories= rs.getLong(2); 
		}			
	}
  
	private static void deleteDataBase(Connection c, String aliment) throws SQLException {
		// 2..creer la requete
		Statement stmt = c.createStatement();
		// 1er paramétre : sqlCmd qui permet de passé une chaine de caratere qui
		// contient la requete sql
		String sql = "Delete FROM \"EVALJDBC\".aliment WHERE name = '" + aliment + "';";
		// 3..executer requette
		stmt.executeUpdate(sql);
	}

	private static void showDataBase(Connection c) throws SQLException {
		// 2..creer la requete
		Statement stmt = c.createStatement();
		String sqlCmd = "Select * from \"EVALJDBC\".aliment";
		// 3..executer requete : stmt.executeQuery(sqlCmd)
		ResultSet rset = stmt.executeQuery(sqlCmd);
		// "rset.next" Déplace le curseur d'une rangée vers l'avant à partir de sa position actuelle.
		System.out.println("Voici la liste des aliments:");
		while (rset.next()) {
			// La méthode "getString" Récupère la valeur de la colonne désignée dans la
			// pour l'affichage du nom qui fait 250 de long, on affiche pas les espaces de fin....
			String Str = new String(rset.getString(2));
			String tmp = "" + rset.getInt(1) + " ; " + Str.trim() + " ; " + rset.getInt(3) + " ; " + rset.getLong(4)
					+ " ; " + rset.getInt(5) + " ; " + rset.getInt(6) + ";";
			System.out.println(tmp);
		}
	}

}

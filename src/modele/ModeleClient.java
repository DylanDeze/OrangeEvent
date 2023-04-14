package modele;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;

import controleur.Client;

public class ModeleClient
{	
	private static Bdd uneBdd =  new Bdd("localhost:8889","orange_Event_JV_23","root","root");
	
	public static void insertClient(Client unClient)
	{
		String requete = "insert into client values (null , '"
				+ unClient.getNom()+"','" + unClient.getPrenom() +"','"
				+ unClient.getAdresse()+"','" + unClient.getEmail()+ "');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			uneBdd.seDeconnecter();
		}
		catch (Exception exp)
		{
			System.out.println("Erreur d'execution :" +requete);
		}
	}
	public static void deleteClient (int idclient)
	{
		String requete = "delete from client where idclient="+idclient+";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			uneBdd.seDeconnecter();
		}
		catch (Exception exp)
		{
			System.out.println("Erreur d'execution :" +requete);
		}
	}
	public static void updateClient(Client unClient)
	{
		String requete = "update client set nom ='"
				+ unClient.getNom()+"', prenom ='" + unClient.getPrenom() +"', email='"
				+unClient.getEmail()+"', adresse ='" + unClient.getAdresse()
				+ "' where idclient ="+ unClient.getIdclient() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			uneBdd.seDeconnecter();
		}
		catch (Exception exp)
		{
			System.out.println("Erreur d'execution :" +requete);
		}
	}
	public static ArrayList<Client> selectAllClient (String filtre)
	{
		String requete = "";
		if (filtre.equals(""))
		{
			requete = "select * from client;";
		} else {
			requete = "select * from client where nom like '%"+filtre+"%' "
					+ " or prenom like '%"+filtre+"%' or adresse like '%"
					+ filtre +"%' or email like '%"+filtre+"%' ;";
		}
		ArrayList<Client> lesClients = new ArrayList<Client>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			// recuperation des clients resultats
			ResultSet desResultats = unStat.executeQuery(requete);
			// on parcours les resultats et on instancie les clients et enfin on les ajoute dans l'ArrayList
			while (desResultats.next())
			{
				Client unClient = new Client (
						desResultats.getInt("idclient"),desResultats.getString("nom"),
						desResultats.getString("prenom"), desResultats.getString("email"),
						desResultats.getString("adresse")
						);
				
		// on ajoute le client dans l'ArrayList
				lesClients.add(unClient);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (Exception exp)
		{
			System.out.println("Erreur d'execution :" +requete);
		}
		return lesClients;
	}
	public static Client selectWhereClient(int idclient)
	{
		String requete = "select * from client where idclient="+idclient+";";
		Client unClient = null;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			// recuperation un seul client resultat
			ResultSet unResultat = unStat.executeQuery(requete);
			// on teste si on a un seul resultat 
			if (unResultat.next())
			{
				 unClient = new Client (
						unResultat.getInt("idclient"),unResultat.getString("nom"),
						unResultat.getString("prenom"), unResultat.getString("email"),
						unResultat.getString("adresse")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (Exception exp)
		{
			System.out.println("Erreur d'execution :" +requete);
		}
		return unClient;
	}
	public static Client selectWhereClient(String email)
	{
		String requete = "select * from client where email='"+email+"';";
		Client unClient = null;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			// recuperation un seul client resultat
			ResultSet unResultat = unStat.executeQuery(requete);
			// on teste si on a un seul resultat 
			if (unResultat.next())
			{
				 unClient = new Client (
						unResultat.getInt("idclient"),unResultat.getString("nom"),
						unResultat.getString("prenom"), unResultat.getString("email"),
						unResultat.getString("adresse")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (Exception exp)
		{
			System.out.println("Erreur d'execution :" +requete);
		}
		return unClient;
	}
}

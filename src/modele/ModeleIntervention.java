package modele;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Intervention;

public class ModeleIntervention {
    private static Bdd uneBdd = new Bdd("localhost:8889", "orange_Event_JV_23", "root", "root");

    public static void insertIntervention(Intervention uneIntervention) {
        String requete = "INSERT INTO intervention VALUES ( null,'"
                + uneIntervention.getMateriel() + "','"
                + uneIntervention.getPanne() + "','"
                + uneIntervention.getDateinter() + "','"
                + uneIntervention.getPrix() + "','"
                + uneIntervention.getIdclient() + "','"
                + uneIntervention.getIdtechnicien() + "')";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void updateIntervention(Intervention uneIntervention) {
        String requete = "UPDATE intervention SET"
                + " materiel = '" + uneIntervention.getMateriel() + "',"
                + " panne = '" + uneIntervention.getPanne() + "',"
                + " dateinter = '" + uneIntervention.getDateinter() + "',"
                + " prix = '" + uneIntervention.getPrix() + "',"
                + " idclient = '" + uneIntervention.getIdclient() + "',"
                + " idtechnicien = '" + uneIntervention.getIdtechnicien() + "'"
                + " WHERE idinter = " + uneIntervention.getIdintervention() + ";";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void deleteIntervention(int idintervention) {
        String requete = "DELETE FROM intervention WHERE idinter = " + idintervention + ";";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static ArrayList<Intervention> selectAllInterventions() {
        ArrayList<Intervention> lesInterventions = new ArrayList<Intervention>();
        String requete = "SELECT * FROM intervention;";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            // recuperation des Interventions
            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
            // on parcours les resultats et on instancie les Interventions
            while (desResultats.next()) {
                Intervention uneIntervention = new Intervention(
                        desResultats.getInt("idinter"),
                        desResultats.getString("materiel"),
                        desResultats.getString("panne"),
                        desResultats.getString("dateinter"),
                        desResultats.getFloat("prix"),
                        desResultats.getInt("idclient"),
                        desResultats.getInt("idtechnicien"));
                // on ajoute l'Intervention dans l'ArrayList
                lesInterventions.add(uneIntervention);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesInterventions;
    }

    public static Intervention selectWhereIntervention(int idintervention) {
        Intervention uneIntervention = new Intervention();
        String requete = "SELECT * FROM intervention WHERE idinter = " + idintervention + ";";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            // recuperation une seul Intervention
            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
            // on teste si on a un resultat
            if (unResultat.next()) {
                uneIntervention = new Intervention(
                        unResultat.getInt("idinter"),
                        unResultat.getString("materiel"),
                        unResultat.getString("panne"),
                        unResultat.getString("dateinter"),
                        unResultat.getFloat("prix"),
                        unResultat.getInt("idclient"),
                        unResultat.getInt("idtechnicien"));
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return uneIntervention;
    }
    
    public static Intervention selectWhereIntervention(String materiel) {
        Intervention uneIntervention = new Intervention();
        String requete = "SELECT * FROM intervention WHERE materiel ='"+materiel+"';";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            // recuperation une seul Intervention
            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
            // on teste si on a un resultat
            if (unResultat.next()) {
                uneIntervention = new Intervention(
                        unResultat.getInt("idinter"),
                        unResultat.getString("materiel"),
                        unResultat.getString("panne"),
                        unResultat.getString("dateinter"),
                        unResultat.getFloat("prix"),
                        unResultat.getInt("idclient"),
                        unResultat.getInt("idtechnicien"));
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return uneIntervention;
    }
}
package modele;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Technicien;

public class ModeleTechnicien {
    private static Bdd uneBdd = new Bdd("localhost:8889", "orange_Event_JV_23", "root", "root");

    public static void insertTechnicien(Technicien unTechnicien) {
        String requete = "INSERT INTO technicien VALUES ( null,'"
                + unTechnicien.getNom() + "', '"
                + unTechnicien.getPrenom() + "', '"
                + unTechnicien.getQualification() + "', '"
                + unTechnicien.getEmail() + "', '"
                + unTechnicien.getMdp() + "');";
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

    public static void updateTechnicien(Technicien unTechnicien) {
        String requete = "UPDATE technicien SET "
                + " nom = '" + unTechnicien.getNom() + "', "
                + " prenom = '" + unTechnicien.getPrenom() + "', "
                + " qualification = '" + unTechnicien.getQualification() + "', "
                + " email = '" + unTechnicien.getEmail() + "', "
                + " mdp = '" + unTechnicien.getMdp() + "' "
                + " WHERE idtechnicien = " + unTechnicien.getIdtechnicien() + ";";
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

    public static void deleteTechnicien(int idtechnicien) {
        String requete = "DELETE FROM technicien WHERE idtechnicien = " + idtechnicien + ";";
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

    public static ArrayList<Technicien> selectAllTechnicien() {
        ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
        String requete = "SELECT * FROM technicien;";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            // recuperation des Techniciens
            java.sql.ResultSet desResultats = unStat.executeQuery(requete);
            // on parcours les resultats et on instancie les Techniciens
            while (desResultats.next()) {
                Technicien unTechnicien = new Technicien(
                        desResultats.getInt("idtechnicien"),
                        desResultats.getString("nom"),
                        desResultats.getString("prenom"),
                        desResultats.getString("qualification"),
                        desResultats.getString("email"),
                        desResultats.getString("mdp"));
                // on ajoute le Technicien dans l'ArrayList
                lesTechniciens.add(unTechnicien);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesTechniciens;
    }

    public static Technicien selectWhereTechnicien(int idtechnicien) {
        Technicien unTechnicien = null;
        String requete = "SELECT * FROM technicien WHERE idtechnicien = " + idtechnicien + ";";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            // recuperation un seul Technicien
            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
            // on teste si on a un résultat
            if (unResultat.next()) {
                unTechnicien = new Technicien(
                        unResultat.getInt("idtechnicien"),
                        unResultat.getString("nom"),
                        unResultat.getString("prenom"),
                        unResultat.getString("qualification"),
                        unResultat.getString("email"),
                        unResultat.getString("mdp"));
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unTechnicien;
    }
    
    public static Technicien selectWhereTechnicien(String email, String mdp) {
        String requete = "SELECT * FROM technicien WHERE email = '"+email+"' and mdp='"+mdp+"';";
        Technicien unTechnicien = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            // recuperation un seul Technicien
            java.sql.ResultSet unResultat = unStat.executeQuery(requete);
            // on teste si on a un résultat
            if (unResultat.next()) {
                unTechnicien = new Technicien(
                        unResultat.getInt("idtechnicien"),
                        unResultat.getString("nom"),
                        unResultat.getString("prenom"),
                        unResultat.getString("qualification"),
                        unResultat.getString("email"),
                        unResultat.getString("mdp"));
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unTechnicien;
    }
}
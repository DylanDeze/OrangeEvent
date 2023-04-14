package controleur;

import java.util.ArrayList;

import modele.ModeleTechnicien;

public class C_Technicien {

	public static void insertTechnicien(Technicien unTechnicien)
	{
		ModeleTechnicien.insertTechnicien(unTechnicien);
	}
	public static ArrayList<Technicien> selectAllTechniciens ()
	{
		return ModeleTechnicien.selectAllTechnicien();
	}
	public static void deleteTechnicien (int idTechnicien)
	{
		ModeleTechnicien.deleteTechnicien(idTechnicien);
	}
	public static void updateTechnicien(Technicien unTechnicien)
	{
		ModeleTechnicien.updateTechnicien(unTechnicien);
	}
	public static Technicien selectWhereTechnicien(int idTechnicien)
	{
		return ModeleTechnicien.selectWhereTechnicien(idTechnicien);
	}
	public static Technicien selectWhereTechnicien(String email, String mdp)
	{
		return ModeleTechnicien.selectWhereTechnicien(email, mdp);
	}
}

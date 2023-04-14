package controleur;

public class Technicien {
	private int idtechnicien;
	private String nom, prenom, email, qualification, mdp;

	public Technicien(int idtechnicien, String nom, String prenom, String qualification, String email, String mdp) {
		this.idtechnicien = idtechnicien;
		this.nom = nom;
		this.prenom = prenom;
		this.qualification = qualification;
		this.email = email;
		this.mdp = mdp;
		
}

	public Technicien(String nom, String prenom, String qualification, String email, String mdp) {
		this.idtechnicien = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.qualification = qualification;
		this.email = email;
		this.mdp = mdp;
		
}

	public Technicien() {
		this.idtechnicien = 0;
		this.nom = "";
		this.prenom = "";
		this.qualification = "";
		this.email = "";
		this.mdp = "";
		
}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getIdtechnicien() {
		return idtechnicien;
	}

	public void setIdtechnicien(int idtechnicien) {
		this.idtechnicien = idtechnicien;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	
}

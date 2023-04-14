package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Client;
import controleur.C_Technicien;
import controleur.Client;
import controleur.Tableau;
import controleur.Technicien;

public class PanelTechniciens extends PanelPrincipal implements ActionListener
{
private JPanel panelForm = new JPanel();
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtQualification = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableTechniciens;
	private Tableau unTableau;
	
	public PanelTechniciens ()
	{
		super();
		this.titre.setText("_____ Gestion des Techniciens _____");
		
		// Contruction du Panel Form
				this.panelForm.setBounds(20, 40, 350, 250);
				this.panelForm.setBackground(new Color (234, 176, 69));
				this.panelForm.setLayout(new GridLayout(6, 2));
				this.panelForm.add(new JLabel("Nom Technicien :"));
				this.panelForm.add(this.txtNom);
				this.panelForm.add(new JLabel("Prénom Technicien :"));
				this.panelForm.add(this.txtPrenom);
				this.panelForm.add(new JLabel("Qualification Technicien :"));
				this.panelForm.add(this.txtQualification);
				this.panelForm.add(new JLabel("Email Technicien :"));
				this.panelForm.add(this.txtEmail);
				this.panelForm.add(new JLabel("Mot de passe :"));
				this.panelForm.add(this.txtMdp);
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btEnregistrer);
				
				// Ajout du panelForm à au panelClients
				this.add(this.panelForm);
				
				// Construction de la JTable
				String entetes [] = {"ID Technicien", "Nom", "Prénom", "Qualification", "Email"};
				Object [][] donnees = this.getDonnees ();
				
				this.unTableau = new Tableau(donnees, entetes);
				this.tableTechniciens = new JTable(this.unTableau);
				
				JScrollPane uneScroll = new JScrollPane(this.tableTechniciens);
				uneScroll.setBounds(380, 80, 450, 210);
				this.add(uneScroll);
				
				// Rendre les boutons écoutables
				this.btAnnuler.addActionListener(this);
				this.btEnregistrer.addActionListener(this);
						
			}
	
	public Object [][] getDonnees ()
	{
		ArrayList<Technicien> lesTechniciens = C_Technicien.selectAllTechniciens();
		Object [][] matrice = new Object [lesTechniciens.size()][5];
		int i=0;
		for (Technicien unTechnicien : lesTechniciens)
		{
			matrice[i][0] = unTechnicien.getIdtechnicien();
			matrice[i][1] = unTechnicien.getNom();
			matrice[i][2] = unTechnicien.getPrenom();
			matrice[i][3] = unTechnicien.getQualification();
			matrice[i][4] = unTechnicien.getEmail();
			i++;
		}
		return matrice;
	}
			
			public void viderChamps ()
			{
				this.txtNom.setText("");
				this.txtPrenom.setText("");
				this.txtEmail.setText("");
				this.txtQualification.setText("");
				this.txtMdp.setText("");
				
			}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer)
		{
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String qualification = this.txtQualification.getText();
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMdp.getPassword());
			
			// Instancier un client
			Technicien unTechnicien = new Technicien(nom, prenom, qualification, email, mdp);
			// On l'enregistre dans la base de données
			C_Technicien.insertTechnicien(unTechnicien);
			// Récupération de l'id client à partir de la BDD
			unTechnicien = C_Technicien.selectWhereTechnicien(email, mdp);
			// Ajout du client dans le tableau
			Object ligne[] = {unTechnicien.getIdtechnicien(), unTechnicien.getNom(), unTechnicien.getPrenom(),
					unTechnicien.getQualification(), unTechnicien.getEmail()};
			this.unTableau.insertLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Technicien inséré avec succès !");
			this.viderChamps();
		}
	}
}

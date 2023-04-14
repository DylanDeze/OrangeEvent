package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



import controleur.C_Client;
import controleur.C_Intervention;
import controleur.C_Technicien;
import controleur.Intervention;
import controleur.Tableau;
import controleur.Technicien;
import controleur.Client;

public class PanelInterventions extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel ();
	
	private JTextField txtMateriel = new JTextField();
	private JTextField txtPanne = new JTextField();
	private JTextField txtDateInter = new JTextField();
	private JTextField txtPrix = new JTextField();
	private JComboBox<String> cbxIdClient = new JComboBox<>();
	private JComboBox<String> cbxIdTechnicien = new JComboBox<>();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableInterventions;
	private Tableau unTableau;
	
	public PanelInterventions ()
	{
		super();
		this.titre.setText("_____ Gestion des Interventions _____");
		
		this.panelForm.setBounds(20, 40, 300, 250);
		this.panelForm.setBackground(new Color (234, 176, 69));
		this.panelForm.setLayout(new GridLayout(7, 2));
		this.panelForm.add(new JLabel("Matériel :"));
		this.panelForm.add(this.txtMateriel);
		this.panelForm.add(new JLabel("Panne Constatée :"));
		this.panelForm.add(this.txtPanne);
		this.panelForm.add(new JLabel("Date Intervention :"));
		this.panelForm.add(this.txtDateInter); 
		this.panelForm.add(new JLabel("Prix Intervention :"));
		this.panelForm.add(this.txtPrix);
		this.panelForm.add(new JLabel("Le Client :"));
		this.panelForm.add(this.cbxIdClient);
		this.panelForm.add(new JLabel("Le Technicien :"));
		this.panelForm.add(this.cbxIdTechnicien);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		// Ajout du panelForm à au panelClients
		this.add(this.panelForm);
		
		// Remplir les CBX Idclients et Idtechnicien
		this.remplirCBX ();
		
		// Construction de la JTable
				String entetes [] = {"ID Intervention","Matériel", "Panne", "Date", "Prix", "Client", "Technicien"};
				Object [][] donnees = this.getDonnees ();
				
				this.unTableau = new Tableau(donnees, entetes);
				this.tableInterventions = new JTable(this.unTableau);
							
				JScrollPane uneScroll = new JScrollPane(this.tableInterventions);
				uneScroll.setBounds(380, 80, 450, 210);
				this.add(uneScroll);
		
		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
				
	}
	
	public Object [][] getDonnees ()
	{
		ArrayList<Intervention> lesInterventions = C_Intervention.selectAllInterventions();
		Object [][] matrice = new Object [lesInterventions.size()][7];
		int i=0;
		for (Intervention uneIntervention : lesInterventions)
		{
			matrice[i][0] = uneIntervention.getIdintervention();
			matrice[i][1] = uneIntervention.getMateriel();
			matrice[i][2] = uneIntervention.getPanne();
			matrice[i][3] = uneIntervention.getDateinter();
			matrice[i][4] = uneIntervention.getPrix();
			matrice[i][5] = uneIntervention.getIdclient();
			matrice[i][6] = uneIntervention.getIdtechnicien();
			i++;
		}
		return matrice;
	}
	
	public void remplirCBX ()
	{
		// Supprimer ou vider le CBX idclient
		this.cbxIdClient.removeAllItems();
		// Récupérer de la base de données tous les clients
		ArrayList<Client> lesClients = C_Client.selectAllClients("");
		// Parcourir lesClients et remplir le CBX
		for(Client unClient : lesClients)
		{
			this.cbxIdClient.addItem(unClient.getIdclient() +"-"+unClient.getNom() +"-"+unClient.getPrenom());
		}
		
		// Supprimer ou vider le CBX idclient
		this.cbxIdTechnicien.removeAllItems();
		// Récupérer de la base de données tous les clients
		ArrayList<Technicien> lesTechniciens = C_Technicien.selectAllTechniciens();
		// Parcourir lesClients et remplir le CBX
		for(Technicien unTechnicien : lesTechniciens)
		{
			this.cbxIdTechnicien.addItem(unTechnicien.getIdtechnicien() +"-"+unTechnicien.getNom() +"-"+unTechnicien.getPrenom());
		}
	}
	
	public void viderChamps ()
	{
		this.txtMateriel.setText("");
		this.txtPanne.setText("");
		this.txtDateInter.setText("");
		this.txtPrix.setText("");
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer)
		{
			String materiel = this.txtMateriel.getText();
			String panne = this.txtPanne.getText();
			String dateinter = this.txtDateInter.getText();
			float prix = Float.parseFloat(this.txtPrix.getText());
			
			// Récupérer les id client et technicien
			String chaine = this.cbxIdClient.getSelectedItem().toString();
			String tab [] = chaine.split("-"); // Explode de PHP
			int idclient = Integer.parseInt(tab[0]);
			
			chaine = this.cbxIdTechnicien.getSelectedItem().toString();
			tab = chaine.split("-"); // Explode de PHP
			int idtechnicien = Integer.parseInt(tab[0]);
			
			// Instancier un client
			Intervention uneIntervention = new Intervention(materiel, panne, dateinter, prix, idclient, idtechnicien);
			// On l'enregistre dans la base de données
			C_Intervention.insertIntervention(uneIntervention);
			
			// Récupération de l'id client à partir de la BDD
			uneIntervention = C_Intervention.selectWhereIntervention(materiel);
			
			// Ajout du client dans le tableau
			Object ligne[] = { uneIntervention.getIdintervention(), uneIntervention.getMateriel(), uneIntervention.getPanne(), uneIntervention.getDateinter(),
					uneIntervention.getPrix(), uneIntervention.getIdclient(), uneIntervention.getIdtechnicien()};
			this.unTableau.insertLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Intervention insérée avec succès !");
			this.viderChamps();
		}
	}
}

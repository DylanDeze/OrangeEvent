package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Client;
import controleur.Client;
import controleur.Tableau;

public class PanelClients extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel();
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtAdresse = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableClients;
	private Tableau unTableau;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	public PanelClients ()
	{
		super();
		this.titre.setText("_____ Gestion des Clients _____");
		
		// Contruction du Panel Form
		this.panelForm.setBounds(20, 40, 350, 250);
		this.panelForm.setBackground(new Color (234, 176, 69));
		this.panelForm.setLayout(new GridLayout(5,2));
		this.panelForm.add(new JLabel("Nom Client :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prénom Client :"));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Adresse Postale :"));
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Email Client :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		// Ajout du panelForm à au panelClients
		this.add(this.panelForm);
		
		// Construction de la JTable
		String entetes [] = {"ID Client", "Nom", "Prénom", "Adresse", "Email"};
		Object [][] donnees = this.getDonnees ("");
		
		this.unTableau = new Tableau(donnees, entetes);
		this.tableClients = new JTable(this.unTableau);
		
		JScrollPane uneScroll = new JScrollPane(this.tableClients);
		uneScroll.setBounds(380, 80, 450, 210);
		this.add(uneScroll);
		
		// Implémenter de la suppression // Modification d'une ligne Client
		this.tableClients.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = tableClients.getSelectedRow();
				int idclient = Integer.parseInt(tableClients.getValueAt(numLigne, 0).toString());
				if(e.getClickCount() >=2)
				{
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce client ?", "Suppression Client", JOptionPane.YES_NO_OPTION);
					if(retour == 0 ) 
					{
						C_Client.deleteClient(idclient);
						unTableau.deleteLigne(numLigne);
						JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès !");
					}
				}
				else if (e.getClickCount() == 1)
				{
					// On remplie les champs pour la modification
					txtNom.setText(tableClients.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(tableClients.getValueAt(numLigne, 2).toString());
					txtAdresse.setText(tableClients.getValueAt(numLigne, 3).toString());
					txtEmail.setText(tableClients.getValueAt(numLigne, 4).toString());
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		
		// Placement du panel Filtre 
		this.panelFiltre.setBounds(400, 50, 350, 20);
		this.panelFiltre.setBackground(new Color (234, 176, 69));
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		
		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
				
	}
	
	public Object [][] getDonnees (String filtre)
	{
		ArrayList<Client> lesClients = C_Client.selectAllClients(filtre);
		Object [][] matrice = new Object [lesClients.size()][5];
		int i=0;
		for (Client unClient : lesClients)
		{
			matrice[i][0] = unClient.getIdclient();
			matrice[i][1] = unClient.getNom();
			matrice[i][2] = unClient.getPrenom();
			matrice[i][3] = unClient.getAdresse();
			matrice[i][4] = unClient.getEmail();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps ()
	{
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtAdresse.setText("");
		this.txtEmail.setText("");
		this.btEnregistrer.setText("Modifier");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer"))
		{
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String email = this.txtEmail.getText();
			
			// Instancier un client
			Client unClient = new Client(nom, prenom, email, adresse);
			// On l'enregistre dans la base de données
			C_Client.insertClient(unClient);
			// Récupération de l'id client à partir de la BDD
			unClient = C_Client.selectWhereClient(email);
			// Ajout du client dans le tableau
			Object ligne[] = {unClient.getIdclient(), unClient.getNom(), unClient.getPrenom(), unClient.getAdresse(), unClient.getEmail()};
			this.unTableau.insertLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Client inséré avec succès !");
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier"))
		{
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String email = this.txtEmail.getText();
			
			int numLigne = this.tableClients.getSelectedRow();
			int idclient = Integer.parseInt(this.tableClients.getValueAt(numLigne, 0).toString());
			
			// Instancier un client
			Client unClient = new Client(idclient, nom, prenom, email, adresse);
			// On modifie dans la base de données
			C_Client.updateClient(unClient);
			// On modifie le client dans le tableau
			Object ligne[] = {unClient.getIdclient(), unClient.getNom(), unClient.getPrenom(), unClient.getAdresse(), unClient.getEmail()};
			this.unTableau.updateLigne(numLigne, ligne);
			
			JOptionPane.showMessageDialog(this, "Client modifié avec succès !");
			this.viderChamps();
		}
		else if (e.getSource() == this.btFiltrer)
		{
			String filtre = this.txtFiltre.getText();
			// Récupération des clients de la base de données
			Object donnees [][] = this.getDonnees(filtre);
			// Actualisation de l'affichage
			this.unTableau.setDonnes(donnees);
		}
	}
}

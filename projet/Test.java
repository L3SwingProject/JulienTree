package projet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;

public class Test {

	public static void main(String[] args) {
		List<Capteur> l = new ArrayList<>();
		l.add(new Capteur("c1","ELECTRICITE:U1:1:DevantSalle202"));
		l.add(new Capteur("c2","ELECTRICITE:U2:3:DevantSalle202"));
		l.add(new Capteur("c3","ELECTRICITE:U2:4:DevantSalle202"));
		l.add(new Capteur("c4","ELECTRICITE:U1:1:DevantSalle202"));
		l.add(new Capteur("c5","ELECTRICITE:U3:2:DevantSalle202"));
		l.add(new Capteur("c6","ELECTRICITE:U3:3:DevantSalle202"));
		
		ModeleArbre modele = new ModeleArbre(l);
		JTree jModTree = new JTree(modele);
		
		//virer la root
		jModTree.setRootVisible(false);
		
		// ouvrir les branches
		for (int i=0;i<jModTree.getRowCount();i++)
			jModTree.expandRow(i);
		
		//virer les icone
		DefaultTreeCellRenderer iconeTree = new  DefaultTreeCellRenderer();
		iconeTree.setClosedIcon(null);
		iconeTree.setOpenIcon(null);
		iconeTree.setLeafIcon(null);
		jModTree.setCellRenderer(iconeTree);
		
		//afficher info
		JPanel jPanelInfo = new JPanel();
		JLabel jLNom = new JLabel();
		JLabel jLLoc = new JLabel();
		JLabel jLType = new JLabel();
		JLabel jLSeuilMax = new JLabel();
		JLabel jLSeuilMin = new JLabel();
		JLabel jLEspace = new JLabel();
		JLabel jLModif = new JLabel();
		
		
		
		
		GroupLayout layout = new GroupLayout(jPanelInfo);
		jPanelInfo.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(jLNom)
						.addComponent(jLLoc)
						.addComponent(jLType)
						.addComponent(jLSeuilMax)
						.addComponent(jLSeuilMin)
						.addComponent(jLEspace)
						.addComponent(jLModif)
						)
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup() 
				.addComponent(jLNom)
				.addComponent(jLLoc)
				.addComponent(jLType)
				.addComponent(jLSeuilMax)
				.addComponent(jLSeuilMin)
				.addComponent(jLEspace)
				.addComponent(jLModif)
				);
		
		//info qd on clique
		jModTree.addTreeSelectionListener(new TreeSelectionListener(){
		      public void valueChanged(TreeSelectionEvent event) {
		    	  Object noeud = jModTree.getLastSelectedPathComponent();
		        if( noeud != null && noeud.getClass().equals(Capteur.class)){
		        	Capteur capActu = (Capteur) noeud;
		        	jLNom.setText("Nom : " + capActu.getNom());
		        	jLLoc.setText("Localisation : "+ capActu.getLocalisation());
		        	jLType.setText("Type : "+ capActu.getType());
		        	jLSeuilMax.setText("Seuil maximum: " + capActu.getSeuilMax());
		        	jLSeuilMin.setText("Seuil minimum : "+ capActu.getSeuilMin());
		        	jLEspace.setText(" ");
		    		jLModif.setText("Modification des seuils :");
		        }
		      }

		    });
		

		JFrame fenetre = new JFrame();
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(jModTree), jPanelInfo);
		
		split.setDividerLocation(200);
		fenetre.getContentPane().add(split, BorderLayout.CENTER);
		
		fenetre.setSize(600, 400);
	    fenetre.setLocationRelativeTo(null);
	    fenetre.setVisible(true);
		
	}
}

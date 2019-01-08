package projet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;


public class ModeleArbre implements TreeModel {
	List<Capteur> liste;
	
	

	public ModeleArbre(List<Capteur> liste) {
		this.liste = liste;
	}

	@Override
	public Object getRoot() {
		return "root";
	}

	@Override
	public Object getChild(Object parent, int index) {
		
		if (parent.getClass().equals(String.class)) {
			String recherche = (String) parent;
			if (recherche.equals("root")) {
				return casRoot(recherche,index);
			}else if (!recherche.contains(":")) {
				return casBatiment(recherche,index);	
			}
			return casEtage(recherche, index);	
		}
		return null;
	}

	private Object casRoot(String recherche, int cur) {
		Set<String> dejaVu = new HashSet<>();
		for(Capteur it : liste) {
			 if (dejaVu.add(it.getBatiment())) {
				 if(cur==0) {
					 return it.getBatiment();
				 }
				 cur--;
			 }
			
		}
		
		return null;
	}

	private Object casBatiment(String parent, int cur) {
		Set<String> dejaVu = new HashSet<>();
		for(Capteur it : liste) {
			if (it.getBatiment().equals(parent)) {
				/*TODO rejouter getEtageString() dans capteur qui renvoie "batiment:etage"*/
				 if (dejaVu.add(it.getEtageString())) {
					 if(cur==0) {
						 return it.getEtageString();
					 }
					 cur--;
				 }
				
			}
		}
		return null;
	}

	private Object casEtage(String parent, int cur) {
		String recherche[] =  parent.split(":");
		String batiment = recherche[0];
		int etage = Integer.valueOf(recherche[1]);
		for(Capteur it : liste) {
			if (it.getBatiment().equals(batiment) && it.getEtage()==etage) {
					
					 if(cur==0) {
						 return it;
					 }
					 cur--;
				 }
				
			}
		
		return null;
	}

	@Override
	public int getChildCount(Object parent) {

		if (parent.getClass().equals(String.class)) {
			String recherche = (String) parent;
			if (recherche.equals("root")) {
				return casRootCount(recherche);
			}else if (!recherche.contains(":")) {
				
				return casBatimentCount(recherche);	
			}
			return casEtageCount(recherche);	
		}
		return 0;
	}

	private int casRootCount(String recherche) {
		Set<String> fils = new HashSet<>();
		for(Capteur it : liste) {
				fils.add(it.getBatiment());
		}
		return fils.size();
	}

	private int casEtageCount(String parent) {
		String recherche[] =  parent.split(":");
		String batiment = recherche[0];
		int etage = Integer.valueOf(recherche[1]);					
		Set<Capteur> fils = new HashSet<>();
		for(Capteur it : liste) {
			if (it.getBatiment().equals(batiment) && it.getEtage()==etage) {
				 fils.add(it) ;	
			}
		}
		return fils.size();
	}

	private int casBatimentCount(String parent) {
		String recherche = (String) parent;
		Set<Integer> fils = new HashSet<>();
		for(Capteur it : liste) {
			if (it.getBatiment().equals(recherche)) {
				 fils.add(it.getEtage()) ;	
			}
		}
		return fils.size();
	}

	@Override
	public boolean isLeaf(Object node) {
		return node.getClass().equals(Capteur.class);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent.getClass().equals(String.class) && child !=null) {
			int index =0;
			Object cur=getChild(parent, index);
			while(cur != null) {
				if (cur.equals(child)) {
					return index;
				}
				index++;
				cur=getChild(parent, index);
			}
		}
		return -1;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

}

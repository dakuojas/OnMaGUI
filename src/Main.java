import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
//
//import COMPAREONTO.JSONIO;
//import COMPAREONTO.Similarity;

import UJ.UJ_JSONIO;
import UJ.SpaciallyRelatedNode;
import UJ.UJSimilarity26feb2017;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSplitPane;

public class Main {

	static JTree jtree1;
	static JTree jtree2;
	OntResource or;
	OntModel om;
	public static ArrayList<UJSimilarity26feb2017> alALIGN = new  ArrayList<>();
	public static String search_node1 = new String();
	public static ArrayList<String> search_node2_1to1 = new ArrayList<>();
	public static ArrayList<String> search_node2_1tom = new ArrayList<>();
	public static ArrayList<SpaciallyRelatedNode> search_node2_1tos = new ArrayList<>();
	public static double search_rank;
	
	
    public  static void getJSONArrayList() throws IOException
    {
    	String file2 = "Alignment_Output_TAVPPRp_.json";
		UJ_JSONIO jsonio = new UJ_JSONIO();
		alALIGN = jsonio.read(file2);
		
		/*for(Similarity s2 : alALIGN)
		{
			String string1 = s2.node1;
		System.out.println("=>"+ s2.toString());
		}*/
		
    }	
    public static UJSimilarity26feb2017 getSimilarityObject(String s) throws IOException
    {
    	UJSimilarity26feb2017 similarity=new UJSimilarity26feb2017();
    	StringSimilarity obj=new StringSimilarity();
    	similarity.Node1="EMPTY";
    	for(UJSimilarity26feb2017 s2 : alALIGN)
		{
			String string1 = s2.Node1;
	    	System.out.println(" comparing :"+s2 +"- "+s);	
			if(obj.similarity(string1, s)>=0.98)
			{
				return(s2);
			}
			System.out.println("=>"+ s2.toString());
		}
		return similarity;
    	
    }
	    
	public static void main(String[] args) throws Exception 
	{		
    	String file1 = "file:///Users/ojas/Ontologies/ndbc.owl";
//    	String file1 = "file:///Users/ojas/Ontologies/durbhaOntologies/IGBP.owl";
		String file2 = "file:///Users/ojas/Ontologies/gomoosont.owl";
		
    	ClassTree tree1 = new ClassTree(file1);
    	ClassTree tree2 = new ClassTree(file2);
    	
    	//READ PRECISION AND RECALL FROM FILE
    	String precision = Files.readAllLines(Paths.get("PrecRec.txt")).get(0).substring(0 , 4);
        String recall = Files.readAllLines(Paths.get("PrecRec.txt")).get(1).substring(0 , 4);
    	
        //GUI BEGINS HERE
    	JFrame frame = new JFrame( "Ontology Hierarchy" );
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.getContentPane().add( new JScrollPane( tree1.getJTree() ), BorderLayout.WEST );
//        frame.getContentPane().add( new JScrollPane( tree2.getJTree() ), BorderLayout.CENTER );
    	
        jtree1 = new JTree();
        jtree1 = tree1.getJTree("JTREE1");

//        jtree1.setCellRenderer(treeCellRenderer);
        for( int r = 0; r < jtree1.getRowCount(); r++ )
            jtree1.expandRow( r );
        
        Object tModel = jtree1.getModel().getRoot();
        System.out.println(tModel.toString());
        
//        JTree jtree2 = new JTree(tree2.getTreeModel());

        jtree2 = new JTree();
        jtree2 = tree2.getJTree("JTREE2");
        
        for( int r = 0; r < jtree2.getRowCount(); r++ )
            jtree2.expandRow( r );
        
        
        
//        COLORING STUFF
        DefaultTreeCellRenderer dtcr1 = (DefaultTreeCellRenderer) jtree1.getCellRenderer();
        DefaultTreeCellRenderer dtcr2 = (DefaultTreeCellRenderer) jtree2.getCellRenderer();
       
        dtcr1.setBackgroundSelectionColor(Color.YELLOW);
        dtcr2.setBackgroundSelectionColor(Color.YELLOW);
        frame.getContentPane().setLayout(new GridLayout(0, 4, 0, 0));
        JScrollPane scrollPane1 = new JScrollPane(jtree1);
        frame.getContentPane().add(scrollPane1);
        JScrollPane scrollPane2 = new JScrollPane(jtree2);
        frame.getContentPane().add(scrollPane2);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{239, 239, 0};
        gbl_panel.rowHeights = new int[]{125, 125, 125, 125, 125, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JButton btnLoadOs = new JButton("<html>Load<br>Os</html>");
        btnLoadOs.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_btnLoadOs = new GridBagConstraints();
        gbc_btnLoadOs.fill = GridBagConstraints.BOTH;
        gbc_btnLoadOs.insets = new Insets(0, 0, 5, 5);
        gbc_btnLoadOs.gridx = 0;
        gbc_btnLoadOs.gridy = 0;
        panel.add(btnLoadOs, gbc_btnLoadOs);
        btnLoadOs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Runtime.getRuntime().exec("C:/Users/ojas/bin/Protege-5.2.0/run.bat gomoosont.owl");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        
        
        JButton btnLoadOt = new JButton("<html>Load<br>Ot</html>");
        btnLoadOt.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_btnLoadOt = new GridBagConstraints();
        gbc_btnLoadOt.fill = GridBagConstraints.BOTH;
        gbc_btnLoadOt.insets = new Insets(0, 0, 5, 0);
        gbc_btnLoadOt.gridx = 1;
        gbc_btnLoadOt.gridy = 0;
        panel.add(btnLoadOt, gbc_btnLoadOt);
        btnLoadOt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Runtime.getRuntime().exec("C:/Users/ojas/bin/Protege-5.2.0/Protege.exe C:/Users/ojas/Ontologies/gomoosont.owl");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        
        JButton btnSearch = new JButton("Match");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_btnSearch = new GridBagConstraints();
        gbc_btnSearch.fill = GridBagConstraints.BOTH;
        gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
        gbc_btnSearch.gridx = 0;
        gbc_btnSearch.gridy = 1;
        panel.add(btnSearch, gbc_btnSearch);
        
            btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				ArrayList<DefaultMutableTreeNode> alnodes = new ArrayList<>();
				for(String string : search_node2_1tom)
				{
					alnodes.add(searchNode(string, (DefaultMutableTreeNode) jtree2.getModel().getRoot()));
				}

				System.out.println("IN jtree2 :");
				for(DefaultMutableTreeNode node: alnodes)
					System.out.println(node);	
				
				OntoTreeCellRenderer otcr = new OntoTreeCellRenderer();

//				jtree2.validate();
				jtree2.setRowHeight(30);
				
				jtree2.setCellRenderer(new OntoTreeCellRenderer());
//				jtree2.repaint();


			}
		});
        
        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_btnEdit = new GridBagConstraints();
        gbc_btnEdit.fill = GridBagConstraints.BOTH;
        gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
        gbc_btnEdit.gridx = 1;
        gbc_btnEdit.gridy = 1;
        panel.add(btnEdit, gbc_btnEdit);
        
        JButton btnOpenAlignment = new JButton("<html>Open<br>ALIGNMENT</html>");
        btnOpenAlignment.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_btnOpenAlignment = new GridBagConstraints();
        gbc_btnOpenAlignment.fill = GridBagConstraints.BOTH;
        gbc_btnOpenAlignment.insets = new Insets(0, 0, 5, 5);
        gbc_btnOpenAlignment.gridx = 0;
        gbc_btnOpenAlignment.gridy = 2;
        panel.add(btnOpenAlignment, gbc_btnOpenAlignment);
        btnOpenAlignment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        try {
					Runtime.getRuntime().exec("kate.exe Alignment_Output_TAVPPRp_.json");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        JButton btnOpenReference = new JButton("<html>Open<br>REFERENCE</html>");
        btnOpenReference.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_btnOpenReference = new GridBagConstraints();
        gbc_btnOpenReference.fill = GridBagConstraints.BOTH;
        gbc_btnOpenReference.insets = new Insets(0, 0, 5, 0);
        gbc_btnOpenReference.gridx = 1;
        gbc_btnOpenReference.gridy = 2;
        panel.add(btnOpenReference, gbc_btnOpenReference);
        btnOpenReference.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Runtime.getRuntime().exec("kate.exe Output_TAVPPRp_NDBC-GomooSe.json");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        
        JLabel lblNewLabel_pt = new JLabel("Precision :");
        lblNewLabel_pt.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_pt.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_lblNewLabel_pt = new GridBagConstraints();
        gbc_lblNewLabel_pt.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_pt.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_pt.gridx = 0;
        gbc_lblNewLabel_pt.gridy = 3;
        panel.add(lblNewLabel_pt, gbc_lblNewLabel_pt);
        
        JLabel lblNewLabel_pv = new JLabel(precision);
        lblNewLabel_pv.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_lblNewLabel_pv = new GridBagConstraints();
        gbc_lblNewLabel_pv.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_pv.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_pv.gridx = 1;
        gbc_lblNewLabel_pv.gridy = 3;
        panel.add(lblNewLabel_pv, gbc_lblNewLabel_pv);
        
        JLabel lblNewLabel_rt = new JLabel("Recall :");
        lblNewLabel_rt.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_rt.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_lblNewLabel_rt = new GridBagConstraints();
        gbc_lblNewLabel_rt.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_rt.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_rt.gridx = 0;
        gbc_lblNewLabel_rt.gridy = 4;
        panel.add(lblNewLabel_rt, gbc_lblNewLabel_rt);
        
        JLabel lblNewLabel_rv = new JLabel(recall);
        lblNewLabel_rv.setFont(new Font("Tahoma", Font.PLAIN, 30));
        GridBagConstraints gbc_lblNewLabel_rv = new GridBagConstraints();
        gbc_lblNewLabel_rv.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_rv.gridx = 1;
        gbc_lblNewLabel_rv.gridy = 4;
        panel.add(lblNewLabel_rv, gbc_lblNewLabel_rv);
        
        DefaultTreeModel m_model = new DefaultTreeModel((TreeNode) jtree2.getModel().getRoot());
        JTree m_tree = new JTree(m_model);
                
        
        frame.setSize( 1280, 720 );
        frame.setVisible( true );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        jtree1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Object selected = jtree1.getSelectionPath().getLastPathComponent();
		        String selectstring = selected.toString().substring(selected.toString().indexOf("#")+1, selected.toString().length()-1);
				System.out.println("CLICKED: "+selectstring);
				try {
					getJSONArrayList();
						} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					System.out.println("Selected Node ===>"+getSimilarityObject(selectstring).toString());
					search_node1 = getSimilarityObject(selectstring).Node1;
					search_node2_1to1 = getSimilarityObject(selectstring).Node2_1to1;
					search_node2_1tom = getSimilarityObject(selectstring).Node2_1tom;
					search_node2_1tos = getSimilarityObject(selectstring).Node2_1tos;
//					search_rank = getSimilarityObject(selectstring).Node2_1tos;
//					for(SpacialyRelatedNode srn : search_node2_1tos)
//					{
//						
//					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});        
	}
	
	
	public static DefaultMutableTreeNode searchNode(String node1str,DefaultMutableTreeNode m_rootNode){		
		DefaultMutableTreeNode node = null;
	    Enumeration e = m_rootNode.breadthFirstEnumeration();
	    while (e.hasMoreElements()) {
	      node = (DefaultMutableTreeNode) e.nextElement();
	    	      
	      if (node1str.equals(node.getUserObject().toString().substring(node.getUserObject().toString().indexOf("#")+1, node.getUserObject().toString().length()-1))) {
	        
	    	  return node;
	      }
	    }
	    return null;
	}
	
	
}

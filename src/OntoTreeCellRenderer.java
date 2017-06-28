import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import UJ.SpaciallyRelatedNode;

public class OntoTreeCellRenderer extends DefaultTreeCellRenderer{

	public static String node = "";
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		
		node = (String) ((DefaultMutableTreeNode) value).getUserObject().toString();
		System.out.println("node : "+node);
		node = node.toString().substring(node.toString().indexOf("#")+1, node.toString().length()-1);
		setText(node);
		
//		System.out.println("ROW BOUNDS : "+tree.getRowBounds(row).toString());
		
		Font treeFont = new Font("Serif", Font.BOLD, 24);
        setFont(treeFont);
        
		return this;
	}
	
	@Override public void paint(Graphics g) {
		super.paint(g);

			int preWidth = 0;
			int matchWidth = g.getFontMetrics().stringWidth(node);
			
			System.out.println("prewidth : "+preWidth);
			System.out.println("matchpart : "+node);
			System.out.println("matchwidth : "+matchWidth);
			
			
			int count = 1;
			char cuni = '\u2081';
			for(String s : Main.search_node2_1tom)
			{
				if(s.equals(node))
				{
					String string=node+" :"+cuni;
//					String string="---------"+node+" "+"---------";
					Rectangle2D rect = g.getFontMetrics().getStringBounds(string, g);
//					Rectangle rect = g.getFontMetrics().getStringBounds(string, g).getBounds();
					
//					g.setColor(getTextNonSelectionColor());					
//					System.out.println("X = "+rect.getWidth()+"string = "+string);
//					g.drawString(string, -(int)rect.getX(), -(int)rect.getY());
					
					g.setColor(Color.getHSBColor(0.166f, 1.0f, 1.0f));
//					c = c + 0.02f;
					g.fillRect(preWidth, 0, (int) rect.getWidth()+100, (int) rect.getHeight()+50);
//					g.fillRect(preWidth, 0, matchWidth+100, getHeight());
					g.setColor(getTextNonSelectionColor());					
					System.out.println("X = "+rect.getWidth()+"string = "+string);
					g.drawString(string, -(int)rect.getX(), -(int)rect.getY());
				}
				count++;
				cuni = (char)(cuni+1);
			}

			ArrayList<SpaciallyRelatedNode> arrs = new ArrayList<>();
			for(SpaciallyRelatedNode srn : Main.search_node2_1tos)//GREEN
			{
				String string;
				arrs.add(srn);
				char cuni1 = '\u2080';
				if((srn.NodeS.equals(node))&&!(srn.NodeS.equals(Main.search_node2_1to1.get(0))))
				{	
					int c1=0;
//					char cuni1 = '\u2080';
					for(String s : Main.search_node2_1tom)
					{
						if(s.equals(node))
						{
	//						if(srn.NodeS.equals(Main.search_node2_1to1.get(0)))
	//							 string="*"+node+cuni1;
							string="*"+node;
							Rectangle rect = g.getFontMetrics().getStringBounds(string, g).getBounds();
	//							g.setColor(Color.getHSBColor(0.7f, 0.5f, (float)srn.Rank));//CHANGING BRIGHTNESS
	//							g.setColor(Color.getHSBColor((float)srn.Rank, 0.5f, 0.5f));//CHANGING HUE
							g.setColor(Color.getHSBColor(0.333f, (float)(1.0 - srn.Rank), 1.0f));//CHANGING SATURATION
							matchWidth = g.getFontMetrics().stringWidth(string);
							g.fillRect(preWidth, 0, matchWidth+100, getHeight());
							g.setColor(getTextNonSelectionColor());
							
							g.drawString(string, -rect.x, -rect.y);
							System.out.println("===="+string);
						}
						c1++;
						cuni1 = (char)(cuni1+1);	
					}
//					g.setColor(Color.getHSBColor(0.333f, (float)(1.0 - srn.Rank), 1.0f));//CHANGING SATURATION
//					g.fillRect(preWidth, 0, matchWidth+50, getHeight());
//					g.setColor(getTextNonSelectionColor());
//					Rectangle rect = g.getFontMetrics().getStringBounds(node+"----", g).getBounds();					
//					g.drawString(" * "+node, preWidth + 1, -rect.y);		
				
				}
				else if((srn.NodeS.equals(node))&&(srn.NodeS.equals(Main.search_node1)))
				{
					g.setColor(Color.getHSBColor(0.166f, (float)(1.0 - srn.Rank), 1.0f));//CHANGING SATURATION
					g.fillRect(preWidth, 0, matchWidth+100, getHeight());
					g.setColor(getTextNonSelectionColor());
					Rectangle rect = g.getFontMetrics().getStringBounds(node+"----", g).getBounds();					
					g.drawString("*"+node+":"+cuni1, preWidth + 1, -rect.y);		
				}
			}
			
			System.out.println(arrs);
	}
}

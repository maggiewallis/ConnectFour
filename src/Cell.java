import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Cell
{
	public static final int CELL_SIZE = 50;
	private static Font cellFont = new Font("Times New Roman",Font.BOLD,36);
	private static Image[] colorImages; // these will be filled with the images in the following files.
	private static String[] filenames = {"BlueChip.png","RedChip.png","YellowChip.png"};
	private static String[] cellColors = {"Blue","Red","Yellow"};
	
	private int colorID; // which background color should be displayed?
	private int x,y; // screen coordinates of the top left corner
	private String marker; // optional character (typically a letter or number) to show on this cell
	private boolean displayMarker; // whether to show the cell or not.
	
	public Cell()
	{
		colorID = 1;

		marker = "";
		displayMarker = false;
		if (colorImages == null)
		{
			colorImages = new Image[filenames.length];
			for (int i =0; i<filenames.length; i++)
				colorImages[i] = (new ImageIcon(filenames[i])).getImage();
		}
	}
	
	public Cell(int cid)
	{
		this();
		colorID = 0;
		cid = colorID;
	}
	
	public Cell(int inRow, int inCol)
	{
		this((int)(Math.random()*filenames.length));
		y = inRow*CELL_SIZE;
		x = inCol*CELL_SIZE;
	}
	
	public Cell(int cid, int inRow, int inCol, String inMarker, boolean disp)
	{
		this(inRow,inCol);
		colorID = cid;
		marker = inMarker;
		displayMarker = disp;
	}
	public int getColorID()
	{
		return colorID;
	}

	public void setColorID(int colorID)
	{
		this.colorID = colorID;
	}
	/**
	 * cycles the color forward one notch.
	 */

	public void advanceColorId()
	{
		colorID = (colorID + 1) % filenames.length;	
	}
	public void advanceColorId2()
	{
		colorID = (colorID + 2) % filenames.length;	
	}

	
	/**
	 * cycles the color backward one notch.
	 */
	public void rewindColorId()
	{
		colorID = (colorID+ (filenames.length-1)) %filenames.length;
	}
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	
	public String getMarker()
	{
		return marker;
	}

	public void setMarker(String marker)
	{
		this.marker = marker;
	}

	public boolean shouldDisplayMarker()
	{
		return displayMarker;
	}

	public void setDisplayMarker(boolean displayMarker)
	{
		this.displayMarker = displayMarker;
	}

	// =============================   DRAW SELF ================================
	public void drawSelf(Graphics g)
	{
	   Graphics2D g2 = (Graphics2D)g;
	   g2.drawImage(colorImages[colorID], x,y, null);
	   
	   g2.setColor(new Color(192,192,192));
	   g2.setStroke(new BasicStroke(3));
	   g2.drawRoundRect(x+1, y+1, 46, 46, 8, 8);
	
	   g2.setColor(new Color(64,64,64));
	   g2.setStroke(new BasicStroke(2));
	   g2.drawRoundRect(x+1, y+1, 46, 46, 8, 8);
	   
	   if (displayMarker)
	   {
		   g2.setFont(cellFont);
		   g2.setColor(Color.WHITE);
		   g2.drawString(marker, x+CELL_SIZE/2-14, y+CELL_SIZE/2+12);
		   
		   g2.setColor(Color.BLACK);
		   g2.drawString(marker, x+CELL_SIZE/2-15, y+CELL_SIZE/2+11);
	   }
	
	}
	// ===================================  OVERRIDDEN OBJECT METHODS ==============================
	public boolean equals(Object other)
	{
		if (other instanceof Cell)
			if ((((Cell) other).colorID == this.colorID) && 
			   (((Cell) other).marker   == this.marker))
			return true;
		return false;
	}

	public String toString()
	{
		return "Cell: "+marker+": color:"+cellColors[colorID];
	}
	
	public int hashCode()
	{
		int result = colorID * 137;
		if (marker!=null) result += marker.hashCode();
		return result;
	}

}

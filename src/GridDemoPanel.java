import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GridDemoPanel extends JPanel implements MouseListener
{
	private Cell[][] theGrid;
	public final static int NUM_ROWS = 6;
	public final static int NUM_COLS = 7;
	public int whosturn = 1;
	
	
	public GridDemoPanel()
	{
		super();
		theGrid = new Cell[NUM_ROWS][NUM_COLS];
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c] = new Cell(r,c);
		//make it all one color in here
		setBackground(Color.WHITE);
		addMouseListener(this);
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c].drawSelf(g);
	}
	
	
	
	//============================ Mouse Listener Overrides ==========================

	@Override
	// mouse was just released within about 1 pixel of where it was pressed.
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
				
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int row = (e.getY())/50;
		int col = (e.getX())/50;
		
		//changes the lowest blue to red or yellow
		for (int i = 5; i >-1; i--)
		{
			if (whosturn == 1)
			{
				if (theGrid[i][col].getColorID() == 0)
				{
					theGrid[i][col].advanceColorId();
					repaint();
					whosturn = 2;
					break;
				}
			}
			else if (whosturn == 2)
			{
				if (theGrid[i][col].getColorID() == 0)
				{
					theGrid[i][col].advanceColorId2();
					repaint();
					whosturn = 1;
					break;
				}
			}
		}
		

		//horizontal check
		for (int i = 0; i < 6; i++)
		{
			for (int j=0; j<4; j++)
			{
				if (theGrid[i][j].getColorID() != 0)
				{
					if (theGrid[i][j].getColorID() == theGrid[i][j+1].getColorID())
					{
						if (theGrid[i][j].getColorID() == theGrid[i][j+2].getColorID())
						{
							if (theGrid[i][j].getColorID() == theGrid[i][j+3].getColorID())
							{
								System.out.println("Game Over");
								if (theGrid[i][j].getColorID() == theGrid[i][j+3].getColorID() && theGrid[i][j+ 3].getColorID() == 1)
								{
									JOptionPane.showMessageDialog(null, "RED WINS");	
								}
								if (theGrid[i][j].getColorID() == theGrid[i][j+3].getColorID() && theGrid[i][j+ 3].getColorID() == 2)
								{
									JOptionPane.showMessageDialog(null, "YELLOW WINS");	
								}
							}
						}
					}
				}
			}
		}
		//vertical check
		for (int i = 0; i < 3; i++)
		{
			for (int j=0; j<6; j++)
			{
				if (theGrid[i][j].getColorID() != 0)
				{
					if (theGrid[i][j].getColorID() == theGrid[i+1][j].getColorID())
					{
						if (theGrid[i][j].getColorID() == theGrid[i+2][j].getColorID())
						{
							if (theGrid[i][j].getColorID() == theGrid[i+3][j].getColorID())
							{
								System.out.println("Game Over");
								if (theGrid[i][j].getColorID() == theGrid[i+3][j].getColorID() && theGrid[i+3][j].getColorID() == 1)
								{
									JOptionPane.showMessageDialog(null, "RED WINS");	
								}
								if (theGrid[i][j].getColorID() == theGrid[i+3][j].getColorID() && theGrid[i+3][j].getColorID() == 2)
								{
									JOptionPane.showMessageDialog(null, "YELLOW WINS");	
								}
							}
						}
					}
				}
			}
		}
		//diagonal check down
				for (int i = 0; i < 3; i++)
				{
					for (int j=0; j<4; j++)
					{
						if (theGrid[i][j].getColorID() != 0)
						{
							if (theGrid[i][j].getColorID() == theGrid[i+1][j+1].getColorID())
							{
								if (theGrid[i][j].getColorID() == theGrid[i+2][j+2].getColorID())
								{
									if (theGrid[i][j].getColorID() == theGrid[i+3][j+3].getColorID())
									{
										System.out.println("Game Over");
										if (theGrid[i][j].getColorID() == theGrid[i+3][j+3].getColorID() && theGrid[i+3][j+3].getColorID() == 1)
										{
											JOptionPane.showMessageDialog(null, "RED WINS");	
										}
										if (theGrid[i][j].getColorID() == theGrid[i+3][j+3].getColorID() && theGrid[i+3][j+3].getColorID() == 2)
										{
											JOptionPane.showMessageDialog(null, "YELLOW WINS");	
										}
									}
								}
							}
						}
					}
				}
				//diagonal check up
				for (int i = 5; i > 2; i--)
				{
					for (int j=0; j<4; j++)
					{
						if (theGrid[i][j].getColorID() != 0)
						{
							if (theGrid[i][j].getColorID() == theGrid[i-1][j+1].getColorID())
							{
								if (theGrid[i][j].getColorID() == theGrid[i-2][j+2].getColorID())
								{
									if (theGrid[i][j].getColorID() == theGrid[i-3][j+3].getColorID())
									{
										System.out.println("Game Over");
										if (theGrid[i][j].getColorID() == theGrid[i-3][j+ 3].getColorID() && theGrid[i-3][j+ 3].getColorID() == 1)
										{
											JOptionPane.showMessageDialog(null, "RED WINS");	
										}
										if (theGrid[i][j].getColorID() == theGrid[i-3][j+ 3].getColorID() && theGrid[i-3][j+ 3].getColorID() == 2)
										{
											JOptionPane.showMessageDialog(null, "YELLOW WINS");	
										}
									}
								}
							}
						}
					}
				}
		

		
		//make it so you can click it and then they can type in an integer and if they type
		//the right integer then you put the integer as a string in the box
		//repaint??
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
			
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
		
	}

	@Override
	// mouse just entered this window
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	// mouse just left this window
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
}

/* Program for representing the simulation graphically
 * Note: Don't resize/move/alter the window. It causes the program to run
 * multiple times
*/

import java.awt.*;
import java.awt.event.*;

import java.util.Random;

import java.io.*;

class RunSimGraphic extends Canvas
{
	public RunSimGraphic()
	{
		setSize(1000,600);
		setBackground(Color.white);
	}

	public static void main(String args[])
	{
		RunSimGraphic GP = new RunSimGraphic();

		Frame aFrame = new Frame("Grass Simulation");
		aFrame.setSize(1000,600);

		aFrame.add(GP);

		aFrame.setVisible(true);
		
	}

	public void paint(Graphics g) //throws IOException
	{
		int length = 42;
		int breadth = 24;
		int offsetX = 60;
		int offsetY = 40;
		int grass[][] = new int[breadth][length];
		int specie[][] = new int[breadth][length];
		//Random r = new Random();

		DataInputStream dataGrass;
		DataInputStream dataSpecie;

		try
		{
			dataGrass = new DataInputStream(new FileInputStream("grassData"));
		}
		catch(IOException exc)
		{
			System.out.println("Cannot open file.");
			return;
		}
		
		try
		{
			dataSpecie = new DataInputStream(new FileInputStream("specieData"));
		}
		catch(IOException exc)
		{
			System.out.println("Cannot open file.");
			return;
		}

		for(int generation = 0; generation < 100; generation++)
		{
			try
			{
				// Load the matrices
				// Grass matrix
				for(int i=0;i<breadth;i++)
				{
					for(int j=0;j<length;j++)
					{
						grass[i][j] = dataGrass.readInt();
						specie[i][j] = dataSpecie.readInt();
					}
				}
			
				// Different shades of green
				for(int i=0;i<breadth;i++)
				{
					for(int j=0;j<length;j++)
					{
						// First paint ground layer
						if(specie[i][j] > 0)
						{
							// Cell contains live creature
							int trophicLevel;
							trophicLevel = specie[i][j]/100000;
							switch(trophicLevel)
							{
								case 2:
								g.setColor(new Color(0,0,235));
								break;

								case 3:
								g.setColor(Color.orange);
								break;

							}
							g.fillRect(20*j+offsetX,20*i+offsetY,20,20);
							g.setColor(Color.black);
							g.drawRect(20*j+offsetX,20*i+offsetY,20,20);
						}
						else
						{
							switch(grass[i][j])
							{
								case 0:
								g.setColor(Color.gray);
								break;

								case 1:
								g.setColor(new Color(0,185,0));
								break;

								case 2:
								g.setColor(new Color(0,135,0));
								break;
							}
							g.fillRect(20*j+offsetX,20*i+offsetY,20,20);
							g.setColor(Color.black);
							g.drawRect(20*j+offsetX,20*i+offsetY,20,20);
						}
						// Next creature layer								
					}
				}
				try
				{
					Thread.sleep(300);
				}
				catch (InterruptedException ie)
				{
					 ie.printStackTrace();
				}
			}catch(IOException exc)
			{
				System.out.println("Read error.");
			}
		}
	}
}					 								

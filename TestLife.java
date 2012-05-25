import java.util.Random;

class Grid
{
	int universe[][];
	int size;

	// Constructors
	Grid(int len)
	{
		universe = new int[len][len];
		size = len;
	}
	Grid()
	{
		universe = new int[10][10];
		size = 10;
	}

	//Display the grid as 1's and 0's
	void showGrid()
	{
		System.out.println();
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				System.out.print(" " +universe[i][j]);
			}
			System.out.println();
		}
	}

	//Initial seeding of the Grid
	void setGrid()
	{
		Random r = new Random();
		for(int i=1; i<(size-1); i++)
		{
			for(int j=1; j<(size-1); j++)
			{
				universe[i][j] = r.nextInt(2);
			}
		}
		setBoundary();	
	}
	
	void setBoundary()
	{
		for(int i=0; i<size; i++)
		{
			universe[0][i] = 0;
			universe[size-1][i] = 0;
			universe[i][0] = 0;
			universe[i][size-1] = 0;
		}
	}
	
	void calcNexGen(Grid h)
	{
		for(int i=1; i<(size-1); i++)
		{
			for(int j=1; j<(size-1); j++)
			{
				if(universe[i][j] == 1)
				{
					switch(countNeighbours(i,j))
					{
						case 0:
						case 1:
							h.universe[i][j] = 0;
							break;
						case 2:
						case 3:
							h.universe[i][j] = 1;
							break;
						case 4:
						case 5:
						case 6:
						case 7:
						case 8:
							h.universe[i][j] = 0;
							break;
						default:
							h.universe[i][j] = 0;
							break;
					}
				}
				else
				{
					if(countNeighbours(i,j) == 3)
						h.universe[i][j] = 1;
					else
						h.universe[i][j] = 0;
				}
						
			}
		}
	}

	int countNeighbours(int i, int j)
	{
		int count = 0;
		count += universe[i-1][j-1];	
		count += universe[i-1][j];
		count += universe[i-1][j+1];
		count += universe[i][j-1];
		//count += universe[i][j];
		count += universe[i][j+1];
		count += universe[i+1][j-1];
		count += universe[i+1][j];
		count += universe[i+1][j+1];
		//System.out.println("Count: " +count+ "for i, j: " +i+j);
		return count;
	}
	
	void copyGrid(Grid h)
	{
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				h.universe[i][j] = this.universe[i][j];
			}
		}
	}
}

class TestLife
{
	public static void main(String args[])
	{
		Grid curr = new Grid();
		Grid nex = new Grid();

		curr.setGrid();
		curr.showGrid();
		for(int k=0; k<10; k++)
		{
			curr.calcNexGen(nex);
			nex.copyGrid(curr);
			curr.showGrid();
		}
	}
}
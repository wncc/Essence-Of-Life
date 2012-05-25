/* 
 * Tuesday May 22 2012 
 * Class Definitions required for our project
 * Numerous methods have been defined as well
*/


import java.util.Random;


/*
 * The Cell class encapsulates the two coordinates of a cell on the grid
 * It is used as a member in subsequent classes
 * It is used frequently as a return type for methods of the 
 * Neighbourhood class
*/
class Cell
{
	int x;
	int y;
	Cell()
	{
		x = 0;
		y = 0;
	}
	Cell(int p, int q)
	{
		x = p;
		x = q;
	}
	Cell(Cell p)
	{
		this.x = p.x;
		this.y = p.y;
	}
}

/* This class definition serves as a template for all species that we
 * observe. Every single organism is an instance of this class.
*/
class Specie
{
	int trophicLevel; // What level the creature occupies in the food web.
	int diet; // 1 is herbivore, 0 is omnivore and -1 is carnivore
	
	int currHealth; // Hitpoints
	int maxHealth;
	int damage;
	int armour;

	int currAge;	// The number of generations that the being has survived
	int maxAge;

	int foodReq;
	int SocialReq;

	int sex;	// Reproduction is sexual

	Cell location;
	int range;	// How far can a creature of this specie can 'sense' around it.
}

class Grid
{
	int length;
	int breadth;
	int area;
	
	// Constructors for class Grid
	Grid()
	{
		length = 0;
		breadth = 0;
		area = 0;	
	}
	Grid(int l, int b)
	{
		length = l;
		breadth = b;
		area = l*b;
	}
	Grid(int len)	//For a square Grid
	{
		length = breadth = len;
		area = len*len;
	}
	Grid(Grid g)
	{
		length = g.length;
		breadth = g.breadth;
		area = g.area;
	}

	// Methods to display the variables
	void showGridVariables()
	{
		System.out.println("Length: " +length);
		System.out.println("Breadth: " +breadth);
		System.out.println("Area: " +area);
	}
		
}

//Class Universe is a subclass of Grid and represents the bottom 'layer'.
class Universe extends Grid
{
	int grass[][];	// Grass Layer
	int maxGrass;
	int organism[][];	// Organism Layer

	//Constructor
	Universe()
	{
		super();
		grass = new int[breadth][length];
		maxGrass = 3;
	}
	Universe(int l, int b, int mg)
	{
		super(l,b);
		grass = new int[breadth][length];
		maxGrass = mg;
	}
	Universe(int len, int mg)
	{
		super(len);
		grass = new int[breadth][length];
		maxGrass = mg;
	}
	Universe(Universe u)
	{
		super(u);
		grass = new int[breadth][length];
		maxGrass = u.maxGrass;
	}

	// Method to display variables
	void showUniverseVariables()
	{
		showGridVariables();
	}

	// Method to randomly seed the grass
	void seedGrass()
	{
		Random r = new Random();
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				grass[i][j] = r.nextInt(3);
				// System.out.println(grass[i][j]);
			}
		}
	}

	// Method to display the grass
	void showGrass()
	{
		for(int i=0; i<breadth; i++)
		{
			System.out.println();
			for(int j=0; j<length; j++)
			{
				System.out.print(" " +grass[i][j]);
			}
		}
		System.out.println();
	}

	/* Method to 'grow' the grass to next generation.
	 * Since the universe is an island, we cannot apply a generic algorithm
	 * for all the cells. Corners, edges and interior cells have different
	 * neighbours and hence their algorithms need to be different. Here
	 * corners are 'grown' first followed by edges and finally interior cells.
	*/
	void growGrass()
	{
		int nextGenGrass[][] = new int[breadth][length];

		// Corners
		// Top Left
		if((grass[0][0] > 0) && (grass[0][0] < maxGrass))
			nextGenGrass[0][0] = grass[0][0] + 1;
		else if(grass[0][0] == maxGrass)
			nextGenGrass[0][0] = maxGrass;
		else if(grass[1][0] + grass[1][1] + grass[0][1] > 0)
			nextGenGrass[0][0] = 1;
		// Top Right
		if((grass[0][length-1] > 0) && (grass[0][length-1] < maxGrass))
			nextGenGrass[0][length-1] = grass[0][length-1] + 1;
		else if(grass[0][length-1] == maxGrass)
			nextGenGrass[0][length-1] = maxGrass;
		else if(grass[0][length-2] + grass[1][length-2] + grass[1][length-1] > 0)
			nextGenGrass[0][length-1] = 1;
		// Bottom Left
		if((grass[breadth-1][0] > 0) && (grass[breadth-1][0] < maxGrass))
			nextGenGrass[breadth-1][0] = grass[breadth-1][0] + 1;
		else if(grass[breadth-1][0] == maxGrass)
			nextGenGrass[breadth-1][0] = maxGrass;
		else if(grass[breadth-2][0] + grass[breadth-2][1] + grass[breadth-1][1] > 0)
			nextGenGrass[breadth-1][0] = 1;
		// Bottom Right
		if((grass[breadth-1][length-1] > 0) && (grass[breadth-1][length-1] < maxGrass))
			nextGenGrass[breadth-1][length-1] = grass[breadth-1][length-1] + 1;
		else if(grass[breadth-1][length-1] == maxGrass)
			nextGenGrass[breadth-1][length-1] = maxGrass;
		else if(grass[breadth-1][length-2] + grass[breadth-2][length-2] + grass[breadth-2][length-1] > 0)
			nextGenGrass[breadth-1][length-1] = 1;

		// Edges
		for(int i=1; i<length-1; i++)
		{
			// Top
			if((grass[0][i] > 0) && (grass[0][i] < maxGrass))
				nextGenGrass[0][i] = grass[0][i] + 1;
			else if(grass[0][i] == maxGrass)
				nextGenGrass[0][i] = maxGrass;
			else if(grass[0][i-1] + grass[1][i-1] + grass[1][i] + grass[1][i+1] + grass[0][i+1] > 0)
				nextGenGrass[0][i] = 1;

			// Bottom
			if((grass[breadth-1][i] > 0) && (grass[breadth-1][i] < maxGrass))
				nextGenGrass[breadth-1][i] = grass[breadth-1][i] + 1;
			else if(grass[breadth-1][i] == maxGrass)
				nextGenGrass[breadth-1][i] = maxGrass;
			else if(grass[breadth-1][i-1] + grass[breadth-2][i-1] + grass[breadth-2][i] + grass[breadth-2][i+1] + grass[breadth-1][i+1] > 0)
				nextGenGrass[breadth-1][i] = 1;
		}
		for(int j=1; j<breadth-1; j++)
		{
			// Left
			if((grass[j][0] > 0) && (grass[j][0] < maxGrass))
				nextGenGrass[j][0] = grass[j][0] + 1;
			else if(grass[j][0] == maxGrass)
				nextGenGrass[j][0] = maxGrass;
			else if(grass[j-1][0] + grass[j-1][1] + grass[j][1] + grass[j+1][1] + grass[j+1][0] > 0)
				nextGenGrass[j][0] = 1;

			// Right
			if((grass[j][length-1] > 0) && (grass[j][length-1] < maxGrass))
				nextGenGrass[j][length-1] = grass[j][length-1] + 1;
			else if(grass[j][length-1] == maxGrass)
				nextGenGrass[j][length-1] = maxGrass;
			else if(grass[j-1][length-1] + grass[j-1][length-2] + grass[j][length-2] + grass[j+1][length-2] + grass[j+1][length-1] > 0)
				nextGenGrass[j][length-1] = 1;
		}

		// Interior
		for(int i=1; i<breadth-1; i++)
		{
			for(int j=1; j<length-1; j++)
			{
				if((grass[i][j] > 0) && (grass[i][j] < maxGrass))
					nextGenGrass[i][j] = grass[i][j] + 1;
				else if(grass[i][j] == maxGrass)
					nextGenGrass[i][j] = maxGrass;
				else if(grass[i-1][j-1] + grass[i-1][j] + grass[i-1][j+1] + grass[i][j-1] + grass[i][j+1] + grass[i+1][j-1] + grass[i+1][j] + grass[i+1][j+1] > 0)
					nextGenGrass[i][j] = 1;
			}
		}

		// Update the values from the temp array
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				grass[i][j] = nextGenGrass[i][j];
			}
		}
	}	
}

/*
 * Neighbourhood class is the container for methods
 * to locate objects on the universe

class Neighbourhood extends Universe
{
	int range;
	Cell centre;

	Neighbourhood(Cell p, int r)
	{
		centre = new Cell(p);	// Call the Copy Constructor
		range = r;

//		for
	}


	Method to locate grass
	Cell findGrass(int ground[][])
	{
		for(int r=1; r<=range; r++)
		{
			for(int i=(centre.x - r); i<=(centre.x + r); i++)
			{}
				
	}
	
}
*/

/* The following class encapsulates methods that are used to determine
 * the next generation.
*/
class Neighbourhood
{
	static Cell findGrass(Cell centre, int range, Universe u)
	{
		// Corners
		if((centre.x == 0) & (centre.y == 0))
		{
			// Top Left Corner
			System.out.println("Top left corner");
			for(int r=0; r<range; r++)
			{
				
		}
		return centre;
	}
}

class TestClassDef
{
	public static void main(String args[])
	{
		/*
		Universe u1 = new Universe();
		
		Universe u3 = new Universe(6);
		Universe u4 = new Universe(u2);

		u1.seedGrass();
		u2.seedGrass();
		u3.seedGrass();
		u4.seedGrass();

		u1.showGrass();
		u2.showGrass();
		u3.showGrass();
		u4.showGrass();

		Universe u2 = new Universe(16,9,2);
		u2.seedGrass();
		u2.showGrass();	
		for(int k=0; k<5; k++)
		{
			u2.growGrass();
			u2.showGrass();
		}
		*/
		Universe u = new Universe(5,5,5);
		Cell centre = new Cell(0,1);
		Neighbourhood.findGrass(centre, 1, u);
	}
}

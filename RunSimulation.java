/* 
 * Tuesday May 22 2012 
 * Class Definitions required for our project
 * Numerous methods have been defined as well
*/

import java.io.*;
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
		y = q;
	}
	Cell(Cell p)
	{
		this.x = p.x;
		this.y = p.y;
	}
}

/* 
 * The Organism class definition serves as a template for all species that we
 * observe. Every single organism is an instance of this class.
*/
class Organism
{
	int specieID;
	long creatureID;

	// Traits that are same for organisms of the same species.
	int trophicLevel; 	// What level the creature occupies in the food web.
	int diet; 	// 0 is herbivore, 1 is carnivore and 2 is omnivore.
	
	int healthMax;
	int healthCur;
	
	int damage;
	int armour;

	int ageMax;
	int ageCur;

	int foodMax;
	int foodCur;
	int foodReq;

	int socialMax;
	int socialCur;
	int socialReq;

	int sex;	// 1 is female, 2 is male
	int litterSize;

	Cell location;
	int range;	// How far can a creature of this specie can 'sense' around it.

	// Default Constructor
	Organism()
	{
		trophicLevel = -1;
		diet = -1;
		healthMax = -1;
		healthCur = -1;
		damage = -1;
		armour = -1;
		ageMax = -1;
		ageCur = -1;
		foodMax = -1;
		foodCur = -1;
		foodReq = -1;
		socialMax = -1;
		socialCur = -1;
		socialReq = -1;
		sex = -1;
		litterSize = -1;
		range = -1;
		location = new Cell(-1,-1);
	}

	// Creates a class based on condensed values (sID and cID)
	// i and j are the row and column number respectively.
	Organism(int sID, long cID, int i, int j)
	{
		specieID = sID;
		socialReq = sID % 10;
		sID /= 10;
		foodReq = sID % 10;
		sID /= 10;
		litterSize = sID % 10;
		sID /= 10;
		range = sID % 10;
		sID /= 10;
		diet = sID % 10;
		sID /= 10;
		trophicLevel = sID;

		creatureID = cID;
		long temp;
		temp = cID - ((cID/100)*100);
		armour = (int) temp;
		cID /= 100;
		
		temp = cID - ((cID/100)*100);
		damage = (int) temp;
		cID /= 100;

		temp = cID - ((cID/100)*100);
		socialCur = (int) temp;
		cID /= 100;
		
		socialMax = 100;	

		temp = cID - ((cID/100)*100);	
		foodCur = (int) temp;
		cID /= 100;

		foodMax = 100;

		temp = cID - ((cID/100)*100);
		healthMax = (int) temp;
		cID /= 100;

		temp = cID - ((cID/100)*100);
		healthCur = (int) temp;
		cID /= 100;

		temp = cID - ((cID/1000)*1000);
		ageMax = (int) temp;
		cID /= 1000;

		temp = cID - ((cID/1000)*1000);
		ageCur = (int) temp;
		cID /= 1000;

		sex = (int) cID;
		
		location = new Cell(i,j);
	}

	// Constructor used when we are interested in specie and not the condition
	// of the Organism.
	Organism(int sID, int i, int j)
	{
		specieID = sID;
		socialReq = sID % 10;
		sID /= 10;
		foodReq = sID % 10;
		sID /= 10;
		litterSize = sID % 10;
		sID /= 10;
		range = sID % 10;
		sID /= 10;
		diet = sID % 10;
		sID /= 10;
		trophicLevel = sID;
		
		location = new Cell(i,j);
	}

	// Calculates the SpecieID based on characteristic values of the Organism.
	int calcSpecieID()
	{
		int sID;

		sID = trophicLevel;
		sID *= 10;
		sID += diet;
		sID *= 10;
		sID += range;
		sID *= 10;
		sID += litterSize;
		sID *= 10;
		sID += foodReq;
		sID *= 10;
		sID += socialReq;

		return sID;
	}

	// Calculates the CreatureID based on the current condition of the
	// Organism.
	long calcCreatureID()
	{
		long cID;

		cID = sex;
		cID *= 1000;
		cID += ageCur;
		cID *= 1000;
		cID += ageMax;
		cID *= 100;
		cID += healthCur;
		cID *= 100;
		cID += healthMax;
		cID *= 100;
		cID += foodCur;
		cID *= 100;
		cID += socialCur;
		cID *= 100;
		cID += damage;
		cID *= 100;
		cID += armour;

		return cID;
	}

	// Sets the Organism to behave similar to a rabbit.
	void makeRabbit()
	{
		Random r = new Random();
		trophicLevel = 2;
		diet = 0;
		healthMax = 99;
		healthCur = 50 + r.nextInt(21);
		damage = 9 + r.nextInt(3);
		armour = 4 + r.nextInt(3);
		ageMax = 25 + r.nextInt(5);
		ageCur = 0;
		foodMax = 100;
		foodCur = 50 + r.nextInt(11);
		foodReq = 20;
		socialMax = 100;
		socialCur = 50;
		socialReq = 15;
		sex = r.nextInt(2) + 1;
		litterSize = 2;
		range = 1;

		specieID = calcSpecieID();
		creatureID = calcCreatureID();

	}

	// Sets the Organism to behave similar to a wolf.
	void makeWolf()
	{
		Random r = new Random();
		trophicLevel = 3;
		diet = 1;
		healthMax = 99;
		healthCur = 50 + r.nextInt(21);
		damage = 35 + r.nextInt(11);
		armour = 15 + r.nextInt(3);
		ageMax = 35 + r.nextInt(5);
		ageCur = 0;
		foodMax = 100;
		foodCur = 70 + r.nextInt(21);
		foodReq = 20;
		socialMax = 100;
		socialCur = 50;
		socialReq = 5;
		sex = r.nextInt(2) + 1;
		litterSize = 3;
		range = 2;

		specieID = calcSpecieID();
		creatureID = calcCreatureID();

	}

	// Sets the organism as a newborn.
	void makebaby()
	{
		Random r = new Random();
		ageCur = 0;
		sex = r.nextInt(2) + 1;
		specieID = calcSpecieID();
		creatureID = calcCreatureID();
	}
	// Moves to Organism towards or onto the destination.
	void moveToCell(Cell destination)
	{
		if(destination.x < location.x)
			location.x -= 1;
		else if(destination.x > location.x)
			location.x += 1;
		else
			location.x = destination.x;

		if(destination.y < location.y)
			location.y -= 1;
		else if(destination.y > location.y)
			location.y += 1;
		else
			location.y = destination.y;
	}

	// Used during testing. Describes the organism.
	void showOrganism()
	{
		System.out.println("SpecieID: " +specieID);
		System.out.println("CreatureID: " +creatureID);
		System.out.println("TrophicLevel: " +trophicLevel);
		switch(diet)
		{
			case 0:	System.out.println("Diet: Herbivore");
					break;
			case 1: System.out.println("Diet: Carnivore");
					break;
			case 2: System.out.println("Diet: Omnivore");
					break;
		}
		System.out.println("Maximum Health: " +healthMax);
		System.out.println("Current Health: " +healthCur);
		System.out.println("Damage: " +damage);
		System.out.println("Armour: " +armour);
		
		System.out.println("Maximum Age: " +ageMax);
		System.out.println("Current Age: " +ageCur);
		
		System.out.println("Maximum Food: " +foodMax);
		System.out.println("Current Food: " +foodCur);
		System.out.println("Food Requirement: " +foodReq);
		
		System.out.println("Maximum Social: " +socialMax);
		System.out.println("Current Social: " +socialCur);
		System.out.println("Social Requirement: " +socialReq);
		
		switch(sex)
		{
			case 2:	System.out.println("Sex: Male");
					break;
			case 1: System.out.println("Sex: Female");
					break;
		}
		System.out.println("LitterSize: " +litterSize);
		System.out.println("Range: " +range);
		System.out.println("Location: " +location.x+ "," +location.y);
	}

	// Sets the priority for the organism as either feeding of socialising.
	int setPriority()
	{
		int priority = 0;
		float foodFactor = foodCur/foodMax;
		float socialFactor = socialCur/socialMax;
		if(foodFactor < socialFactor)
			priority = 1;
		else
			priority = 2;
		return priority;
	}

	// Affects the health as per its armour and the opponents damage.
	void combat(int dam)
	{
		healthCur -= (dam - armour);
	}

	// Checks whether the organism is hungry. Hungry is defined as the stomach
	// being less than or equal to 70% full.
	boolean isHungry()
	{
		boolean flagHungry = true;
		if(foodCur > 70)
			flagHungry = false;
		return flagHungry;
	}

	// Checks whether the organism is lonely. Lonely is defined as when the
	// Social meter is less than or equal to 70%.
	boolean isLonely()
	{
		boolean flagLonely = true;
		if(socialCur > 70)
			flagLonely = false;
		return flagLonely;
	}

	// Checks whether the organism has reached sexual maturity. The favourable
	// aage is defined as between 20% to 80% of the creatures lifespan.
	boolean isOfAge()
	{
		boolean flag = false;
		float ageMinReq = ageMax/5;
		float ageMaxPos = (ageMax/5)*4;
		if(ageCur > ageMinReq && ageCur < ageMaxPos)
			flag = true;
		return flag;
	}

	// For Herbivores. Increases current food as per quantity of grass given.
	void eatGrass(int grassQuantity)
	{
		foodCur += (grassQuantity*5);
		if(foodCur > foodMax)
			foodCur = foodMax;
	}

	// Increases the current social meter as per the number of neighbouring
	// allies.
	void socialise(int neighbourAllyCount)
	{
		socialCur += (socialReq*neighbourAllyCount);
		if(socialCur > socialMax)
			socialCur = socialMax;
	}

	// The effects of growing through to the next generation are carried out
	// here.
	void age()
	{
		ageCur++;
		foodCur -= foodReq;
		socialCur -= socialReq;
		healthCur += (healthMax/20);
	}

	// Checks whether the organism will be able to survive on to the next
	// generation.
	void checkLife()
	{
		if((ageCur >= ageMax) || (foodCur <= 0) || (socialCur <= 0) || (healthCur <= 0))
			die();
	}

	// RIP
	void die()
	{
		trophicLevel = 0;
		diet = 0;
		healthMax = 0;
		healthCur = 0;
		damage = 0;
		armour = 0;
		ageMax = 0;
		ageCur = 0;

		foodMax = 0;
		foodCur = 0;
		foodReq = 0;
		socialMax = 0;
		socialCur = 0;
		socialReq = 0;
		sex = 0;
		litterSize = 0;
		range = 0;

		specieID = calcSpecieID();
		creatureID = calcCreatureID();
		
		location = new Cell(-1,-1);
	}	
}

/* The following class defines a general grid. The matrix itslef is not defined.
 * This is a superclass for the Universe class.  
*/
class Grid
{
	int length;
	int breadth;
	int area;
	
	// Constructors for class Grid
	// Default Constructor
	Grid()
	{
		length = 0;
		breadth = 0;
		area = 0;	
	}
	
	// Rectangular Grid
	Grid(int l, int b)
	{
		length = l;
		breadth = b;
		area = l*b;
	}

	// Square Grid
	Grid(int len)
	{
		length = breadth = len;
		area = len*len;
	}

	// Copy constructor
	Grid(Grid g)
	{
		length = g.length;
		breadth = g.breadth;
		area = g.area;
	}

	// Methods to display the variables of the grid.
	void showGridVariables()
	{
		System.out.println("Length: " +length);
		System.out.println("Breadth: " +breadth);
		System.out.println("Area: " +area);
	}
		
}

/* Class Universe
 * This is where all the organisms and vegetation are 'stored'. It is derived
 * from class Grid. Instances of other classes i.e. Cell and Organism are 
 * utilized within methods of this class.
*/ 
class Universe extends Grid
{
	int grass[][];	// Grass Layer
	int maxGrass;	// Maximum level of vegetation
	int specie[][];	// Specie Layer
	long organism[][];	// Organism Layer

	int grassNextGen[][];	// Next Generation Grass Layer
	int specieNextGen[][];	// Next Generation Specie Layer
	long organismNextGen[][];	// Next Generation Organism Layer

	int rabbitCount;
	int rabbitDeadCount;
	int rabbitBornCount;

	int wolfCount;
	int wolfDeadCount;
	int wolfBornCount;

	int grassCount;
	int grassAmountCount;

	float grassAvg;

	// Constructors
	// Default constructor
	Universe()
	{
		super();
		
		grass = new int[breadth][length];
		specie = new int[breadth][length];
		organism = new long[breadth][length];
		
		grassNextGen = new int[breadth][length];
		specieNextGen = new int[breadth][length];
		organismNextGen = new long[breadth][length];
		
		maxGrass = 3;

		rabbitCount = 0;
		rabbitDeadCount = 0;
		rabbitBornCount = 0;
		
		wolfCount = 0;
		wolfDeadCount = 0;
		wolfBornCount = 0;

		grassCount = 0;
		grassAmountCount = 0;
		grassAvg = 0;
	}

	// Sets length, breadth and maxGrass.
	Universe(int l, int b, int mg)
	{
		super(l,b);
		
		grass = new int[breadth][length];
		specie = new int[breadth][length];
		organism = new long[breadth][length];

		grassNextGen = new int[breadth][length];
		specieNextGen = new int[breadth][length];
		organismNextGen = new long[breadth][length];

		maxGrass = mg;

		rabbitCount = 0;
		
		wolfCount = 0;

		grassCount = 0;
		grassAmountCount = 0;
		grassAvg = 0;
	}

	// Square universe. Also sets maxGrass.
	Universe(int len, int mg)
	{
		super(len);
		
		grass = new int[breadth][length];
		specie = new int[breadth][length];
		organism = new long[breadth][length];

		grassNextGen = new int[breadth][length];
		specieNextGen = new int[breadth][length];
		organismNextGen = new long[breadth][length];
		
		maxGrass = mg;

		rabbitCount = 0;
		rabbitDeadCount = 0;
		rabbitBornCount = 0;
		
		wolfCount = 0;
		wolfDeadCount = 0;
		wolfBornCount = 0;

		grassCount = 0;
		grassAmountCount = 0;
		grassAvg = 0;
	}

	// Copy constructor
	Universe(Universe u)
	{
		super(u);
		
		grass = new int[breadth][length];
		specie = new int[breadth][length];
		organism = new long[breadth][length];

		grassNextGen = new int[breadth][length];
		specieNextGen = new int[breadth][length];
		organismNextGen = new long[breadth][length];
		
		maxGrass = u.maxGrass;

		rabbitCount = 0;
		rabbitDeadCount = 0;
		rabbitBornCount = 0;
		
		wolfCount = 0;
		wolfDeadCount = 0;
		wolfBornCount = 0;

		grassCount = 0;
		grassAmountCount = 0;
		grassAvg = 0;
	}

	// Method to display variables
	void showUniverseVariables()
	{
		showGridVariables();
		System.out.println("Maximum level of grass: " + maxGrass);
	}

	// Method to randomly seed the grass layer. maxSeed is the maximum amount
	// of grass that is present at the zeroth generation.
	void seedGrassRandom(int maxSeed)
	{
		Random r = new Random();
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				grass[i][j] = r.nextInt(maxSeed+1);
				if(grass[i][j] > 0)
				{
					grassCount++;
					grassAmountCount += grass[i][j];
				}
			}
		}
		grassAvg = (float) grassAmountCount / grassCount;
	}

	// Method to randomly seed organisms. rabbitCount and wolfCount is the
	// number of Rabbits and Wolves to be seeded onto the zeroth generation.
	void seedOrganismRandom(int rabbitNum, int wolfNum)
	{
		rabbitCount = rabbitNum;
		wolfCount = wolfNum;
		Random r = new Random();
		Organism o; 
		int i, j;
		while(rabbitNum > 0)
		{
			i = r.nextInt(breadth);
			j = r.nextInt(length);
			if(specie[i][j] <= 0)
			{
				o = new Organism(212345, i, j);
				o.makeRabbit();
				// Rabbit created. Now to record its state in specie[][]
				// and organism[][] respectively;
				specie[o.location.x][o.location.y] = o.calcSpecieID();
				organism[o.location.x][o.location.y] = o.calcCreatureID();
				rabbitNum--;
			}
		}
		while(wolfNum > 0)
		{
			i = r.nextInt(breadth);
			j = r.nextInt(length);
			if(specie[i][j] <= 0)
			{
				o = new Organism(322345, i, j);
				o.makeWolf();
				// Rabbit created. Now to record its state in specie[][]
				// and organism[][] respectively;
				specie[o.location.x][o.location.y] = o.calcSpecieID();
				organism[o.location.x][o.location.y] = o.calcCreatureID();
				wolfNum--;
			}
		}
	}

	// Display the grass matrix. Value shown is the amount of grass present on
	// the respective location.
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

	// Method to display organisms in a descriptive manner.
	void showOrganismsDescriptive()
	{
		Organism o;
		int count = 0;
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				if(specie[i][j] > 0)
				{
					o = new Organism(specie[i][j], organism[i][j], i, j);
					count++;
					System.out.println("\nOrganism no: " +count);
					o.showOrganism();
				}
			}
		}
	}

	// Display the organism matrix. Each organism is represented by its trophic
	// level.
	void showOrganismGrid()
	{
		Organism o;
		for(int i=0; i<breadth; i++)
		{
			System.out.println();
			for(int j=0; j<length; j++)
			{
				o = new Organism(specie[i][j], organism[i][j], i, j);
				System.out.print(" " +o.trophicLevel);
			}
		}
		System.out.println();
	}

	// Updates the entire universe to the next generation.
	void updateToNextGen()
	{
		// Reset statistics
		resetStats();
		
		// First all creatures interact with their neighbours.
		interactCreatures();
		
		// Creatures who pass away during interactionCreatures() are removed
		// from the grid next.
		updateCreatures();
		
		// Calculates the next generation location for all creatures based on
		// their needs.
		calcCreatures();

		// Creatures are moved to their destinations.
		updateCreatures();

		// Grass is grown.
		calcGrass();

		// Grass is updated.
		updateGrass();
	}

	// For resetting the statistic counters at the start of each generation.
	void resetStats()
	{
		rabbitCount = 0;
		wolfCount = 0;
		grassCount = 0;
		grassAmountCount = 0;
		grassAvg = 0;
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				if(specie[i][j] > 0)
				{
					int tLevel = specie[i][j] / 100000;
					if(tLevel == 2)
					{
						rabbitCount++;
					}
					if(tLevel == 3)
					{
						wolfCount++;
					}
				}
				if(grass[i][j] > 0)
				{
					grassCount++;
					grassAmountCount += grass[i][j];
				}
			}
		}
		if(grassCount > 0)
			grassAvg = (float) grassAmountCount / grassCount;
	}

	// Creatures interact with neighbours in this method.	
	void interactCreatures()
	{
		Organism creature;
		Cell locationFood[];
		Cell locationPredator[];
		
		for(int I=0; I<breadth; I++)
		{
			for(int J=0; J<length; J++)
			{
				// I and J span the matrix
				if(specie[I][J] > 0)
				{
					// Encountered live creature
					creature = new Organism(specie[I][J], organism[I][J], I, J);

					// Check whether creature requires food.
					if(creature.isHungry())
					{
						// Creature is hungry
						locationFood = new Cell[8];
						// Locating and if found, consuming food
						switch(creature.diet)
						{
							case 0:	// Herbivore
								locationFood = Neighbourhood.findGrass(creature, grass, length, breadth);
								boolean flagEaten = false;
								while(flagEaten == false)
								{
									// The temp variable is used to determine a random location
									// for the creature to graze from.
									Random r = new Random();
									int temp = r.nextInt(locationFood.length);
									Cell eatFrom = new Cell();
									eatFrom.x = locationFood[temp].x;
									eatFrom.y = locationFood[temp].y;
									if(eatFrom.x != -1 && eatFrom.y != -1)
									{
										creature.eatGrass(grass[eatFrom.x][eatFrom.y]);
										grass[eatFrom.x][eatFrom.y] = 0;
										grassNextGen[eatFrom.x][eatFrom.y] = 0;
										flagEaten = true;
									}
								}
								break;
							case 1: // Carnivore
								// i and j span the 3X3 matrix with centre at creatures location.
								for(int i=creature.location.x - 1; i<=creature.location.x + 1; i++)
								{
									// Check i within bounds.
									if((i<0) || (i>=breadth))
										continue;
									
									for(int j=creature.location.y - 1; j<=creature.location.y + 1; j++)
									{
										// Check j within bounds. 
										if((j<0) || (j>=length))
											continue;

										// Check i, j not creature's location.
										else if((i==creature.location.x) && (j==creature.location.y))
											continue;
										
										else
										{
											// i and j values acceptable.
											if(specie[i][j] > 0 && specie[i][j] < creature.specieID)
											{
												// Prey found
												Organism prey = new Organism(specie[i][j], organism[i][j], i, j);
												
												prey.combat(creature.damage);
												creature.combat(prey.damage);
												
												if(creature.healthCur <= 0)
												{
													// Predator has died.
													creature.die();
													specie[i][j] = prey.calcSpecieID();
													organism[i][j] = prey.calcCreatureID();
													wolfDeadCount++;
													break;
												}
												else if(prey.healthCur <= 0)
												{
													// Predator attack succesful
													prey.die();
													specie[i][j] = prey.calcSpecieID();
													organism[i][j] = prey.calcCreatureID();
													rabbitDeadCount++;

													// Feed
													creature.foodCur += 30;
													if(creature.foodCur > 100)
														creature.foodCur = 100;
												}
												
												specieNextGen[i][j] = prey.calcSpecieID();
												organismNextGen[i][j] = prey.calcCreatureID();
											}
										}
									}
								}
								
								break;
							
							default: // Not supposed to be here
								System.out.println("Error in interactCreatures, switch structure creature diet");
						}
					}	// End of isHungry() block

					// Check whether creature is lonely.
					if(creature.isLonely())
					{
						for(int i=creature.location.x - 1; i<=creature.location.x + 1; i++)
						{
							// Check i within bounds.
							if(i<0 || i>=breadth)
								continue;
							
							for(int j=creature.location.y - 1; j<=creature.location.y + 1; j++)
							{
								// Check j within bounds.
								if((j<0) || (j>=length))
									continue;

								// Check i, j not creature's location.
								else if((i==creature.location.x) && (j==creature.location.y))
									continue;
								
								else
								{
									// i and j values acceptable.
									if(specie[i][j] == creature.specieID)
									{
										// Ally found. Socialise.
										creature.socialise(1);
									}
								}
							}
						}
					}	// end of isLonely() block.
					
					// Reproduction					
					if(creature.isOfAge() && creature.sex == 1)
					{
						// Creature is female and of age.
						for(int i=creature.location.x - 1; i<=creature.location.x + 1; i++)
						{
							// Check i within bounds.
							if(i<0 || i>=breadth)
								continue;
							
							for(int j=creature.location.y - 1; j<=creature.location.y + 1; j++)
							{
								// Check j within bounds.
								if((j<0) || (j>=length))
									continue;

								// Check i, j not creature's location.
								else if((i==creature.location.x) && (j==creature.location.y))
									continue;
								
								else
								{
									if(specie[i][j] == creature.specieID)
									{
										// Ally found.
										Organism mate = new Organism(specie[i][j], organism[i][j], i, j);
										if(mate.isOfAge() && mate.sex == 2)
										{
											// Ally is male and of age.
											// Mates are compatible.
											// Find empty location for baby(s).
											Cell locationFreeSpace[] = new Cell[8];
											locationFreeSpace = Neighbourhood.findFreeSpace(specie, length, breadth, creature.location);

											// tempCount holds number of empty spaces neighbouring the mother.
											int tempCount = 0;
											while(locationFreeSpace[tempCount].x != -1)
											{
												tempCount++;
											}
											
											if(tempCount >= creature.litterSize)
											{
												// Space found is enough
												// Random locations chosen for birth.
												Random r = new Random();
												int numberOfYoung = 1;
												while(numberOfYoung <= creature.litterSize)
												{
													int temp = r.nextInt(tempCount);
													if(locationFreeSpace[temp].x != -1)
													{
														// Create a copy of mother but in new location.
														Organism baby = new Organism(creature.specieID, creature.creatureID, locationFreeSpace[temp].x,
																					locationFreeSpace[temp].y);

														// Set age at zero etc
														baby.makebaby();
														numberOfYoung++;
														specieNextGen[baby.location.x][baby.location.y] = baby.calcSpecieID();
														organismNextGen[baby.location.x][baby.location.y] = baby.calcCreatureID();
														if(baby.trophicLevel == 2)
															rabbitBornCount++;
														if(baby.trophicLevel == 3)
															wolfBornCount++;
													}
												}
											}
										}	// End of mate search block
									}
								}
							}
						}
					}	// End of reproduction block
					
					int temp = creature.trophicLevel;
					creature.age();
					creature.checkLife();

					specieNextGen[I][J] = creature.calcSpecieID();
					organismNextGen[I][J] = creature.calcCreatureID();
				}	// End of encountered live creature block.
			}
		}
	}	// End of interactCreatures() method.
	
	// The following method accounts for movement of the creatures based on
	// their current state and needs
	void calcCreatures()
	{
		Organism creature;
		Cell locationFood[];
		Cell locationAlly[];
		Cell locationPredator[];

		// I and J span the creature grid
		for(int I=0; I<breadth; I++)
		{
			for(int J=0; J<length; J++)
			{
				if(specie[I][J] > 0)
				{
					// Encountered live Creature
					creature = new Organism(specie[I][J], organism[I][J], I, J);
					locationFood = new Cell[creature.range*8];
					locationAlly = new Cell[creature.range*8];
					locationPredator = new Cell[creature.range*8];

					// Sets food or socialising as primary concern.
					int priority = creature.setPriority();
					boolean flagPreyFound = false;

					// Locating Food
					switch(creature.diet)
					{
						case 0:	// Herbivore
							locationFood = Neighbourhood.findGrass(creature, grass, length, breadth);
							break;
							
						case 1: // Carnivore
							locationFood = Neighbourhood.findPrey(creature, specie, organism, length, breadth);
							if(locationFood[0].x >= 0 && locationFood[0].y >= 0)
								flagPreyFound = true;
							break;
							
						case 2: // Onmivore
							Random r = new Random();
							int z = r.nextInt(2);
							if(z == 1)
							{
								locationFood = Neighbourhood.findPrey(creature, specie, organism, length, breadth);
								if(locationFood[0].x == -1 && locationFood[0].y == -1)
									locationFood = Neighbourhood.findGrass(creature, grass, length, breadth);
							}
							else
								locationFood = Neighbourhood.findGrass(creature, grass, length, breadth);
							break;
							
						default: // Not supposed to be here
							System.out.println("Error in calcCreatures, switch structure creature diet");
					}
					
					// Locating Allys
					locationAlly = Neighbourhood.findAlly(creature, specie, length, breadth);

					// Locating Enemies
					locationPredator = Neighbourhood.findPredator(creature, specie, organism, length, breadth);

					/* At this point, surroundings have been scanned.
					 * Now to decide where the organism will move.
					 * Based on the locations calculated above, all locations
					 * are given some value. Higher the value, more the creature
					 * wants to go there.
					*/
					
					int locationValue[][] = new int[breadth][length];

					// +10 value for cell with food (20 if priority is food).
					// +4 value for cells surrounding prey (8 likewise).
					
					for(int i=0; i<locationFood.length; i++)
					{
						if(locationFood[i].x >= 0 && locationFood[i].y >= 0)
						{
							
							int multiplier = 1;
							if(priority == 1)
								multiplier = 2;
								
							locationValue[locationFood[i].x][locationFood[i].y] += (15*multiplier);

							// Further, only for carnivores
							if(flagPreyFound)
							{
								if((locationFood[i].x -1 >= 0)&&(locationFood[i].y -1 >= 0)&&(locationFood[i].x -1 < breadth)&&(locationFood[i].y -1 < length))
									locationValue[locationFood[i].x -1][locationFood[i].y -1] += (4*multiplier);
				
								if((locationFood[i].x -1 >= 0)&&(locationFood[i].y >= 0)&&(locationFood[i].x -1 < breadth)&&(locationFood[i].y < length))
									locationValue[locationFood[i].x -1][locationFood[i].y] += (4*multiplier);
							
								if((locationFood[i].x -1 >= 0)&&(locationFood[i].y +1 >= 0)&&(locationFood[i].x -1 < breadth)&&(locationFood[i].y +1 < length))
									locationValue[locationFood[i].x -1][locationFood[i].y +1] += (4*multiplier);
							
								if((locationFood[i].x >= 0)&&(locationFood[i].y -1 >= 0)&&(locationFood[i].x < breadth)&&(locationFood[i].y -1 < length))
									locationValue[locationFood[i].x][locationFood[i].y -1] += (4*multiplier);
							
								if((locationFood[i].x >= 0)&&(locationFood[i].y +1 >= 0)&&(locationFood[i].x < breadth)&&(locationFood[i].y +1 < length))
									locationValue[locationFood[i].x][locationFood[i].y +1] += (4*multiplier);
							
								if((locationFood[i].x +1 >= 0)&&(locationFood[i].y -1 >= 0)&&(locationFood[i].x +1 < breadth)&&(locationFood[i].y -1 < length))
									locationValue[locationFood[i].x +1][locationFood[i].y -1] += (4*multiplier);
							
								if((locationFood[i].x +1 >= 0)&&(locationFood[i].y >= 0)&&(locationFood[i].x +1 < breadth)&&(locationFood[i].y < length))
									locationValue[locationFood[i].x +1][locationFood[i].y] += (4*multiplier);

								if((locationFood[i].x +1 >= 0)&&(locationFood[i].y +1 >= 0)&&(locationFood[i].x +1 < breadth)&&(locationFood[i].y +1 < length))
									locationValue[locationFood[i].x +1][locationFood[i].y +1] += (4*multiplier);

							}							 
						}
					}

					// +5 for cell with ally (+10 according to priority)
					for(int i=0; i<locationAlly.length; i++)
					{
						int multiplier = 1;
						if(priority == 2)
							multiplier = 2;

						// Setting value for ally location
						if(locationAlly[i].x >= 0 && locationAlly[i].y >= 0)
						{
							locationValue[locationAlly[i].x][locationAlly[i].y] += (5*multiplier);

							// Setting value around the ally
							if((locationAlly[i].x - 1 >= 0)&&(locationAlly[i].y - 1 >= 0)&&(locationAlly[i].x - 1 < breadth)&&(locationAlly[i].y - 1 < length))
								locationValue[locationAlly[i].x -1][locationAlly[i].y -1] += (2*multiplier);
							
							if((locationAlly[i].x - 1 >= 0)&&(locationAlly[i].y >= 0)&&(locationAlly[i].x - 1 < breadth)&&(locationAlly[i].y < length))
								locationValue[locationAlly[i].x -1][locationAlly[i].y] += (2*multiplier);
							
							if((locationAlly[i].x - 1 >= 0)&&(locationAlly[i].y + 1 >= 0)&&(locationAlly[i].x - 1 < breadth)&&(locationAlly[i].y + 1 < length))
								locationValue[locationAlly[i].x -1][locationAlly[i].y +1] += (2*multiplier);
							
							if((locationAlly[i].x >= 0)&&(locationAlly[i].y - 1 >= 0)&&(locationAlly[i].x < breadth)&&(locationAlly[i].y - 1 < length))
								locationValue[locationAlly[i].x][locationAlly[i].y -1] += (2*multiplier);
							
							if((locationAlly[i].x >= 0)&&(locationAlly[i].y + 1 >= 0)&&(locationAlly[i].x < breadth)&&(locationAlly[i].y + 1 < length))
								locationValue[locationAlly[i].x][locationAlly[i].y +1] += (2*multiplier);
							
							if((locationAlly[i].x + 1 >= 0)&&(locationAlly[i].y - 1 >= 0)&&(locationAlly[i].x + 1 < breadth)&&(locationAlly[i].y - 1 < length))
								locationValue[locationAlly[i].x +1][locationAlly[i].y -1] += (2*multiplier);
							
							if((locationAlly[i].x + 1 >= 0)&&(locationAlly[i].y >= 0)&&(locationAlly[i].x + 1 < breadth)&&(locationAlly[i].y < length))
								locationValue[locationAlly[i].x +1][locationAlly[i].y] += (2*multiplier);

							if((locationAlly[i].x + 1 >= 0)&&(locationAlly[i].y + 1 >= 0)&&(locationAlly[i].x + 1 < breadth)&&(locationAlly[i].y + 1 < length))
								locationValue[locationAlly[i].x +1][locationAlly[i].y +1] += (2*multiplier);
						}
					}

					// -10 for cell with predator, -5 around predator
					for(int i=0; i<locationPredator.length; i++)
					{
						if(locationPredator[i].x>=0 && locationPredator[i].y>=0)
						{
							locationValue[locationPredator[i].x][locationPredator[i].y] -= 10;

							// Setting value around the Predator
							if((locationPredator[i].x-1>=0)&&(locationPredator[i].y-1>=0)&&(locationPredator[i].x-1<breadth)&&(locationPredator[i].y-1<length))
								locationValue[locationPredator[i].x -1][locationPredator[i].y -1] -= 5;
							
							if((locationPredator[i].x-1>=0)&&(locationPredator[i].y>=0)&&(locationPredator[i].x-1<breadth)&&(locationPredator[i].y < length))
								locationValue[locationPredator[i].x -1][locationPredator[i].y] -= 5;
							
							if((locationPredator[i].x-1>=0)&&(locationPredator[i].y+1>=0)&&(locationPredator[i].x-1<breadth)&&(locationPredator[i].y+1 < length))
								locationValue[locationPredator[i].x -1][locationPredator[i].y +1] -= 5;
							
							if((locationPredator[i].x>=0)&&(locationPredator[i].y-1>=0)&&(locationPredator[i].x < breadth)&&(locationPredator[i].y-1<length))
								locationValue[locationPredator[i].x][locationPredator[i].y -1] -= 5;
							
							if((locationPredator[i].x>=0)&&(locationPredator[i].y+1>=0)&&(locationPredator[i].x < breadth)&&(locationPredator[i].y+1 < length))
								locationValue[locationPredator[i].x][locationPredator[i].y +1] -= 5;
							
							if((locationPredator[i].x+1>=0)&&(locationPredator[i].y-1>=0)&&(locationPredator[i].x+1 < breadth)&&(locationPredator[i].y-1<length))
								locationValue[locationPredator[i].x +1][locationPredator[i].y -1] -= 5;
							
							if((locationPredator[i].x+1>=0)&&(locationPredator[i].y>=0)&&(locationPredator[i].x+1 < breadth)&&(locationPredator[i].y < length))
								locationValue[locationPredator[i].x +1][locationPredator[i].y] -= 5;

							if((locationPredator[i].x+1>=0)&&(locationPredator[i].y+1>=0)&&(locationPredator[i].x+1<breadth)&&(locationPredator[i].y+1<length))
								locationValue[locationPredator[i].x +1][locationPredator[i].y +1] -= 5;
						}
					}

					// Now to find value of most valuable location
					int maxVal = 0;
					for(int i=creature.location.x-2; i<=creature.location.x+2; i++)
					{
						if(i < 0 || i >= breadth)
							continue;
						for(int j=creature.location.y-2; j<=creature.location.y+2; j++)
						{
							if(j < 0 || j >= length)
								continue;
							if(locationValue[i][j] > maxVal)
							{
								maxVal = locationValue[i][j];
							}
						}
					}

					Cell destination = new Cell(creature.location.x, creature.location.y);

					boolean locFound = false;

					while(locFound == false)
					{
						Random rcell = new Random();
						int p = rcell.nextInt(6);
						p -= 2;
						int q = rcell.nextInt(6);
						q -= 2;
						if(creature.location.x+p < 0 || creature.location.x+p >= breadth)
							continue;
						if(creature.location.y+q < 0 || creature.location.y+q >= length)
							continue;
						if(locationValue[creature.location.x+p][creature.location.y+q] == maxVal);
						{
							destination.x = creature.location.x + p;
							destination.y = creature.location.y + q;
							locFound = true;
						}
					}
					
					if(destination.x == creature.location.x && destination.y == creature.location.y)
					{
						boolean locationFound = false;
						while(locationFound == false)
						{
							Random r = new Random();
							destination.x += (r.nextInt(3)-1);
							destination.y += (r.nextInt(3)-1);
							if(destination.x < 0)
							{
								if(r.nextInt(2) == 0)
									destination.x = 0;
								else
									destination.x = 1;
							}
							if(destination.x >= breadth)
							{
								if(r.nextInt(2) == 0)
									destination.x = breadth-1;
								else
									destination.x = breadth-2;
							}
							if(destination.y < 0)
							{
								if(r.nextInt(2) == 0)
									destination.y = 0;
								else
									destination.y = 1;
							}
							if(destination.y >= length)
							{
								if(r.nextInt(2) == 0)
									destination.y = length-1;
								else
									destination.y = length-2;
							}
							if(specieNextGen[destination.x][destination.y] <= 0)
							{
								locationFound = true;
							}
						}
					}

					// Delete current location
					specieNextGen[creature.location.x][creature.location.y] = 0;
					organismNextGen[creature.location.x][creature.location.y] = 0;
					
					// Now to move towards the most coveted location
					// First to check for occupancy
					if(specie[destination.x][destination.y] <= 0)
						creature.moveToCell(destination);

					// Write new location
					specieNextGen[creature.location.x][creature.location.y] = creature.calcSpecieID();
					organismNextGen[creature.location.x][creature.location.y] = creature.calcCreatureID();					
				}	// End of creature found
			}
		}
	}	// End of method calcCreatures()
	
	/* Method to 'grow' the grass to next generation. If grass is already
	 * present, it grows to the next level. Barren land, if surrounded by grass,
	 * grows grass at level 1. The algorithm for this method was written long
	 * before other methods. Hence it is a crude algorithm and not very 
	 * efficient. May be replaced with an algorithm similar to the one used
	 * in other methods in forthcoming versions.
	*/
	void calcGrass()
	{

		// Corners
		// Top Left
		if((grass[0][0] > 0) && (grass[0][0] < maxGrass))
			grassNextGen[0][0] = grass[0][0] + 1;
		else if(grass[0][0] == maxGrass)
			grassNextGen[0][0] = maxGrass;
		else if(grass[1][0] + grass[1][1] + grass[0][1] > 0)
			grassNextGen[0][0] = 1;
		// Top Right
		if((grass[0][length-1] > 0) && (grass[0][length-1] < maxGrass))
			grassNextGen[0][length-1] = grass[0][length-1] + 1;
		else if(grass[0][length-1] == maxGrass)
			grassNextGen[0][length-1] = maxGrass;
		else if(grass[0][length-2] + grass[1][length-2] + grass[1][length-1] > 0)
			grassNextGen[0][length-1] = 1;
		// Bottom Left
		if((grass[breadth-1][0] > 0) && (grass[breadth-1][0] < maxGrass))
			grassNextGen[breadth-1][0] = grass[breadth-1][0] + 1;
		else if(grass[breadth-1][0] == maxGrass)
			grassNextGen[breadth-1][0] = maxGrass;
		else if(grass[breadth-2][0] + grass[breadth-2][1] + grass[breadth-1][1] > 0)
			grassNextGen[breadth-1][0] = 1;
		// Bottom Right
		if((grass[breadth-1][length-1] > 0) && (grass[breadth-1][length-1] < maxGrass))
			grassNextGen[breadth-1][length-1] = grass[breadth-1][length-1] + 1;
		else if(grass[breadth-1][length-1] == maxGrass)
			grassNextGen[breadth-1][length-1] = maxGrass;
		else if(grass[breadth-1][length-2] + grass[breadth-2][length-2] + grass[breadth-2][length-1] > 0)
			grassNextGen[breadth-1][length-1] = 1;

		// Edges
		for(int i=1; i<length-1; i++)
		{
			// Top
			if((grass[0][i] > 0) && (grass[0][i] < maxGrass))
				grassNextGen[0][i] = grass[0][i] + 1;
			else if(grass[0][i] == maxGrass)
				grassNextGen[0][i] = maxGrass;
			else if(grass[0][i-1] + grass[1][i-1] + grass[1][i] + grass[1][i+1] + grass[0][i+1] > 0)
				grassNextGen[0][i] = 1;

			// Bottom
			if((grass[breadth-1][i] > 0) && (grass[breadth-1][i] < maxGrass))
				grassNextGen[breadth-1][i] = grass[breadth-1][i] + 1;
			else if(grass[breadth-1][i] == maxGrass)
				grassNextGen[breadth-1][i] = maxGrass;
			else if(grass[breadth-1][i-1] + grass[breadth-2][i-1] + grass[breadth-2][i] + grass[breadth-2][i+1] + grass[breadth-1][i+1] > 0)
				grassNextGen[breadth-1][i] = 1;
		}
		for(int j=1; j<breadth-1; j++)
		{
			// Left
			if((grass[j][0] > 0) && (grass[j][0] < maxGrass))
				grassNextGen[j][0] = grass[j][0] + 1;
			else if(grass[j][0] == maxGrass)
				grassNextGen[j][0] = maxGrass;
			else if(grass[j-1][0] + grass[j-1][1] + grass[j][1] + grass[j+1][1] + grass[j+1][0] > 0)
				grassNextGen[j][0] = 1;

			// Right
			if((grass[j][length-1] > 0) && (grass[j][length-1] < maxGrass))
				grassNextGen[j][length-1] = grass[j][length-1] + 1;
			else if(grass[j][length-1] == maxGrass)
				grassNextGen[j][length-1] = maxGrass;
			else if(grass[j-1][length-1] + grass[j-1][length-2] + grass[j][length-2] + grass[j+1][length-2] + grass[j+1][length-1] > 0)
				grassNextGen[j][length-1] = 1;
		}

		// Interior
		for(int i=1; i<breadth-1; i++)
		{
			for(int j=1; j<length-1; j++)
			{
				if((grass[i][j] > 0) && (grass[i][j] < maxGrass))
					grassNextGen[i][j] = grass[i][j] + 1;
				else if(grass[i][j] == maxGrass)
					grassNextGen[i][j] = maxGrass;
				else if(grass[i-1][j-1] + grass[i-1][j] + grass[i-1][j+1] + grass[i][j-1] + grass[i][j+1] + grass[i+1][j-1] + grass[i+1][j] + grass[i+1][j+1] > 0)
					grassNextGen[i][j] = 1;
			}
		}

		// Random sprout
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				if(grass[i][j] == 0)
				{
					Random r = new Random();
					if(r.nextInt(100) == 0)
						grassNextGen[i][j] = 1;
				}
			}
		}
	}

	// Updates the grass to the calculated values
	void updateGrass()
	{
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				grass[i][j] = grassNextGen[i][j];
			}
		}
	}	

	// Updates the creature states and locations as calculated
	void updateCreatures()
	{
		for(int i=0; i<breadth; i++)
		{
			for(int j=0; j<length; j++)
			{
				specie[i][j] = specieNextGen[i][j];
				organism[i][j] = organismNextGen[i][j];
				specieNextGen[i][j] = 0;
				organismNextGen[i][j] = 0;
			}
		}
	}
}

/* The neighbourhood class encapsulates several methods. These methods are used
 * to locate certain creatures or grass in a creature's surroundings. The
 * methods are defined as static so that they can be used similar to global
 * methods. These methods take into account that the range of different species
 * of organisms is different. Range is how far a creature can sense around it.
 * Eg - A rabbit has a range of 1, indicating that it can sense things only in
 * its immediate vicinity.
*/
class Neighbourhood
{
	// Locates unoccupied space in the neighbourhood
	static Cell[] findFreeSpace(int specie[][], int length, int breadth, Cell centre)
	{
		Cell location[] = new Cell[8];

		// Set default return values
		for(int i=0; i<location.length; i++)
			location[i] = new Cell(-1,-1);

		int k=0;
		for(int i = centre.x-1; i <= centre.x+1; i++)
		{
			if(i<0 || i>=breadth)
				continue;
			for(int j = centre.y-1; j <= centre.y+1; j++)
			{
				if(j<0 || j>=length)
					continue;
				if(i == centre.x && j == centre.y)
					continue;
				if(specie[i][j] <= 0)
				{
					location[k].x = i;
					location[k].y = j;
					k++;
				}
			}
		}
		return location;
	}

	// Locates grass in the neighbourhood
	static Cell[] findGrass(Organism o, int grass[][], int length, int breadth)
	{
		Cell location[];
		int maxLocations = 8 * o.range;
		
		location = new Cell[maxLocations];
		
		int k=0;
		boolean flagGrassFound = false;
		boolean flagRepeat = false;

		// Setting default return locations
		for(int i=0; i<maxLocations; i++)
			location[i] = new Cell(-1,-1);

		for(int r=1; r<=o.range && flagGrassFound == false; r++)
		{
			for(int i=o.location.x-r; i<=o.location.x+r; i++)
			{
				// Validate i
				if(i<0 || i>=breadth)
					continue;

				for(int j=o.location.y-r; j<=o.location.y+r; j++)
				{
					// Validate j
					if(j<0 || j>=length)
						continue;

					// Validate that the cell is not creature's location
					if(i == o.location.x && j == o.location.y)
						continue;

					flagRepeat = false;
					for(int z=0; z<k; z++)
					{
						if(i == location[z].x && j == location[k].y)
							flagRepeat = true;
					}
					if(grass[i][j] > 0 && flagRepeat == false)
					{
						//System.out.println("Grass");
						location[k].x = i;
						location[k].y = j;
						k++;
						flagGrassFound = true;
					}
				}
			}
		}
		return location;
	}

	// Locates members of the same species
	static Cell[] findAlly(Organism o, int specie[][], int length, int breadth)
	{

		// The following array returns the positions of located organisms.
		Cell locationFound[];
		int maxLocations = 8 * o.range;
		locationFound = new Cell[maxLocations];
		
		int k=0;
		boolean flagAllyFound = false;
		boolean flagRepeat = false;

		// Setting default return locations
		for(int i=0; i<maxLocations; i++)
			locationFound[i] = new Cell(-1,-1);

		for(int r=1; r<=o.range && flagAllyFound == false; r++)
		{
			for(int i=o.location.x-r; i<=o.location.x+r; i++)
			{
				// Validate i
				if(i<0 || i>=breadth)
					continue;

				for(int j=o.location.y-r; j<=o.location.y+r; j++)
				{
					// Validate j
					if(j<0 || j>=length)
						continue;
						
					// Validate that the cell is not creature's location
					if(i == o.location.x && j == o.location.y)
						continue;

					flagRepeat = false;
					for(int z=0; z<k; z++)
					{
						if(i == locationFound[z].x && j == locationFound[k].y)
							flagRepeat = true;
					}
					if(specie[i][j] == o.specieID && flagRepeat == false)
					{
						locationFound[k].x = i;
						locationFound[k].y = j;
						k++;
						flagAllyFound = true;
					}
				}
			}
		}	
		return locationFound;
	}

	// locates prey for carnivores
	static Cell[] findPrey(Organism predator, int specie[][], long organism[][], int length, int breadth)
	{
		Cell location[];
		int maxLocations = 8 * predator.range;
		location = new Cell[maxLocations];
		Organism o;
		int k=0;
		boolean flagPreyFound = false;
		boolean flagRepeat = false;

		// Setting default return locations
		for(int i=0; i<maxLocations; i++)
			location[i] = new Cell(-1,-1);

		for(int r=1; r<=predator.range && flagPreyFound == false; r++)
		{
			for(int i=predator.location.x-r; i<=predator.location.x+r; i++)
			{
				// Validate i
				if(i<0 || i>=breadth)
					continue;

				for(int j=predator.location.y-r; j<=predator.location.y+r; j++)
				{
					// Validate j
					if(j<0 || j>=length)
						continue;
					
					// Validate that the cell is not creature's location
					if(i == predator.location.x && j == predator.location.y)
						continue;

					if(specie[i][j] == 0)
						continue;

					flagRepeat = false;
					for(int z=0; z<k; z++)
					{
						if(i == location[z].x && j == location[k].y)
							flagRepeat = true;
					}
					o = new Organism(specie[i][j], organism[i][j], i, j);
					if(predator.trophicLevel > o.trophicLevel && flagRepeat == false)
					{
						location[k].x = i;
						location[k].y = j;
						k++;
						flagPreyFound = true;
					}
				}
			}
		}	
		return location;
	}

	// Locates predators
	static Cell[] findPredator(Organism prey, int specie[][], long organism[][], int length, int breadth)
	{
		Cell location[];
		int maxLocations = 8 * prey.range;
		location = new Cell[maxLocations];
		Organism o;
		int k=0;
		boolean flagPredatorFound = false;
		boolean flagRepeat = false;

		// Setting default return locations
		for(int i=0; i<maxLocations; i++)
			location[i] = new Cell(-1,-1);

		for(int r=1; r<=prey.range && flagPredatorFound == false; r++)
		{
			for(int i=prey.location.x-r; i<=prey.location.x+r; i++)
			{
				// Validate i
				if(i<0 || i>=breadth)
					continue;

				for(int j=prey.location.y-r; j<=prey.location.y+r; j++)
				{
					// Validate j
					if(j<0 || j>=length)
						continue;
						
					// Validate that the cell is not creature's location
					if(i == prey.location.x && j == prey.location.y)
						continue;

					flagRepeat = false;
					for(int z=0; z<k; z++)
					{
						if(i == location[z].x && j == location[k].y)
							flagRepeat = true;
					}
					o = new Organism(specie[i][j], organism[i][j], i, j);
					if(prey.trophicLevel < o.trophicLevel && flagRepeat == false)
					{
						location[k].x = i;
						location[k].y = j;
						k++;
						flagPredatorFound = true;
					}
				}
			}
		}	
		return location;
	}
}

/* The following class contains the main() method
*/
class RunSimulation
{
	public static void main(String args[]) throws IOException
	{
		Universe u = new Universe(42,24,2);
		u.seedOrganismRandom(60,30);
		u.seedGrassRandom(2);
		
		DataOutputStream dataGrass;
		DataOutputStream dataSpecie;
		DataOutputStream dataOrganism;

		FileWriter fw;
		
		try
		{
			dataGrass = new DataOutputStream(new FileOutputStream("grassData"));
		}catch(IOException exc) 
		{
			System.out.println("Cannot open file.");
			return;
		}
		
		try
		{
			dataSpecie = new DataOutputStream(new FileOutputStream("specieData"));
		}catch(IOException exc)
		{
			System.out.println("Cannot open file.");
			return;
		}
		
		try
		{
			dataOrganism = new DataOutputStream(new FileOutputStream("organismData"));
		}catch(IOException exc) 
		{
			System.out.println("Cannot open file.");
			return;
		}

		try
		{
			fw = new FileWriter("Simulationlog.txt");
		}catch(IOException exc)
		{
			System.out.println("Cannot open file");
			return;
		}

		try
		{
			fw.write("Simulation Log"); 			
		}catch(IOException exc)
		{
			System.out.println("Write error.");
		}
	
		for(int generation = 0; generation < 100; generation++)
		{
			try
			{
				for(int i = 0; i < u.breadth; i++)
				{
					for(int j = 0; j < u.length; j++)
					{
						dataGrass.writeInt(u.grass[i][j]);
						dataSpecie.writeInt(u.specie[i][j]);
						dataOrganism.writeLong(u.organism[i][j]);
					}
				}
				fw.write("\r\nGeneration: " + generation);
				fw.write("\r\n\tRabbit Details: ");
				fw.write("\r\n\t\tPopulation: " + u.rabbitCount); 
				
				fw.write("\r\n\tWolf Details: ");
				fw.write("\r\n\t\tPopulation: " + u.wolfCount);

				fw.write("\r\n\tGrass Details: ");
				fw.write("\r\n\t\tCells with vegetation: " + u.grassCount);
				fw.write("\r\n\t\tNet vegetation: " + u.grassAmountCount);
				fw.write("\r\n\t\tAverage amount of vegetation per cell: " + u.grassAvg);
				
				fw.write("\r\n");
			}catch(IOException exc)
			{
				System.out.println("Write error.");
			}
			u.updateToNextGen();
		}
						
		dataGrass.close();
		dataSpecie.close();
		dataOrganism.close();

		fw.close();
			
	}
}

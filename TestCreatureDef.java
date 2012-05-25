/* This class definition serves as a template for all species that we
 * observe. Every single organism is an instance of this class.
*/
class Creature
{
	int trophicLevel; // What level the creature occupies in the food web.
	int diet; // 0 is herbivore, 1 is omnivore and 2 is carnivore
	
	int currHealth; // Hitpoints
	int maxHealth;
	int damage;
	int armour;

	int currAge;	// The number of generations that the being has survived
	int maxAge;

	int foodReq;
	int foodMax;
	int foodCurr;
	int socialReq;
	int socialMax;
	int socialCurr;
	

	int sex;	// Reproduction is sexual

	Cell location;
}


abstract class HoneyBee extends Insect {
    private int foodCost;
    public static double HIVE_DMG_REDUCTION;

    public HoneyBee(Tile position, int hp, int foodCost) {
        super(position, hp);
        this.foodCost = foodCost;
    }

    public int getCost() {
        return foodCost;
    }


    @Override
    public void takeDamage(int damage) {
        if (this.getPosition().isHive()) {

            int reducedDamage = (int) (damage * (1 - HIVE_DMG_REDUCTION));

            super.takeDamage(reducedDamage);
        }
        else {
            super.takeDamage(damage);
        }
    }
}
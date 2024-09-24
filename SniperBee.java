class SniperBee extends HoneyBee {
    private int attackDamage;
    private int piercingPower;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public SniperBee(Tile position, int attackDamage, int piercingPower) {
        super(position, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
        this.piercingPower = piercingPower;
    }

    // private variable to help with takeAction
    private boolean shoot = false;

    @Override
    public boolean takeAction() {
        if (!getPosition().isOnThePath()) {
            return false; // not on the path
        }

        // If bee is aiming, switch to shooting mode and dont attack this turn
        if (!shoot) {
            shoot = true;
            return false;
        }

        Tile currentTile = this.getPosition();
        int numHornets = currentTile.getNumOfHornets();

        if (shoot) {
            while (!currentTile.isNest()) {
                if (numHornets > 0) {
                    for (int i = 0; i < Math.min(piercingPower, numHornets); i++) {
                        Hornet hornet = currentTile.getHornet();
                        hornet.takeDamage(attackDamage);
                    }
                    Aiming = false;
                    return true;
                }
            }
        }
        return false;
    }
}

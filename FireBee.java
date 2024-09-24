class FireBee extends HoneyBee {
    private int maxAttackRange;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public FireBee(Tile position, int maxAttackRange) {
        super(position, BASE_HEALTH, BASE_COST);
        this.maxAttackRange = maxAttackRange;
    }

    @Override
    public boolean takeAction() {

        Tile currentTile = getPosition();

        if (currentTile.isOnThePath()) {
            for (int i = 0; i < maxAttackRange; i++) {
                Tile targetTile = currentTile.towardTheNest();
                if (targetTile == null || targetTile.isNest() || targetTile.equals(this.getPosition())) {
                    break;
                }

                if (!targetTile.isOnFire() && targetTile.getNumOfHornets() > 0) {
                    targetTile.setOnFire();
                    return true;
                }
                currentTile = targetTile;
            }
        }
            return false;

    }


}

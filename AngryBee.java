class AngryBee extends HoneyBee {
    private int attackDamage;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public AngryBee(Tile position, int attackDamage) {
        super(position, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
    }

    @Override
    public boolean takeAction() {
        Tile currentTile = this.getPosition();

        if (currentTile != null && currentTile.isOnThePath() && !currentTile.isNest()) {

            Hornet targetHornet = currentTile.getHornet();
            if (targetHornet != null) {
                targetHornet.takeDamage(this.attackDamage);
                return true;
            }

            Tile nextTile = currentTile.towardTheNest();
            if (nextTile != null && !nextTile.isNest()) {
                Hornet nextTargetHornet = nextTile.getHornet();
                if (nextTargetHornet != null) {
                    nextTargetHornet.takeDamage(this.attackDamage);
                    return true;
                }
            }
        }

        return false;
    }
}
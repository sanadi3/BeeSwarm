class Hornet extends Insect {
    private int attackDamage;

    private boolean isQueen = false;
    private static int queenCount = 0;

    public Hornet(Tile position, int hp, int attackDamage) {
        super(position, hp);
        this.attackDamage = attackDamage;
    }

    private boolean takeOneAction() { // since queen takes two actions needs helper method
        if (getPosition().isOnFire()) {
            this.takeDamage(BASE_FIRE_DMG);
        }

        if (this.getPosition().getBee() != null) {
            this.getPosition().getBee().takeDamage(this.attackDamage);
            return true;
        }
        else if (this.getPosition().getBee() == null && this.getPosition().isHive() != true) {
            Tile nextTile = this.getPosition().towardTheHive();
            this.getPosition().removeInsect(this);
            this.setPosition(nextTile);
            return true;
        }
        return false;
    }

    @Override
    public boolean takeAction() {
        boolean tookAction = true;
        boolean temp = this.takeOneAction();
        if (temp == false) {
            tookAction = false;
        }
        if (this.isQueen) {
            boolean temp2 = this.takeOneAction();
            if (!temp2) {
                tookAction = false;
            }
        }
        return tookAction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hornet) {
            Hornet other = (Hornet) obj;
            return super.equals(obj) && this.attackDamage == other.attackDamage;
        }
        return false;
    }

    public static int BASE_FIRE_DMG;

    public boolean isTheQueen() {
        return isQueen;
    }

    public void promote() {
        if (queenCount == 0) {
            this.isQueen = true;
            queenCount++;
        }
    }

}

abstract class Insect {
    private Tile position;
    private int hp;

    public Insect(Tile position, int hp) {

        if (!position.addInsect(this)) {
            throw new IllegalArgumentException("Error.");
        }

        this.position = position;
        this.hp = hp;
    }

    public final Tile getPosition() {
        return position;
    }

    public final int getHealth() {
        return hp;
    }

    public void setPosition(Tile position) {
        this.position = position;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.position.removeInsect(this);
        }
    }

    public abstract boolean takeAction();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Insect) {
            Insect other = (Insect) obj;
            return this.position == other.position && this.hp == other.hp;
        }
        return false;
    }

    public void regenerateHealth(double percentage) {
        this.hp += (int) Math.round(this.hp * percentage / 100);
    }
}

public class Tile {
    private int food;
    private boolean beeHiveBuilt;
    private boolean hornetNestBuilt;
    private boolean isOnPath;
    private Tile nextTileTowardsHive;
    private Tile nextTileTowardsNest;
    private HoneyBee bee;
    private SwarmOfHornets swarm;



    public Tile() {

        this.food = 0;
        this.beeHiveBuilt = false;
        this.hornetNestBuilt = false;
        this.isOnPath = false;
        this.nextTileTowardsHive = null;
        this.nextTileTowardsNest = null;
        this.bee = null;
        this.swarm = new SwarmOfHornets();
    }


    public Tile(int food, boolean beeHiveBuilt, boolean hornetNestBuilt, boolean isOnPath, Tile nextTileTowardsHive, Tile nextTileTowardsNest, HoneyBee bee, SwarmOfHornets swarm) {
        this.food = food;
        this.beeHiveBuilt = beeHiveBuilt;
        this.hornetNestBuilt = hornetNestBuilt;
        this.isOnPath = isOnPath;
        this.nextTileTowardsHive = nextTileTowardsHive;
        this.nextTileTowardsNest = nextTileTowardsNest;
        this.bee = bee;
        this.swarm = swarm;
    }


    public boolean isHive() {
        return beeHiveBuilt;
    }


    public boolean isNest() {
        return hornetNestBuilt;
    }


    public void buildHive() {
        this.beeHiveBuilt = true;
    }


    public void buildNest() {
        this.hornetNestBuilt = true;
    }


    public boolean isOnThePath() {
        return isOnPath;
    }

    public Tile towardTheHive() {
        if (!isOnPath || this.beeHiveBuilt) {
            return null;
        }
        return nextTileTowardsHive;
    }

    public Tile towardTheNest() {
         if (!isOnPath || this.hornetNestBuilt) {
            return null;
        }
        return nextTileTowardsNest;
    }


    public void createPath(Tile nextTileTowardsHive, Tile nextTileTowardsNest) {
        if ((nextTileTowardsHive == null && !this.beeHiveBuilt) || (nextTileTowardsNest == null && !this.hornetNestBuilt)) {
            throw new IllegalArgumentException("Error");
        }
        this.nextTileTowardsHive = nextTileTowardsHive;
        this.nextTileTowardsNest = nextTileTowardsNest;

    }


    public int collectFood() {
        int Food = this.food;
        this.food = 0;
        return Food;
    }

    public void storeFood(int amount) {
        this.food += amount;
    }

    public int getNumOfHornets() {
        return swarm.sizeOfSwarm();
    }

    public HoneyBee getBee() {
        return this.bee;
    }

    public Hornet getHornet() {
        return swarm.getFirstHornet();
    }

    public Hornet[] getHornets() {
        return swarm.getHornets();
    }

    public boolean addInsect(Insect insect) {
        if (insect instanceof HoneyBee) {
            if (this.bee == null && !this.hornetNestBuilt) {
                this.bee = (HoneyBee) insect;
                insect.setPosition(this);
                return true;
            }
        } else if (insect instanceof Hornet) {
            if (this.isOnPath) {
                this.swarm.addHornet((Hornet) insect);
                insect.setPosition(this);
                return true;
            }
        }
        return false;
    }


    public boolean removeInsect(Insect insect) {
        if (insect == this.bee) {
            this.bee = null;
            insect.setPosition(null);
            return true;
        }

        else if (this.swarm.removeHornet((Hornet) insect)) {
            insect.setPosition(null);
            return true;
        }
        return false;
    }

    private boolean onFire = false;
    public void setOnFire() {
        this.onFire = true;
    }

    public boolean isOnFire() {
        return this.onFire;
    }



}


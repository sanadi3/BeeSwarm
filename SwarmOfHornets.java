public class SwarmOfHornets {
    private Hornet[] swarm;
    private int size;

    public static double QUEEN_BOOST;

    public SwarmOfHornets() {
        swarm = new Hornet[1];
        size = 0;
    }


    public int sizeOfSwarm() {
        return size;
    }

    public Hornet[] getHornets() {
        Hornet[] hornetArray = new Hornet[size];

        for (int i = 0; i < size; i++) {
            hornetArray[i] = swarm[i];
        }

        return hornetArray;
    }


    public Hornet getFirstHornet() {
        if (size == 0) {
            return null;
        } else {
            return swarm[0];
        }
    }

    // private method to use in addHornet()
    private void addSpace() {

        Hornet[] newSwarm = new Hornet[swarm.length + 5];

        for (int i = 0; i < swarm.length; i++) {
            newSwarm[i] = swarm[i];
        }

        swarm = newSwarm;
    }

    public void addHornet(Hornet hornet) {
        if (size == swarm.length) {
            addSpace();
        }

        swarm[size] = hornet;
        size++;

        if (hornet.isTheQueen()) {

            for (int i = 0; i < size - 1; i++) {
                if (!swarm[i].isTheQueen()) {
                    swarm[i].regenerateHealth(QUEEN_BOOST);
                }
            }
        }
    }


    public boolean removeHornet(Hornet hornet) {
            for (int i = 0; i < size; i++) {
                if (swarm[i] == hornet) {

                    for (int k = i; k < size - 1; k++) {
                        swarm[k] = swarm[k + 1];
                    }

                    size--;
                    return true;
                }
            }
            return false;
    }

}


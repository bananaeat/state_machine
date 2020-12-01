import java.util.LinkedList;

public class World extends AbstractWorld {
    private LinkedList<AbstractSM> lsm;

    public void addSM(AbstractSM o){
        lsm.add(o);
    }

    World(){
        this.lsm = new LinkedList<>();
        this.time = 0;
    }

    @Override
    public World clone(){
        World newWorld = new World();
        for(AbstractSM o : this.lsm){
            AbstractSM newO = o.clone(newWorld);
            newWorld.addSM(newO);
        }
        return newWorld;
    }

    @Override
    public void tick() {
        World newWorld = this.clone();
        this.time ++;
        for(AbstractSM o : lsm){
            o.tick();
        }
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("World :\n");
        for(AbstractSM o : this.lsm){
            str.append(o);
        }
        str.append("\n");
        return str.toString();
    }
}

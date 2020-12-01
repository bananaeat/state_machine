import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class World extends AbstractWorld {

    public void addSM(AbstractSM o){
        listSM.put(o.label, o);
    }

    World(){
        super();
        this.time = 0;
    }

    public double getTime(){
        return this.time;
    }

    @Override
    public World clone(){
        World newWorld = new World();
        for(AbstractSM o : this.listSM.values()){
            AbstractSM newO = o.clone();
            newWorld.addSM(newO);
        }
        newWorld.time = this.time;
        return newWorld;
    }

    @Override
    public World tick() {
        World newWorld = this.clone();
        newWorld.time ++;
        newWorld.listSM.values().forEach(AbstractSM::tick);
        newWorld.listSM.values().forEach(o -> o.updateWorldReference(newWorld));
        return newWorld;
    }

    @Override
    public AbstractSM getSM(String label) {
        return this.listSM.get(label);
    }

    @Override
    public LinkedList<AbstractSM> getSMsWithRegExp(String regexp) {
        LinkedList<AbstractSM> smList = new LinkedList<>();
        for(Map.Entry<String, AbstractSM> o: this.listSM.entrySet()){
            if(o.getKey().matches(regexp)){
                smList.add(o.getValue());
            }
        }
        return smList;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("World State, tick number: ").append(this.time).append("\n");
        for(Map.Entry<String, AbstractSM> o : this.listSM.entrySet()){
            str.append(o.getKey()).append(":");
            str.append(o.getValue());
            str.append("\n");
        }
        str.append("\n");
        return str.toString();
    }
}

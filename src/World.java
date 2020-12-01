import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class World extends AbstractWorld {
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
        HashMap<String, AbstractSM> hm = (HashMap<String, AbstractSM>) newWorld.listSM.clone();
        hm.values().forEach(AbstractSM::affect);

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

    public String statisticString(){
        StringBuilder str = new StringBuilder("World State, tick number: ").append(this.time).append("\n");
        str.append("Sea:").append(this.listSM.get("Sea")).append("\n");

        double sumSize = 0;
        double sumAge = 0;
        double maxSize = 0;
        double maxAge = 0;
        double minSize = 1000000000;
        LinkedList<AbstractSM> lFish = this.getSMsWithRegExp("Fish[0-9]+");

        for(AbstractSM o : lFish){
            sumSize += ((Fish) o).getSize();
            maxSize = Math.max(((Fish) o).getSize(), maxSize);
            minSize = Math.min(((Fish) o).getSize(), minSize);
            sumAge += ((Fish) o).getAge();
            maxAge = Math.max(((Fish) o).getAge(), maxAge);
        }

        int population = lFish.size();

        str.append("{Fish Population: ").append(population).append("}\n");
        str.append("{Average Fish Size: ").append(sumSize / population).append("}\n");
        str.append("{Max Fish Size: ").append(maxSize).append("}\n");
        str.append("{Min Fish Size: ").append(minSize).append("}\n");
        str.append("{Average Fish Age: ").append(sumAge / population).append("}\n");
        str.append("{Max Fish Age: ").append(maxAge).append("}\n");
        str.append("\n");

        return str.toString();
    }
}

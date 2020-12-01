import java.util.AbstractList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstractWorld {
    protected int time;
    protected HashMap<String, AbstractSM> listSM;
    protected AbstractWorld worldReference = null;

    AbstractWorld(){
        this.listSM = new HashMap<>();
    }

    public void addSM(AbstractSM o){ listSM.put(o.label, o); }

    public void removeSM(String label){ listSM.remove(label); }

    public abstract AbstractWorld clone();
    public abstract AbstractWorld tick();
    public abstract AbstractSM getSM(String label);
    public abstract LinkedList<AbstractSM> getSMsWithRegExp(String regexp);

}

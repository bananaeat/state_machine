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

    public abstract AbstractWorld clone();
    public abstract AbstractWorld tick();
    public abstract AbstractSM getSM(String label);
    public abstract LinkedList<AbstractSM> getSMsWithRegExp(String regexp);

}

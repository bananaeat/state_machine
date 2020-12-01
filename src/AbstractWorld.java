import java.util.AbstractList;

public abstract class AbstractWorld {
    double time;
    AbstractList<AbstractSM> listSM = null;
    AbstractWorld worldReference = null;

    public abstract AbstractWorld clone();
    public abstract void tick();
}

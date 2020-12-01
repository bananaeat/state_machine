public abstract class AbstractSM {
    AbstractWorld worldReference;

    AbstractSM(AbstractWorld w){
        this.worldReference = w;
    }

    abstract AbstractSM clone(AbstractWorld w);
    abstract void tick();
}

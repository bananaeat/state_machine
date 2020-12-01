public abstract class AbstractSM {
    AbstractWorld worldReference;
    String label;

    AbstractSM(AbstractWorld w, String label){
        this.worldReference = w;
        this.label = label;
    }

    public abstract AbstractSM clone();
    public abstract void tick();
    public abstract String toString();
    public void updateWorldReference(AbstractWorld w) { this.worldReference = w; }
}

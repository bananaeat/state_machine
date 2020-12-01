public class Sea extends AbstractSM {
    private double seaLevel;
    private double seaArea;

    Sea(double seaLevel, double seaArea, AbstractWorld w){
        super(w, "Sea");
        this.seaLevel = seaLevel;
        this.seaArea = seaArea;
    }

    public double getSeaLevel(){ return this.seaLevel; }

    public double getSeaArea(){ return this.seaArea; }

    @Override
    public AbstractSM clone() { return new Sea(this.seaLevel, this.seaArea, this.worldReference); }

    @Override
    public void tick() {
        this.seaLevel = 10 + Math.abs(Math.sin(this.worldReference.time * Math.PI / 16));
        seaArea = seaArea * 1.01;
    }

    @Override
    public String toString(){ return String.format("{seaLevel: %.2f, seaArea: %.2f}", seaLevel, seaArea); }
}

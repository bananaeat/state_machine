public class Sea extends AbstractSM {
    private double seaLevel;
    private double seaArea;

    Sea(double seaLevel, double seaArea, AbstractWorld w){
        super(w);
        this.seaLevel = seaLevel;
        this.seaArea = seaArea;
    }


    @Override
    AbstractSM clone(AbstractWorld w) {
        return new Sea(this.seaLevel, this.seaArea, w);
    }

    @Override
    public void tick() {
        this.seaLevel = 10 + Math.abs(Math.sin(this.worldReference.time * Math.PI / 16));
        seaArea = seaArea * 1.01;
    }

    @Override
    public String toString(){
        return String.format("{Sea: seaLevel: %.2f, seaArea: %.2f", seaLevel, seaArea);
    }
}

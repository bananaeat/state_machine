import java.util.HashSet;
import java.util.LinkedList;

public class Fish extends AbstractSM{
    private double size;
    private double age;
    private int labelN;

    Fish(double size, double age, AbstractWorld w, int labelN) {
        super(w, "Fish" + labelN);
        this.labelN = labelN;
        this.age = age;
        this.size = size;
    }

    @Override
    public AbstractSM clone() { return new Fish(this.size, this.age, this.worldReference, this.labelN); }

    @Override
    public void tick() {
        Sea sea = (Sea) this.worldReference.getSM("Sea");
        LinkedList<AbstractSM> fishList = this.worldReference.getSMsWithRegExp("Fish[0-9]");

        double sumFishSize = 0;
        for(AbstractSM o : fishList){
            sumFishSize += ((Fish) o).size;
        }
        this.size = this.size * (sea.getSeaLevel() - 9.5) * (1 / (1 + Math.exp(-this.size * fishList.size() / sumFishSize)) + 0.5);
        this.age += 0.01;
    }

    @Override
    public String toString() { return String.format("{size: %.2f, age: %.2f}", size, age); }
}

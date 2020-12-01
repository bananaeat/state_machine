import java.util.HashSet;
import java.util.LinkedList;

public class Fish extends AbstractSM{
    private double size;
    private double age;
    private long labelN;

    Fish(double size, double age, AbstractWorld w, long labelN) {
        super(w, "Fish" + labelN);
        this.labelN = labelN;
        this.age = age;
        this.size = size;
    }

    public double getSize(){ return this.size; }

    public double getAge(){ return this.age; }

    @Override
    public AbstractSM clone() { return new Fish(this.size, this.age, this.worldReference, this.labelN); }

    @Override
    public void tick() {
        Sea sea = (Sea) this.worldReference.getSM("Sea");
        LinkedList<AbstractSM> fishList = this.worldReference.getSMsWithRegExp("Fish[0-9]+");

        double sumFishSize = 0;
        for(AbstractSM o : fishList){
            sumFishSize += ((Fish) o).size;
        }
        this.size = this.size * (sea.getSeaLevel() - 9.5) * (1 / (1 + Math.exp(-this.size * fishList.size() / sumFishSize)) + 0.5);
        this.age += 1;
    }

    @Override
    public void affect() {
        if(this.size > 10){
            double sizeA = Math.random() * this.size;
            double sizeB = this.size - sizeA;
            Fish fishChild1 = new Fish(sizeA, 0, this.worldReference, this.labelN*10L+1L);
            Fish fishChild2 = new Fish(sizeB, 0, this.worldReference, this.labelN*10L+2L);
            this.worldReference.addSM(fishChild1);
            this.worldReference.addSM(fishChild2);
            this.worldReference.removeSM("Fish" + this.labelN);
            this.worldReference = null;
        } else if (this.age > 8 || this.size < 0.5 || this.size > 100){
            this.worldReference.removeSM("Fish" + this.labelN);
            this.worldReference = null;
        }
    }

    @Override
    public String toString() { return String.format("{size: %.2f, age: %.2f}", size, age); }
}

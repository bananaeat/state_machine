public class Main {
    public static void main(String[] args) {

        World newWorld = initializeWorld();

        System.out.println(newWorld);
        for(int i = 0; i < 20; i++){
            newWorld = newWorld.tick();
            System.out.println(newWorld);
        }

    }

    public static World initializeWorld(){
        //Create World Instance
        World newWorld = new World();

        //Create State Machines
        Sea littleSea = new Sea(10,1, newWorld); //Create Sea State Machine

        Fish littleFish = new Fish(1, 0, newWorld, 1); //Create Fish State Machines
        Fish mediumFish = new Fish(2, 0, newWorld, 2);
        Fish largeFish = new Fish(3, 0, newWorld, 3);

        //Add State Machines to World
        newWorld.addSM(littleSea); //Add Sea State Machine

        newWorld.addSM(littleFish); //Add Fish State Machines
        newWorld.addSM(mediumFish);
        newWorld.addSM(largeFish);

        return newWorld;
    }
}

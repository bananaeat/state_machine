public class Main {
    public static void main(String[] args) {

        World newWorld = initializeWorld();

        for(int i = 0; i < 20; i++){
            newWorld.tick();
            System.out.println(newWorld);
        }


    }

    public static World initializeWorld(){
        //Create World Instance
        World newWorld = new World();

        //Create State Machines
        Sea littleSea = new Sea(0,1,newWorld); //Create Sea State Machine

        //Add State Machines to World
        newWorld.addSM(littleSea); //Add Sea State Machine

        return newWorld;
    }
}

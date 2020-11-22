package problemb;

public class MainClass {
    public static void main(String[] args) {        
        java.util.Scanner sc = new java.util.Scanner(System.in);        
        World w = new World();
        
        if(w.enterNM(sc) && w.createAndBuildMap(sc)) {            
            if (w.checkOuterRingOfTheMatrixForSeaWater()) {
                while(w.fillTheRestOfTheMatrixWithSeaWater()) {
                    // Repeat process until done
                }
                
                System.out.println(w.calculateTotalCoastLine());
                //w.printMap();
            }
            else {
                // The entire outer ring of the matrix is land, meaning we can start counting the coast line.
                System.out.println(w.calculateCoastLineOutsideTheMatrix());
            }
        }
    }
}

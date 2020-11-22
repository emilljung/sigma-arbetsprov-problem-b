package problemb;

public class World {
    private char[][] map;
    private int N, M;
    
    public World() {}
    
    public boolean enterNM(java.util.Scanner sc) {
        boolean correctInput = true;
        
        try {
            this.N = sc.nextInt();
            this.M = sc.nextInt();
            
            if (!this.checkNM()) {
                correctInput = false;
            }
        }
        catch (java.util.InputMismatchException e) {
            System.out.println("You must enter 2 integers; '<N> <M>'");
            correctInput = false;
        }
        
        return correctInput;
    }
    
    public boolean checkNM() {
        boolean correctInput = true;
        
        // Check if the integers, '<N> <M>', you've just added are within the acceptable range
        if (!((1 <= this.N && this.N <= 1000) && (1 <= this.M && this.M <= 1000))) {
            System.out.println("<N> & <M> must be any value in the range 1-1000");
            correctInput = false;
        }
        return correctInput;
    }
    
    public boolean createAndBuildMap(java.util.Scanner sc) {
        boolean correctInput = true;
        
        this.map = new char[this.N][this.M];
        
        for(int i = 0; i < this.N; i++) {
            String input = sc.next();
            if (input.length() != this.M) {
                System.out.println("The length of the input must be " + this.M);
                correctInput = false;
                break;
            }
            else if (!this.checkIfLineConsistsOfOnesAndZeros(input)) {
                System.out.println("The input may only consist of '1' and/or '0' characters!");
                correctInput = false;
                break;
            }
            else {
                for(int j = 0; j < this.M; j++) {
                    this.map[i][j] = input.charAt(j);
                }
            }
        }
        
        return correctInput;
    }
    
    public boolean checkIfLineConsistsOfOnesAndZeros(String line) {
        boolean correctInput = true;
        
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '0' && line.charAt(i) != '1') {
                correctInput = false;
                break;
            }
        }
        
        return correctInput;
    }
    
    public boolean checkOuterRingOfTheMatrixForSeaWater() {
        // All the water which is connected to the water "outside the matrix" is marked as 'x'.
        // The coast lines will later be counted by checking against 'x'.
        
        boolean anyWater = false;
        // Check the top row for '0'
        for (int i = 0; i < this.M; i++) {
            if (this.map[0][i] == '0') {
                this.map[0][i] = 'x';
                anyWater = true;
            }
        }
        
        // Check the rest of the row edges for '0'
        for (int i = 1; i < this.N-1; i++) {
            if (this.map[i][0] == '0') {
                this.map[i][0] = 'x';
                anyWater = true;
            }
            
            if (this.map[i][this.M-1] == '0') {
                this.map[i][this.M-1] = 'x';
                anyWater = true;
            }
        }
        
        // Check the bottom row for '1'
        for (int i = 0; i < this.M; i++) {
            if (this.map[this.N-1][i] == '0') {
                this.map[this.N-1][i] = 'x';
                anyWater = true;
            }
        }
        
        return anyWater;
    }
    
    public boolean fillTheRestOfTheMatrixWithSeaWater() {
       boolean seaWaterExpanded = false;
       
       for (int i = 1; i < this.N-1; i++) {
           for (int j = 1; j < this.M-1; j++) {
               if (this.map[i][j] == '0') {
                   if (this.map[i-1][j] == 'x' || this.map[i+1][j] == 'x' || this.map[i][j-1] == 'x' || this.map[i][j+1] == 'x') {
                       this.map[i][j] = 'x';
                       seaWaterExpanded = true;
                   }
               }
           }
       }
       
       return seaWaterExpanded;
   }
    
    public int calculateCoastLineOutsideTheMatrix() {
        int coastLine = 0;
        
        // Check the top row for '1'
        for (int i = 0; i < this.M; i++) {
            if (this.map[0][i] == '1') {
                coastLine++;
            }
        }
        
        // Check the rest of the row edges for '0'
        for (int i = 0; i < this.N; i++) {
            if (this.map[i][0] == '1') {
                coastLine++;
            }
            
            if (this.map[i][this.M-1] == '1') {
                coastLine++;
            }
        }
        
        // Check the bottom row for '1'
        for (int i = 0; i < this.M; i++) {
            if (this.map[this.N-1][i] == '1') {
                coastLine++;
            }
        }
        
        return coastLine;
    }
    
    public int calculateTotalCoastLine() {
        int coastLine = 0;
        
        coastLine += this.calculateCoastLineOutsideTheMatrix();
                
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.M; j++) {
                if (this.map[i][j] == '1') {
                    if (i > 0 && this.map[i-1][j] == 'x') {
                       coastLine++;
                    }
                    if (i < this.N-1 && this.map[i+1][j] == 'x') {
                       coastLine++;
                    }
                    if (j > 0 && this.map[i][j-1] == 'x') {
                       coastLine++;
                    }
                    if (j < this.M-1 && this.map[i][j+1] == 'x') {
                       coastLine++;
                    }
                }
            }
        }
        
        return coastLine;
    }
    
    public void printMap() {
        System.out.println("");
        for (int i = 0; i < this.N; i++) {
            System.out.println(this.map[i]);
        }
    }
}

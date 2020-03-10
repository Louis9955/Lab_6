import java.util.Scanner;

public class ConnectFour {

    public static void printBoard(char[][] array){ //displays board
        for(int i = array.length-1; i >= 0; i--){
            for(int j = 0; j <= array[i].length-1; j++){
                System.out.print(array[i][j]);
                if(j == array[i].length-1){
                    System.out.println();
                } else{
                    System.out.print(" ");
                }
            }
        }
    }

    public static char[][] initializeBoard(char[][] array){ //initializes board
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array[i].length; j++)
                array[i][j] = '-';
       return array;
    }

    public static int insertChip(char[][] array, int col, char chipType){ //returns row for char to be added
        for(int i = 0; i < array.length; i++)
            if(array[i][col] == '-'){
                array[i][col] = chipType;
                return i;
            }
        return 0;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){ //checks if win condition is met
        int count;

        //horizontal check
        for(int i = 0; i < array.length; i++){
            count = 0;
            for(int j = 0; j < array[i].length; j++)
                if(array[i][j] == chipType){
                    count++;
                    if(count == 4)
                        return true;
                } else{
                    count = 0;
                }
        }

        //vertical check
        for(int i = 0; i < array[0].length; i++){
            count = 0;
            for(int j = 0; j < array.length; j++)
                if(array[j][i] == chipType){
                    count++;
                    if(count == 4)
                        return true;
                } else{
                    count = 0;
                }
        }

        //check draw
        count = 0; //count repurposed for blank space count
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array[i].length; j++)
                count = (array[i][j] == '-') ? count+1 : count;
        if(count == 0){ //if no blank spaces on board
            System.out.println("\nDraw. Nobody wins.");
            System.exit(0);
        }

        //win condition not met
        return false;
    }

    public static void main(String[] args) {

        //Initialization
        Scanner input = new Scanner(System.in);
        int col, row;
        System.out.print("What would you like the height of the board to be? ");
        int height = input.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = input.nextInt();
        char[][] board = new char[height][length];
        board = initializeBoard(board);

        printBoard(board);
        System.out.println("\nPlayer 1: x");
        System.out.println("Player 2: o");

        //Main Program Loop
        while(true){
            System.out.print("\nPlayer 1: Which column would you like to choose?");
            col = input.nextInt();
            row = insertChip(board, col, 'x');
            printBoard(board);
            if(checkIfWinner(board, col, row, 'x') == true){
                System.out.println("\nPlayer 1 won the game!");
                System.exit(0);
            }

            System.out.print("\nPlayer 2: Which column would you like to choose?");
            col = input.nextInt();
            row = insertChip(board, col, 'o');
            printBoard(board);
            if(checkIfWinner(board, col, row, 'o') == true){
                System.out.println("\nPlayer 2 won the game!");
                System.exit(0);
            }
        }
    }
}

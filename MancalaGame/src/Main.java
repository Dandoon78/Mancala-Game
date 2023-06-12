/* public class Main
 * Student Name: Daniel Roufail
 * Course Code: ICS 4U1
 * Project Start Date: May 31, 2023
 * Project Completion Date: June 12, 2023
 * 
 * This class contains the main method where program execution begins. 
 * The game I am making is based on the board game Mancala. This game consists 
 * of a two by six grid space with the top row being used by one player and the 
 * bottom row being used by the other player. There are four pieces in each of the blocks 
 * for both rows. There are also storage blocks on either side of the board which will be 
 * used to tally the pieces stored for each player. The goal of the game is to have the 
 * most pieces in your storage by the end of the game. 
 * The rules of the game is as follows:  
 *      1. Each player takes turns selecting one of the blocks containing stones from their side of the board.
 * 
 *      2. The pieces from this selected block are then distributed one by one to the other blocks in a 
 *          counter-clockwise direction until the pieces are all out.
 * 
 *      3. If the last piece lands in the storage block then the player gains a free turn.  
 * 
 *      4. If the last piece lands on an empty hole on the current player’s side, then all the pieces in the row, 
 *          including the the pieces on the opponent’s side, are captured and put into the current player’s 
 *          storage. 
 * 
 *      5. The game ends when all six holes on either side of the board are empty. If a player still has any 
 *          pieces left on the board they are then transferred to their storage. 
 * 
 */
public class Main {

    /* Method startDisplay
     * This method outputs the initial welcome display
     * Parameters:
     *      none
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public static void startDisplay(){
        
        // Outputs the display
        System.out.println("-------------------------");
        System.out.println("Welcome to the Mancala Game!");
        System.out.println("Brought to you by Daniel Roufail");
        System.out.println("-------------------------");
    }
    
    /* Method playerMove
     * This method completes the player's move when it is their turn
     * Parameters:
     *      board                   : GameBoard variable used to store the gameboard which contains the pieces
     *      row                     : Integer variable used to store the current row of the piece being moved
     * Return Values:
     *      none
     * Local Variables used:
     *      col                     : Integer variable used to store the column of the selected block
     *      player                  : Integer variable used to store the current player
     */
    public static void playerMove(GameBoard board, int row){
        int col;
        int player = row;

        // Outputs the board in proper format
        System.out.println("It is currently Player" + player + "'s turn ...");
        System.out.println(board);
        System.out.println("--------------------------");

        // Prompts the user for a column until valid
        do{
            col = Utils.inputIntegerBetween("Specify a column to move your pieces (1-6): ", 1, 6);

            if (!board.isEmpty(row, col)){
                board.setMove(row, col);
                System.out.println("--------------------------");
                break;
            }
            else
                System.out.println("Column empty! Invalid move!");
        }
        while (true);
        
    }

    /* Method displayEnding
     * This method outputs the ending after checking the winner
     * Parameters:
     *      board                   : GameBoard variable used to store the gameboard which contains the pieces
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public static void displayEnding(GameBoard board){
        
        // Clears the board and transfers all the pieces to their designated storage
        board.clearBoard();
        System.out.println(board);


        // Checks and outputs the winner of the game
        if (board.declareWinner() == 0)
            System.out.println("Its a Tie!");
        else if (board.declareWinner() == 1)
            System.out.println("Player1 wins!");
        else
            System.out.println("Player2 wins!");

        System.out.println("-------------------------");
    }

    /* Main Method
     * Local Variables used: 
     *      again                   : String variable used to store whether the user wants to play again or not
     */
    public static void main(String[] args) throws Exception {
        char again = 'a';

        // Runs the game until the users selects Y to quit
        do{
            // Initializes and displays the board
            GameBoard board = new GameBoard();
            startDisplay();

            // Alternates player moves until one of the rows are empty
            do{
                if (!board.checkRow())
                    playerMove(board, 1);

                if (!board.checkRow())
                    playerMove(board, 2);
            }
            while(!board.checkRow());

            // Displays the ending a prompts the user to see if they want to play again
            displayEnding(board);
            again = Utils.obtainYesNo("Click y to play again! ");
        }
        while (again == 'y');
    
        System.out.println("Thanks for playing!");
    }
}

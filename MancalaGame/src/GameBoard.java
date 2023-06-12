public class GameBoard {

    // 2D array that stores Block used to store 2 rows and 6 columns of blocks
    private Block [][] board = new Block[2][6];

    // Block variable used to store the number of pieces in player1's storage
    private Block storageOne = new Block();

    // Block variable used to store the number of pieces in player2's storage
    private Block storageTwo = new Block();

    /* Constructor GameBoard
     * This constructor initalizes the gameboard and sets the storage of each player
     * Parameters:
     *      none
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public GameBoard(){
        
        // Initializes the board and it's storages
        initializeBoard();
        setStorageOne(0);
        setStorageTwo(0);
    }

    /* Method initializeBoard
     * This method creates the board with the dimensions 2 by 6
     * Parameters:
     *      none
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    private void initializeBoard(){
        
        // Loops through all the indexes in the board and adds a standard Block with 4 pieces
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
                board[i][j] = new Block();
    }

    /* Method clearBoard
     * This method clears the board and all the remaining pieces are moved to its designated storage
     * Parameters:
     *      none
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public void clearBoard(){
        
        // Cycles through all the indexes in the board and all the pieces on the board is transferred to the designated storage
        // The board is then cleared
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++){
                if (i == 0)
                    storageOne.setPieces(storageOne.getPieces() + board[i][j].getPieces());
                else
                    storageTwo.setPieces(storageTwo.getPieces() + board[i][j].getPieces());

                board[i][j].setPieces(0);
            }
    }

    /* Method setStorageOne
     * This method sets the number of pieces in player1's storage
     * Parameters:
     *      pieces                  : Integer variable used to store the pieces in player1's storage  
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public void setStorageOne(int pieces){
        // Checks to see if the inputted number of pieces is not negative
        if (pieces < 0)
            throw new IllegalArgumentException("Cannot have negative pieces!");
        storageOne.setPieces(pieces);
    }

    /* Method setStorageTwo 
     * This method sets the number of pieces in player2's storage
     * Parameters:
     *      pieces                  : Integer variable used to store the pieces in player2's storage
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public void setStorageTwo(int pieces){
        // Checks to see if the inputted number of pieces is not negative
        if (pieces < 0)
            throw new IllegalArgumentException("Cannot have negative pieces!");
        storageTwo.setPieces(pieces);
    }

    /* Method getStorageOne
     * This method returns the number of pieces in player1's storage
     * Parameters:
     *      none
     * Return Values:
     *      int
     * Local Variables used:
     *      none
     */
    public int getStorageOne(){
        return storageOne.getPieces();
    }

    /* Method getStorageTwo
     * This method  returns the number of pieces in player2's storage
     * Parameters:
     *      none
     * Return Values:
     *      int
     * Local Variables used:
     *      none
     */
    public int getStorageTwo(){
        return storageTwo.getPieces();
    }

    /* Method setMove
     * This method sets the move for the current player
     * Parameters:
     *      row                     : Integer variable used to store the row selected
     *      col                     : Integer variable used to store to column selected
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public void setMove(int row, int col){
        
        // Checks to see if row and column selected lies on the board if so it distributes the pieces
        if(row < 1 || row > 2)
            throw new IllegalArgumentException("Invalid row specified");
        if (col < 1 || col > 6)
            throw new IllegalArgumentException("Invalid col specified");

        distributePieces(row - 1, col - 1);
    }

    /* Method getOpponent
     * This method finds out who which row is the opponent to the current player
     * Parameters:
     *      initialPlayer              : Integer variable used to store the current player
     * Return Values:
     *      int
     * Local Variables used:
     *      none
     */
    public int getOpponent(int initialPlayer){
        
        // Checks to see which row the initial player is and returns the opposite for the opponent
        if (initialPlayer == 0)
            return 1;
        else
            return 0;
    }

    /* Method distributePieces
     * This method makes the selected column empty and distributes the pieces counter clockwise until it runs out. If the
     * last piece lands in the storage then the player plays again. If the last pieces last on an empty
     * spot of the current player and the opponent has pieces there, then the entire column is captured 
     * and transferred to the current player's side.
     * Parameters:
     *      row                     : Integer variable used to store the row selected
     *      col                     : Integer variable used to store to column selected
     * Return Values:
     *      none
     * Local Variables used:
     *      count                   : Integer variable used to store the number of pieces of the block selected
     *      initialPlayer           : Integer variable used to store the current player making a move
     *      index                   : Integer variable used to store the index to cycle through the blocks in the row
     *      isIndexDone             : Checks if the pieces make it to the end of the board
     */
    private void distributePieces(int row, int col){
        int count = board[row][col].getPieces();
        int initialPlayer = row;
        int index = col + 1;
        boolean isIndexDone = true;

        // The pieces in the selected column are removed
        board[row][col].setPieces(0);

        if (row == 0)
            index -= 2;
        
        // loops until the number of pieces runs out
        do{

            // loops until the number of pieces runs out and the index has not reached the end
            while(count > 0 && isIndexDone){
            
                // If it is in second row (row = 1) then it will move towards the right
                if (row == 1)
                    if (index < 6){
                        board[row][index].setPieces(board[row][index].getPieces() + 1);
                        index++;
                        count--;
                    }
                    else
                        isIndexDone = false;           

                // If it is in first row (row = 0) then it will move towards the left
                else {
                    if (index >= 0){
                        board[row][index].setPieces(board[row][index].getPieces() + 1);
                        index--;
                        count--;            
                    }
                    else
                        isIndexDone = false;                
                }
            }

            // Checks if the bust mechanic is activated in the designated row
            if (count == 0 && row == 0)
                isBust(row, index + 1, initialPlayer, getOpponent(initialPlayer));
            else if (count == 0 && row == 1)
                isBust(row, index - 1, initialPlayer, getOpponent(initialPlayer)); 

            // Checks if the current player can play again and adds the last piece to the storage
            else if (count == 1 && row == initialPlayer && row == 0 && !checkRow()){
                storageOne.setPieces(storageOne.getPieces() + 1);
                playAgain(row + 1);
            } 
            else if (count == 1 && row == initialPlayer && row == 1 && !checkRow()){
                storageTwo.setPieces(storageTwo.getPieces() + 1);
                playAgain(row + 1);
            }

            // If there are more pieces left then it changes the direction going on the other row
            else if (count > 0)
                if (row == 0){
                    storageOne.setPieces(storageOne.getPieces() + 1);
                    row = 1;
                    index = 0;
                }
                else{
                    storageTwo.setPieces(storageTwo.getPieces() + 1);
                    row = 0;
                    index = 5;
                } 

            count--; 
            isIndexDone = true;                                    
            
        }
        while (count > 0);

    }

    /* Method playAgain
     * This method makes it so the user selected plays again
     * Parameters:
     *      row                     : Integer variable used to store the row of the current player
     * Return Values:
     *      none
     * Local Variables used:
     *      newCol                  : Integer variable used to store the new selected column
     */
    private void playAgain(int row){
        int newCol;
        
        // Displays the board and allows the player to play again
        System.out.println("Player" + row + " plays again!");
        System.out.println(toString());
        System.out.println("--------------------------");
        
        // Prompts the user for a column until it is valid
        do{
            newCol = Utils.inputIntegerBetween("Specify a column to move your pieces (1-6): ", 1, 6);

            if (!isEmpty(row, newCol)){
                setMove(row, newCol);
                System.out.println("--------------------------");
                break;
            }
            else
                System.out.println("Column empty! Invalid move!");
        }
        while (true);
        
    }

    /* Method checkRow
     * This method checks to see if any of the rows are completely empty
     * Parameters:
     *      none
     * Return Values:
     *      boolean
     * Local Variables used:
     *      rowOne                  : Boolean variable used to store whether row 1 is completely empty
     *      rowTwo                  : Boolean variable used to store whether row 2 is completely empty
     */
    public boolean checkRow(){
        boolean rowOne = true;
        boolean rowTwo = true;

        // Loops through all the indexes in both rows and checks if one of them has pieces
        // It will output true otherwise
        for (int i = 0; i < board[0].length; i++){
            if (!board[0][i].isEmpty())
                rowOne = false;

            if (!board[1][i].isEmpty())
                rowTwo = false;
        }

        return rowOne || rowTwo;    
    }

    /* Method isEmpty
     * This method checks to see if the block in the specific row and column is empty
     * Parameters:
     *      row                     : Integer variable used to store the row selected
     *      col                     : Integer variable used to store the column selected
     * Return Values:
     *      boolean
     * Local Variables used:
     *      none
     */
    public boolean isEmpty(int row, int col){
        return board[row - 1][col - 1].isEmpty();
    }

    /* Method isBust
     * This method checks to see if the last pieces being distributed lands on and empty spot 
     * int the current player's side. If it does and the opponent has pieces on the same column,
     * then the enitre column is captured and transferred to the current player's storage.
     * Parameters:
     *      currentRow              : Integer variable used to store the current row
     *      currentCol              : Integer variable used to store the current column
     *      initialPlayer           : Integer variable used to store the current player making a move
     *      opponent                : Integer variable used to store the opponent of the current player
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    private void isBust(int currentRow, int currentCol, int initialPlayer, int opponent){
        
        // Checks to see if the conditions for a bust is met
        // If it is it sends all the pieces to it's designated storage
        if ((board[currentRow][currentCol].getPieces() == 1) && currentRow == initialPlayer && board[opponent][currentCol].getPieces() != 0){
            if (initialPlayer == 0)
                setStorageOne(getStorageOne() + board[0][currentCol].getPieces() + board[1][currentCol].getPieces());

            else
                setStorageTwo(getStorageTwo() + board[0][currentCol].getPieces() + board[1][currentCol].getPieces());

            board[0][currentCol].setPieces(0);
            board[1][currentCol].setPieces(0);
        }
    }

    /* Method declareWinner
     * This method checks to see which player has a more pieces in their storage.
     * Parameters:
     *      none
     * Return Values:
     *      int
     * Local Variables used:
     *      none
     */
    public int declareWinner(){
        
        // If the storages are the same it will return 0
        // Otherwise it will return 1 if storage one is great and 2 if storage 2 is greater
        if (getStorageOne() == getStorageTwo())
            return 0;
        else if (getStorageOne() > getStorageTwo())
            return 1;
        else
            return 2;
    }

    /* Method toString
     * This method returns the board in proper format
     * Parameters:
     *      none
     * Return Values:
     *      String
     * Local Variables used:
     *      result                  : String variable used to store the format of the board
     */
    public String toString(){
        String result = String.format("%n %-10s%-6s%-6s%-6s%-6s%-6s%-10s%-5s%n", "P1",  "1", "2", "3", "4", "5", "6", "P2");
        
        // Adds the storage of player1 in proper format
        result += String.format("%n%-2s%-3d%-4s", "|", storageOne.getPieces(), "|");
        
        // Cycles through all the indexes in the board array and adds each block with their designated pieces in proper format
        for (int i = 0; i < board.length; i++){       
            for (int j = 0; j < board[0].length; j++){
                result += String.format("%-2s%-3s%-1s", "|", board[i][j].toString(), "|");
            }

            if(i == 0)
                result += String.format("%-10s%-3s%n%-5s%-4s", "     |", "|", "|", "|");
        }

        // Adds the storage of player2 in proper format
        result += String.format("%-7s%-3d%-3s", "     |", storageTwo.getPieces(), "|");
        return result + "\n";

    }
}

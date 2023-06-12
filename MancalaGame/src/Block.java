public class Block {

    // Integer variable used to store the number of pieces in the block
    private int pieces; 

    /* Constructor Block
     * This constructor initializes the block with 4 starting pieces
     * Parameters:
     *      none
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public Block(){
        pieces = 4;
    }

    /* Method setPieces
     * This method sets the number of pieces in the block
     * Parameters:
     *      pieces                  : Integer variable used to store the number of pieces in the block
     * Return Values:
     *      none
     * Local Variables used:
     *      none
     */
    public void setPieces(int pieces){
        // Checks if the pieces inputted is not negative and throws an error if it is
        if (pieces < 0)
            throw new IllegalArgumentException("You can't have negative pieces");
        this.pieces = pieces;
    }

    /* Method getPieces
     * This method returns the number of pieces in the block
     * Parameters:
     *      none
     * Return Values:
     *      int
     * Local Variables used:
     *      none
     */
    public int getPieces(){
        return pieces;
    }

    /* Method isEmpty
     * This method checks to see if the block is empty
     * Parameters:
     *      none
     * Return Values:
     *      boolean
     * Local Variables used:
     *      none
     */
    public boolean isEmpty(){
        return pieces == 0;
    }
    
    /* Method toString
     * This method returns the number of pieces in the block as a string 
     * Parameters:
     *      none
     * Return Values:
     *      String
     * Local Variables used:
     *      none
     */
    public String toString(){
        return "" + getPieces();
    }
}

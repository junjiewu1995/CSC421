import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameTicTacToe extends Game {
	
    char marks[] = {'O', 'X'}; //'O' for computer, 'X' for human
    
    int winningLines[][] = {
        { 1, 2, 3 },
        { 4, 5, 6 },
        { 7, 8, 9 },
        { 1, 4, 7 },
        { 2, 5, 8 },
        { 3, 6, 9 },
        { 1, 5, 9 },
        { 3, 5, 7 }
    };
    
    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;    
    
    public GameTicTacToe() { 
        /**
         * GameTicTacToe extends from the Game - @currentState
         * StateTicTacToe extends from State - @player && @board
         * currentState - @player && @board
         */
        currentState = new StateTicTacToe(); 
    }
    
    public boolean isWinState(State state)
    {
        StateTicTacToe tstate = (StateTicTacToe) state;
        
        //player who did the last move
        int previous_player = (state.player==0 ? 1 : 0);  
        char mark = marks[previous_player];
        
        for (int i = 0; i < winningLines.length; i++) {
            if (mark == tstate.board[winningLines[i][0]]
             && mark == tstate.board[winningLines[i][1]]
             && mark == tstate.board[winningLines[i][2]]) {
            	return true;
            }
        }
        return false;
    }

    /**
     * @check whether the state's full condition 
     */
    
    public boolean isStuckState(State state) {
    	
        if (isWinState(state)) 
            return false;
        
        StateTicTacToe tstate = (StateTicTacToe) state;
        
        for (int i=1; i<=9; i++) 
            if ( tstate.board[i] == ' ' ) 
                return false;
        return true;
    }
	
	
	public Set<State> getSuccessors(State state) {

		if(isWinState(state) || isStuckState(state)) return null;
		
		Set<State> successors = new HashSet<State>();
        StateTicTacToe tstate = (StateTicTacToe) state;
        StateTicTacToe successor_state;
        
        char mark = 'O';
        if (tstate.player == 1) mark = 'X';
        
        /**
         * Brute Force checking every possible postion for computer
         */
        for (int i = 1; i <= 9; i++) {
            if (tstate.board[i] == ' ') {
                successor_state = new StateTicTacToe(tstate);
                successor_state.board[i] = mark;
                successor_state.player = (state.player==0 ? 1 : 0); 
                successors.add(successor_state);
            }
        } 
        return successors;
    }	
    
    /**
     * Tree meets the leafs Calls eval()
     * @return Score
     */
    public double eval(State state) 
    {   
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player==0 ? 1 : 0);
	    	if (previous_player==0) //computer wins
	            return WinningScore;
	    	else //human wins
	            return LosingScore;
    	}

        return NeutralScore;
    }
    
    
    public static void main(String[] args) throws Exception {

        System.out.println("Tik Tok Checking .....");

        /**
         * 初始化TicTac状态 
         * player set to be 1 && board array ''
         */

        Game game = new GameTicTacToe();  

        /**
         * 初始化Search Algorithm
         * @input game
         * this.game equals game
         */
        Search search = new Search(game); 

        int depth = 8;  //tree depth
        
        /*
         * @input
         * needed to get human's move
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
        	StateTicTacToe 	nextState = null;
            switch ( game.currentState.player ) {

              case 1:
            	  //get human's move
                  System.out.print("Enter your *valid* move> ");
                  int pos = Integer.parseInt( in.readLine() );
                  
                  /**
                   * set up the player's position
                   */
                  nextState = new StateTicTacToe((StateTicTacToe)game.currentState);
                  nextState.player = 1;
                  nextState.board[pos] = 'X';
                  System.out.println("Human: \n" + nextState);
                  break;
                  
              case 0: //Computer

                  /**
                   * Computer calls Search() 
                   */
            	  nextState = (StateTicTacToe)search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.println("Computer: \n" + nextState);
                  break;
            }
                        
            game.currentState = nextState;
            game.currentState.player = (game.currentState.player==0 ? 1 : 0); //Change player
            
            // Who wins?
            if ( game.isWinState(game.currentState) ) {
                // i.e. last move was by the computer
            	if (game.currentState.player == 1) 
            		System.out.println("Computer wins!");
            	else
            		System.out.println("You win!");
            	break;
            }
            
            if (game.isStuckState(game.currentState) ) { 
            	System.out.println("Cat's game!");
            	break;
            }
        }
    }
}
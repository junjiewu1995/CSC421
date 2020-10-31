import java.util.Set;

public class Search {

	Game game; // current state, isWin(), eval() ...
	
	public Search(Game game) { this.game = game; }
	
	public State bestSuccessorState(int depth) {
		
		double max = Double.NEGATIVE_INFINITY;
		State max_state = null;
		
		/*
		 * call the game.getSuccessors ()
		 * retrived leafs
		 * 
		*/ 
		for(State state : game.getSuccessors(game.currentState)) {
			/**
			 * give every's state's MinValue and Search for the Max state
			 */
			double min_val = MinValue(state,depth);
			if(max < min_val) {
				max = min_val;
				max_state = state;
			}
		}
		return max_state;
	}
	
	/**
	 * MinMaxTree, Find a max value of the following frontier's min value
	 */
	double MaxValue(State state, int depth) {
		Set<State> successors = game.getSuccessors(state);
		if (depth==0 || successors==null) return game.eval(state);
		
		double v = Double.NEGATIVE_INFINITY;
		for(State s : game.getSuccessors(state)) 
			v = Math.max(v, MinValue(s,depth-1));
		return v;
	}
	
	/**
	 * currentState, depth equals to 8 - there are 8 position for each level of tree
	 */
	double MinValue(State state, int depth) {

		Set<State> successors = game.getSuccessors(state);
		
		if (depth==0 || successors==null)
			return game.eval(state);

		double v = Double.POSITIVE_INFINITY;

		/**
		 * For folowing possible states, find a minvalue of the following node's maximum value
		 */
		for(State s : game.getSuccessors(state)) 
			v = Math.min(v, MaxValue(s,depth-1));
		return v;
	}	
}

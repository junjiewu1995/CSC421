import java.util.HashSet;
import java.util.Set;

public class ProblemCannibals extends Problem {

    static final int cannL = 0;
    static final int cannR = 3;
    static final int missL = 1;
    static final int missR = 4;
    static final int boatL = 2;
    static final int boatR = 5;
    
    
	boolean goal_test(Object state) {
        StateCannibals s = (StateCannibals) state;
        if (s.array[cannR]==3 && s.array[missR]==3 && s.array[boatR]==1) return true;
        else return false;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateCannibals s= (StateCannibals) state;
        StateCannibals successor_state;
        
        // 1 cannibal only from left to right
        successor_state = new StateCannibals(s);
        successor_state.array[cannL] -= 1;
        successor_state.array[cannR] += 1;
        successor_state.array[boatL] -= 1;
        successor_state.array[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        // 1 cannibal only from right to left
        successor_state = new StateCannibals(s);
        successor_state.array[cannL] += 1;
        successor_state.array[cannR] -= 1;
        successor_state.array[boatL] += 1;
        successor_state.array[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);        
        
        // 2 cannibals from left to right
        successor_state = new StateCannibals(s);
        successor_state.array[cannL] -= 2;
        successor_state.array[cannR] += 2;
        successor_state.array[boatL] -= 1;
        successor_state.array[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        // 2 cannibals from right to left
        successor_state = new StateCannibals(s);
        successor_state.array[cannL] += 2;
        successor_state.array[cannR] -= 2;
        successor_state.array[boatL] += 1;
        successor_state.array[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        // 1 missionary only from left to right
        successor_state = new StateCannibals(s);
        successor_state.array[missL] -= 1;
        successor_state.array[missR] += 1;
        successor_state.array[boatL] -= 1;
        successor_state.array[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        // 1 missionary only from right to left
        successor_state = new StateCannibals(s);
        successor_state.array[missL] += 1;
        successor_state.array[missR] -= 1;
        successor_state.array[boatL] += 1;
        successor_state.array[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        // 2 missionaries from left to right
        successor_state = new StateCannibals(s);
        successor_state.array[missL] -= 2;
        successor_state.array[missR] += 2;
        successor_state.array[boatL] -= 1;
        successor_state.array[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        // 2 missionaries from right to left
        successor_state = new StateCannibals(s);
        successor_state.array[missL] += 2;
        successor_state.array[missR] -= 2;
        successor_state.array[boatL] += 1;
        successor_state.array[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        // 1 cannibal and 1 missionary from left to right
        successor_state = new StateCannibals(s);
        successor_state.array[cannL] -= 1;
        successor_state.array[cannR] += 1;
        successor_state.array[missL] -= 1;
        successor_state.array[missR] += 1;
        successor_state.array[boatL] -= 1;
        successor_state.array[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        // 1 cannibal and 1 missionary from right to left
        successor_state = new StateCannibals(s);
        successor_state.array[cannL] += 1;
        successor_state.array[cannR] -= 1;
        successor_state.array[missL] += 1;
        successor_state.array[missR] -= 1;
        successor_state.array[boatL] += 1;
        successor_state.array[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        return set;
    }
    
    private boolean isValid(StateCannibals state)
    {   
        // Checking to see if any element of the array is negative 
        for (int i=0; i<6; i++)
            if (state.array[i] < 0) return false;
        
        // Checking to see if the numbers of cannibals, missionaries, and boat are more then 3,3,1 respectively
        if (state.array[cannL] > 3) return false;
        if (state.array[cannR] > 3) return false;
        if (state.array[missL] > 3) return false;
        if (state.array[missR] > 3) return false;
        if (state.array[boatL] > 1) return false;
        if (state.array[boatR] > 1) return false;
        
        // Checking if cannibals out number missionaries
        if (state.array[missL] > 0 && state.array[cannL] > state.array[missL]) return false;
        if (state.array[missR] > 0 && state.array[cannR] > state.array[missR]) return false;
        
        return true;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { 
        StateCannibals c = (StateCannibals) state;
        //To come up with a heuristic we solve a relaxed problem. 
        //If we remove the constraint that cannibals eat missionaries, 
        //we can compute how many trips are needed to take everyone across. 
        //The boat takes two people, but after each trip across the boat 
        //has to come back to the left side and so at least one person must ride the boat. 
        //After some reasoning, the number of trips needed ends up to be 
        //the number of people on the left times 2 minus 3.
        //Why? Let the number of people on the left be n. 
        //For the first n-2 people, we need 2 trips, i.e. 2(n-2) 
        //For the last 2 people, we need only 1 trip. 
        //Total is 2(n-2)+1 = 2n-3
        return 2*(c.array[0]+c.array[1])-3;
	}


	public static void main(String[] args) throws Exception {

		ProblemCannibals problem = new ProblemCannibals();
        int[] canArray = {3,3,1,0,0,0};
		problem.initialState = new StateCannibals(canArray); 
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
}

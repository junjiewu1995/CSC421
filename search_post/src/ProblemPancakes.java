import java.util.HashSet;
import java.util.Set;

public class ProblemPancakes extends Problem {

  final static int n = 10;

  boolean goal_test(Object state){
    StatePancakes pstate = (StatePancakes) state;
    boolean result = true;
    for (int i = 0; i < pstate.n; i++){
      result &= (i==pstate.pancakesArray[i]);
    }
    return result;
  }

  Set<Object> getSuccessors(Object state) {

    Set<Object> set = new HashSet<Object>();
    StatePancakes s = (StatePancakes) state;
    StatePancakes successor_state;

    for(int i = 1; i < s.n; i++){
      successor_state = new StatePancakes(s);
      int [] t = flipPancakes(0, i, s.pancakesArray);
      for (int k = 0; k < t.length; k++) { 
          successor_state.pancakesArray[k] = t[k];
      } 
      set.add(successor_state);
    }
    return set;
  }

  int [] flipPancakes(int l, int r,int pancakesArray[]) { 

      int [] pancakesArrayCopy = pancakesArray.clone(); 
      int d = (r-l+1)/2;
      for(int i = 0; i < d; i++) {
         int t = pancakesArrayCopy[l+i];
         pancakesArrayCopy[l+i] = pancakesArrayCopy[r-i];
         pancakesArrayCopy[r-i] = t;
      }      
      return pancakesArrayCopy;
  } 

  double step_cost(Object fromState, Object toState) { return 1; }

  public double h(Object state) { 
    StatePancakes s = (StatePancakes) state;
    int sum = 0;
    for (int i = 1; i < s.n; i++){
      sum += Math.abs(s.pancakesArray[i] - s.pancakesArray[i-1]);
    }
    return sum; 
  }

  public static void main(String [] args) throws Exception{
    
    System.out.println("Start Testing ...");

    ProblemPancakes problem = new ProblemPancakes();
    int[] array = {0,1,3,5,7,8,2,4,6,9};

    problem.initialState = new StatePancakes(array);

    Search search  = new Search(problem);

		System.out.println("TreeSearch------------------------");
		//System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		// System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		// System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		// System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		// System.out.println("\n\nGraphSearch----------------------");
		// System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		// System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		// System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		// System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		// System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		// System.out.println("\n\nIterativeDeepening----------------------");
		//System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		//System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
  }

}
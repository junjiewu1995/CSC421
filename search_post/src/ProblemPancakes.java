import java.util.HashSet;
import java.util.Set;

public class ProblemPancakes extends Problem {

  final static int n = 6;

  boolean goal_test(Object state){
    StatePancakes pstate = (StatePancakes) state;
    boolean result = false;
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
      successor_state = flipPancakes(i, s.array);
      set.add(successor_state);
    }

    return set;
  }

  int[] flipPancakes(int n, int[] pancakes) {
    int[] pancakesCopy = pancakes.clone();
    int length = pancakesCopy.length;
    for(int i = n; i < (length + n) / 2; i++) {
      int temp = pancakesCopy[i];
      pancakesCopy[i] = pancakesCopy[length - i + n - 1];
      pancakesCopy[length - i + n - 1] = temp;
    }
    return pancakesCopy;
  }

  double step_cost(Object fromState, Object toState) { return 1; }

  public double h(Object state) { return 0; }

  public static void main(String [] args) throws Exception{
    
    System.out.println("Start Testing ...");

    ProblemPancakes problem = new ProblemPancakes();
    int[] array = {1, 0, 3, 5, 2, 4};

    problem.initialState = new StatePancakes(array);

    Search search  = new Search(problem);

		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		// System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		// System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		// System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		// System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
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
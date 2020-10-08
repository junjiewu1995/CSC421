import java.util.HashSet;
import java.util.Set;

public class ProblemFarmer extends Problem {

  static final int n = 4;
  
  static final int Bank = 0;
  static final int Wolf = 1;
  static final int Goat = 2;
  static final int Cabbage = 3;

  boolean goal_test(Object state) {
    StateFarmer fstate = (StateFarmer) state;
    if (fstate.array[Bank] == 1 && fstate.array[Wolf] == 1 && fstate.array[Goat] == 1 && fstate.array[Cabbage] == 1 ) { return true; } 
    else { return false; }
  }

  Set<Object> getSuccessors(Object state) {
  
    Set<Object> set = new HashSet<Object>();
    StateFarmer s = (StateFarmer) state;
    StateFarmer successor_state;

    if(!isValid(s)) {return set;}
    else {

      if(s.array[Bank] == 1){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 0;
        set.add(successor_state);
      }

      if(s.array[Bank] == 0){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 1;
        set.add(successor_state);
      }

      if (s.array[Bank] == 0 && s.array[Goat] == 0){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 1;
        successor_state.array[Goat] = 1;
        set.add(successor_state);
      }

      if (s.array[Bank] == 0 && s.array[Cabbage] == 0){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 1;
        successor_state.array[Cabbage] = 1;
        set.add(successor_state);
      }

      if (s.array[Bank] == 1 && s.array[Wolf] == 1){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 0;
        successor_state.array[Wolf] = 0;
        set.add(successor_state);
      }

      if (s.array[Bank] == 0 && s.array[Wolf] == 0){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 1;
        successor_state.array[Wolf] = 1;
        set.add(successor_state);
      }

      if (s.array[Bank] == 1 && s.array[Goat] == 1){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 0;
        successor_state.array[Goat] = 0;
        set.add(successor_state);
      }


      if (s.array[Bank] == 1 && s.array[Cabbage] == 1){
        successor_state = new StateFarmer(s);
        successor_state.array[Bank] = 0;
        successor_state.array[Cabbage] = 0;
        set.add(successor_state);
      }
    }
    return set;
  }

  public boolean isValid(StateFarmer state) {
      if (state.array[Bank] == 0 && state.array[Wolf] == 1 && state.array[Goat] == 1){return false;} 
      if (state.array[Bank] == 1 && state.array[Wolf] == 0 && state.array[Goat] == 0){return false;}
      if (state.array[Bank] == 0 && state.array[Goat] == 1 && state.array[Cabbage] == 1){return false;}
      if (state.array[Bank] == 1 && state.array[Goat] == 0 && state.array[Cabbage] == 0){return false;}
      return true;
  }

  double step_cost(Object fromState, Object toState) { return 1; }

  public double h(Object state) { return 0; }

  public static void main(String [] args) throws Exception{
    
    System.out.println("Start Testing ...");

    ProblemFarmer problem = new ProblemFarmer();
    int[] array = new int[n];
    problem.initialState = new StateFarmer(array);

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
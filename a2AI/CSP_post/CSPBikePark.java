import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSPBikePark extends CSP {

  /**
   * Setting Up varCol 
   */
  static Set<Object> varCol = new HashSet<Object>(Arrays.asList(new String[] {"blue", "green", "ivory", "red", "yellow"}));
 
  /**
   * Setting Up constrains
  */
  public boolean isGood (Object X, Object Y, Object x, Object y) {
    
    //if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
    return true;
  
    //check to see if there is an arc between X and Y
    //if there isn't an arc, then no constraint, i.e. it is good
    if(!C.get(X).contains(Y))
      return true;
    
    //not equal constraint
    if(!x.equals(y)) 
      return true;
  
    return false;
  }
  
  /**
   * Main function 
   */
  public static void main() {
    CSPBikePark csp = new CSPBikePark();
    /**
     * Fill Up the Part 
     */
    Search search = new Search(csp);
    System.out.println(search.BacktrackingSearch());
  }
}
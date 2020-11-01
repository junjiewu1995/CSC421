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
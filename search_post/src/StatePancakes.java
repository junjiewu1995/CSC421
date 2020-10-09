public class StatePancakes {

  static final int n = 6;
  int pancakesArray[];
  public StatePancakes(int[] array) { this.pancakesArray = array; }

  public StatePancakes(StatePancakes state){
    pancakesArray = new int[n];
    for (int i = 0; i < n; i ++) {
      this.pancakesArray[i] = state.pancakesArray[i];
    }
  }

  public int hashcode (){
    return this.pancakesArray[0]*100000 + this.pancakesArray[1]*010000 + this.pancakesArray[2]*001000 + this.pancakesArray[3]*000100 + this.pancakesArray[4]*000010 + this.pancakesArray[5]*000001;
  }

  public String toString(){
      String str = "";
      for (int i = 0; i < n; i++) {
        str += pancakesArray[i] + " ";
      }
      return str;
  }
}
public class StateFarmer { 
  
  static final int n = 4;
  int array[];
  public StateFarmer(int[] array) { this.array = array; }

  public StateFarmer(StateFarmer state){
    array = new int[n];
    for (int i = 0; i < n; i ++){
      this.array[i] = state.array[i];
    }
  }

  public int hashcode (){
    return this.array[0]*1000 + this.array[1]*0100 + this.array[2]*0010 + this.array[3]*0001;
  }

  public String toString(){
      String str = "";
      for (int i = 0; i < n; i++)
      {
        str += array[i] + " ";
      }
      return str;
  }

}
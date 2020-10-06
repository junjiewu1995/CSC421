public class StatePancake {

  static final int n = 6;
  int array[];
  public StatePancake(int[] array) { this.array = array; }

  public StatePancake(StatePancake state){
    array = new int[n];
    for (int i = 0; i < n; i ++) {
      this.array[i] = state.array[i];
    }
  }

  public int hashcode (){
    return this.array[0]*100000 + this.array[1]*010000 + this.array[2]*001000 + this.array[3]*000100 + this.array[4]*000010 + this.array[5]*000001;
  }

  public String toString(){
      String str = "";
      for (int i = 0; i < n; i++) {
        str += array[i] + " ";
      }
      return str;
  }
}
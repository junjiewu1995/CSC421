public class StateCannibals 
{    
    int array[];
    public StateCannibals(int[] array) { this.array = array; }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StateCannibals(StateCannibals state) {
    	array = new int[6];
        for(int i=0; i<6; i++) 
            this.array[i] = state.array[i];
    }
    
    public boolean equals(Object o)
    {
        StateCannibals state = (StateCannibals) o;
        for (int i=0; i<6; i++)
            if (this.array[i] != state.array[i])
                return false;
        return true;
    }
    
    public int hashCode() {
        return array[0]*100000 + array[1]*10000 + array[2]*1000 + array[3]*100 + array[4]*10 + array[5];
    }    
    
    public String toString()
    {
        String ret = "";
        for (int i=0; i<6; i++)
            ret += " " + this.array[i];
        return ret;
    }
}
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSPBikePark extends CSP {
	
	static Set<Object> varBike = new HashSet<Object>(Arrays.asList(new String[] {"black", "blue", "green", "red", "white"}));
	static Set<Object> varName = new HashSet<Object>(Arrays.asList(new String[] {"adrian", "charles", "henry", "joel", "richard"}));
  static Set<Object> varSandwich = new HashSet<Object>(Arrays.asList(new String[] {"bacon", "chicken", "cheese", "pepperoni", "tuna"}));
  static Set<Object> varJuice = new HashSet<Object>(Arrays.asList(new String[] {"apple", "cranberry", "grapefruit", "orange", "pineapple"}));
	static Set<Object> varAge = new HashSet<Object>(Arrays.asList(new String[] {"12", "13", "14", "15", "16"}));
	static Set<Object> varSport = new HashSet<Object>(Arrays.asList(new String[] {"baseball", "basketball", "hockey", "soccer", "swimming"}));
	
	public boolean isGood(Object X, Object Y, Object x, Object y) {
		//if X is not even mentioned in by the constraints, just return true
		if(!C.containsKey(X))
      return true;
      
    if(!C.get(X).contains(Y))
      return true;

    //white is between 15 and youngest
    if(X.equals("white") && Y.equals("15") && (Integer)x-(Integer)y!=1)
			return false;

		return true;
	}
		
	public static void main(String[] args) throws Exception {
	CSPBikePark csp = new CSPBikePark();
		
		Integer[] dom = {1,2,3,4,5};
		
		for(Object X : varBike) 
			csp.addDomain(X, dom);
		
		for(Object X : varName) 
			csp.addDomain(X, dom);
		
		for(Object X : varSandwich) 
			csp.addDomain(X, dom);
		
		for(Object X : varJuice) 
			csp.addDomain(X, dom);
		
		for(Object X : varAge) 
      csp.addDomain(X, dom);
      
    for(Object X : varSport) 
			csp.addDomain(X, dom);
		
		
		//unary constraints: just remove values from domains
		
		//middle likes baseball.
		for(int i=1; i<=5; i++)
			if(i != 3)
        csp.D.get("baseball").remove(i);
        
    //fourth likes pineapple
		for(int i=1; i<=5; i++)
    if(i != 4)
      csp.D.get("pineapple").remove(i);
				
    //The cyclist who is going to eat Tuna sandwich is at one of the ends.
		for(int i=1; i<=5; i++)
			if(i != 1 || i != 5)
        csp.D.get("tuna").remove(i);
        
    //green bikes at one of the ends
		for(int i=1; i<=5; i++)
      if(i != 1 || i != 5)
        csp.D.get("green").remove(i);
		
		//black bike is third
		for(int i=1; i<=5; i++)
      if(i != 3)
        csp.D.get("black").remove(i);
        
    //fifth position is 13    
    for(int i=1; i<=5; i++)
      if(i != 5)
        csp.D.get("13").remove(i);
          
    //fifth position is hockey    
    for(int i=1; i<=5; i++)
      if(i != 5)
        csp.D.get("hockey").remove(i);

		//binary constraints: add constraint arcs
		
		//hockey eats pepperoni
    csp.addBidirectionalArc("hockey", "pepperoni");		
    
    //16 year old has cheese
    csp.addBidirectionalArc("16", "cheese");		
		
    //swimming is next to baseball
    csp.addBidirectionalArc("swimming", "baseball");
    
    //joel is next to 16
    csp.addBidirectionalArc("joel", "16");	

    //adrian left to pepperoni
    csp.addBidirectionalArc("adrian", "pepperoni");	
    
    //henry left of soccer
    csp.addBidirectionalArc("henry", "soccer");	

    //baseball next to apple
    csp.addBidirectionalArc("baseball", "apple");	

    //bacon right of white
    csp.addBidirectionalArc("bacon", "white");
    
    //owner of white between 15 and 12
    csp.addBidirectionalArc("white", "15");
    csp.addBidirectionalArc("white", "12");

    //grapefruit between tuna and pineapple
    csp.addBidirectionalArc("grapefruit", "tuna");
    csp.addBidirectionalArc("grapefruit", "pineapple");

    //pineapple between 14 and orange juice
    csp.addBidirectionalArc("pineapple", "14");
    csp.addBidirectionalArc("pineapple", "orange");

    //white between blue and black
    csp.addBidirectionalArc("white", "blue");
    csp.addBidirectionalArc("white", "black");

    //12 between 14 and 16
    csp.addBidirectionalArc("12", "14");
    csp.addBidirectionalArc("12", "16");

    //white between richard and red
    csp.addBidirectionalArc("white", "richard");
    csp.addBidirectionalArc("white", "red");

    //charles between richard and adrian
    csp.addBidirectionalArc("charles", "richard");
    csp.addBidirectionalArc("charles", "adrian");

		//Uniqueness constraints
		
	
        
    for(Object X : varBike) 
      for(Object Y : varBike)
        csp.addBidirectionalArc(X,Y);
		
    for(Object X : varName) 
      for(Object Y : varName)
          csp.addBidirectionalArc(X,Y);
		
    for(Object X : varSandwich) 
      for(Object Y : varSandwich)
        csp.addBidirectionalArc(X,Y);
		
    for(Object X : varJuice) 
      for(Object Y : varJuice)
        csp.addBidirectionalArc(X,Y);
		
    for(Object X : varAge) 
      for(Object Y : varAge)
        csp.addBidirectionalArc(X,Y);
      
    for(Object X : varSport) 
      for(Object Y : varSport)
        csp.addBidirectionalArc(X,Y);
		


		//Now let's search for solution 
		
		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}
package aoc2;

public class AlgoritmosTeste {
    protected Memoria[] vetor, RAM;
    protected Cache[] CACHE;
    protected Menu menu;
    public int hit;
    public int miss;
    
    protected void hitmissCount(int value) {
    	boolean dataIn = menu.esta_na_cache(vetor, CACHE, value);
        if(dataIn == false) miss++;
        else hit++;
    }
	
	public void printVetor() {
    	for(int i = 0; i < vetor.length; i++) {
    		System.out.println(vetor[i].getDado());
    	}
    }
}

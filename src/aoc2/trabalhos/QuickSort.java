/*
*   Algoritmo adaptado do site
*   https://www.geeksforgeeks.org/quick-sort/
*/

package aoc2.trabalhos;

public class QuickSort {
    private Memoria[] RAM;
    private Cache[] CACHE;
    private Menu menu;
    public int hit;
    public int miss;
   
    public QuickSort(Memoria[] RAM, Cache[] CACHE, Menu menu){
        this.RAM = RAM;
        this.CACHE = CACHE;
        this.menu = menu;
    }

    private int partition(int low, int high) { 
    	// Procurar na cache toda vez que o vetor for utilizado
        // Verificar se houve hit ou miss
    	boolean dataIn = menu.inCache(RAM, CACHE, high);
        if(dataIn == false) miss++;
        else hit++;
        //
        int pivot = RAM[high].getDado();
        
        int i = (low-1);
        for (int j = low; j < high; j++) { 
        	//
        	dataIn = menu.inCache(RAM, CACHE, j);
            if(dataIn == false) miss++;
            else hit++;
            //
        	if (RAM[j].getDado() <= pivot) { 
                i++; 
                //
                dataIn = menu.inCache(RAM, CACHE, i);
                if(dataIn == false) miss++;
                else hit++;
                //
                
                int temp = RAM[i].getDado();
                
                //Substituir na cache e RAM
                RAM[i].setDado(RAM[j].getDado(), 0);
                RAM[j].setDado(temp, 0); 
            } 
        } 
  
        int temp = RAM[i+1].getDado();
        //
        dataIn = menu.inCache(RAM, CACHE, high);
        if(dataIn == false) miss++;
        else hit++;
        //
        RAM[i+1].setDado(RAM[high].getDado(), 0); 
        RAM[high].setDado(temp, 0); 
  
        return i+1; 
    }
    
    public void Sort(int low, int high) {
        if (low < high) { 
            int pi = partition(low, high); 
            Sort(low, pi-1); 
            Sort(pi+1, high); 
        } 
    } 
    
    public void printVetor() {
    	System.out.println("Vetor: " + RAM.length);
    	for(int i = 0; i < RAM.length; i++) {
    		System.out.println(RAM[i].getDado());
    	}
    }
}
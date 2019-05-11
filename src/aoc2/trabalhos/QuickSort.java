/*
*   Algoritmo adaptado do site
*   https://www.geeksforgeeks.org/quick-sort/
*/

package aoc2.trabalhos;

public class QuickSort {
    private Memoria[] RAM;
    private Cache[] CACHE;
    private Menu menu;
    private int[] vetor;
   
    public QuickSort(Memoria[] RAM, Cache[] CACHE, Menu menu){
        this.RAM = RAM;
        this.CACHE = CACHE;
        this.menu = menu;
        vetor = new int[RAM.length];
        for(int i = 0; i < RAM.length; i++){
            vetor[i] = RAM[i].getDado();
        }
    }
    
    public static void main1(String[] args){

        int quantidade = 10000;
        int[] vetor = new int[quantidade];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = (int) (Math.random()*quantidade);
        }

        long tempoInicial = System.currentTimeMillis();

//        quickSort(vetor,0,vetor.length-1);

        long tempoFinal = System.currentTimeMillis();

        System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");

    }
        
    private int partition(int low, int high) { 
    	// Verificar se o pivot está na cache
        int pivot = vetor[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) { 
            // If current element is smaller than or 
            // equal to pivot 
            if (vetor[j] <= pivot) { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = vetor[i]; 
                vetor[i] = vetor[j]; 
                vetor[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = vetor[i+1]; 
        vetor[i+1] = vetor[high]; 
        vetor[high] = temp; 
  
        return i+1; 
    }
    
    public void Sort(int low, int high) {
    	/*
         * The main function that implements QuickSort()
         * arr[] --> Array to be sorted,
         * low  --> Starting index,
         * high  --> Ending index  
         */
        if (low < high) { 
            /*
             * pi is partitioning index, arr[pi] is
             * now at right place
             */
            int pi = partition(low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            Sort(low, pi-1); 
            Sort(pi+1, high); 
        } 
    } 
    
    public void printVetor() {
    	System.out.println("Vetor: " + vetor.length);
    	for(int i = 0; i < vetor.length; i++) {
    		System.out.println(vetor[i]);
    	}
    }
}
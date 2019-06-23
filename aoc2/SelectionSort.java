/*
*   Algoritmo adaptado do site
*   https://www.devmedia.com.br/algoritmos-de-ordenacao-em-java/32693
*/

package aoc2;

public class SelectionSort extends AlgoritmosTeste{
     public SelectionSort(Memoria[] RAM, Cache[] CACHE, Menu menu){
        this.vetor = RAM.clone();
        this.CACHE = CACHE;
        this.menu = menu;
    }
    
    public void selectionSort() {
    	for (int fixo = 0; fixo < vetor.length - 1; fixo++) {
		    int menor = fixo;
		   
		    for (int i = menor + 1; i < vetor.length; i++) {
		    	hitmissCount(i);
		    	hitmissCount(menor);
		    	if (vetor[i].getDado() < vetor[menor].getDado()) {
		    		menor = i;
		    	}
		    }
		    if (menor != fixo) {
		    	int t = vetor[fixo].getDado();
		    	hitmissCount(fixo);
		    	
		    	vetor[fixo].setDado(vetor[menor].getDado(), 0);
		    	hitmissCount(menor);
		    	
		    	vetor[menor].setDado(t, 0);
		    }
    	}
    }
}
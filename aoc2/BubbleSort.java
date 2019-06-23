/*
*   Algoritmo adaptado do site
*   https://www.devmedia.com.br/algoritmos-de-ordenacao-em-java/32693
*/

package aoc2;

public class BubbleSort extends AlgoritmosTeste{
   
    public BubbleSort(Memoria[] RAM, Cache[] CACHE, Menu menu){
        this.vetor = RAM.clone();
        this.CACHE = CACHE;
        this.menu = menu;
    }

    public void bubbleSort(){
        boolean troca = true;
        int aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < vetor.length - 1; i++) {
            	hitmissCount(i);
            	hitmissCount(i + 1);
                if (vetor[i].getDado() > vetor[i + 1].getDado()) {
                    aux = vetor[i].getDado();
                    vetor[i].setDado(vetor[i + 1].getDado(), 0);
                    vetor[i + 1].setDado(aux, 0);
                    troca = true;
                }
            }
        }
    }
}
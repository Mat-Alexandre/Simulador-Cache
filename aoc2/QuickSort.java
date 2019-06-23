/*
*   Algoritmo adaptado do site
*   https://www.devmedia.com.br/algoritmos-de-ordenacao-em-java/32693
*/

package aoc2;

public class QuickSort extends AlgoritmosTeste{
   
    public QuickSort(Memoria[] RAM, Cache[] CACHE, Menu menu){
        this.vetor = RAM.clone();
        this.RAM = RAM;
        this.CACHE = CACHE;
        this.menu = menu;
    }

    public void quickSort(int inicio, int fim) {
        if (inicio < fim) {
           int posicaoPivo = separar(inicio, fim);
           quickSort(inicio, posicaoPivo - 1);
           quickSort(posicaoPivo + 1, fim);
        }
    }
    
    private int separar(int inicio, int fim) {
    	// Verificar hit ou miss toda vez que alguma variável receber o valor do vetor
    	// Toda vez que um dado do vetor for alterado, aplicar política de escrita
    	int dado;
    	int pivo = vetor[inicio].getDado();
    	hitmissCount(inicio);
        int i = inicio + 1, f = fim;
        
        while (i <= f) {
            if (vetor[i].getDado() <= pivo) {
                hitmissCount(i);
                i++;
            }else if (pivo < vetor[f].getDado()) {
                hitmissCount(f);
                f--;
            }else {
                int troca = vetor[i].getDado();
                hitmissCount(i);
                dado = vetor[f].getDado();
                vetor[i].setDado(dado, 0);
                //menu.mudar_dado(vetor, CACHE, i, dado);
                
                hitmissCount(f);
                vetor[f].setDado(troca, 0);
                //menu.mudar_dado(vetor, CACHE, f, troca);

                i++;
                f--;
            }
        }
        vetor[inicio].setDado(vetor[f].getDado(), 0);
        dado = vetor[f].getDado();
        //menu.mudar_dado(vetor, CACHE, inicio, dado);

        hitmissCount(f);
        
        vetor[f].setDado(pivo, 0);
        
        return f;
    }
}
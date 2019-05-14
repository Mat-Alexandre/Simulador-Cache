package aoc2.trabalhos;

import java.util.Random;

public class Principal {

    public static void main(String args[]) {
        Random rng          = new Random();
        int capacidade      = 16;// * 8 * 1024;

        Menu menu           = new Menu();

        int capacidadeCahce = menu.cache.getPalavras();
        Memoria[] RAM       = new Memoria[capacidade];
        Cache[] CACHE       = new Cache[capacidadeCahce];

        // Inicialização das memórias
        menu.startMems(RAM, CACHE, capacidade, rng);

        // Aplicação do algoritmo
        for(int i = 0; i < 6; i++) {
        	menu.toCache(RAM, CACHE, i);	
        }
        // Imprimindo a cache
        menu.showCache(CACHE);
        
        // Campos da cache
        menu.cacheBits();

//        QuickSort q = new QuickSort(RAM, CACHE, menu);
//        q.Sort(0, RAM.length-1);
//        
//        menu.showCache(CACHE);
//        System.out.println("Total: " + (q.hit + q.miss) + "\nHit: "+q.hit+"\nMiss: "+q.miss);
    }
}
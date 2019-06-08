package aoc2.trabalhos;

import java.util.Random;

public class Principal {

    public static void main(String args[]) {
    	/*
    	 * Classe principal para execuÁ„o do simulador.
    	 * 
    	 * As memÛrias Cache e RAM s„o arrays de suas respectivas classes.
    	 * A capacidade da RAM È predeterminada pelo programa, enquanto a da Cache pode ser escolhida pelo usu·rio.
    	 * 
    	 */
        Random rng          = new Random();
        int capacidade      = 16;// * 8 * 1024;

        Menu menu           = new Menu();

        int capacidadeCahce = menu.cache.getPalavras();
        Memoria[] RAM       = new Memoria[capacidade];
        Cache[] CACHE       = new Cache[capacidadeCahce];

        // Inicializa√ß√£o das mem√≥rias
        menu.startMems(RAM, CACHE, capacidade, rng);

        // Aplica√ß√£o do algoritmo
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
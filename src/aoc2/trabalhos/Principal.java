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

        // Atribuindo os valores da RAM à CACHE
        // Método deve ser chamado pelo algorítmo a ser testado
        QuickSort q = new QuickSort(RAM, CACHE, menu);
        q.Sort(0, RAM.length-1);
        
        // Imprimindo a cache
        // menu.showCache(CACHE);

        // Campos da cache
//        menu.cacheBits();
    }
}
package aoc2;

import java.util.Random;
import java.util.Scanner;

public class Principal {

    public static void main1(String args[]) {
    	/*
    	 * Classe principal para execução do simulador.
    	 * 
    	 * As memórias Cache e RAM são arrays de suas respectivas classes.
    	 * A capacidade da RAM é predeterminada pelo programa, enquanto a da Cache pode ser escolhida pelo usuário.
    	 * 
    	 */
        Random rng          = new Random();
        int capacidadeRam   = 32;// * 8 * 1024;

        Menu menu           = new Menu();

        int capacidadeCahce = menu.cache.get_tam_cache();
        Memoria[] RAM       = new Memoria[capacidadeRam];
        Cache[] CACHE       = new Cache[capacidadeCahce];
        
        
        // Inicializando a memória
        menu.inicia_memoria(RAM, CACHE, capacidadeRam, rng);
        
//        String resultado = null;
        Scanner sc          = new Scanner(System.in);
        
        for(int i = 0; i < 17; i++){
            menu.para_a_cache(RAM, CACHE, i);
        }
        
        menu.print_cache(CACHE);
        System.out.println(menu.cacheBits());

//        switch(menu.alg){
//            case 1:{
//                QuickSort alg = new QuickSort(RAM, CACHE, menu);
//                alg.quickSort(0, RAM.length-1);
//                resultado = "Acessos:\t"+(alg.hit+alg.miss)+"\nHit:\t\t"+alg.hit+"\nMiss:\t\t"+alg.miss+"\n";
//                resultado += menu.cacheBits();
//                break;
//            }
//            case 2:{
//                BubbleSort alg = new BubbleSort(RAM, CACHE, menu);
//                alg.bubbleSort();
//                resultado = "Acessos:\t\t"+(alg.hit+alg.miss)+"\nHit:\t\t"+alg.hit+"\nMiss:\t\t"+alg.miss+"\n";
//                resultado += menu.cacheBits();
//                break;
//            }
//            case 3:{
//                SelectionSort alg = new SelectionSort(RAM, CACHE, menu);
//                alg.selectionSort();
//                resultado = "Acessos:\t\t"+(alg.hit+alg.miss)+"\nHit:\t\t"+alg.hit+"\nMiss:\t\t"+alg.miss+"\n";
//                resultado += menu.cacheBits();
//                break;
//            }
//            default:
//                break;
//        }
//        
//        // Resultado
//        System.out.println(resultado);
        
        sc.close();
    }
}
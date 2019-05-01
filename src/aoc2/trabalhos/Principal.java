package aoc2.trabalhos;

import java.util.Random;

public class Principal {
	
	public static void main(String args[]) {
		Random rng          = new Random();
		int capacidade      = 16 * 8 * 1024;
	
		Menu menu           = new Menu();
		
		int capacidadeCahce = menu.cache.getPalavras();
		int pal_blocos      = menu.cache.getBlocos();
		Memoria[] RAM       = new Memoria[capacidade];
		Cache[] CACHE       = new Cache[capacidadeCahce];
		
		// Inicialização das memórias
		menu.startMems(RAM, CACHE, capacidade, capacidadeCahce, pal_blocos, rng);
		
		// Atribuindo os valores da RAM à CACHE
		menu.RAMtoCache(RAM, CACHE, capacidadeCahce, pal_blocos);
		
		// Imprimindo a cache
		menu.showCache(CACHE, capacidadeCahce);
		
		// Campos da cache
		menu.cacheBits();

	}
}

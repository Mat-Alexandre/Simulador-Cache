package aoc2.trabalhos;

import java.util.Random;
import java.util.Scanner;

public class Menu {
	
	public CacheInfo cache;
	
	public Menu() {
		int p = 0, b = 0, m = 0, a = 0, s = 0, e = 0;
		System.out.println("Tamanho da CACHE:");
		System.out.println("(1) 16 Palavras");
		System.out.println("(2) 32 Palavras");
		System.out.println("(3) 64 Palavras");
		System.out.println("(4) 128 Palavras");
		System.out.println("(5) 256 Palavras");
		System.out.println("(6) 512 Palavras");
		
		Scanner sc = new Scanner(System.in);
		int op;
		op = sc.nextInt();
		
		switch (op) {
			case 1:
				p = 16;
				break;
			case 2:
				p = 32;
				break;
			case 3:
				p = 63;
				break;
			case 4:
				p = 128;
				break;
			case 5:
				p = 256;
				break;
			case 6:
				p = 512;
				break;
			default:
				System.out.println("Opção invlida");
				break;
		}
		
		System.out.println("Tamanho do BLOCO:");
		System.out.println("(1) 1 Palavra por bloco");
		System.out.println("(2) 2 Palavras por bloco");
		System.out.println("(3) 4 Palavras por bloco");
		System.out.println("(4) 8 Palavras por bloco");
		System.out.println("(5) 16 Palavras por bloco");
		System.out.println("(6) 32 Palavras por bloco");
		
		op = sc.nextInt();
		
		switch (op) {
			case 1:
				b = 1;
				break;
			case 2:
				b = 2;
				break;
			case 3:
				b = 4;
				break;
			case 4:
				b = 8;
				break;
			case 5:
				b = 16;
				break;
			case 6:
				b = 32;
				break;
			default:
				System.out.println("Opção invlida");
				break;
		}
		
		System.out.println("Tipo do MAPEAMENTO:");
		System.out.println("(1) Direto");
		System.out.println("(2) Associativo por Conjunto");
		System.out.println("(3) Totalmente Associativo");
		
		op = sc.nextInt();
		
		switch (op) {
			case 1:
				m = 1;
				break;
			case 2:
				m = 2;
				System.out.println("Quantidade de VIAS:");
				System.out.println("(1) 2 vias\n(2) 4 vias\n(3) 8 vias");
				int op2 = sc.nextInt();
				switch (op2) {
				case 1:
					a = 2;
					break;
				case 2:
					a = 4;
					break;
				case 3:
					a = 8;
					break;
				default:
					System.out.println("Opção inválida");
					break;
				}
				break;
			case 3:
				m = 3;
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}
		
		System.out.println("Política de SUBSTITUIÇÃO:");
		System.out.println("(1) LRU\n(2) LFU\n(3) FIFO");
		
		op = sc.nextInt();
		
		switch (op) {
			case 1:
				s = 1;
				break;
			case 2:
				s = 2;
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}
		
		System.out.println("Política de ESCRITA:");
		System.out.println("(1) Write-Through\n(2) Write-Back");
		
		op = sc.nextInt();
		
		switch (op) {
			case 1:
				e = 1;
				break;
			case 2:
				e = 2;
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}
		
		cache = new CacheInfo(p, b, m, a, s, e);
		sc.close();
	}

	public void RAMtoCache(Memoria[] RAM, Cache[] CACHE, int capacidadeCahce, int pal_blocos) {
		for(int i = 0; i < 2*capacidadeCahce; i++) {
			int end     = RAM[i].linhaCache(RAM[i].enderecoBloco(RAM[i].getEndereco(), pal_blocos), capacidadeCahce);
			int posicao = RAM[i].offsetBloco(RAM[i].getEndereco(), pal_blocos);
			int dado    = RAM[i].getDado();
			CACHE[end].setDado(dado, posicao);
			CACHE[end].setValidade(true);
		}
	}

	public void showCache(Cache[] CACHE, int capacidadeCahce) {
		System.out.println("-------------------------CACHE--------------------------");
		System.out.println("| LINHA |  VAL  |  SUJO  | ENDERECO |       DADOS      |");
		for(int i = 0; i < capacidadeCahce; i++) {
			System.out.printf("|%4d   | %3d   |  %3d   | %5d    |  %5s\n",
			(i+1), CACHE[i].getValidade(), CACHE[i].getSujo(), CACHE[i].getEndereco(), CACHE[i].toString());
		}
	}

	public void cacheBits() {
		System.out.println("\nQuantidade de bits para cada campo da CACHE:");
		int obresult = (int)(Math.log(this.cache.getBlocos()) / Math.log(2));
		int indresult = (int)(Math.log(this.cache.getPalavras()) / Math.log(2));
		System.out.println("Offset Bloco: " + obresult);
		System.out.println("Índice:       " + indresult);
		System.out.println("TAG:          " + (32 - obresult - indresult));
	}
	
	public void startMems(Memoria[] RAM, Cache[] CACHE, int capacidade, int capacidadeCahce, int pal_blocos, Random rng) {
		for(int i = 0; i < capacidade; i++) {
			RAM[i] = new Memoria(i, rng.nextInt(capacidade));
		}
		
		for(int i = 0; i < capacidadeCahce; i++) {
			CACHE[i] = new Cache(i, pal_blocos);
		}
	}
}

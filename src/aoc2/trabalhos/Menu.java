package aoc2.trabalhos;

import java.util.Random;
import java.util.Scanner;
//import javax.swing.SpringLayout;

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

    public Menu(int a){
    }

    public boolean inCache(Memoria[] RAM, Cache[] CACHE, int index) {
    	int endPalavra = RAM[index].getEndereco();
        int endBloco   = RAM[index].enderecoBloco(endPalavra, cache.getBlocos());
        int posicao    = RAM[index].offsetBloco(endPalavra, cache.getBlocos());
		
        switch (cache.getMapeamento()) {
        case "Direto":
        {
        	// Mapeamento direto verificar a tag e validade
        	// Verificar qual linha na cache o dado está
        	int linha = RAM[index].linhaCache(endBloco, cache.getBlocos());
        	
        	// Verificando a tag do endereço
        	int tag = (int)Math.ceil(endBloco/cache.getPalavras());
        	
        	if(CACHE[linha].getValidade() == 1) {
        		if(CACHE[linha].getTag() != tag) {
        			// MISS
        			// Buscar dados na RAM
        			toCache(RAM, CACHE, index);
        			return false;
        		}else {
        			//Ler posição (hit)	
        			return true;
        		}
        	}else {
        		// MISS
        		// Buscar dados na RAM
        		toCache(RAM, CACHE, index);
        		return false;
        	}
        }	
        case "Associativo":
        {
        	// | END. BLOCO | OFFSET |
        	// | TAG | CONJ | OFFSET |
        	int qtdConjuntos = cache.getPalavras()/cache.getAssociatividade();
            int conjunto     = (int)Math.floorMod(endBloco, qtdConjuntos);
            int linha        = conjunto*cache.getAssociatividade();
            int tag          = (int)Math.ceil(endBloco/qtdConjuntos);
            int tentativas   = 0;
           
            while(tentativas < cache.getAssociatividade()){
                if(CACHE[linha].getValidade() == 1 && CACHE[linha].getTag() == tag){
                    return true;
                }
                tentativas++;
                linha++;
            }
            // MISS
    		// Buscar dados na RAM
            toCache(RAM, CACHE, index);
            return false;   
        }
        case "Total":
        {
        	int linha = 0;
        	int tag   = (int)Math.ceil(endBloco/1);
        	
        	while(linha < cache.getPalavras()){
        		//Ler posição (hit)
        		if(CACHE[linha].getValidade() == 1 && CACHE[linha].getTag() == tag)
            		return true;
        		linha++;
            }
        	if(linha == cache.getPalavras()) {
        		// MISS
        		// Buscar dados na RAM
        		toCache(RAM, CACHE, index);
        		return false;
        	}
        }
        default:
        	return false;
        }
    }
    
    public void toCache(Memoria[] RAM, Cache[] CACHE, int index) {
    	int endPalavra = RAM[index].getEndereco();
        int endBloco   = RAM[index].enderecoBloco(endPalavra, cache.getBlocos());
        int posicao    = RAM[index].offsetBloco(endPalavra, cache.getBlocos());
		
    	switch(cache.getMapeamento()) {
	    	case "Direto":
	    	{
	    		int linhaCache = RAM[index].linhaCache(endBloco, cache.getPalavras());
	    		//
	    		CACHE[linhaCache].setEndereco(endPalavra);
	    		//
	    		CACHE[linhaCache].setValidade(true);
	    		CACHE[linhaCache].setTag(endBloco, cache.getPalavras());
	    		
	    		// Copiar os dados do bloco
	            int indexAux = index - posicao;
	            for(int i = 0; i < cache.getBlocos(); i++){
	                CACHE[linhaCache].setDado(RAM[indexAux + i].getDado(), i);
	            }
	    		break;
	    	}
	    	case "Associativo":
	    	{
	    		int qtdConjuntos = cache.getPalavras()/cache.getAssociatividade();
	            int conjunto     = (int)Math.floorMod(endBloco, qtdConjuntos);
	            int linha        = conjunto*cache.getAssociatividade();
	            int tentativas   = 0;
	            boolean escrito  = false;
	           
	            System.out.println("End. Palavra: "+endPalavra);
	            System.out.println("End. Bloc: "+endBloco);
	            System.out.println("Posição: "+posicao);
	            System.out.println("Qtd Blocos: "+cache.getBlocos());
	            System.out.println("Conjunto: "+conjunto);
	            System.out.println("Linha: "+linha+"\n");
	            while(tentativas < cache.getAssociatividade() && escrito == false){
                    if(CACHE[linha].validade == false){
                        // TAG (a.c.) = end. bloco / (conjunto)
                        CACHE[linha].setTag(endBloco, qtdConjuntos);
                        CACHE[linha].setValidade(true);
                        //
        	    		CACHE[linha].setEndereco(endPalavra);
        	    		//
                        
                        // Copiando os dados do bloco
                        int indexAux = index - posicao;
                        for(int i = 0; i < cache.getBlocos(); i++){        	
//                            CACHE[linha].setDado(RAM[index + i].getDado(), i);
                        }
                        escrito = true;
                    }    
                    linha++;
                }
                if(escrito == false){
                    System.out.println("Substituição");
                }
	    		break;
	    	}
	    		
	    	case "Total":
	    	{
	    		int linhaCache = 0;
                boolean escrito = false;
                
                // Enquanto for menor que o tamanho da cache.
                while(linhaCache < cache.getPalavras() && escrito == false){
                    if(CACHE[linhaCache].validade == false){
                    	// apenas para verificação. Retirar quando finalizado
                        CACHE[linhaCache].setEndereco(endPalavra);

                        // TAG (t.a.) = end. bloco
                        CACHE[linhaCache].setTag(endBloco, 1);
                        CACHE[linhaCache].setValidade(true);
                        //
        	    		CACHE[linhaCache].setEndereco(endPalavra);
        	    		//
        	    		
                        // Copiando os dados do bloco
                        int indexAux = index - posicao;
                        for(int i = 0; i < cache.getBlocos(); i++){
                            CACHE[linhaCache].setDado(RAM[indexAux + i].getDado(), i);
                        }
                        escrito = true;
                    }    
                    linhaCache++;
                }
                if(escrito == false){
                    System.out.println("Substituição");
                }
	    		break;	
	    	}
    	}
    }

    private void toRAM(Memoria[] RAM, Cache[] CACHE, int index) {
    	
    }

    public void verifyCache(int index) {
    	
    }
    
    public void showCache(Cache[] CACHE) {
        System.out.println("-------------------------CACHE--------------------------");
        System.out.println("| LINHA | VAL | SUJO |    TAG   |  ENDERECO |       DADOS      |");
        for(int i = 0; i < cache.getPalavras(); i++) {
            System.out.printf("|%4d   | %3d | %3d  | %8d | %9d |  %5s\n",
            (i), CACHE[i].getValidade(), CACHE[i].getSujo(),CACHE[i].getTag(), CACHE[i].getEndereco(), CACHE[i].toString());
        }
    }

    public void cacheBits() {
    	int bitsOb;
        System.out.println("\nQuantidade de bits para cada campo da CACHE:");
        switch(cache.getMapeamento()) {
        	case "Direto":
        		bitsOb = (int)(Math.log(this.cache.getBlocos()) / Math.log(2));
                int bitsInd = (int)(Math.log(this.cache.getPalavras()) / Math.log(2));
                System.out.println("Offset Bloco:\t" + bitsOb);
                System.out.println("Índice:\t" + bitsInd);
                System.out.println("TAG:\t\t" + (32 - bitsInd - bitsOb));
        		break;
        	case "Associativo":
        		bitsOb = (int)(Math.log(this.cache.getBlocos()) / Math.log(2));
                int conjuntos = cache.getPalavras()/cache.getAssociatividade();
                int bitsConj = (int)(Math.log(conjuntos)/Math.log(2));
        		
                System.out.println("Offset Bloco:\t" + bitsOb);
                System.out.println("Conjunto:\t" + bitsConj);
                System.out.println("TAG:\t\t" + (32 - bitsConj - bitsOb));
        		break;
        	case "Total":
        		bitsOb = (int)(Math.log(this.cache.getBlocos()) / Math.log(2));
        		System.out.println("Offset Bloco:\t" + bitsOb);
                System.out.println("TAG:\t\t" + (32 - bitsOb));
        		break;
        	default:
        		break;
        }
        
    }

    public void startMems(Memoria[] RAM, Cache[] CACHE, int capacidade, Random rng) {
        for(int i = 0; i < capacidade; i++) {
        	int dado = rng.nextInt(capacidade);
            RAM[i] = new Memoria(i, dado);
        }

        for(int i = 0; i < cache.getPalavras(); i++) {
            CACHE[i] = new Cache(cache.getBlocos());
        }
    }

}
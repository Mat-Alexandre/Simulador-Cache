package aoc2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Menu {
	/*
	 * Classe que possui os principai métodos para modificação e verificação
	 * de dados nas memórias.
	 * 
	 * Construtor com parâmetro é usado somente para a GUI.
	 * 
	 * O Construtor utilizado para execução em linha de comando recebe todas as informações
	 * necessárias para a simulação da cache.
	 * 
	 * O método:
	 * 	inicia_memoria>	inicia as memórias Cache e RAM.
	 * 	esta_na_cache>	verifica se um dado na posição 'index' está presente na memória cache. Retorna true se
	 * estiver e se não estiver, chama o método para_a_cache() e retorna false.
	 * 	para_a_cache>	responsável por armazenar na Cache o dado na posição 'index'. Caso o local ja esteja sendo ocupado por
	 * algum outro valor, deverá ser executado a política de substituição.
	 * 	print_cache>	imprime no console a linha, validade, bit sujo, tag, endereço e dados de cada posição da Cache.
	 * 	cacheBits>	imprime no console a quantidade de bits para representar cada informação da cache.
	 */
	
    private ArrayList<Integer> arraySub = new ArrayList<Integer>();
    public int alg;
    public CacheInfo cache;
    private int EB = 6926;

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
                System.out.println("Opção inválida");
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
                System.out.println("Opção inválida");
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
            case 3:
                s = 3;
                break;
            default:
                System.out.println("Opção Inválida");
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
            	System.out.println("Opção Inválida");
                break;
        }
        
        System.out.println("Algoritmo a ser testado:\n(1) QuickSort\n(2) BubbleSort\n(3) SelectionSort\n");

        alg = sc.nextInt();

        cache = new CacheInfo(p, b, m, a, s, e);
        
        sc.close();
    }

    public Menu(String GUI){
    }

    public void inicia_memoria(Memoria[] RAM, Cache[] CACHE, int capacidadeRam, Random rng) {
        for(int i = 0; i < capacidadeRam; i++) {
            int dado = rng.nextInt(capacidadeRam);
            RAM[i] = new Memoria(i, dado);
        }
        for(int i = 0; i < cache.get_tam_cache(); i++){
            CACHE[i] = new Cache(cache.get_pal_por_bloco());
        }
    }
    
    public boolean esta_na_cache(Memoria[] RAM, Cache[] CACHE, int index) {
    	int en_de_palavra = RAM[index].getEndereco();
        int en_de_bloco   = CACHE[0].endereco_de_bloco(en_de_palavra, cache.get_pal_por_bloco());
        
        switch (cache.getMapeamento()) {
        case "Direto":
        {
            // Mapeamento direto verificar a tag e validade
            // Verificar qual linha na cache o dado está
            int linha = CACHE[0].linha_da_cache(en_de_bloco, cache.get_tam_cache());
            int tag = CACHE[0].valor_da_tag(en_de_bloco, cache.get_tam_cache());

            if(CACHE[linha].getValidade() == 1) {
                if(CACHE[linha].getTag() != tag) {
                    // MISS
                    para_a_cache(RAM, CACHE, index);
                    return false;
                }else {
                    // HIT
                    return true;
                }
            }else {
                // MISS
                para_a_cache(RAM, CACHE, index);
                return false;
            }
        }	
        case "Associativo":
        {
            int qt_de_conjuntos = cache.get_tam_cache()/cache.getAssociatividade();
            int conjunto        = CACHE[0].getConjunto(en_de_bloco, qt_de_conjuntos);
            int tag             = CACHE[0].valor_da_tag(en_de_bloco, qt_de_conjuntos);
            int linha           = conjunto*cache.getAssociatividade();
            int tentativas      = 0;
           
            while(tentativas < cache.getAssociatividade()){
                if(CACHE[linha].getValidade() == 1 && CACHE[linha].getTag() == tag){
                    // HIT
                    return true;
                }
                tentativas++;
                linha++;
            }
            // MISS
            para_a_cache(RAM, CACHE, index);
            return false;   
        }
        case "Total":
        {
            int linha = 0;
            int tag   = CACHE[0].valor_da_tag(en_de_bloco, 1);

            while(linha < cache.get_tam_cache()){
                // HIT
                if(CACHE[linha].getValidade() == 1 && CACHE[linha].getTag() == tag)
                return true;
                linha++;
            }
            if(linha == cache.get_tam_cache()) {
                // MISS
                para_a_cache(RAM, CACHE, index);
                return false;
            }
        }
        default:
        	return false;
        }
    }
    
    public boolean pertence_ao_conjunto(Memoria[] RAM, Cache[] CACHE, int conjunto, int linha){
        int en_de_palavra = RAM[linha].getEndereco();
        int en_de_bloco = CACHE[0].endereco_de_bloco(en_de_palavra, cache.get_pal_por_bloco());
        int qt_de_conjuntos = cache.get_tam_cache()/cache.getAssociatividade();
        int conj = (int)Math.floorMod(en_de_bloco, qt_de_conjuntos);
        return conj == conjunto;
    }
    
    public void para_a_cache(Memoria[] RAM, Cache[] CACHE, int index) {
    	int en_de_palavra = RAM[index].getEndereco();
        int en_de_bloco   = CACHE[0].endereco_de_bloco(en_de_palavra, cache.get_pal_por_bloco());
        boolean escrito   = false;
        
    	switch(cache.getMapeamento()) {
        case "Direto":
        {
            int linha  = CACHE[0].linha_da_cache(en_de_bloco, cache.get_tam_cache());
            int offset = CACHE[0].offset_de_bloco(en_de_palavra, en_de_bloco);
            int tag    = CACHE[0].valor_da_tag(en_de_bloco, cache.get_tam_cache());
            
            if(CACHE[linha].getValidade() == 0 || CACHE[linha].getTag() != tag) {
                // Dado não está na cache. Por ser mapeamento direto, independe da política de substituição
                CACHE[linha].setEndereco(en_de_palavra);
                CACHE[linha].setValidade(true);
                CACHE[linha].setTag(en_de_bloco, cache.get_tam_cache());
                
                // Copiando o bloco inteiro
                if(en_de_palavra + cache.get_pal_por_bloco() < 16 * 8 * 1024){
                    for(int i = 0; i < cache.get_pal_por_bloco(); i++){
                        CACHE[linha].setDado(RAM[(en_de_palavra - EB) + i].getDado(), i);
                    }
                }
            }
            //else: Dado na posição INDEX está na cache
            break;
        }
        case "Associativo":
        {
            int qt_de_conjuntos = cache.get_tam_cache()/cache.getAssociatividade();
            int conjunto        = CACHE[0].getConjunto(en_de_bloco, qt_de_conjuntos);
            int tag             = CACHE[0].valor_da_tag(en_de_bloco, qt_de_conjuntos);
            int linha           = conjunto*cache.getAssociatividade();
            int tentativas      = 0;
            int linhaAux        = linha;
            
            while(tentativas < cache.getAssociatividade() && escrito == false){
                if(CACHE[linhaAux].getValidade() == 1){
                    // Tem algum dado naquela posição
                    if(CACHE[linhaAux].getTag() == tag){
                        // O dado é o que procuramos
                        escrito = true;                    
                    }else{
                        // O dado presente não pode ser modificado, ainda.
                        tentativas++;
                        linhaAux++;
                    }
                }else{
                    tentativas = cache.getAssociatividade();
                }
                //else: Posição vazia.
            }
            
            if(escrito == false){
                // Se nenhuma posição da via continha os dados
                // Verificar a próxima linha vazia
                linhaAux = linha;
                tentativas = 0;
                try {
                    while(linhaAux < cache.get_pal_por_bloco() && CACHE[linhaAux].getValidade() == 1 && tentativas < cache.getAssociatividade()) {
                        linhaAux++;
                        tentativas++;
                    }
                } catch (Exception e) {
                    System.err.println("linhaaux: "+ linhaAux);
                }
            }
            
            if(CACHE[linhaAux].getValidade() == 0 && (linhaAux-linha) < cache.getAssociatividade()) {
                // Endereço para verificação
                escrito = true;
                CACHE[linhaAux].setEndereco(en_de_palavra);
                CACHE[linhaAux].setTag(en_de_bloco, qt_de_conjuntos);
                CACHE[linhaAux].setValidade(true);
                CACHE[linhaAux].setUso();
                substituicao(linhaAux);

                if(en_de_palavra + cache.get_pal_por_bloco() < 16 * 8 * 1024){
                    for(int j = 0; j < cache.get_pal_por_bloco(); j++){
                        CACHE[linha].setDado(RAM[(en_de_palavra - EB) + j].getDado(), j);
                    }
                }
            }
            if(escrito == false) {
                // inicio Pol substituição
                if(cache.getSubstituicao() == "LRU") {
                    // Verificar qual linha da cache foi usado a mais tempo
                    // O array, neste caso, se comporta como uma fila. Sendo assim, basta substituir o último
                    int tam = arraySub.size();
                    int linhaSubstituir;
                    
                    if(tam > 0) linhaSubstituir = arraySub.get(tam-1);
                    else linhaSubstituir = 0;

                    CACHE[linhaSubstituir].setUso(0);
                    CACHE[linhaSubstituir].setEndereco(en_de_palavra);
                    CACHE[linhaSubstituir].setTag(en_de_bloco, 1);
                    CACHE[linhaSubstituir].setValidade(true);

                    // Removendo aquela linha
                    if(arraySub.size() > 0) arraySub.remove(arraySub.size()-1);
                    
                }else if(cache.getSubstituicao() == "LFU") {
                    // Verificar qual linha da cache foi usada com menos frequência
                    int linhaSubstituir = linha;

                    for(int i: arraySub) {
                        int enBlocoAux = CACHE[0].endereco_de_bloco(CACHE[i].getEndereco(), cache.get_pal_por_bloco());
                        int conjAux = CACHE[0].getConjunto(enBlocoAux, qt_de_conjuntos);
                        
                        if(conjAux == conjunto){
                            if(CACHE[i].getUso() < CACHE[linhaSubstituir].getUso()){
                                i = linhaSubstituir;
                            }
                        }
                    }
                    
                    System.out.println("linha a ser subs: "+linhaSubstituir);

                    CACHE[linhaSubstituir].setUso(0); //Resetando o uso
                    CACHE[linhaSubstituir].setEndereco(en_de_palavra);
                    CACHE[linhaSubstituir].setTag(en_de_bloco, qt_de_conjuntos);
                    CACHE[linhaSubstituir].setValidade(true);
                    
                    // Removendo aquela linha
                    if(arraySub.indexOf(linhaSubstituir) > 0){
                        arraySub.remove(arraySub.indexOf(linhaSubstituir));
                    }
                }else{
                    // FIFO
                    int linhaSubstituir = linha;
                    boolean achou_linha = false;
                    int i = -1;
                    // Se o tamanho do array for maior do que 0, então recebe a última posição dele
                    if(arraySub.size() > 0){
                        i = arraySub.size();
                    }
                    
                    while(i >= 0 && achou_linha == false){
                        int nova_linha = arraySub.get(i-1);
                        
                        int enBlocoAux = CACHE[0].endereco_de_bloco(CACHE[nova_linha].getEndereco(), cache.get_pal_por_bloco());
                        int conjAux = CACHE[0].getConjunto(enBlocoAux, qt_de_conjuntos);
                        // Verificando se o ambas as posição possuem o mesmo conjunto
                        if(conjAux == conjunto){
                            linhaSubstituir = nova_linha;
                            achou_linha = true;
                            // Removendo aquela linha
                            if(arraySub.size() > 0) arraySub.remove(arraySub.size()-1);
                        }
                        i--;
                    }
                    CACHE[linhaSubstituir].setUso(0);
                    CACHE[linhaSubstituir].setEndereco(en_de_palavra);
                    CACHE[linhaSubstituir].setTag(en_de_bloco, qt_de_conjuntos);
                    CACHE[linhaSubstituir].setValidade(true);
                }
                // fim Pol substituição
            }
            break;
        }

        case "Total":
        {
            int linha      = 0;
            int tentativas = 0;
            int linhaAux   = linha;
            int tag        = CACHE[0].valor_da_tag(en_de_bloco, 1);
            // arrumar aux
            int offset     = CACHE[0].offset_de_bloco(en_de_palavra, en_de_bloco);
            
            while(tentativas < cache.get_tam_cache() && escrito == false){
                if(CACHE[linhaAux].getValidade() == 1){
                    // Tem algum dado naquela posição
                    if(CACHE[linhaAux].getTag() == tag){
                        // O dado é o que procuramos
                        escrito = true;                    
                    }else{
                        // O dado presente não pode ser modificado, ainda.
                        tentativas++;
                        linhaAux++;
                    }
                }else{
                    tentativas = cache.get_tam_cache();
                }
                //else: Posição vazia.
            }
            
            if(linhaAux >= cache.get_tam_cache()) linhaAux = linha;
            
            if(CACHE[linhaAux].getValidade() == 0) {
                // Endereço para verificação
                escrito = true;
                CACHE[linhaAux].setEndereco(en_de_palavra);
                CACHE[linhaAux].setTag(en_de_bloco, 1);
                CACHE[linhaAux].setValidade(true);
                CACHE[linhaAux].setUso();
                substituicao(linhaAux);

                if(en_de_palavra + cache.get_pal_por_bloco() < 16 * 8 * 1024){
                    for(int j = 0; j < cache.get_pal_por_bloco(); j++){
                        CACHE[linha].setDado(RAM[(en_de_palavra - EB) + j].getDado(), j);
                    }
                }
            }
            
            if(escrito == false){
                // inicio pol substituição
                if(cache.getSubstituicao() == "LRU") {
                    // Verificar qual linha da cache foi usado a mais tempo
                    // O array, neste caso, se comporta como uma fila. Sendo assim, basta substituir o último
                    int linhaSubstituir;
                    
                    if(arraySub.size() > 0) linhaSubstituir = arraySub.get(arraySub.size()-1);
                    else linhaSubstituir = 0;

                    CACHE[linhaSubstituir].setUso(0);
                    CACHE[linhaSubstituir].setEndereco(en_de_palavra);
                    CACHE[linhaSubstituir].setTag(en_de_bloco, 1);
                    CACHE[linhaSubstituir].setValidade(true);

                    // Removendo aquela linha
                    if(arraySub.size() > 0) arraySub.remove(arraySub.size()-1);
                    
                }else if(cache.getSubstituicao() == "LFU") {
                    // Verificar qual linha da cache foi usada com menos frequência
                    int linhaSubstituir = 0;

                    for(int i: arraySub) {
                        if(CACHE[i].getUso() < CACHE[linhaSubstituir].getUso()) {
                            linhaSubstituir = i;
                        }
                    }

                    CACHE[linhaSubstituir].setUso(0); //Resetando o uso
                    CACHE[linhaSubstituir].setEndereco(en_de_palavra);
                    CACHE[linhaSubstituir].setTag(en_de_bloco, 1);
                    CACHE[linhaSubstituir].setValidade(true);

                    // Removendo aquela linha
                    if(arraySub.indexOf(linhaSubstituir) > 0) arraySub.remove(arraySub.indexOf(linhaSubstituir));
                }else{
                    // FIFO
                    int linhaSubstituir;
                    
                    if(arraySub.size() > 0) linhaSubstituir = arraySub.get(arraySub.size()-1);
                    else linhaSubstituir = 0;

                    CACHE[linhaSubstituir].setUso(0);
                    CACHE[linhaSubstituir].setEndereco(en_de_palavra);
                    CACHE[linhaSubstituir].setTag(en_de_bloco, 1);
                    CACHE[linhaSubstituir].setValidade(true);

                    // Removendo aquela linha
                    if(arraySub.size() > 0) arraySub.remove(arraySub.size()-1);
                }
                // fim pol substituição
            }
            break;	
        }
    	}
    }
    
    public void print_cache(Cache[] CACHE) {
        System.out.println("-------------------------CACHE--------------------------");
        System.out.println("| LINHA | VAL | SUJO |    TAG   |  ENDERECO |       DADOS      |");
        for(int i = 0; i < cache.get_tam_cache(); i++) {
            System.out.printf("|%4d   | %3d | %3d  | %8d | %9d |  %5s\n",
            (i), CACHE[i].getValidade(), CACHE[i].getSujo(),CACHE[i].getTag(), CACHE[i].getEndereco(), CACHE[i].toString());
        }
    }

    public void substituicao(int posCache) {
    	// É diferente para LRU
    	if(cache.getSubstituicao() == "LRU") {
            if(arraySub.contains(posCache) == false) {
                arraySub.add(0, posCache);
            }else {
                arraySub.remove(arraySub.indexOf(posCache));
                arraySub.add(posCache);
            }
    	}else {
            if(arraySub.contains(posCache) == false) arraySub.add(0, posCache);
    	}
    }
    
    public void mudar_dado(Memoria[] RAM, Cache[] CACHE, int posicao, int dado){
        int en_de_palavra = RAM[posicao].getEndereco();
        int en_de_bloco   = CACHE[0].endereco_de_bloco(en_de_palavra, cache.get_pal_por_bloco());
        int offset        = CACHE[0].offset_de_bloco(en_de_palavra, en_de_bloco);

        switch (cache.getMapeamento()) {
        case "Direto":
        {
            int linha = CACHE[0].linha_da_cache(en_de_bloco, cache.get_tam_cache());
            int tag   = CACHE[0].valor_da_tag(en_de_bloco, cache.get_tam_cache());


            if(CACHE[linha].getValidade() == 1 && CACHE[linha].getTag() == tag) {
                // HIT
                /*
                Setar sujo para 1
                Verificar qual offset alterar
                Verificar se a política é Write-Back
                */
                if(cache.getSubstituicao().equals("Write-Back")){
                    CACHE[linha].setSujo(true);
                    CACHE[linha].setDado(RAM[posicao].getDado(), offset);
                }else{
                    CACHE[linha].setDado(RAM[posicao].getDado(), offset);
                    RAM[posicao].setDado(dado, 0);
                }
            }
            break;
        }	
        case "Associativo":
        {
            int qt_de_conjuntos = cache.get_tam_cache()/cache.getAssociatividade();
            int conjunto        = (int)Math.floorMod(en_de_bloco, qt_de_conjuntos);
            int linha           = conjunto*cache.getAssociatividade();
            int tag             = (int)Math.ceil(en_de_bloco/qt_de_conjuntos);
            int tentativas      = 0;
           
            while(tentativas < cache.getAssociatividade()){
                if(CACHE[linha].getValidade() == 1 && CACHE[linha].getTag() == tag){
                    // HIT
                    if(cache.getSubstituicao().equals("Write-Back")){
                        CACHE[linha].setSujo(true);
                        CACHE[linha].setDado(RAM[posicao].getDado(), offset);
                    }else{
                        CACHE[linha].setDado(RAM[posicao].getDado(), offset);
                        RAM[posicao].setDado(dado, 0);
                    }
                }
                tentativas++;
                linha++;
            }
        }
        case "Total":
        {
            int linha = 0;
            int tag   = (int)Math.ceil(en_de_bloco/1);

            while(linha < cache.get_tam_cache()){
                if(CACHE[linha].getValidade() == 1 && CACHE[linha].getTag() == tag){
                    // HIT
                    if(cache.getSubstituicao().equals("Write-Back")){
                        CACHE[linha].setSujo(true);
                        CACHE[linha].setDado(RAM[posicao].getDado(), offset);
                    }else{
                        CACHE[linha].setDado(RAM[posicao].getDado(), offset);
                        RAM[posicao].setDado(dado, 0);
                    }
                }
                linha++;
            }
        }
        default:
            break;
        }
    }
    
    public String cacheBits() {
    	int bitsOb;
    	String resultado = "\nQuantidade de bits para cada campo da CACHE:";
        switch(cache.getMapeamento()) {
            case "Direto":
                bitsOb = (int)(Math.log(this.cache.get_pal_por_bloco()) / Math.log(2));
                int bitsInd = (int)(Math.log(this.cache.get_tam_cache()) / Math.log(2));
                resultado += "\nOffset Bloco:\t" + bitsOb;
                resultado += "\nÍndice:\t\t" + bitsInd;
                resultado += "\nTAG:\t\t" + (32 - bitsInd - bitsOb);
                return resultado;
            case "Associativo":
                bitsOb = (int)(Math.log(this.cache.get_pal_por_bloco()) / Math.log(2));
                int conjuntos = cache.get_tam_cache()/cache.getAssociatividade();
                int bitsConj = (int)(Math.log(conjuntos)/Math.log(2));

                resultado += "\nOffset Bloco:\t" + bitsOb;
                resultado += "\nConjunto:\t" + bitsConj;
                resultado += "\nTAG:\t\t" + (32 - bitsConj - bitsOb);
                return resultado;
            default:
                bitsOb = (int)(Math.log(this.cache.get_pal_por_bloco()) / Math.log(2));
                resultado += "\nOffset Bloco:\t" + bitsOb;
                resultado += "\nTAG:\t\t" + (32 - bitsOb);
                return resultado;
        }
    }

}
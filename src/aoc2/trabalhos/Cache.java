package aoc2.trabalhos;

import java.util.Arrays;

public class Cache extends Memoria {
    public boolean validade, sujo;
    private int tag;

    /*
     * Possui construtor da cache, inicializa o vetor de dados
     * com 'pal_blocos' posições. Além de validade e sujo receberem false.
     * 
     * Método toString retorna o vetor de dados em forma de lista.
     * 
     * Getters and Setters para 'validade', 'sujo' e 'tag'.
     * 
     * Método:
     * 	enderecoBloco>	recebe end. de palavra e nº de palavras por bloco. Retorna o endereço de bloco.
     * 	linhaCache>		recebe o end. de bloco e o tamanho da cache. Retorna a linha da cache ao qual o endereço pertence.
     * 	offsetBloco>	recebe o end. de palavra e nº de palavras por bloco. Retorna o offset daquele endereço.
     * 	getConjunto>	recebe o end. de bloco e a qtd. de vias. Retorna o conjunto.
     * */
    
    public Cache(int pal_blocos) {
        this.dado = new int[pal_blocos];
        this.validade = false;
        this.sujo = false;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.dado);
    }
    
    public void setTag(int endBloco, int pal_via){
        this.tag = (int)Math.ceil(endBloco/pal_via);
    }

    public int getTag() {
        return tag;
    }

    public int getValidade() {
        if(this.validade == true)
                return 1;
        return 0;
    }

    public int getSujo() {
        if(this.sujo == true)
                return 1;
        return 0;
    }

    public void setValidade(boolean valor) {
        validade = valor;
    }

    public void setSujo(boolean valor) {
        sujo = valor;
    }
    
    public int enderecoBloco(int endPalavra, int numPalBlocos) {
        return Math.floorDiv(endPalavra, numPalBlocos);
    }

    public int linhaCache(int endBloco, int tamCache) {
        return Math.floorMod(endBloco, tamCache);
    }

    public int offsetBloco(int endPalavra, int numPalBlocos) {
        return Math.floorMod(endPalavra, numPalBlocos);
    }
    
    public int getConjunto(int endBloco, int vias){
        return Math.floorMod(endBloco, vias);
    }

}
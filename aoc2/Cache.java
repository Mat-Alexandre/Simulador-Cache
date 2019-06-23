package aoc2;

import java.util.Arrays;

public class Cache extends Memoria {
    public boolean validade, sujo;
    private int tag;
    private int uso = 0;

    /*
     * Possui construtor da cache, inicializa o vetor de dados
     * com 'pal_por_blocos' posições. Além de validade e sujo receberem false.
     * 
     * Método toString retorna o vetor de dados em forma de lista.
     * 
     * Getters and Setters para 'validade', 'sujo' e 'tag'.
     * 
     * Método:
     * 	endereco_de_bloco>	recebe end. de palavra e nº de palavras por bloco. Retorna o endereço de bloco.
     * 	linha_da_cache>	recebe o end. de bloco e o tamanho da cache. Retorna a linha da cache ao qual o endereço pertence.
     * 	offset_de_bloco>	recebe o end. de palavra e nº de palavras por bloco. Retorna o offset daquele endereço.
     * 	getConjunto>	recebe o end. de bloco e a qtd. de vias. Retorna o conjunto.
     * */
    
    public Cache(int pal_por_blocos) {
        this.dado     = new int[pal_por_blocos];
        this.validade = false;
        this.sujo     = false;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.dado);
    }
    
    public void setUso() {
    	uso++;
    }
    
    public void setUso(int anyI) {
    	// Resetando o valor do uso
    	uso = 0;
    }
    
    public int getUso() {
    	return uso;
    }
    
    public void setTag(int en_de_bloco, int pal_por_via){
        this.tag = (int)Math.ceil(en_de_bloco/pal_por_via);
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
    
    public int endereco_de_bloco(int en_de_palavra, int pal_por_blocos) {
        return Math.floorDiv(en_de_palavra, pal_por_blocos);
    }

    public int linha_da_cache(int en_de_bloco, int tam_da_cache) {
        return Math.floorMod(en_de_bloco, tam_da_cache);
    }

    public int offset_de_bloco(int en_de_palavra, int pal_por_blocos) {
        return Math.floorMod(en_de_palavra, pal_por_blocos);
    }
    
    public int getConjunto(int en_de_bloco, int vias){
        return Math.floorMod(en_de_bloco, vias);
    }

    public int valor_da_tag(int en_de_bloco, int tam_da_cache){
        return (int)Math.ceil(en_de_bloco/tam_da_cache);
    }
}
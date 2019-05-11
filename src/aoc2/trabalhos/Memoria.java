package aoc2.trabalhos;

public class Memoria {
    protected int dado[];
    protected int endereco;

    public Memoria(int endereco, int dado) {
        this.dado = new int[1];
        this.dado[0] = dado;
        this.endereco = 214748364 + endereco;
//		this.endereco = String.format("%032d", Long.valueOf(Long.toBinaryString(endereco)));
    }

    public Memoria() {
        dado = new int[1];
        dado[0] = 0;
        endereco = 0; //String.format("%032d", Long.valueOf(Long.toBinaryString(0)));
    }

    public int getDado() {
        return dado[0];
    }

    public int getEndereco() {
        return endereco;
    }

    public void setDado(int[] dado) {
        this.dado = dado;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
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

    public void setDado(int dado, int posicao) {
        // TODO Auto-generated method stub

    }

}
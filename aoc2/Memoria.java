package aoc2;

public class Memoria {
    protected int dado[];
    protected int endereco;
    
    /*
     * Classe pai da classe Cache.
     * A memória RAM e CACHE possuem fatores em comum, como: endereço e dado.
     * 
     */

    public Memoria(int endereco, int dado) {
        this.dado = new int[1];
        this.dado[0] = dado;
        this.endereco = 6926 + endereco;
//		this.endereco = String.format("%032d", Long.valueOf(Long.toBinaryString(endereco)));
    }

    public Memoria() {
        dado = new int[1];
        dado[0] = 0;
        endereco = 0;
    }

    public int getDado() {
        return dado[0];
    }

    public int getEndereco() {
        return endereco;
    }

    public void setDado(int dado, int posicao) {
        this.dado[posicao] = dado;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

}
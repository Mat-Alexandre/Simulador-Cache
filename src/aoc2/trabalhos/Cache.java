package aoc2.trabalhos;

import java.util.Arrays;

public class Cache extends Memoria {
	public boolean validade, sujo;
	
	public Cache(int endereco, int pal_blocos) {
		this.dado = new int[pal_blocos];
		this.endereco = endereco;
		this.validade = false;
		this.sujo = false;
	}
	@Override
	public void setDado(int dado, int posicao) {
		this.dado[posicao] = dado;
	}
	
	@Override
	public String toString() {
//		return " " + validade + " " + sujo + "       " + endereco + "      " + Arrays.toString(this.dado);
		return Arrays.toString(this.dado);
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
	
	public int getEndereco() {
		return this.endereco;
	}
	
	public void setValidade(boolean valor) {
		validade = valor;
	}
	
	public void setSujo(boolean valor) {
		sujo = valor;
	}
}

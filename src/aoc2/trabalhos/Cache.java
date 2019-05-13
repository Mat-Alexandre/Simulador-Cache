package aoc2.trabalhos;

import java.util.Arrays;

public class Cache extends Memoria {
    public boolean validade, sujo;
    private int tag;

    public Cache(int pal_blocos) {
        this.dado = new int[pal_blocos];
        this.validade = false;
        this.sujo = false;
    }

    @Override
    public String toString() {
//            return " " + validade + " " + sujo + "       " + endereco + "      " + Arrays.toString(this.dado);
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
}
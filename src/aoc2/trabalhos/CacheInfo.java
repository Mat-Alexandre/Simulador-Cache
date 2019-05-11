package aoc2.trabalhos;

public class CacheInfo {
	// Quantidade de palavras na cache (i.e linhas da cache) 
	private int palavras;
	// Número de palavras por bloco
	private int blocos;
	private String mapeamento;
	private int associatividade;
	private String substituicao;
	private String escrita;
	
	
	public int getPalavras() {
		return palavras;
	}

	public int getBlocos() {
		return blocos;
	}

	public String getMapeamento() {
		return mapeamento;
	}

	public int getAssociatividade() {
		return associatividade;
	}

	public String getSubstituicao() {
		return substituicao;
	}

	public String getEscrita() {
		return escrita;
	}

	public CacheInfo(int p, int b, int m, int a, int s, int e) {
		palavras   = p;
		blocos     = b;
		
		switch (m) {
			case 1:
				mapeamento = "Direto";
				break;
			case 2:
				mapeamento = "Associativo";
				break;
			case 3:
				mapeamento = "Total";
				break;
			default:
				mapeamento = "Direto";
				break;
		}
		
		if(mapeamento.equals("Associativo") == true) {
			associatividade = a;
		}else {
			associatividade = 0;
		}
		
		switch (s) {
			case 1:
				substituicao = "LRU";
				break;
			case 2:
				substituicao = "LFU";
				break;
			case 3:
				substituicao = "FIFO";
				break;
			default:
				substituicao = "LRU";
				break;
		}
		
		switch (e) {
			case 1:
				escrita = "Write-Through";
				break;
			case 2:
				escrita = "Write-Back";
				break;
			default:
				escrita = "Write-Through";
				break;
		}
	}
	
	@Override
	public String toString() {
		return "Palavras: " + palavras + "\nBlocos: " + blocos + "\nMapeamento: "
				+ mapeamento + "\nAssociatividade: " + associatividade +
				"\nPolítica de Substituição: " + substituicao + 
				"\nPolítica de Escrita: " + escrita;
	}
	
}
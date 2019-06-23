# Simulador-Cache
Este programa é um trabalho da disciplina de Arquitetura e Organização de Computadores II e tem por finalidade simular o funcionamento de uma cache. O algoritmo em questão foi feito em Java.

### Opções de personalização:

- Tamanho da Cache: 16, 32, 64, 128, 256 e 512 palavras;

- Tamanho do Bloco: 1, 2, 4, 8, 16 e 32 palavras por bloco;

- Tipo de Mapeamento: direto, associativo por conjunto e totalmente associativo;

- Associatividade: 2, 4 ou 8 vias;

- Política de substituição: LRU, LFU e FIFO;

- Política de escrita: write-through e write-back/

### Configurações da Simulação:
No simulador, teremos uma memória de 16 kiB, também simulada, endereçada por palavra. Cada palavra tem 32 bits.

Além disso, serão implementados quatro algoritmos para o estudo do comportamento das caches. Serão eles:
- QuickSort, SelectionSort, BubbleSort e soma de vetores.

### Progresso:
- [X] Criação das memórias(Cache e RAM);
- [X] Mapeamento Direto;
- [X] Mapeamento Associativo por Conjunto;
- [X] Mapeamento Totalmente Associativo;
- [X] QuickSort (em parte);
- [x] Políticas de Substituição;
- [ ] Políticas de Escrita;
- [x] SelectionSort;
- [x] BubbleSort;
- [ ] Soma de vetores;
- [x] Interface Gráfica;

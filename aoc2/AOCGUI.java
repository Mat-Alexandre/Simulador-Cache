package aoc2;

import java.util.Random;

public class AOCGUI extends javax.swing.JFrame {
    public int p, b, m, a, s, e;
    public Memoria[] RAM;
    public Cache[] CACHE;
    private boolean clicado = false;
    public AOCGUI() {
        initComponents();
        lb6.setVisible(false);
        cb6.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        cb1 = new javax.swing.JComboBox<>();
        lb2 = new javax.swing.JLabel();
        cb2 = new javax.swing.JComboBox<>();
        lb3 = new javax.swing.JLabel();
        cb3 = new javax.swing.JComboBox<>();
        lb4 = new javax.swing.JLabel();
        cb4 = new javax.swing.JComboBox<>();
        lb5 = new javax.swing.JLabel();
        cb5 = new javax.swing.JComboBox<>();
        cb6 = new javax.swing.JComboBox<>();
        lb6 = new javax.swing.JLabel();
        btnSimular = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cb7 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        pane = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lb1.setText("Tamanho da cache:");

        cb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "16", "32", "64", "128", "256", "512" }));

        lb2.setText("Tamanho de bloco:");

        cb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "4", "8", "16", "32" }));

        lb3.setText("Mapeamento:");

        cb3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Direto", "Associativo C.", "Totalmente A." }));
        cb3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb3ItemStateChanged(evt);
            }
        });

        lb4.setText("Política de substituição:");

        cb4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LRU", "LFU", "FIFO" }));

        lb5.setText("Política de escrita:");

        cb5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "write-through", "write-back" }));

        cb6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "4", "8" }));

        lb6.setText("vias:");

        btnSimular.setText("SIMULAR");
        btnSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularActionPerformed(evt);
            }
        });

        jLabel1.setText("Algoritmo:");

        cb7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quick sort", "Bubble sort", "Selection sort" }));
        cb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb7ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(pane);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Resultado da simulação:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb1)
                            .addComponent(lb2)
                            .addComponent(lb3)
                            .addComponent(lb4)
                            .addComponent(lb5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(lb6)
                                .addGap(29, 29, 29)
                                .addComponent(cb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnSimular)
                                .addComponent(cb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(label))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb1)
                            .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb2)
                            .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb3)
                            .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb4)
                            .addComponent(cb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb5)
                            .addComponent(cb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cb7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimular))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb3ItemStateChanged
        if(evt.getItem().equals("Associativo C.")){
            lb6.setVisible(true);
            cb6.setVisible(true);
        }else{
            a = 0;
            lb6.setVisible(false);
            cb6.setVisible(false);
        }
    }//GEN-LAST:event_cb3ItemStateChanged

    private void btnSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularActionPerformed
        if(clicado == false){
            Menu menu = new Menu("a");
            int capacidade      = 16 * 8 * 1024;

            p = Integer.parseInt(cb1.getSelectedItem().toString());
            b = Integer.parseInt(cb2.getSelectedItem().toString());

            if(cb3.getSelectedItem().toString().equals("Direto")) {
                m = 1;
                a = 0;
            }else if(cb3.getSelectedItem().toString().equals("Associativo C.")){
                m = 2;
                a = Integer.parseInt(cb6.getSelectedItem().toString());
            }else if(cb3.getSelectedItem().toString().equals("Totalmente A.")){
                m = 3;
                a = 0;
            }

            if(cb4.getSelectedItem().toString().equals("LRU")) {
                s = 1;
            }else if(cb4.getSelectedItem().toString().equals("LFU")){
                s = 2;
            }else if(cb4.getSelectedItem().toString().equals("FIFO")){
                s = 3;
            }

            if(cb4.getSelectedItem().toString().equals("write-through")) {
                e = 1;
            }else{
                e = 2;
            }

            menu.cache = new CacheInfo(p, b, m, a, s, e);
            int capacidadeCahce = menu.cache.get_tam_cache();

            RAM = new Memoria[capacidade];
            CACHE = new Cache[capacidadeCahce];
            String resultado;

            Random rng = new Random();
            menu.inicia_memoria(RAM, CACHE, capacidade, rng);

            if(cb7.getSelectedItem().toString().equals("Quick sort")){
                QuickSort alg = new QuickSort(RAM, CACHE, menu);
                alg.quickSort(0, RAM.length-1);
                resultado = "Acessos:\t\t"+(alg.hit+alg.miss)+"\nHit:\t\t"+alg.hit+"\nMiss:\t\t"+alg.miss+"\n";
                resultado += menu.cacheBits();
            }else if(cb7.getSelectedItem().toString().equals("Bubble sort")){
                BubbleSort alg = new BubbleSort(RAM, CACHE, menu);
                alg.bubbleSort();
                resultado = "Acessos:\t\t"+(alg.hit+alg.miss)+"\nHit:\t\t"+alg.hit+"\nMiss:\t\t"+alg.miss+"\n";
                resultado += menu.cacheBits();
            }else{
                SelectionSort alg = new SelectionSort(RAM, CACHE, menu);
                alg.selectionSort();
                resultado = "Acessos:\t\t"+(alg.hit+alg.miss)+"\nHit:\t\t"+alg.hit+"\nMiss:\t\t"+alg.miss+"\n";
                resultado += menu.cacheBits();
            }

            pane.setText(resultado);

//            menu.print_cache(CACHE);
//            clicado = true;
        }
    }//GEN-LAST:event_btnSimularActionPerformed

    private void cb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb7ActionPerformed

  
    public static void main(String args[]) {
        AOCGUI a = new AOCGUI();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                a.setVisible(true);
                a.setTitle("Simulador de Cache");
                a.setResizable(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimular;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JComboBox<String> cb3;
    private javax.swing.JComboBox<String> cb4;
    private javax.swing.JComboBox<String> cb5;
    private javax.swing.JComboBox<String> cb6;
    private javax.swing.JComboBox<String> cb7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JTextPane pane;
    // End of variables declaration//GEN-END:variables
}

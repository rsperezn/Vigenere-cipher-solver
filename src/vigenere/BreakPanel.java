/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
public class BreakPanel extends javax.swing.JPanel {

    //Global Variables
    static int maxKeylength=20;//the maximun lenght of the key tha we will be able to break
    final JFileChooser fc = new JFileChooser();
    File file;
    String ctextline,ciphertext;
    public static String  brokenfilename;
    static ArrayList<String> KEYS= new ArrayList();// all the possible keys generated
    static double[]  ICStats=new double[maxKeylength];//for the average of IC with different keylengths
    
    public BreakPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        infileTextF = new javax.swing.JTextField();
        OpenButton = new javax.swing.JButton();
        BreakButton = new javax.swing.JButton();
        jCheckBox = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(335, 260));

        jPanel1.setPreferredSize(new java.awt.Dimension(335, 260));

        jLabel1.setText("Choose File");

        infileTextF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infileTextFActionPerformed(evt);
            }
        });

        OpenButton.setText("...");
        OpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenButtonActionPerformed(evt);
            }
        });

        BreakButton.setText("Break");
        BreakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BreakButtonActionPerformed(evt);
            }
        });

        jCheckBox.setText("Show files when done");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BreakButton))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(infileTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(OpenButton)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(infileTextF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BreakButton)
                    .addComponent(jCheckBox))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void infileTextFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infileTextFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infileTextFActionPerformed

    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
        // TODO add your handling code here:
        //reinitialize ciphertext
        ctextline="";ciphertext="";
        
        fc.addChoosableFileFilter(new TextFilter());
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal==JFileChooser.APPROVE_OPTION){
                file = fc.getSelectedFile();
                infileTextF.setText(file.getName());
                try{
                    StringBuilder sb= new StringBuilder();
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    ctextline=br.readLine();
                    while(ctextline!=null){
                        sb.append(ctextline);
                        sb.append("\n");
                        ctextline=br.readLine(); 
                    }
                    ciphertext=sb.toString();
                }
                catch(IOException e){
                    e.printStackTrace();
                }      
        }	
                ciphertext=ciphertext.replaceAll("[^A-Za-z  \n]", "");
            //update the preview window in MainInterface
                MainInterface.textAreaIN.setEditable(true);
                MainInterface.setInPrevTextA(ciphertext);
    }//GEN-LAST:event_OpenButtonActionPerformed

    private void BreakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BreakButtonActionPerformed
        String temp0,temp1;
        ArrayList<Integer> possibleKLength= new ArrayList();
        //reinitilize KEYS
        KEYS.clear();
        if(infileTextF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Make sure to choose a file");
            return;
        }
        
        //shows extra functions buttons in MainInterface
        MainInterface.activateButtons();
        VigenereBreaker vb = new VigenereBreaker(ciphertext);
        int kasikiguess= vb.KasiskiTest(ciphertext);
        possibleKLength.add(kasikiguess);
        System.out.println();
        // Now use Friedman Test and see what are the Index of Coincidence that
        //have the closest valud to the English Ic and then try those sizes of keys
        ArrayList<IndexCoincidence> ICS= new ArrayList<IndexCoincidence>(maxKeylength);
        for(int i=0; i< maxKeylength;i++){
            IndexCoincidence ic= new IndexCoincidence();
            ICS.add(ic);
        }    
        
        //create the ArrayList with their poss and value corrspondintly
        for(int i=1; i<= maxKeylength;i++){
            ICS.get(i-1).setPos(i);//set just the possition
            ICS.get(i-1).setVal(vb.confirmKeyLength(ciphertext, i));
            double currIC=vb.confirmKeyLength(ciphertext, i);
            System.out.print("For key length " + i+ ":"+ vb.confirmKeyLength(ciphertext, i));
            ICStats[i-1]=currIC;//adding the values for each correspondent ic
            System.out.println();
        }
        //sort the elemants
        Collections.sort(ICS);
        //find the position that is interesting for the IC
        for(int i=0; i<maxKeylength;i++){
            double currIC=ICS.get(i).getVal();
            //just use the number that are useful
            if (currIC>=0.06){
                int position= ICS.get(i).getPos();
                System.out.println("Index: "+position + " value: "+currIC);
                possibleKLength.add(position);}
        }

        //vb.confirmKeyLength(ciphertext,kasikiguess);
        for(int j=0;j<possibleKLength.size();j++){
            String[] splits= vb.splitCiphertext(ciphertext, possibleKLength.get(j));
            String[] guessedKeys=vb.guessKey(splits);
            KEYS.add(guessedKeys[0]);
            KEYS.add(guessedKeys[1]);
        }
        String towrite="";   
//        //try to get rid of those keys that are multiples of the others in lenght
//        temp0=(String)KEYS.get(0);KEYS.remove(0);
//        temp1=(String)KEYS.get(1);KEYS.remove(1);
//        Collections.sort(KEYS);
//        for(int i =0; i<KEYS.size()-1;i++){
//            for(int j =i+1;j<KEYS.size();j++){
//                String content= (String)KEYS.get(i);
//                String container=(String) KEYS.get(j);
//                if(container.contains(content)){
//                    KEYS.remove(j);
//                }
//            }
//        }
//        KEYS.add(0, temp0);
//        KEYS.add(1, temp1);
        
        
        
        
        
        Decryptor dec= new Decryptor();
        for(int i=0;i<KEYS.size();i++){
            ciphertext=ciphertext.toUpperCase();
            towrite=towrite+dec.decrypt(ciphertext, KEYS.get(i));
        }
        String name = file.getName();
        try { 
                int pos = name.lastIndexOf(".");
                if (pos > 0) {
                     name = name.substring(0, pos);
                    }
                brokenfilename=name+"Broken.txt";
                BufferedWriter bw = new BufferedWriter(new FileWriter(brokenfilename));
                bw.write(towrite);
                bw.close();
                System.out.println("Done");
        } catch (IOException e) {
                e.printStackTrace();
            }
	//update the corresponding outPrevTexArea in MainInterface
        MainInterface.textAreaOUT.setEditable(true);
        MainInterface.setOutPrevTextA(dec.decrypt(ciphertext, KEYS.get(0)),1);
        
          if(jCheckBox.isSelected()){
            try {
            Runtime.getRuntime().exec("notepad "+brokenfilename);
        } catch (IOException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        
        
          
    }//GEN-LAST:event_BreakButtonActionPerformed
    
    public static String getBrokenFileName(){
        return brokenfilename;
    }
    
    public static double[] getICS(){
    return ICStats;
} 
    public static ArrayList getKEYS(){
        return KEYS;
    }
    public static void clearTextF(){
        infileTextF.setText("");
        jCheckBox.setSelected(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BreakButton;
    private javax.swing.JButton OpenButton;
    public static javax.swing.JTextField infileTextF;
    public static javax.swing.JCheckBox jCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
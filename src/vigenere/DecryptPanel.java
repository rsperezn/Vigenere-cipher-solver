package vigenere;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class DecryptPanel extends javax.swing.JPanel {
    //Global Variables
    final JFileChooser fc = new JFileChooser();
    File file;
    char[] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char[] alphabetMatrix;
    String inkey,fmtkey, ctextline,ciphertext;
    char[]plaintext;
    Hashtable<Character,Integer> charToint = new Hashtable<Character,Integer>();
    /**
     * Creates new form DecryptPanel
     */
    public DecryptPanel() {
        initComponents();
        initAlphabetMatrix();
        initHash();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        infileTextF = new javax.swing.JTextField();
        OpenButton = new javax.swing.JButton();
        keyTextF = new javax.swing.JTextField(50);
        jLabel2 = new javax.swing.JLabel();
        decryptButton = new javax.swing.JButton();
        jCheckBox = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(335, 260));

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

        keyTextF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyTextFActionPerformed(evt);
            }
        });

        jLabel2.setText("Key to Decrypt File:");

        decryptButton.setText("Decrypt");
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });

        jCheckBox.setText("Show files when done");
        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(decryptButton)
                        .addGap(42, 42, 42)
                        .addComponent(jCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(infileTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(OpenButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(keyTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(infileTextF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyTextF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptButton)
                    .addComponent(jCheckBox))
                .addContainerGap(154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void infileTextFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infileTextFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infileTextFActionPerformed
    private void initHash(){
        for(int i=0;i<alphabet.length;i++){
            charToint.put(alphabet[i], i);
        }
    }
    //initializes the Matrix to use
    private void initAlphabetMatrix(){
      int size= alphabet.length;  
      alphabetMatrix= new char[size];
     for(int i =0;i<size;i++){
       alphabetMatrix[i]=alphabet[(i)]; 
     }
    }
    //set the key the same lenght as the plaintext
    private String formatKey(int textl,String inkey){
        char[] result= new char[textl-1];
        int keyl=inkey.length();
        for(int i =0;i<result.length;i++){
            result[i]=inkey.charAt(i%keyl);
        }
        return (new String(result));   
    }
    
    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
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
                ciphertext=ciphertext.replaceAll("[^A-Za-z0-9  \n]", "");
                
                //update the preview window in MainInterface
                MainInterface.textAreaIN.setEditable(true);
                MainInterface.setInPrevTextA(ciphertext);
                
                
        }		
    }//GEN-LAST:event_OpenButtonActionPerformed

    private void keyTextFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyTextFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keyTextFActionPerformed

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
        if(keyTextF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Make sure to enter a key");
            return;
        }
        if((keyTextF.getText().indexOf(" "))!=-1){
            JOptionPane.showMessageDialog(null, "The key cannot contain spaces");
            return;
        }
        if((keyTextF.getText().matches(".*\\d.*"))){
            JOptionPane.showMessageDialog(null, "The key cannot contain numbers");
            return;
        }
        if((keyTextF.getText().length()>20)){
            JOptionPane.showMessageDialog(null, "Max key length  20 characters, current length:"+keyTextF.getText().length());
            return;
        }
        if(infileTextF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Make sure to choose a file");
            return;
        }
        
        int clength=ciphertext.length();
        inkey=keyTextF.getText();
        fmtkey=this.formatKey(clength,inkey);
        plaintext=new char[clength];
        ciphertext=ciphertext.toUpperCase();// so it matches with the entries in the alphabetMatrix
        fmtkey=fmtkey.toUpperCase();
        String name = file.getName();
        try{
            BufferedWriter writer = null;
                int pos = name.lastIndexOf(".");
                if (pos > 0) {
                     name = name.substring(0, pos);
                    }
            writer = new BufferedWriter( new FileWriter(name+"Decrypted.txt"));
            int keypos=0;
            for(int i=0;i<clength-1;i++ ){
                if ((!(ciphertext.charAt(i)==(' '))&&((!(ciphertext.charAt(i)==('\n'))))&&(!(Character.isDigit(ciphertext.charAt(i)))))){
                    int cipher=charToint.get(ciphertext.charAt(i));
                    int key= charToint.get(fmtkey.charAt(keypos));
                    int plain=(((cipher-key)%26)+26)%26;//to get a positive value of java modulus
                    plaintext[i]=alphabetMatrix[plain];
                    writer.write(plaintext[i]);
                    keypos++;}
                else if (Character.isDigit(ciphertext.charAt(i))){
                    plaintext[i]=ciphertext.charAt(i);
                    writer.write(plaintext[i]);
                }
                else if (ciphertext.charAt(i)==(' ')){
                     plaintext[i]=' ';
                     writer.write(plaintext[i]);}
                else if (ciphertext.charAt(i)==('\n')){
                     plaintext[i]='\n';
                     writer.newLine();}
            } writer.close();
        }
        catch(Exception e){e.printStackTrace();}
        String plaintextresult= new String(plaintext);
        System.out.println(plaintextresult);
        
        //update the corresponding outPrevTexArea in MainInterface
        MainInterface.textAreaOUT.setEditable(true);
        MainInterface.setOutPrevTextA(plaintextresult,0);
        
          if(jCheckBox.isSelected()){
            try {
            Runtime.getRuntime().exec("notepad "+name+"Decrypted.txt");
        } catch (IOException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        
        
        
        
    }//GEN-LAST:event_decryptButtonActionPerformed

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxActionPerformed
    public static void clearTextF(){
        infileTextF.setText("");
        keyTextF.setText("");
        jCheckBox.setSelected(false);
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OpenButton;
    private javax.swing.JButton decryptButton;
    public static javax.swing.JTextField infileTextF;
    public static javax.swing.JCheckBox jCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JTextField keyTextF;
    // End of variables declaration//GEN-END:variables
}
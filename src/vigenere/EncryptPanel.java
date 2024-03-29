package vigenere;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
public class EncryptPanel extends javax.swing.JPanel {
    //Global Variables
    final JFileChooser fc = new JFileChooser();
    File file;
    char[] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char[] alphabetMatrix;
    String inkey,fmtkey, ptextline,plaintext;
    char[]ciphertext;
    Hashtable<Character,Integer> charToint = new Hashtable<Character,Integer>();
    
    /**
     * Creates new form EncryptPanel
     */
    public EncryptPanel() {
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
        openButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        keyTextF = new javax.swing.JTextField(50);
        jCheckBox = new javax.swing.JCheckBox();
        encryptButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(350, 290));

        jLabel1.setText("Choose File :");

        infileTextF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infileTextFActionPerformed(evt);
            }
        });

        openButton.setText("...");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Choose Key:");

        keyTextF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyTextFActionPerformed(evt);
            }
        });

        jCheckBox.setText("Show files when done");
        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });

        encryptButton.setText("Encrypt");
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(encryptButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jCheckBox))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(keyTextF, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(infileTextF))
                                .addGap(18, 18, 18)
                                .addComponent(openButton)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(infileTextF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(keyTextF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encryptButton)
                    .addComponent(jCheckBox))
                .addContainerGap(172, Short.MAX_VALUE))
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
    
    private void keyTextFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyTextFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keyTextFActionPerformed

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        fc.addChoosableFileFilter(new TextFilter());
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal==JFileChooser.APPROVE_OPTION){
                file = fc.getSelectedFile();
                infileTextF.setText(file.getName());
                try{
                    StringBuilder sb= new StringBuilder();
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    ptextline=br.readLine();
                    while(ptextline!=null){
                        sb.append(ptextline);
                        sb.append("\n");
                        ptextline=br.readLine(); 
                    }
                    plaintext=sb.toString();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
               plaintext=plaintext.replaceAll("[^A-Za-z0-9  \n]", "");

                //update the preview window in MainInterface
                MainInterface.textAreaIN.setEditable(true);
                MainInterface.setInPrevTextA(plaintext);
        }      
    }//GEN-LAST:event_openButtonActionPerformed

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed
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
            JOptionPane.showMessageDialog(null, "Max key length  20 characters, current length: "+keyTextF.getText().length());
            return;
        }
        if(infileTextF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Make sure to choose a file");
            return;
        }
        
        int plength=plaintext.length();
        inkey=keyTextF.getText();
        fmtkey=this.formatKey(plength,inkey);
        ciphertext=new char[plength];
        plaintext=plaintext.toUpperCase();// so it matches with the entries in the alphabetMatrix
        fmtkey=fmtkey.toUpperCase();
        String name = file.getName();
        try{
            BufferedWriter writer = null;
                int pos = name.lastIndexOf(".");
                if (pos > 0) {
                     name = name.substring(0, pos);
                    }
            writer = new BufferedWriter( new FileWriter(name+"Encrypted.txt")); 
            int keypos=0;//avoid increasing counter for the key if space or line break is found
            for(int i=0;i<plength-1;i++ ){
                if ((!(plaintext.charAt(i)==(' '))&&((!(plaintext.charAt(i)==('\n'))))&&(!(Character.isDigit(plaintext.charAt(i)))))){
                    int plain=(charToint.get(plaintext.charAt(i)));
                    int key=(charToint.get(fmtkey.charAt(keypos)));
                    int cipher= (plain+key)%26;
                    ciphertext[i]= alphabetMatrix[cipher];
                    writer.write(ciphertext[i]);
                    keypos++;}
                else if (Character.isDigit(plaintext.charAt(i))){
                    ciphertext[i]=plaintext.charAt(i);
                    writer.write(ciphertext[i]);
                }
                else if (plaintext.charAt(i)==(' ')){
                     ciphertext[i]=' ';
                     writer.write(ciphertext[i]);}
                else if (plaintext.charAt(i)==('\n')){
                     ciphertext[i]='\n';
                     writer.newLine();}
            } writer.close();
        }
        catch(Exception e){e.printStackTrace();}
        String ciphertextresult = new String(ciphertext);
        System.out.println(ciphertextresult);        //String resultEncryption=new String(ciphertext); 
        //update the corresponding outPrevTexArea in MainInterface
        MainInterface.textAreaOUT.setEditable(true);
        MainInterface.setOutPrevTextA(ciphertextresult,0);
        if(jCheckBox.isSelected()){
            try {
            Runtime.getRuntime().exec("notepad "+name+"Encrypted.txt");
        } catch (IOException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
      
        
    }//GEN-LAST:event_encryptButtonActionPerformed

    //return plaintext to put it int the preview TextArea
    public String getText(){
        return plaintext;

    }
    public static void clearTextF(){
        infileTextF.setText("");
        keyTextF.setText("");
        jCheckBox.setSelected(false);

    }
    public static boolean getcheckBox(){
        return(jCheckBox.isSelected());
}
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton encryptButton;
    public static javax.swing.JTextField infileTextF;
    public static javax.swing.JCheckBox jCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JTextField keyTextF;
    private javax.swing.JButton openButton;
    // End of variables declaration//GEN-END:variables
}

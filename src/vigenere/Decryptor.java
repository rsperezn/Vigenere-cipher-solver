/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Hashtable;

/**
 *
 * @author Owner
 */
public class Decryptor {
    //Global Variables
    char[]plaintext;
    Hashtable<Character,Integer> charToint = new Hashtable<Character,Integer>();
    char[] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char[] alphabetMatrix;
    
    
    public String decrypt(String ciphertext, String guessedkey){
        initAlphabetMatrix();
        initHash();
        
   
        int clength=ciphertext.length();
        String fmtkey=this.formatKey(clength,guessedkey);
        plaintext=new char[clength];
        ciphertext=ciphertext.toUpperCase();// so it matches with the entries in the alphabetMatrix
        fmtkey=fmtkey.toUpperCase();
       // try{
            //BufferedWriter writer = null;
            //writer = new BufferedWriter( new FileWriter("VigenereBroken.txt"));
            int keypos=0;
            //writer.write("Result using key \" "+ guessedkey+ "\"");
            //writer.newLine();
            String title= ("Breaking Vinegere with key "+ "\""+ guessedkey + "\"" + "\r\n");
            for(int i=0;i<clength-1;i++ ){
                if ((!(ciphertext.charAt(i)==(' '))&&((!(ciphertext.charAt(i)==('\n')))))){
                    int cipher=charToint.get(ciphertext.charAt(i));
                    int key= charToint.get(fmtkey.charAt(keypos));
                    int plain=(((cipher-key)%26)+26)%26;//to get a positive value of java modulus
                    plaintext[i]=alphabetMatrix[plain];
                   // writer.write(plaintext[i]);
                    keypos++;}
                else if (ciphertext.charAt(i)==(' ')){
                     plaintext[i]=' ';
                     //writer.write(plaintext[i]);
                }
                else if (ciphertext.charAt(i)==('\n')){
                     plaintext[i]='~';//thi will be the space simbol
                     //writer.newLine();
                }
            //}//writer.newLine(); writer.close();
        }//}
        //catch(Exception e){e.printStackTrace();}    
        String plaintextresult= new String(plaintext);
        plaintextresult=plaintextresult.replaceAll("~", "\r\n");//put the breaklines for the broken encrypted text
        plaintextresult= "\r\n"+title +plaintextresult+"\r\n";
        System.out.println(plaintextresult);
        return plaintextresult;
    }
    
    private String formatKey(int textl,String inkey){
        char[] result= new char[textl-1];
        int keyl=inkey.length();
        for(int i =0;i<result.length;i++){
            result[i]=inkey.charAt(i%keyl);
        }
        return (new String(result));   
    }
    
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
}

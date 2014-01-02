/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Hashtable;

/**
 *
 * @author Santiago Perez
 */
class VigenereBreaker {
    //global variables
    ArrayList triplesPos;
    ArrayList triples;   
    ArrayList triplesDiff;
    char[] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    double[] expected= {0.08167,0.0492,0.02782,0.04253,0.12702,0.02228,0.02015,0.06094,0.06966,0.00153,0.00772,
                        0.04025,0.02406,0.06749,0.07507,0.01929,0.00095,0.05987,0.06327,0.09056,0.02758,0.00978,
                        0.02360,0.00150,0.01974,0.00074};
    Hashtable<Character,Integer> charToint = new Hashtable<Character,Integer>();
    //constructor
    public VigenereBreaker(String ciphertext){
        ciphertext=ciphertext.toUpperCase();
        ciphertext=ciphertext.replaceAll(" ","");
        ciphertext=ciphertext.replaceAll("\n","");
        this.initHash();    
    }
    private void initHash(){
        for(int i=0;i<alphabet.length;i++){
            charToint.put(alphabet[i], i);
        }
    }
    
    public char prevChar(char curr,int disp){
        int currint= charToint.get(curr);
        int prevint= ((currint-disp)+26)%26;
        return alphabet[prevint];
    }
    
    public int KasiskiTest(String ciphertext){
        triplesPos=new ArrayList<Integer>();
        triples=new ArrayList();
        triplesDiff=new ArrayList<Integer>();
        int kLengthguess;//the guess of the Key Lenght after Kasiskis test is done
         ciphertext=ciphertext.toUpperCase();
        String fmtcipher = ciphertext.replaceAll(" ", "");//get rid of spaces 
        fmtcipher=fmtcipher.replaceAll("\n","");//get rid of breaklines
        for(int i=0;i<fmtcipher.length()-3;i++){//read the string in groups of 3
            String target= fmtcipher.substring(i,i+3);
            String therest =fmtcipher.substring(i+3,fmtcipher.length());
            if(therest.contains(target)&& (!triples.contains(target))){
                this.getPatterpos(fmtcipher,target);
                triples.add(target);
            }
        }
        System.out.println(triplesDiff.toString());
        /*build array of the difference in possition of the repeated 
         triples that were found in the text*/
        int []diffArray=new int[triplesDiff.size()];
        for(int i=0;i<diffArray.length;i++){
            diffArray[i]=(int)triplesDiff.get(i);     
        }
        kLengthguess=this.gcdOfArray(diffArray);
        System.out.println("The lenght of the key guessed is :"+kLengthguess);
        //make a second check to make sure the key lenght is correct
        /*for(int i=1; i<= 10;i++){
            System.out.print("For key length:" + i);
            System.out.print(this.confirmKeyLength(fmtcipher, i));
            System.out.println();
        }*/
        return kLengthguess;
        
        /*String[] test = new String[1];
        test[0]="aoljhlzhyjpwolypzvulvmaollhysplzaruvduhukzptwslzajpwolyzpapzhafwlvmzbizapabapvujpwolypudopjolhjoslaalypuaolwshpualeapzzopmalkhjlyahpuubtilyvmwshjlzkvduaolhswohila".toUpperCase();
        this.guessKey(test);*/
        
    }
    // find the starting possition of the repeated triples
    public void getPatterpos(String ciphertext,String tofind){
        ciphertext=ciphertext.toUpperCase();
        ciphertext=ciphertext.replaceAll(" ","");
        ciphertext=ciphertext.replaceAll("\n","");
        ArrayList al= new ArrayList<Integer>();
        Pattern p = Pattern.compile(tofind);
        Matcher m = p.matcher(ciphertext);
        int count = 0;
        while (m.find()){
            count +=1;
            System.out.print("Start index: " + (m.start()+1));
            al.add(m.start() +1);
            System.out.print(" End index: " + (m.end()+1));
            System.out.println(" Found: " + m.group());
        }
        System.out.println("count:"+count);
        this.settriplesDiff(al);
    }
    
    /*update Global Variable to contain the difference in distances
     for each corresponding set of possitions of their corresponding pattern
     */
    public void settriplesDiff(ArrayList startpos){
        int size=startpos.size();
        int firsthalf=size-1 ;//the initial round
        int dist= size-2;
        int secondhalf=(dist*(dist+1))/2;//second half
        int total= firsthalf+secondhalf;
        int[] result= new int[total];
        //filing in the first half
        for(int i=0;i<size-1;i++){
                result[i]= (int) startpos.get(i+1)-(int)startpos.get(i);
                triplesDiff.add(result[i]);
        }
        //filing in the second half
        if(total>=3){
           int pos=2;
           int index=firsthalf;
           for(int i=0;i<dist;i++){
               for(int j =0; j<pos-1;j++){
                   result[index]=(int) startpos.get(pos)-(int)startpos.get(j);
                   triplesDiff.add(result[index]);
                   index++;
               }
               pos++;
           }
        } 
   }
    
    //recursively find the gcd of two numbers
    public int gcd (int a, int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }    
   
    // loop though list of distances and get the gcd
    public int gcdOfArray(int[] intarray){
        int size=  intarray.length;
        if(size<2){
            return 0;
        }
        int firstgcd= this.gcd(intarray[0],intarray[1]);
        //recursively find gdc with the already found first gcd
        for(int i=0;i<size;i++){
            firstgcd=this.gcd(firstgcd,intarray[i]);
        }
        return firstgcd;
    }
    
    public String[] splitCiphertext(String ciphertext, int klengthguess){
        ciphertext=ciphertext.toUpperCase();
        ciphertext=ciphertext.replaceAll(" ","");
        ciphertext=ciphertext.replaceAll("\n", "");
        String[] subStrings = new String[klengthguess];
        //initialize empty groups of stings
        for(int k=0;k<klengthguess;k++){
            subStrings[k]="";  
        }
        //create the corresponding substrings
        for(int i=0;i<klengthguess;i++){
            for(int j=0;j<ciphertext.length()-klengthguess;j=j+klengthguess){
                subStrings[i]=subStrings[i].concat(Character.toString(ciphertext.charAt(i+j)));           
            }
        }
        return subStrings;
    }
    
    //use Friedmans method to calculate the Index of Coincidence
    
    public float confirmKeyLength(String cipher, int lengthguess){
         cipher=cipher.toUpperCase();
        cipher=cipher.replaceAll(" ","");
        cipher=cipher.replaceAll("\n","");
        String[] splits;//the splited ciphertext
        int clength=cipher.length();//ciphertext length
        splits=this.splitCiphertext(cipher, lengthguess);
        int totsubstring=splits.length; //total number of substrings
        float[] ICs= new float[lengthguess];
        int[][]charCount=new int[totsubstring][alphabet.length];//to store the counts of  the characters for each substring
        //count characters for each of the totsubstrings
        for(int i=0;i<totsubstring;i++){
            for(int j=0;j<splits[i].length();j++){
                charCount[i][charToint.get(splits[i].charAt(j))]++;
            }
        }
        //print them
       /*for(int i=0;i<totsubstring;i++){
            for(int j=0;j<alphabet.length;j++){
                
                System.out.print(charCount[i][j]);
            }
            System.out.println();
       }*/
        //caculate the summations
        int[] sum= new int[totsubstring];
        
        for(int i=0;i<totsubstring;i++){
            for(int j=0;j<alphabet.length;j++){
                sum[i]=sum[i]+charCount[i][j]*(charCount[i][j]-1);
            }
        }
        //divide by the constant which is the total length of the ciphertext
        float sum2=0;
        for(int k =0;k<totsubstring;k++){
            ICs[k]=sum[k]/(float)(splits[k].length()*(splits[k].length()-1));
            //System.out.println("IC"+k+ ": "+ ICs[k]);
            sum2=sum2+ICs[k];                    
        }
        float avg =(float)sum2/totsubstring;
        //System.out.println("With an average of:"+avg);
        System.out.println();
        return avg;  
    }//end confirmKeyLength
    
    public String[] guessKey(String splits[]){
        //for each of the substrings shift them and calculate the chisquare
        int totsubstring=splits.length;
        String theguess1="";
        String theguess2="";
        int[] keypos=new int[totsubstring*2];
        String currShift="";
        double[]buffer=new double[alphabet.length];
        for(int i=0;i<totsubstring;i++){//for each substring
            for(int j=0;j<alphabet.length;j++){//do 26 times
                for(int k=0;k<splits[i].length();k++){//for all the ciphertext
                    char curr=splits[i].charAt(k);
                    char prevchar=this.prevChar(curr,j);
                    currShift=currShift.concat(Character.toString(prevchar));
                }
                buffer[j]=this.Chisqr(currShift);
                currShift="";//restart and then reuse it */
            }
            keypos[i*2]=this.getMinpos(buffer)[0];//flush the buffer to the ArrayList
            keypos[i*2+1]=this.getMinpos(buffer)[1];
            buffer= new double[alphabet.length];//empty the buffer
        }
        //since it may be possible that the results of Chisquare give some 
        //erroneous shifts to be made to the key. Get the 2 smallest values
        
        for(int k =0; k<keypos.length/2;k++){
            theguess1=theguess1.concat(Character.toString(alphabet[keypos[k*2]]));
            theguess2=theguess2.concat(Character.toString(alphabet[keypos[2*k+1]]));
        }
        System.out.println("The guessed key1: "+ theguess1);
        System.out.println("The guessed key2: "+ theguess2);
        
        String[] theGuesses= new String[2];
        theGuesses[0]=theguess1;
        theGuesses[1]=theguess2;
        return theGuesses;
        
        
        
        
        
    
    }
    //calculate the chisquare of a given string.
    //later we will have to find the mininum of every shift
    public double Chisqr(String currShift){
        int[] charCount=new int [alphabet.length];
        for(int i =0; i<currShift.length();i++){
            charCount[charToint.get(currShift.charAt(i))]++;
        }
        double sum=0;
        int length=currShift.length();
        for(int j =0;j<alphabet.length;j++){
            sum= sum +Math.pow((charCount[j]- length*expected[j]), 2)/(double)(length*expected[j]);
        }  
        return sum;
    }
    
    //get the possition of the first character that must for the
    //keyword for th ciphertext
    public int[] getMinpos(double[] chisqr){
        int min1=0;int min2=0; //since we care about the possition that'll require an alphabet with >100chars
        for(int i=0; i<chisqr.length;i++){
            for(int j =0; j<alphabet.length;j++){
                if(chisqr[j] < chisqr[min1]) {
                    min1 = j;
                }
                
            }
            for(int k =0; k<alphabet.length;k++){
                if(chisqr[k] < chisqr[min2] && (k!=min1)) {
                    min2 = k;
                }    
            }
        }
        int[]result={min1,min2};
        return result;
    
    
    }  
    
    
    
    
    
    
    
   
    
    
        
}//end classs
    
    
    
    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simcommunity;

/**
 *
 * @author Raffaele
 */
public final class GlobalVar {

    //variabili per evitare la sovrapposizione
    public static int Raggio = 15;                //raggio dell'oggetto persona
    public static int Scostamento = Raggio * 2;     //scostamento tra due oggetti persona

    public static int dimensionecom = 20;    	   // numero individui
    public static int numcollegamenti = 20;     // numero di collegamenti
    public static int numERE = 20;          	 // numero di ere (cicli)
    public static int ereEffettuate = 0;        //ere effettivamente fatte
    public static int videoW = 1024; 
    public static int videoH = 768;
    
    public static int dimDNA = 9;         	 // dimensione del DNA --- DEVE ESSERE SEMPRE MULTIPLO DI 3	
    public static int[][] vicinato;
    
    public static double omoSoglia = 0.2;        //soglia limite
    
    
   //public static Community community = new Community(GlobalVar.dimensionecom, GlobalVar.numcollegamenti, GlobalVar.dimDNA, 1024, 768);
}

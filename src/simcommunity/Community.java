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
import java.util.ArrayList;

public class Community {

    public class Collegamento {

        public int start;
        public int end;

    }
    //parametri della comunità

    private int communitySize = 100;
    private int linkNumber = 3;

    //dati della comunità
    private Collegamento[] link; //collegamenti
    private Persona[] communityArray; //numero di persone della comunità 

    private int[] omoarray; 		//array dell'andamento dell'omogeneizzazione 

    public Community(int comSize, int numcollegamenti, int dimDNA, int mX, int mY) {
			// setup della comunità

        communitySize = comSize;
        linkNumber = numcollegamenti;

        communityArray = new Persona[communitySize];
        link = new Collegamento[numcollegamenti];

        Persona appo;

        int i;
        int j;
        boolean sovrapposto;

        communityArray[0] = new Persona("Persona0", 0, dimDNA, mX, mY);

        //System.out.print("\nRAGGIO+SCOSTAMENTO");
        //System.out.print(GlobalVar.Raggio + GlobalVar.Scostamento);

        //creazione di un array di persone/nodi
        for (i = 1; i < communitySize; i++) {
            //ciclare la creazione della persona fino a che la nuova persona non si sovrappone con le esistenti				
            do {

					//System.out.print("\ni");
                //System.out.print(i);
                appo = new Persona("Persona" + i, i, dimDNA, mX, mY);

                /*System.out.print("\nappo X");
                 System.out.print(appo.getX());
                 System.out.print("\nappo Y");
                 System.out.print(appo.getY());*/
                j = 0;
                sovrapposto = false;
                while ((j < i) && (sovrapposto == false)) {

                    /*System.out.print("\nj");
                     System.out.print(j);
							
                     System.out.print("\ncoomunity X");
                     System.out.print(communityArray[j].getX());
                     System.out.print("\ncoomunity Y");
                     System.out.print(communityArray[j].getY());*/
                    if (((Math.abs(communityArray[j].getX() - appo.getX()) < (GlobalVar.Raggio + GlobalVar.Scostamento)) && (Math.abs(communityArray[j].getY() - appo.getY()) < (GlobalVar.Raggio + GlobalVar.Scostamento)))) {
                        sovrapposto = true;
                    }

                    j++;

                }
            } while (sovrapposto); // evita sovrapposizione			

            communityArray[i] = new Persona(appo);
				//communityArray[i]=appo;

        }

			//genera collegamenti casuali tra le persone della comunità
			//TO-DO: evitare collegamenti doppi/tripli/ecc ...
			//USARE: funzioni avanzate array
        //http://help.adobe.com/en_US/FlashPlatform/reference/actionscript/3/Array.html#length
        for (i = 0; i < linkNumber; i++) {
            link[i] = new Collegamento();

            do {

                link[i].start = (int) Math.round(0 + (communitySize-1) * Math.random());
                link[i].end = (int) Math.round(0 + (communitySize-1) * Math.random());

            } while (link[i].start == link[i].end); // evita collegamenti con sè stessi				

        }

    }

    //conta i bit = 1 di posizione K
    public int contabit1posizionek(int k) {

        int i;
        int quanti1 = 0;

        for (i = 0; i < communitySize; i++) {

            Persona mypersona = this.getPersona(i);
            if (mypersona.getBitDna(k) == 1) {
                quanti1++;
            }
        }

        return quanti1;

    }

    public double omogeneitabitposizionek(int k) {

        double omo;
                
        omo = Math.abs((double)communitySize - 2.0 * (double)contabit1posizionek(k)) / (double)communitySize;

			//System.out.print("\nnumero bit pari a 1 in posizione "+k);
        //System.out.print(contabit1posizionek(k));
			//System.out.print("\ncom size "+communitySize);
        //System.out.print("\ndivisione "+(contabit1posizionek(k) / communitySize));
			//System.out.print("\ngrado omo "+omo);			
			//return    (Math.abs((contabit1posizionek(k) / communitySize)-0.5)*2);
        
        /*System.out.print("[DEBUG] bit 1 in posizione "+k+" sono "+contabit1posizionek(k)+"\n");
        System.out.print("[DEBUG] omo della posizione "+k+" e' "+omo+"\n");
        System.out.print("[DEBUG] communitySize "+communitySize+"\n");*/
        
        return (omo);

        /*
			
         |K-2N1|/K

         dove K = numero bit
         N1 = numero di bit pari a 1

         */
    }
    
        public double omogeneitacommunity() {

        int i;
        double somma = 0;

        for (i = 0; i < GlobalVar.dimDNA; i++) {
            
            //System.out.print("\n[DEBUG] omogenita' del bit posizione "+i+" vale "+omogeneitabitposizionek(i)+"\n");
            somma = somma + omogeneitabitposizionek(i);

        }

			//omoarray.push(somma / GlobalVar.dimDNA);
        return somma / GlobalVar.dimDNA;

    }

    public void stampaarrayomogeneitacommunity() {

        int i;

        System.out.print("\n============================\n");
        System.out.print("ARRAY Omogeneità\n");

        for (i = 0; i < omoarray.length; i++) {

            System.out.print(" " + omoarray[i]);
        }
        System.out.print("\n============================\n");

    }



    //restituisce un Array dei vicini del nodo/persona ID
    public int[] vicini(int id) {

        int[] neighbors; //elenco (id) dei vicini
        ArrayList apponeighbors = new ArrayList();

        int i;
        int j;

        for (i = 0; i < linkNumber; i++) {
            if (link[i].start == id) {
                apponeighbors.add(link[i].end);

            }

            if (link[i].end == id) {
                apponeighbors.add(link[i].start);

            }

        }

        neighbors = new int[apponeighbors.size()];

        for (i = 0; i < apponeighbors.size(); i++) {
            neighbors[i] = (int) apponeighbors.get(i);
        }

        return neighbors;

    }

		//VERIFICARE SE TUTTA COMUNITA' OMOGENEA
    //funzione booleana che verifica se SOLO quelli collegati sono diventati omogenei
		//prende la persona/nodo e cambia il suo bit in base ai suoi vicini
    //comunica il NUOVO valore di questo bit
    public void cambia(int id, int bit) {

        int[] myVicini = this.vicini(id);
        int i;

        int quanti0 = 0;
        int quanti1 = 0;

        if (myVicini.length != 0) {

            for (i = 0; i < myVicini.length; i++) {
                //prendo la persona vicina a quella in esame
                Persona mypersona = this.getPersona(myVicini[i]);

                //analizzo il bit di interesse del suo dna
                if (mypersona.getBitDna(bit) == 0) {
                    quanti0++;
                } else {
                    quanti1++;
                }

            }

            if (quanti0 > quanti1) {

					//posizione nell'array coincide con l'id della persona??? VERIFICARE
                this.communityArray[id].setBitDna(bit, 0);
                //System.out.print("\nCambio il bit in 0");

            } else {

                //System.out.print("\nCambio il bit in 1");
                this.communityArray[id].setBitDna(bit, 1);

            }
        }
    }

    public Persona getPersona(int posizione) {

        return (communityArray[posizione]);
    }

    public Collegamento[] getLink() {
        return link;
    }

    //TO-DO
    public boolean isOmogenea() {
        return false;
    }

}

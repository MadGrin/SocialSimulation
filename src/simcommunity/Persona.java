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
public class Persona {

    private int[] dna;

    private String name; //eliminabile ...
    private int id;     //id univoco della persona/nodo
    private int xPos;     //coordinate X
    private int yPos;     //coordinate Y
    private int Xmin = 60;//60 px in alto a sx dell'area
    private int Xmax = 950;//1000 px in basso a dx dell'area
    private int Ymin = 60;//30 px in alto a sx dell'area
    private int Ymax = 500;//700 px in basso a dx dell'area

    private int max = 2; //max valore del dna --> valore escluso
    private int min = 0; //min valore del dna --> valore incluso

    private int dnaSize = 10; //dimensione del dna

    /*public function Persona(d:Array,l:Array,v:String){
				
     this.dna=d;
     this.link=l;
     this.name=v;
     }*/
    public Persona(Persona p) {

        name = p.getName();
        int i;

        dnaSize = p.getDnaSize();

        dna = new int[dnaSize];

        dna = p.getDna();

        id = p.getID();

        xPos = p.getX();
        yPos = p.getY();

    }

    public Persona(String n, int identifier, int dimDNA, int mX, int mY) {

        this.name = n;
        int i;

        dnaSize = dimDNA;

        dna = new int[dnaSize];

        //creazione del dna (casuale)
        for (i = 0; i < dnaSize; i++) {
            dna[i] = (int) Math.floor((float) min + (float) (max - min) * Math.random()); //verificare!!!

        }

        id = identifier;

        //collocazione della persona sul video
				/*xPos=Math.floor(mX * Math.random());
         yPos=Math.floor(mY * Math.random());*/
        xPos = getRandIntX(Xmin, Xmax);
        yPos = getRandIntY(Ymin, Ymax);

    }

    public int getRandIntX(int X_min, int X_max) {
        return X_min + (int) Math.floor(Math.random() * (X_max - X_min));
    }

    public int getRandIntY(int Y_min, int Y_max) {
        return Y_min + (int) Math.floor(Math.random() * (Y_max - Y_min));
    }

    public int[] getDna() {

        return (dna);
    }

    //COLORI
    public int getR() {

        int quanti1 = 0;
        int R;
        int i;

        for (i = 0; i < dnaSize / 3; i++) {
            if (dna[i] == 1) {
                quanti1++;
            }

        }

        R = (quanti1 * 255) / (dnaSize / 3);

        return (R);
    }

    public int getG() {

        int quanti1 = 0;
        int G;
        int i;

        for (i = dnaSize / 3; i < 2 * dnaSize / 3; i++) {
            if (dna[i] == 1) {
                quanti1++;
            }

        }

        G = (quanti1 * 255) / (dnaSize / 3);

        return (G);
    }

    public int getB() {

        int quanti1 = 0;
        int B;
        int i;

        for (i = 2 * dnaSize / 3; i < dnaSize; i++) {
            if (dna[i] == 1) {
                quanti1++;
            }

        }

        B = (quanti1 * 255) / (dnaSize / 3);

        return (B);
    }

    public int getBitDna(int n) {

        return (dna[n]);
    }

    public int getX() {

        return (xPos);
    }

    public int getY() {

        return (yPos);
    }

    public void setBitDna(int n, int val) {

        dna[n] = val;
    }

    public int getID() {

        return (id);
    }

    public String getName() {

        return (name);
    }

    public void setName(String NewName) {

        name = NewName;
    }

    public void setDnaSize(int NewSize) {

        dnaSize = NewSize;
    }

    public int getDnaSize() {

        return dnaSize;
    }

    public void setDna(int[] Dna) {
        this.dna = Dna;
    }

}

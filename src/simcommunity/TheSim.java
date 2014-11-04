/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simcommunity;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

public class TheSim {

    public static void TraceDNA(int[] dna) {

        for (int i = 0; i < GlobalVar.dimDNA; i++) {
            System.out.print(dna[i]);
        }

        System.out.println();
    }

    public static void TracePersona(Persona p) {
        System.out.println("ID " + p.getID());
        System.out.println("Rosso " + p.getR());
        System.out.println("Verde " + p.getG());
        System.out.println("Blu " + p.getB());
        int[] dna = p.getDna();
        System.out.println();

        System.out.println("DNA");
        TraceDNA(dna);

        System.out.println();
    }

    public static void TraceCommunity(Community com) {
        Persona p;

        for (int i = 0; i < GlobalVar.dimensionecom; i++) {
            p = com.getPersona(i);
            TracePersona(p);

        }
    }

    public static void disegnapallocco(final Community c, final int[][] vicinato, final String titleFrame) {

        //Graph<String, String> g = new SparseMultigraph<String, String>();
        //StaticLayout<String, String> layout = new StaticLayout<String, String>(g);
        final DirectedSparseGraph<String, String> g = new DirectedSparseGraph<>();
        final Layout<String, String> layout = new StaticLayout<>(g);

        int lung, R, G, B;
        int[] vicini;
        String sR, sG, sB, nomeV, nomeE;
        String[] test2 = new String[GlobalVar.dimensionecom];

        for (int i = 0; i < GlobalVar.dimensionecom; i++) {
            R = c.getPersona(i).getR();
            G = c.getPersona(i).getG();
            B = c.getPersona(i).getB();

            if (R > 255) {
                R = 255;
            }
            if (G > 255) {
                G = 255;
            }
            if (B > 255) {
                B = 255;
            }

            sR = Integer.toString(R);
            sG = Integer.toString(G);
            sB = Integer.toString(B);

            if (sR.length() == 2) {
                sR = "0" + sR;
            }
            if (sR.length() == 1) {
                sR = "00" + sR;
            }
            if (sG.length() == 2) {
                sG = "0" + sG;
            }
            if (sG.length() == 1) {
                sG = "00" + sG;
            }
            if (sB.length() == 2) {
                sB = "0" + sB;
            }
            if (sB.length() == 1) {
                sB = "00" + sB;
            }

            //nome=Integer.toString(R)+"a"+Integer.toString(G)+"a"+Integer.toString(B)+"a"+Integer.toString(i);
            test2[i] = sR + sG + sB + Integer.toString(i);
        }

        for (int i = 0; i < GlobalVar.dimensionecom; i++) {
            R = c.getPersona(i).getR();
            G = c.getPersona(i).getG();
            B = c.getPersona(i).getB();

            if (R > 255) {
                R = 255;
            }
            if (G > 255) {
                G = 255;
            }
            if (B > 255) {
                B = 255;
            }

            sR = Integer.toString(R);
            sG = Integer.toString(G);
            sB = Integer.toString(B);

            if (sR.length() == 2) {
                sR = "0" + sR;
            }
            if (sR.length() == 1) {
                sR = "00" + sR;
            }
            if (sG.length() == 2) {
                sG = "0" + sG;
            }
            if (sG.length() == 1) {
                sG = "00" + sG;
            }
            if (sB.length() == 2) {
                sB = "0" + sB;
            }
            if (sB.length() == 1) {
                sB = "00" + sB;
            }

            nomeV = sR + sG + sB;

            g.addVertex(nomeV + Integer.toString(i));

            Point2D point = new Point2D.Double(c.getPersona(i).getX(), c.getPersona(i).getY());
            layout.setLocation(nomeV + Integer.toString(i), point);

            //layout.setLocation(i, c.getPersona(i).getX(), c.getPersona(i).getY()); //setto la posizione senza Point2D solo con Static Layout
            vicini = vicinato[i];
            lung = vicini.length;

            for (int j = 0; j < lung; j++) {
                if (vicini[j] == -1) {
                } else {
                    nomeE = "Edge" + Integer.toString(i) + Integer.toString(j);
                    g.addEdge(nomeE, nomeV + Integer.toString(i), test2[vicini[j]]);

                }
            }

        }

        layout.setSize(new Dimension(GlobalVar.videoW, GlobalVar.videoH));

        final VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout);

        /*
         vv.getRenderContext().setVertexLabelTransformer(transformer);
         transformer = new Transformer<String, String>() {
         @Override public String transform(String arg0) { return arg0; }
         };
         vv.getRenderContext().setEdgeLabelTransformer(transformer);*/
        vv.setPreferredSize(new Dimension(1024, 768));

        // The following code adds capability for mouse picking of vertices/edges. Vertices can even be moved!
        final DefaultModalGraphMouse<String, Number> graphMouse = new DefaultModalGraphMouse<>();
        vv.setGraphMouse(graphMouse);
        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);

        Transformer<String, String> transformer = new Transformer<String, String>() {
            @Override
            public String transform(String arg0) {
                return arg0;
            }
        };
        vv.getRenderer().setVertexRenderer(new MyRenderer(c));

        JFrame frame = new JFrame();
        frame.setTitle(titleFrame);
        frame.getContentPane().add(vv);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public static void calcoloVicinato(Community c) {
        int[][] vicinato;
        int[] ll = new int[GlobalVar.dimensionecom];
        int tempV, k;

        vicinato = new int[GlobalVar.dimensionecom][];
        GlobalVar.vicinato = new int[GlobalVar.dimensionecom][];

        for (k = 0; k < GlobalVar.dimensionecom; k++) {
            vicinato[k] = c.vicini(k);
            ll[k] = c.vicini(k).length;
            GlobalVar.vicinato[k] = c.vicini(k);

        }

        k = 0;

        for (int l1 = 0; l1 < GlobalVar.dimensionecom; l1++) {
            for (int l2 = 0; l2 < ll[l1]; l2++) {
                tempV = GlobalVar.vicinato[l1][l2];

                if (tempV > l1) {
                    if (tempV == -1) {
                    } else {
                        for (int l3 = 0; l3 < ll[tempV]; l3++) {
                            if (GlobalVar.vicinato[tempV][l3] == l1) {
                                GlobalVar.vicinato[tempV][l3] = -1;
                            } else {
                                GlobalVar.vicinato[l1][l2] = vicinato[l1][l2];
                            }
                        }
                    }
                }

            }
        }

    }
    
    public static void main(String[] args) {
        
        Scanner valore = new Scanner(System.in);
        
        System.out.print("\n===================================");
        System.out.print("\nSIMULAZIONE COMUNITA'");
        System.out.print("\nPARISI-NICOLUSSI-SITA'");
        System.out.print("\nemail: r.nicolussi@gmail.com");
        System.out.print("\n===================================");
        
        System.out.print("\n\nChe tipo di simulazione vuoi eseguire? \n1) interattiva\n"
                + "2) batch con individui fissi, connessioni variabili\n"
                + "3) batch con connessioni fisse,  individui variabili\n"
                + "4) batch completo");
        int scelta = valore.nextInt();
        
        switch (scelta) {
            
        case 1:  interactiveSim(); break;
        case 2:  batchSim_fixedindividui(); break;
        case 3:  batchSim_fixedconnessioni(); break;
        case 4:  batchSim_completa(); break;
        }
        
    }
    
    public static void batchSim_fixedconnessioni() {
                
        Scanner valore = new Scanner(System.in).useLocale(Locale.US);
        
        double o; //omogeneità
        
        System.out.print("\n===================================");
        System.out.print("\nSIMULAZIONE BATCH con individui crescenti e numero di connessioni fisse\n");
        System.out.print("\n===================================");
        
        System.out.print("\n\nDimensione della comunità iniziale ");
        int comIniziali = valore.nextInt();
        
        System.out.print("Dimensione della comunità finale ");
        int comFinale = valore.nextInt();
        
        System.out.print("Incremento comunità ");
        int incrementocomunita = valore.nextInt();

        System.out.print("Numero collegamenti ");
        GlobalVar.numcollegamenti = valore.nextInt();
      
        System.out.print("Dimensione del DNA ");
        GlobalVar.dimDNA = valore.nextInt();

        System.out.print("Quante ere ");
        GlobalVar.numERE = valore.nextInt();
        
        System.out.print("Soglia di omogeneità ");
        GlobalVar.omoSoglia = valore.nextDouble();
        
        System.out.println("\nINDIVIDUI\tCONNESSIONI\tCICLI\tOMOGENEITÀ");
        
        for (int i=comIniziali; i<comFinale; i=i+incrementocomunita)
        {            
            GlobalVar.dimensionecom=i;
            o=ciclocomunita();
            
            System.out.println("\n"+i+"\t\t"+GlobalVar.numcollegamenti+"\t\t"+GlobalVar.ereEffettuate+"\t\t"+o);
            
        }
        
        
    }
    
    
    public static void batchSim_completa() {
                
        Scanner valore = new Scanner(System.in).useLocale(Locale.US);
        
        double o; //omogeneità
        
        System.out.print("\n===================================");
        System.out.print("\nSIMULAZIONE BATCH completa\n");
        System.out.print("\n===================================");
        
        System.out.print("\n\nNumero individui iniziale ");
        int comIniziali = valore.nextInt();
        
        System.out.print("Numero individui  finale ");
        int comFinale = valore.nextInt();
        
        System.out.print("Incremento individui ");
        int incrementocomunita = valore.nextInt();

        System.out.print("Numero collegamenti iniziale ");
        int colIniziali = valore.nextInt();
        
        System.out.print("Numero collegamenti finale ");
        int colFinali = valore.nextInt();
        
        System.out.print("Incremento collegamenti ");
        int incrementocollegamenti = valore.nextInt();
      
        System.out.print("Dimensione del DNA ");
        GlobalVar.dimDNA = valore.nextInt();

        System.out.print("Soglia ere massima ");
        GlobalVar.numERE = valore.nextInt();
        
        System.out.print("Soglia di omogeneità ");
        GlobalVar.omoSoglia = valore.nextDouble();
        
        //modifica
        //System.out.println("\nind\tcon\tere\tomo");
        
        //Riga degli individui
        for (int i=comIniziali; i<=comFinale; i=i+incrementocomunita) System.out.print("\t"+i);
        System.out.println();
        
        for (int j=colIniziali; j<=colFinali; j=j+incrementocollegamenti)
        {
            System.out.print(j+"\t");
            for (int i=comIniziali; i<=comFinale; i=i+incrementocomunita)
            {  GlobalVar.dimensionecom=i;
               GlobalVar.numcollegamenti=j;
                        
               o=ciclocomunita();
                        
               System.out.print(GlobalVar.ereEffettuate+"\t");
            }
            System.out.println();
            
            
        }
        
        
    }
    
    public static double ciclocomunita() {
    //Community c = new Community(GlobalVar.dimensionecom, GlobalVar.numcollegamenti, GlobalVar.dimDNA, 1024, 768);
        Community c = new Community(GlobalVar.dimensionecom, GlobalVar.numcollegamenti, GlobalVar.dimDNA, GlobalVar.videoW, GlobalVar.videoH);;
        Community cI = c;

        calcoloVicinato(c);

        /*disegnapallocco(cI, GlobalVar.vicinato, "Prima");
        Scanner sc = new Scanner(System.in);
        System.out.print("Premi invio per continuare. ");
        sc.nextLine();*/

        //TraceCommunity(c);

        int era = 0;
        int idPersona;
        int idBit;
        //Omogeneità iniziale
        double omo = c.omogeneitacommunity();

        //System.out.println("Omogeneità INIZIALE" + omo);

        //disegnare collegamenti TO DO
        while ((era < GlobalVar.numERE) && (omo<GlobalVar.omoSoglia)) { 
            //System.out.print("\nERA " + (era + 1));

            //prendo una persona a caso
            idPersona = (int) Math.floor(0 + (GlobalVar.dimensionecom) * Math.random());

            //prendo un bit a caso
            idBit = (int) Math.floor(0 + (GlobalVar.dimDNA) * Math.random());

            //System.out.print("\nAggiorno la persona " + idPersona + " e il bit " + idBit);

            //System.out.print("\nPrima");

            //TraceDNA(c.getPersona(idPersona).getDna());

            c.cambia(idPersona, idBit);

            //System.out.print("\nDopo");
            //TraceDNA(c.getPersona(idPersona).getDna());
            
            omo = c.omogeneitacommunity();
            /*
            System.out.print("\n===================================");
            System.out.print("\nGrado di omogeneita': " + omo);
            System.out.print("\n===================================");
*/
            era++;
        }

        calcoloVicinato(c);
        
        GlobalVar.ereEffettuate=era;
        
        return(omo);
        
    } 
    
    public static void batchSim_fixedindividui() {
                
        Scanner valore = new Scanner(System.in).useLocale(Locale.US);
        
        double o; //omogeneità
        
        System.out.print("\n===================================");
        System.out.print("\nSIMULAZIONE BATCH con connessioni crescenti e numero di individui fissi\n");
        System.out.print("\n===================================");
        
        System.out.print("\n\nDimensione della comunità (fisso) ");
        GlobalVar.dimensionecom = valore.nextInt();

        System.out.print("Numero collegamenti iniziale ");
        int colIniziali = valore.nextInt();
        
        System.out.print("Numero collegamenti finale ");
        int colFinali = valore.nextInt();
        
        System.out.print("Incremento collegamenti ");
        int incrementocollegamenti = valore.nextInt();

        System.out.print("Dimensione del DNA ");
        GlobalVar.dimDNA = valore.nextInt();

        System.out.print("Quante ere ");
        GlobalVar.numERE = valore.nextInt();
        
        System.out.print("Soglia di omogeneità ");
        GlobalVar.omoSoglia = valore.nextDouble();
        
        System.out.println("\nINDIVIDUI\tCONNESSIONI\tCICLI\tOMOGENEITÀ");
        
        for (int i=colIniziali; i<colFinali; i=i+incrementocollegamenti)
        {            
            GlobalVar.numcollegamenti=i;
            o=ciclocomunita();
            
            System.out.println("\n"+GlobalVar.dimensionecom+"\t\t"+i+"\t\t"+GlobalVar.ereEffettuate+"\t\t"+o);
            
        }
        
        
    }

    public static void interactiveSim() {

        Scanner valore = new Scanner(System.in);
        /*int[] DNA;
        
       
         Persona p = new Persona("Mario", 0, 9, 1024, 768);

         DNA = p.getDna();

         System.out.print("Stampo i dati di UNA persona");
         for (int i = 0; i < 9; i++) {
         System.out.print(DNA[i]);
         }
         System.out.println();*/

        System.out.print("\n===================================");
        System.out.print("\nCREAZIONE DELLA COMMUNITY");
        System.out.print("\n===================================");

        System.out.print("\n\nDimensione della comunità ");
        GlobalVar.dimensionecom = valore.nextInt();

        System.out.print("Numero collegamenti ");
        GlobalVar.numcollegamenti = valore.nextInt();

        System.out.print("Dimensione del DNA ");
        GlobalVar.dimDNA = valore.nextInt();

        System.out.print("Quante ere ");
        GlobalVar.numERE = valore.nextInt();
        
        System.out.print("Soglia di omogeneità ");
        GlobalVar.omoSoglia = valore.nextInt();

        //Community c = new Community(GlobalVar.dimensionecom, GlobalVar.numcollegamenti, GlobalVar.dimDNA, 1024, 768);
        Community c = new Community(GlobalVar.dimensionecom, GlobalVar.numcollegamenti, GlobalVar.dimDNA, GlobalVar.videoW, GlobalVar.videoH);;
        Community cI = c;

        calcoloVicinato(c);

        disegnapallocco(cI, GlobalVar.vicinato, "Prima");
        Scanner sc = new Scanner(System.in);
        System.out.print("Premi invio per continuare. ");
        sc.nextLine();

        TraceCommunity(c);

        int era = 0;
        int idPersona;
        int idBit;
        //Omogeneità iniziale
        double omo = c.omogeneitacommunity();

        System.out.println("Omogeneità INIZIALE" + omo);

        //disegnare collegamenti TO DO
        while ((era < GlobalVar.numERE) && (omo<GlobalVar.omoSoglia)) { 
            System.out.print("\nERA " + (era + 1));

            //prendo una persona a caso
            idPersona = (int) Math.floor(0 + (GlobalVar.dimensionecom) * Math.random());

            //prendo un bit a caso
            idBit = (int) Math.floor(0 + (GlobalVar.dimDNA) * Math.random());

            System.out.print("\nAggiorno la persona " + idPersona + " e il bit " + idBit);

            System.out.print("\nPrima");

            TraceDNA(c.getPersona(idPersona).getDna());

            c.cambia(idPersona, idBit);

            System.out.print("\nDopo");
            TraceDNA(c.getPersona(idPersona).getDna());
            
            omo = c.omogeneitacommunity();
            
            System.out.print("\n===================================");
            System.out.print("\nGrado di omogeneita': " + omo);
            System.out.print("\n===================================");

            era++;
        }

        calcoloVicinato(c);

        disegnapallocco(c, GlobalVar.vicinato, "Dopo");

    }

    static class MyRenderer implements Renderer.Vertex<String, String> {

        Community community;

        MyRenderer(Community c) {

            this.community = c;
        }

        @Override
        public void paintVertex(RenderContext<String, String> rc,
                Layout<String, String> layout, String vertex) {
            GraphicsDecorator graphicsContext = rc.getGraphicsContext();

            Point2D center = layout.transform(vertex);
            Shape shape = null;
            Color color = null;

            int R, G, B;
            String sR, sG, sB, nome, sRt, sGt, sBt;
            int temp;

            for (int i = 0; i < GlobalVar.dimensionecom; i++) {
                R = community.getPersona(i).getR();
                G = community.getPersona(i).getG();
                B = community.getPersona(i).getB();

                sR = Integer.toString(R);
                sG = Integer.toString(G);
                sB = Integer.toString(B);

                if (sR.length() == 2) {
                    sR = "0" + sR;
                }
                if (sR.length() == 1) {
                    sR = "00" + sR;
                }
                if (sG.length() == 2) {
                    sG = "0" + sG;
                }
                if (sG.length() == 1) {
                    sG = "00" + sG;
                }
                if (sB.length() == 2) {
                    sB = "0" + sB;
                }
                if (sB.length() == 1) {
                    sB = "00" + sB;
                }

                nome = sR + sG + sB + i;

                if (vertex.equals(nome)) {

                    color = new Color(R, G, B);
                    shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);

                }

            }

            if (color == null) {

            } else {
                graphicsContext.setPaint(color);
                graphicsContext.fill(shape);

            }

        }
    }
}

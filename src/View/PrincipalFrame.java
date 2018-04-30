/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MineSweeper;
import View.Listener.EncabezadoListener;
import View.Listener.TableroListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Karen Velasco
 */
public class PrincipalFrame extends JFrame{
    private ControlPanel pnlControl;
    private TableroPanel pnlTablero;
    private JMenuBar menu;
    private MineSweeper mineSweeper; //in PrincipalFrame
    
    public PrincipalFrame(String title){
        super(title);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(200,300);
        super.setLayout(new BorderLayout());

        super.setJMenuBar(createMenu());
        
        mineSweeper = new MineSweeper(8,8,10);

//penel de control, tablero
        pnlControl = new ControlPanel();
                
        pnlControl.setListener(new EncabezadoListener() {
            
            public void btnJuegoOnClick(String minas, String tiempo) {
                pnlTablero.setMensaje(minas+" : "+tiempo);
            }

            public void btnJuegoOnClick() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void btnJuegoOnClick(Integer x, Integer y) {
                //dependiendo de los valores de x,y 
               //mineSweeper.openCell(x, y);
            } 
        });
        
        pnlTablero = new TableroPanel(mineSweeper.getX(), mineSweeper.getY());
        pnlTablero.drawTablero(mineSweeper);
        pnlTablero.setTableroListener(new TableroListener() {
            public void onButtonClick(Integer x, Integer y) {
                System.out.println("Hicieron click en ["+x+"]["+y +"]");
                mineSweeper.openCell(x, y);
                pnlTablero.removeAll();
                pnlTablero.drawTablero(mineSweeper);
            }

            public void onButtonClick() {
                //Pintar el tablero
                
            }

            @Override
            public void onRightClickButton(Integer x, Integer y) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
//        pnlTablero.setBounds(0, 150, 35*mineSweeper.getX(), 35*mineSweeper.getY());
        //super.remove(Tablero);
        //enviar mensaje a tablero desde un boton, para eliminar 
        
        super.add(pnlControl,BorderLayout.NORTH);
        super.add(pnlTablero, BorderLayout.CENTER);
        super.setSize(35 * mineSweeper.getX() + 20, 35*mineSweeper.getY()+200);
        super.setVisible(true);
    }

    private JMenuBar createMenu(){
        JMenuBar menu = new JMenuBar();
        
        JMenu mmArchivo = new JMenu("Archivo");
        JMenuItem nnNuevo = new JMenuItem("Nuevo");
        nnNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        nnNuevo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nuevo..."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        JMenuItem nnAbrir = new JMenuItem("Abrir...");
        nnAbrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                
                //file filter para maniana
                
                if(fc.showOpenDialog(PrincipalFrame.this)== JFileChooser.APPROVE_OPTION){
                    //cargar el archivo
                    System.out.println(fc.getSelectedFiles());
                }
            }
        });
        
        
        JMenuItem nnGuardar = new JMenuItem("Guardar...");
        nnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                if(fc.showSaveDialog(PrincipalFrame.this)== JFileChooser.APPROVE_OPTION){
                    System.out.println(fc.getSelectedFile());
                    File f = new File(fc.getSelectedFile().toString());
                    if(f.exists()){
                        JOptionPane.showMessageDialog(
                                PrincipalFrame.this,
                                "Mensajito",
                                "a,sknd",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
            }
        });
        
        
        JMenuItem nnSalir= new JMenuItem("Salir");
        nnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        mmArchivo.add(nnNuevo);
        mmArchivo.addSeparator();
        mmArchivo.add(nnAbrir);
        mmArchivo.addSeparator();
        mmArchivo.add(nnGuardar);
        mmArchivo.addSeparator();
        mmArchivo.add(nnSalir);
        
        JMenu mmNiveles = new JMenu("Niveles");
        
        
        
        JRadioButtonMenuItem nnPrincipiante= new JRadioButtonMenuItem("Principiante", true);
        JRadioButtonMenuItem nnIntermedio = new JRadioButtonMenuItem("Intermedio");
        JRadioButtonMenuItem nnExperto= new JRadioButtonMenuItem("Experto");
        JRadioButtonMenuItem nnPersonalizado= new JRadioButtonMenuItem("Personalizado...");
        ButtonGroup bgpOpciones = new ButtonGroup();
        bgpOpciones.add(nnPrincipiante);
        bgpOpciones.add(nnIntermedio);
        bgpOpciones.add(nnExperto);
        bgpOpciones.add(nnPersonalizado);
        
        mmNiveles.add(nnPrincipiante);
        mmNiveles.addSeparator();
        mmNiveles.add(nnIntermedio);
        mmNiveles.addSeparator();
        mmNiveles.add(nnExperto);
        mmNiveles.addSeparator();
        mmNiveles.add(nnPersonalizado);
        
        JMenu mmAyuda = new JMenu("Ayuda");
        JMenuItem nnAbout= new JMenuItem("Ayuda");
        
        
        mmAyuda.add(nnAbout);
        
//        JMenu mmTest = new JMenu("Test", false);
        
        menu.add(mmArchivo);
        menu.add(mmNiveles);
        menu.add(mmAyuda);
        
        
        
        return menu;
    }
}

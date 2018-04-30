/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MineSweeper;
import Model.Cell;
import static Model.Status.REVEALED;
import View.Listener.TableroListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Karen Velasco
 */
public class TableroPanel extends JPanel{
//    private JLabel mensaje;
    private TableroListener listener;
    TTextField boton;
    public TableroPanel(Integer x, Integer y){
        super();
    //    super.setBackground(Color.BLUE);
        super.setLayout(null);
        System.out.println("You created a tablero");
        
    }
    public void setMensaje(String mensajito){
 //       mensaje.setText(mensajito);
    }
    public void drawTablero(MineSweeper buscaminas){
        for(int i=0;i< buscaminas.getX();i++){
            for(int j = 0; j<buscaminas.getY(); j++){
                Cell celda = buscaminas.getGrid()[i][j];
                boton = new TTextField(celda, j, j);
                if(celda.getStatus() == REVEALED){ 
                        JLabel abierto = new JLabel();
                        if(celda.getNumber()!=0){
                            abierto.setText(celda.getNumber().toString());    
                        }
                        
                        abierto.setBounds(i*35+10, j*35+10, 30, 30);
                        super.add(abierto);
                }else{
                    TTextField cerrado = new TTextField(celda, i, j); 
                    cerrado.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            listener.onButtonClick(cerrado.getX(), cerrado.getY());
                        }
                    });
                    
                    cerrado.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent  evt){
                            if(evt.getButton() == MouseEvent.BUTTON1){
                                listener.onRightClickButton(cerrado.getX(), cerrado.getY());
                            }
                        }
                    });
                    
                    cerrado.setBounds(i*35+10, j*35+10, 30, 30);
                    
                    TableroPanel.this.add(boton);

                }
            }
        }
//        mensaje = new JLabel("kdfjdkfjdk");
//        super.add(mensaje);
    }
    
    public void setTableroListener(TableroListener listener){
        this.listener = listener;
    }
}

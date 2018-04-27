/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.Listener.EncabezadoListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Karen Velasco
 */

public class ControlPanel extends JPanel implements ActionListener{
    private TButton btnJuego;
    private JTextField txtMinas;
    private JTextField txtTiempo;
    
    private EncabezadoListener listener;
    
    public ControlPanel(){
        super();
        super.setLayout(new BorderLayout());
        super.setBackground(Color.RED);
        
       btnJuego = new TButton("/Images/sFace1.png"); //de titulo nada
       
       btnJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.btnJuegoOnClick(btnJuego.getXX(),btnJuego.getYY());
                
            }
        });
       
       
       
       txtMinas = new JTextField("10");
       txtMinas.setPreferredSize(new Dimension(50,30));
       txtMinas.setBounds(0,0,100,30);
       txtMinas.setFont(new Font("Courier New", Font.BOLD, 24));
       txtMinas.setForeground(Color.RED);
       txtMinas.setBackground(Color.BLACK);
       txtMinas.setHorizontalAlignment(SwingConstants.CENTER);
       //txtMinas.setFocus();
       
       JPanel pnlBoton = new JPanel();
       pnlBoton.setBackground(Color.GREEN);
       pnlBoton.add(btnJuego);
       
       txtTiempo = new JTextField("0000");
       txtTiempo.setPreferredSize(new Dimension(50,30));
       
       super.add(txtMinas, BorderLayout.EAST);
       super.add(pnlBoton, BorderLayout.CENTER); 
       super.add(txtTiempo, BorderLayout.WEST);
       
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String k = txtMinas.getText(); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    public void setListener(EncabezadoListener listener){ 
        this.listener = listener;
    }
}

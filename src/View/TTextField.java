/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cell;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Karen Velasco
 */
public class TTextField extends JTextField{
    private Cell celda; 
    private Integer xx;
    private Integer yy;
    
    TTextField(String path) throws IOException{
        super();
        this.cargarIcono(path);
    }
    
    public Integer getXX(){
        return xx;
    }
    
    
    public Integer getYY(){
        return yy;
    }
    
    public TTextField(Cell celda, Integer xx, Integer yy) throws IOException{
        this.xx = xx;
        this.yy = yy;
        this.celda = celda;
        switch(celda.getStatus()){
            case QUESTION:
                this.cargarIcono("/icons/question.png");
                break;
            case FLAG:
                this.cargarIcono("/images/flag.png");
                break;
            case REVEALED:
                //a number
            default:
                break;
        }
        
    }
    
    private void cargarIcono(String path) throws IOException{
        ImageIcon im = new ImageIcon(path);
        BufferedImage wPic = ImageIO.read(this.getClass().getResource(path));
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        
    }
}

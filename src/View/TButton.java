/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cell;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Karen Velasco
 */
public class TButton extends JButton{
    private Cell celda; 
    private Integer xx;
    private Integer yy;
    
    TButton(String path){
        super();
        this.cargarIcono(path);
    }
    
    public Integer getXX(){
        return xx;
    }
    
    
    public Integer getYY(){
        return yy;
    }
    
    public TButton(Cell celda, Integer xx, Integer yy){
        this.xx = xx;
        this.yy = yy;
        this.celda = celda;
        switch(celda.getStatus()){
            case QUESTION:
                this.cargarIcono("/Images/interrogacion.png");
                break;
            case FLAG:
                this.cargarIcono("/Images/bandera.png");
                break;
            default:
                break;
        }
        
    }
    
    private void cargarIcono(String path){
        URL url = System.class.getResource(path);
        ImageIcon im = new ImageIcon(url);
       super.setIcon(im);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import Controller.MineSweeper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author Karen Velasco
 */
public class Archivo {
   
    public void writeFile(String str, MineSweeper m){//Nombre del archivo y objeto
        FileOutputStream fileOut;
        ObjectOutputStream salida;
        JFileChooser fc = new JFileChooser(); //dialog
        
        try{
            fileOut = new FileOutputStream(str+".txt");
            salida = new ObjectOutputStream(fileOut);
            
            salida.writeObject(m);
            
            salida.close();
            fileOut.close();
        }catch(Exception e){
            System.out.println("Eroor de escritura");
        }
    }
    
    public MineSweeper openFile(String str){
        FileInputStream fileIn;
        ObjectInputStream entrada;
        try{
            fileIn = new FileInputStream(str); //pasar el nombre del archivo en disco o un objeto de la claseFile
            entrada = new ObjectInputStream(fileIn);
            
            MineSweeper resultado = (MineSweeper)entrada.readObject();
            
            entrada.close();
            fileIn.close();
            
            return resultado;
        }catch(Exception e){
            System.out.println("Error de lectura");
            return null;
        } 
    }
}

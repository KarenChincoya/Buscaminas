/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import View.PrincipalFrame;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Karen Velasco
 */
public class Test {
    public static void main23() throws IOException {//String[] args
        
        File file = new File("C:\\Users\\Karen Velasco\\Desktop\\Proyectos POO\\test.txt");
        System.out.println("Es un directorio? " + file.isDirectory());
        try ( //crear un archivo
                FileWriter writer = new FileWriter(file) //pide un file, input output error
        ) {
            writer.append("Hola mundo \r\nSalto de linea\r\n");
            writer.append("Mi name is danger\r\nKaren");
        }
    }
    
    public static void main(String[] args) throws IOException {//String[] args
        
        File file = new File("C:\\Users\\Karen Velasco\\Desktop\\Proyectos POO\\test.txt");
        System.out.println("Es un directorio? " + file.isDirectory());
        //crear un archivo
        FileReader reader = new FileReader(file); //pide un file, input output error
        
        String texto = "";
        
        int caracter = 0;
        
        while(caracter!=-1){
            caracter = reader.read();
            
            
            
            char c = (char) caracter;
            
            String k = String.valueOf(c);
            
            texto = texto.concat(k);
            
//            System.out.print(c); //(caracter)
        }
        
        System.out.println(texto);
    }
    
    
    
    /*
    public static void main12() { //String[] args
        new PrincipalFrame("Buscaminas");
    }
    
    */
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Controller.MineSweeper;
import Files.Archivo;
import Model.gameStatus;
import View.PrincipalFrame;
import java.util.Scanner;

/**
 *
 * @author Karen Velasco
 */
public class Main {
    public static void main(String[] args){//String[] args
        
        MineSweeper b = new MineSweeper(8,8,10);
        Archivo a = new Archivo();
        String nombre;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Juego");//Opciones de juego, juego nuevo
        System.out.println("1. Juego nuevo");
        System.out.println("2. Abrir");
        
        int opcion = input.nextInt();
        int opc=1;
        Integer xt=0;
        Integer yt=0;
        
        switch(opcion){
        case 1:   
            b = new MineSweeper(8,8,10); 
            break;
        case 2:   
            input.nextLine();
            System.out.print("Ingrese el nombre del archivo: ");
            String nFile = input.nextLine();
            b = a.openFile("miArchivo.txt");
        }
        
        while(b.getGameStatus() == gameStatus.PLAYING){
            System.out.println("MENU");
            System.out.println("1. Abrir una celda");
            System.out.println("2. Marcar con una bandera");
            System.out.println("3. Marcar con un ?");
            System.out.println("4. Guardar");
            
            opc = input.nextInt();
            
            if(opc!=4){
                System.out.println("Ingrese la celda: ");
                System.out.println("x: ");
                xt = input.nextInt();
                System.out.println("y: ");
                yt = input.nextInt();
            }
            
            
            switch(opc){
                case 1: b.openCell(xt, yt); break;
                case 2: b.markCell(xt, yt, 1); break;
                case 3: b.markCell(xt, yt, 2); break;
                case 4: 
                    input.next();
                    System.out.print("Ingrese el nombre del archivo: ");
                    nombre = input.next();
                    a.writeFile(nombre, b);
                    break;
                
                default: System.out.println("El dato que ingreso es incorrecto.");
            }
            
            if(b.getRevealedCells() == (b.getX() * b.getY() - b.getMines())){
                System.out.println("Ganaste!");
                b.setGameStatus(gameStatus.WIN);
            }
            b.printGrid();
        }
        
        System.out.println();
        b.printFinalGrid();
                
    }
        

}

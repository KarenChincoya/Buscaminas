/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.gameStatus;
import Model.Status;
import Model.Cell;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Karen Velasco
 */
public class MineSweeper implements Serializable{
    private gameStatus gs;
    private Integer x;
    private Integer y;
    public Cell[][] grid;
    private Integer mines;
    private Integer revealedCells;
    
    public MineSweeper(Integer x, Integer y, Integer m){
        this.grid = new Cell[x][y];
        this.x = x;
        this.y = y;
        this.mines = m;
        this.revealedCells = 0;
        this.gs = gameStatus.PLAYING;
        //construir las celdas
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                grid[i][j] = new Cell(i,j);
            }
        }
        setMines();
    }
    
   public Cell[][] getGrid(){
       return grid;
   }
    
    public gameStatus getGameStatus(){
        return gs;
    }
    public void setGameStatus(gameStatus gs){
        this.gs = gs;
    }
    
    public Integer getMines(){
        return mines;
    }
    
    public Integer getX(){
        return x;
    }
    
    public Integer getY(){
        return y;
    }
    
    public Integer getRevealedCells(){
        return revealedCells;
    }
    private void setMines(){
        //here i have to put n mines inside the grid
        Random random = new Random();
        int counter = mines;
        while(counter>0){
            int rx = random.nextInt(this.x); //without +1 because it can be 0
            int ry = random.nextInt(this.y);
            if( ! this.grid[rx][ry].isMine() ){
                this.grid[rx][ry].setMine();
                counter--;                
            }
        }
    
    }
    
    public void openCell(Integer xOpen, Integer yOpen){
        
    //    System.out.println();
    //    System.out.println();
        
    //    System.out.println("openCell() ["+xOpen+"][ "+yOpen+"]");
        
        if(isValidCell(xOpen,yOpen)){
    //        System.out.println("    openCell(). es celda Valida");
            if(grid[xOpen][yOpen].isMine()){
    //            System.out.println("    openCell().Perdiste, habia una mina en g["+xOpen+"]["+yOpen+"]");
                //grid[xOpen][yOpen].setStatus(Status.REVEALED);
                grid[xOpen][yOpen].setNumber(9);
                this.gs = gameStatus.LOSE;
            }else{
    //            System.out.println("    openCell().No es mina, puede continuar");
                if( grid[xOpen][yOpen].getStatus() == Status.CLOSE ){//si esta cerrado, contar las minas
    //                System.out.println("    openCell().Esta cerrada, puede abrirse");
                    
                    int minas = getMines(xOpen,yOpen); //error
                    this.grid[xOpen][yOpen].setNumber(minas);
                    
                    if(minas == 0){ //abre las celdas de alrededor
    //                    System.out.println("    openCell().Tiene 0 minas alrededor, hay que explotar");
                        for(int i=xOpen-1; i<=xOpen+1; i++){
                            for(int j=yOpen-1; j<=yOpen+1 ;j++){
                                if( i== xOpen && j==yOpen){
                                    grid[xOpen][yOpen].setStatus(Status.REVEALED);
                                    revealedCells++;
                                }else
                                    openCell(i,j);
                            }
                        }
                    }else{
    //                    System.out.println("    openCell().La celda tiene un numero >0, puede abrise");
                        
                        grid[xOpen][yOpen].setStatus(Status.REVEALED);
                        revealedCells++;
                        
                    }
                }                 
            }
        }else{
    //        System.out.println("    openCell().Es una celda no valida");
        }
    }
    
    public void markCell(Integer x, Integer y, Integer n){
        switch(n){
            case 1: //flag
                this.grid[x][y].setStatus(Status.FLAG);break;
            case 2: 
                this.grid[x][y].setStatus(Status.QUESTION);
        }
        
    }
    
    private boolean isValidCell(Integer xV, Integer yV){
    //    System.out.println("openCell() ["+xV+"][ "+yV+"]");
        if(xV>=0 && xV<this.x && yV>=0 && yV<this.y){
    //        System.out.println("    isValidCell().result = Es una celda valida");
            return true;
        }else{
    //        System.out.println("    isValidCell().result = Es una celda invalida");
            return false;
        }
    }
    
    private Integer getMines(Integer xOpen, Integer yOpen){ //we'll obtain the number of the cell
        int counter=0;
    //    System.out.println("getMines() ["+xOpen+"]["+yOpen+"]");
        for(int i=xOpen-1; i<=(xOpen+1) ; i++){
            for(int j=yOpen-1; j<=(yOpen+1) ; j++){
                if(isValidCell(i,j) && grid[i][j].isMine()){ //split y && le quite uno
                       counter++; //solo un operador and   
                }
            }
        }
//        System.out.println("    getMines().result = "+counter);
        return counter; //contador de minas, el mismo no puede ser una mina 
    }
    
    public void printFinalGrid(){
        System.out.println("Final grid");
        //primero asigna y luego imprime
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(! grid[i][j].isMine()){
                    
                    int minas = getMines(i,j);
                    grid[i][j].setNumber(minas);
                }
            }
        }
        
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(this.grid[i][j].isMine())
                    System.out.print(" "+"M"+" ");
                else{
                    System.out.print(" "+grid[i][j].getNumber()+" ");
                }
            }
            System.out.println();
        }
    }
    
    public void printGrid(){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                switch(this.grid[i][j].getStatus()){
                    case CLOSE: System.out.print(" "+"_"+" "); break;
                    case FLAG: System.out.print(" "+"F"+" ");break;
                    case QUESTION: System.out.print(" "+"?"+" "); break;
                    case REVEALED: System.out.print(" "+this.grid[i][j].getNumber()+" ");
                }
            }
            System.out.println();
        }
    }
    
   /* public static void guardar(MineSweeper b) throws FileNotFoundException, IOException{
        File file = new File("miArchivo.txt");
        FileOutputStream output = new FileOutputStream(file); //el flujo 
        ObjectOutputStream writer = new ObjectOutputStream(output);
        writer.writeObject(b);
        
        writer.close();
        output.close();
    }
   */     
}

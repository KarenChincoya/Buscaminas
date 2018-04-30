/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Karen Velasco
 */
public class Cell implements Serializable{
    private Status status;
    private Integer x;
    private Integer y;
    private Boolean mine;
    private Integer number;
    
    public Cell(Integer x, Integer y){
        this.x = x;
        this.y = y;
        this.status = Status.CLOSE;
        this.mine = false;
    }
    
    public void setMine(){
        this.mine = true;
    }
    
    public void setStatus(Status status){
        this.status = status;
    }
    public void setNumber(Integer number){
        this.number = number;
    }
    public Integer getNumber(){
        return number;
    }
    public boolean isMine(){
        return mine==true;
    }
    
    public Status getStatus(){
        return status;
    }
}

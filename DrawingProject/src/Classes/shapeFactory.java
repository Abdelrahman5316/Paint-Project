/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.Color;


public class shapeFactory {
     public Shape CreateShape(String name,Color color) {
        if (name.equals("Circle")) {
            return new Circle(color);
        } else if (name.equals("Rectangle")) {
            return new Rectangle(color);
        } 
        else if (name.equals("Square")) {
            return new Square(color);}
            else {
            return null;
        }
    }

}


package Classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public abstract class Shape extends Polygon implements Cloneable {
    public Color color;
    public boolean circle;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape(Color color) {
        this.color = color;
    }
    @Override
    public Object clone() throws  CloneNotSupportedException//prototype
    {
        return super.clone();
    }

 public abstract boolean contains(int x,int y);

    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

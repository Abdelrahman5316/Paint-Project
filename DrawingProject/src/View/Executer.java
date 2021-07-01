/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Classes.Circle;
import Classes.Line;
import Classes.Oval;
import Classes.Rectangle;
import Classes.Shape;
import Classes.Triangle;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Arc;
import javax.swing.JPanel;

public class Executer extends JPanel implements MouseListener, MouseMotionListener {

    private static Stack<ArrayList> stack = new Stack<>();
    private static Stack<ArrayList> stack2 = new Stack<>();
    Color currentColor = Color.BLACK;
    int mode = -1;//0 for line 1 for rect

   
    public static Stack<ArrayList> getStack() {
        return stack;
    }

    public static void setStack(Stack<ArrayList> stack) {
        Executer.stack = stack;
    }

    public static Stack<ArrayList> getStack2() {
        return stack2;
    }

    public static void setStack2(Stack<ArrayList> stack2) {
        Executer.stack2 = stack2;
    }
    int x1, y1;
    boolean first = false;
    int x2, y2;
    int flag = -1;
    private static ArrayList<Shape> x = new ArrayList<Shape>();

    public ArrayList<Shape> getAx() {
        return x;
    }

    public void setAx(ArrayList<Shape> x) {
        this.x = x;
    }
    private Shape select = null;

    public Executer() {
        addMouseListener(this);
        addMouseMotionListener(this);
        try {
            stack.push(copy(x));
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        Iterator<Shape> it = x.iterator();//Design Pattern: iterator
        while (it.hasNext()) {
            //x.get(i).draw(g);
            Shape s = it.next();
            if (s instanceof Line) {
                Line l = (Line) s;
                g.setColor(l.getColor());
                g.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
            } else if (s instanceof Rectangle) {
                Rectangle l = (Rectangle) s;
                g.setColor(l.getColor());
                if (l.getX1() < l.getX2() && l.getY1() < l.getY2()) {
                    g.fillRect(l.getX1(), l.getY1(), l.getX2() - l.getX1(), l.getY2() - l.getY1());
                } else if (l.getX1() > l.getX2() && l.getY1() > l.getY2()) {
                    g.fillRect(l.getX2(), l.getY2(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));

                } else if (l.getX1() < l.getX2() && l.getY1() > l.getY2()) {
                    g.fillRect(l.getX1(), l.getY2(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));

                } else if (l.getX1() > l.getX2() && l.getY1() < l.getY2()) {
                    g.fillRect(l.getX2(), l.getY1(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));

                }
            } else if (s instanceof Circle) {//Circle
                Circle l = (Circle) s;
                g.setColor(l.getColor());
                if (l.getX1() < l.getX2() && l.getY1() < l.getY2()) {
                    g.fillOval(l.getX1(), l.getY1(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getX2() - l.getX1()));
                } else if (l.getX1() > l.getX2() && l.getY1() > l.getY2()) {
                    g.fillOval(l.getX2(), l.getY2(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getX2() - l.getX1()));

                } else if (l.getX1() < l.getX2() && l.getY1() > l.getY2()) {
                    g.fillOval(l.getX1(), l.getY2(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getX2() - l.getX1()));

                } else if (l.getX1() > l.getX2() && l.getY1() < l.getY2()) {
                    g.fillOval(l.getX2(), l.getY1(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getX2() - l.getX1()));

                }
            } else if (s instanceof Oval) {//Oval
                Oval l = (Oval) s;
                g.setColor(l.getColor());

                if (l.getX1() < l.getX2() && l.getY1() < l.getY2()) {
                    g.fillOval(l.getX1(), l.getY1(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));
                } else if (l.getX1() > l.getX2() && l.getY1() > l.getY2()) {
                    g.fillOval(l.getX2(), l.getY2(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));

                } else if (l.getX1() < l.getX2() && l.getY1() > l.getY2()) {
                    g.fillOval(l.getX1(), l.getY2(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));

                } else if (l.getX1() > l.getX2() && l.getY1() < l.getY2()) {
                    g.fillOval(l.getX2(), l.getY1(), Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));

                }
            } else if (s instanceof Triangle) {
                Triangle l = (Triangle) s;
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(l.getColor());

                Path2D path = new Path2D.Double();
                path.moveTo(l.getX1(), l.getY1());
                path.lineTo(l.getX2(), l.getY2());
                path.lineTo((l.getX1() + (l.getX1() - l.getX2())), l.getY2());
                path.closePath();
                g2.setStroke(new BasicStroke(3));
                g2.setColor(l.getColor());
                g2.draw(path);
            }
        }
    }
//                } else if (l.getX1() < l.getX2() && l.getY1() < l.getY2()) {
//                    Path2D path = new Path2D.Double();
//                    path.moveTo(l.getX1(), l.getY1());
//                    path.lineTo(l.getX2(), l.getY2());
//                    path.lineTo(Math.abs(l.getX2() - l.getX1()) * 2,Math.abs(l.getY2() - l.getY1()) * 2) ;
//                    path.closePath();
//                    g2.setStroke(new BasicStroke(3));
//                    g2.setColor(l.getColor());
//                    g2.draw(path);
//                }
//                    else if (l.getX1() < l.getX2() && l.getY1() > l.getY2()) {
//                    Path2D path = new Path2D.Double();
//                    path.moveTo(l.getX1(), l.getY1());
//                    path.lineTo(l.getX2(), l.getY2());
//                    path.lineTo(l.getX2(), Math.abs(l.getY2() - l.getY1()));
//                    path.closePath();
//                    g2.setStroke(new BasicStroke(3));
//                    g2.setColor(l.getColor());
//                    g2.draw(path);
//                } else {
//                    Path2D path = new Path2D.Double();
//                    path.moveTo(l.getX1(), l.getY1());
//                    path.lineTo(l.getX2(), l.getY2());
//                    path.lineTo(Math.abs(l.getX2() - l.getX1()) * 2, l.getY2());
//                    path.closePath();
//                    g2.setStroke(new BasicStroke(3));
//                    g2.setColor(l.getColor());
//                    g2.draw(path);
//
//                }
//            }
////                else if (s instanceof Polygon){
//                    
//                }

//    }
//                else if (s instanceof RoundRectangle2D){//Arc
//                RoundRectangle2D l =(RoundRectangle2D) x.get(i);
//                  g.setColor(l.getColor());
//                 
//                  if (l.getX1() < l.getX2() && l.getY1() < l.getY2()) {
//                  g.fillOval(l.getX1(), l.getY1(),  Math.abs(l.getX2() - l.getX1()), Math.abs(l.getY2() - l.getY1()));
//            }
//       }
    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        x1 = me.getX();
        y1 = me.getY();
        if (mode == 0) {
            Line l = new Line(x1, y1, x1, y1, currentColor);
            x.add(l);
            repaint();

        } else if (mode == 1) {
            Rectangle r = new Rectangle(x1, y1, x1, y1, currentColor);
            x.add(r);
            repaint();
        } else if (mode == 2) {
            Circle r = new Circle(x1, y1, x1, y1, currentColor);
            x.add(r);
            repaint();
        } else if (mode == 3) {
            Oval r = new Oval(x1, y1, x1, y1, currentColor);
            x.add(r);
            repaint();
        } else if (mode == 4) {
            Triangle r = new Triangle(x1, y1, x1, y1, currentColor);
            x.add(r);
            repaint();
        } else if (mode == 10) {
            for (int i = x.size() - 1; i >= 0; i--) {
                if (x.get(i).contains(x1, y1)) {
                    select = x.get(i);
                    System.out.println("selected");
                    break;
                }
            }

        } else if (mode == 11) {
            for (int i = x.size() - 1; i >= 0; i--) {
                if (x.get(i).contains(x1, y1)) {
                    select = x.get(i);
                    System.out.println("selected");
                    break;
                }
            }

        } else if (mode == 12) {
            for (int i = x.size() - 1; i >= 0; i--) {
                if (x.get(i).contains(x1, y1)) {
                    select = x.get(i);
                    System.out.println("selected");
                    break;
                }
            }

        } else if (mode == 13) {
            for (int i = x.size() - 1; i >= 0; i--) {
                if (x.get(i).contains(x1, y1)) {
                    select = x.get(i);

                    if (x.get(i) instanceof Rectangle) {
                        Rectangle r = (Rectangle) x.get(i);
                        try {
                            Rectangle s = (Rectangle) r.clone();
                            s.setX1(0);
                            s.setY1(0);
                            s.setX2(Math.abs(r.getX2() - r.getX1()));
                            s.setY2(Math.abs(r.getY2() - r.getY1()));
                            x.add(s);
                            repaint();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        System.out.println("selected");
                    }

                    break;
                }

            }
        } else if (mode == 14) {

        }
    }

    public static ArrayList<Shape> copy(ArrayList<Shape> x1) throws CloneNotSupportedException {
        ArrayList<Shape> arr = new ArrayList<>();
        int i = 0;
        for (i = 0; i < x1.size(); i++) {
            arr.add((Shape) x1.get(i).clone());
           
        }
        return arr;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (mode >= 1) {
            try {
                stack.push(copy(x));
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Executer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (mode == 0) {
            x2 = me.getX();
            y2 = me.getY();
            Shape s = x.get(x.size() - 1);
            if (s instanceof Line) {
                Line l = (Line) s;
                l.setX2(x2);
                l.setY2(y2);
            }
            flag = 0;
            repaint();
            first = false;
        } else if (mode == 1) {
            x2 = me.getX();
            y2 = me.getY();
            Shape s = x.get(x.size() - 1);
            if (s instanceof Rectangle) {
                Rectangle r = (Rectangle) s;
                r.setX2(x2);
                r.setY2(y2);
            }
            repaint();
            first = false;
            flag = 1;
        } else if (mode == 2) {

            x2 = me.getX();
            y2 = me.getY();
            Shape s = x.get(x.size() - 1);
            if (s instanceof Circle) {
                Circle r = (Circle) s;
                r.setX2(x2);
                r.setY2(y2);
            }
            flag = 2;
            repaint();
            first = false;
        } else if (mode == 3) {

            x2 = me.getX();
            y2 = me.getY();
            Shape s = x.get(x.size() - 1);
            if (s instanceof Oval) {
                Oval r = (Oval) s;
                r.setX2(x2);
                r.setY2(y2);
            }
            flag = 3;
            repaint();
            first = false;
        } else if (mode == 4) {

            x2 = me.getX();
            y2 = me.getY();
            Shape s = x.get(x.size() - 1);
            if (s instanceof Triangle) {
                Triangle r = (Triangle) s;
                r.setX2(x2);
                r.setY2(y2);
            }
            flag = 3;
            repaint();
            first = false;
        } else if (mode == 10) {
            x2 = me.getX();
            y2 = me.getY();
            if (select != null) {
                if (select instanceof Rectangle) {
                    Rectangle r = (Rectangle) select;

                    r.setX1(r.getX1() + x2 - x1);
                    r.setY1(r.getY1() + y2 - y1);
                    x1 = x2;
                    y1 = y2;
                    repaint();
                } else if (select instanceof Circle) {
                    Circle r = (Circle) select;

                    r.setX1(r.getX1() + x2 - x1);
                    r.setY1(r.getY1() + y2 - y1);
                    x1 = x2;
                    y1 = y2;
                    repaint();
                }

            }
        } else if (mode == 11) {

            x2 = me.getX();
            y2 = me.getY();
            if (select != null) {
                if (select instanceof Rectangle) {
                    Rectangle r = (Rectangle) select;

                    x.remove(select);
                    repaint();
                    select = null;
                } else if (select instanceof Circle) {
                    Circle r = (Circle) select;

                    x.remove(select);
                    repaint();
                    select = null;
                } else if (select instanceof Line) {
                    Line r = (Line) select;

                    x.remove(select);
                    repaint();
                    select = null;
                } else if (select instanceof Oval) {
                    Oval r = (Oval) select;

                    x.remove(select);
                    repaint();
                    select = null;
                }
            }
        } else if (mode == 12) {
            x2 = me.getX();
            y2 = me.getY();
            if (select != null) {
                Rectangle r = (Rectangle) select;

                r.setX1(r.getX1() + x2 - x1);
                r.setY1(r.getY1() + y2 - y1);
                r.setX2(r.getX2() + x2 - x1);
                r.setY2(r.getY2() + y2 - y1);
                x1 = x2;
                y1 = y2;
                repaint();;

            }
        }
    }

    public void undo() throws CloneNotSupportedException {
        if (stack.size() > 1) {
            stack2.push(stack.pop());
            x = copy(stack.peek());
            repaint();
        }
    }

    public void redo() throws CloneNotSupportedException {
        if (stack2.size() >0) {
            stack.push(stack2.pop());
            x = copy(stack.peek());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

}

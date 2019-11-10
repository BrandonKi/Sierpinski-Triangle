package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class STPanel extends JPanel
{
    private int order = 1;
    private DPanel drawing;
    private JPanel selection;
    private JTextField inputOrder;
    private JLabel directions;

    public STPanel()
    {
        setPreferredSize(new Dimension(650,650));
        setLayout(new BorderLayout());
        drawing = new DPanel();
        selection = new JPanel();
        directions = new JLabel("Enter an order: ");
        inputOrder = new JTextField(4);
        inputOrder.addActionListener(new OrderListener());
        selection.add(directions);
        selection.add(inputOrder);

        add(drawing, BorderLayout.CENTER);
        add(selection, BorderLayout.SOUTH);
        int delay = 1000;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            order++;
            repaint();
            if(order == 12)
                order = 1;
            }
        };
  new Timer(delay, taskPerformer).start();
    }


    public class DPanel extends JPanel 
    {
        public void paintComponent(Graphics g)
        {
          super.paintComponent(g);

          if(order != -1) {
            Point p1 = new Point(getWidth()/2, 10);
            Point p2 = new Point(10, getHeight()-10);
            Point p3 = new Point(getWidth()-10, getHeight()-10);
            displayTriangles(order, p1, p2, p3, g);
          }
        }

        private void displayTriangles(int order, Point p1, Point p2, Point p3, Graphics g)
        {
            Polygon pg = new Polygon();
            double cool = 2;
            if(order == 1){
                pg.addPoint((int)p1.getX(), (int)p1.getY());
                pg.addPoint((int)p2.getX(), (int)p2.getY());
                pg.addPoint((int)p3.getX(), (int)p3.getY());
                g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
                g.fillPolygon(pg);
           }
           else{
                pg.addPoint((int)p1.getX(), (int)p1.getY());
                pg.addPoint((int)p2.getX(), (int)p2.getY());
                pg.addPoint((int)p3.getX(), (int)p3.getY());
                order--;
                Point p4 = new Point((int)((p1.getX() + p2.getX())/cool), (int)((p1.getY() + p2.getY())/cool));
                Point p5 = new Point((int)((p2.getX() + p3.getX())/cool), (int)((p2.getY() + p3.getY())/cool));
                Point p6 = new Point((int)((p1.getX() + p3.getX())/cool), (int)((p1.getY() + p3.getY())/cool));
                displayTriangles(order, p4, p2, p5, g);
                displayTriangles(order, p1, p4, p6, g);
                displayTriangles(order, p6, p5, p3, g);
           }
                
            
        }
    }

    private class OrderListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            order = Integer.parseInt(inputOrder.getText());
            repaint();
        }
    }
}
//This program will display a sketch pad for the user to interact with. 
//The user can change the color of the drawing as well. 

//Name: Nick Rentschler
//Date: 6-12-2019

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Sketch extends JFrame implements MouseListener, MouseMotionListener, ItemListener{
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 300;
    private static final int FRAME_X_ORIGIN = 150;
    private static final int FRAME_Y_ORIGIN = 250;
    
    private int last_x;
    private int last_y;
    private JRadioButton[] radioButton;
    private JButton clearButton;
    private JPanel contentPanel;
    private Color currentDrawingColor;
    
    //This method will create the frame and set the visible to true. 
    public static void main (String [] args) {
        Sketch frame = new Sketch();
        frame.setVisible(true);
    }
    
    //This constructor will set the frame and panels along with the buttons. 
    public Sketch() {
        JPanel radioPanel;
       
        ButtonGroup colorGroup;
        String [] btnText = {"Black", "Red", "Blue", "Green"};
        
        setTitle("Sketch Pad");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
        
        
        radioPanel = new JPanel(new GridLayout(0,4));
        contentPanel = new JPanel(new BorderLayout());
        
        colorGroup = new ButtonGroup();
        radioButton = new JRadioButton[btnText.length];
        
        for(int i=0; i<radioButton.length; i++){
            radioButton[i] = new JRadioButton(btnText[i]);
            radioButton[i].addItemListener(this);
            colorGroup.add(radioButton[i]);
            radioPanel.add(radioButton[i]);
        }
        radioButton[0].setSelected(true);
        
        
        clearButton = new JButton("Clear");
        clearButton.addMouseListener(this);
        
        add(clearButton, BorderLayout.NORTH);
        add(radioPanel, BorderLayout.SOUTH);
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setBackground(Color.white);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        last_x = last_y = 0;
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    //This method will set the drawing color based off which button is pushed. 
    public void itemStateChanged(ItemEvent event) {
        if(radioButton[0].isSelected()) {
            currentDrawingColor = Color.black;
        }else if(radioButton[1].isSelected()){
            currentDrawingColor = Color.red;
        }else if(radioButton[2].isSelected()) {
            currentDrawingColor = Color.blue;
        }else if(radioButton[3].isSelected()) {
            currentDrawingColor = Color.green;
        }
    }
    
    //This method will set the value of x and y based off which side of mouse is pressed. 
    public void mousePressed( MouseEvent event ) {
        int x = event.getX();
        int y = event.getY();
        
        if ( event.isMetaDown() ) {
            last_x = x;
            last_y = y;
        } else {
            //the left mouse button is pressed,
            //remember the starting point of a new mouse drag
            last_x = x;
            last_y = y;
        }
        
        if(event.getSource() instanceof JButton) {
            Graphics g = contentPanel.getGraphics();
            Rectangle   r = getBounds();
            g.setColor(Color.white);
            g.fillRect(0, 0, r.width, r.height);
            g.dispose();
        }
        
    }
    
    //These methods are not used but have to be initialized. 
    public void mouseClicked(MouseEvent event){ }
    public void mouseEntered(MouseEvent event) { }
    public void mouseExited(MouseEvent event) { }
    public void mouseMoved (MouseEvent event) { }
    
    
    //This method will draw a line when the right mouse button is released. 
    public void mouseReleased(MouseEvent event) { 
        int x = event.getX();
        int y = event.getY();
        
        if (event.isMetaDown() ){
            Graphics g = getGraphics();
            g.setColor(currentDrawingColor);
            g.drawLine(last_x, last_y, x, y);
            g.dispose();
        }
    }
 
    //This method will draw a line when the left mouse button is done dragging. 
    public void mouseDragged( MouseEvent event ) {
        int x = event.getX();
        int y = event.getY();

        if(!event.isMetaDown() ){
            Graphics g = getGraphics();
            
            g.setColor(currentDrawingColor);
            g.drawLine(last_x, last_y, x, y);
            g.dispose();

            last_x = x;
            last_y = y;
        }
    }
}
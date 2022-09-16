//Basic Game Application
//Version 2
//Keyboard Input

//Adds keyboard control of the astronaut

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Sounds
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.*;

//Keyboard and Mouse
import java.awt.event.*;

//*******************************************************************************
// Class Definition Section

public class DrawImageApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //declare the image file variable
    public Image image;

    public Image imagebackofCard;

    public Image backGround;

    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public DrawImageApp() {

        setUpGraphics();


        //variable and objects
        //create (construct) the objects needed for the game and load up

        //load the image file
        image = Toolkit.getDefaultToolkit().getImage("cards.jpg");
        backGround = Toolkit.getDefaultToolkit().getImage("BlackJack Background (1).png");
        imagebackofCard = Toolkit.getDefaultToolkit().getImage("BackofCard.PNG");


    }// BasicGameApp()

    public void run() {

        while (true) {

            try {
                render();  // paint the graphics
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            }
            pause(20); // sleep for 10 ms
        }
    }

    public void render() throws IOException, FontFormatException {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(backGround, 0, 0, WIDTH, HEIGHT, null);

        //Drawing Text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.PLAIN, 18));

        if (Game.playersAtTable.get(0).hand.cardsInHand != null) {
            for (int i = 0; i < Game.playersAtTable.get(0).hand.cardsInHand.size(); i++) {

                if (Game.playersAtTable.get(0).hand.cardsInHand.get(i).suit.equals("Hearts")) {
                    g.drawImage(image, 465 + 7 * i, 445 + 16 * i, 535 + 7 * i, 545 + 16 * i, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord()) * 225, 0, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord() + 1) * 225, 315, null);
                } else if (Game.playersAtTable.get(0).hand.cardsInHand.get(i).suit.equals("Spades")) {
                    g.drawImage(image, 465 + 7 * i, 445 + 16 * i, 535 + 7 * i, 545 + 16 * i, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord()) * 225, 315, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord() + 1) * 225, 630, null);
                } else if (Game.playersAtTable.get(0).hand.cardsInHand.get(i).suit.equals("Diamonds")) {
                    g.drawImage(image, 465 + 7 * i, 445 + 16 * i, 535 + 7 * i, 545 + 16 * i, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord()) * 225, 630, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord() + 1) * 225, 945, null);
                } else if (Game.playersAtTable.get(0).hand.cardsInHand.get(i).suit.equals("Clubs")) {
                    g.drawImage(image, 465 + 7 * i, 445 + 16 * i, 535 + 7 * i, 545 + 16 * i, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord()) * 225, 945, (Game.playersAtTable.get(0).hand.cardsInHand.get(i).retrieveRankCord() + 1) * 225, 1260, null);
                }

            }

            if (Game.ds.get(0).displayFull == false && Game.playersAtTable.get(0).hand.cardsInHand.size() > 0) {

                g.drawImage(imagebackofCard, 465, 84, 70, 100, null);

                for (int ir = 0; ir < Game.ds.get(0).hand.cardsInHand.size() - 1; ir++) {


                    if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Hearts")) {
                        g.drawImage(image, 472 + 7 * ir, 100 + 16 * ir, 542 + 7 * ir, 200 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 0, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 315, null);
                    } else if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Spades")) {
                        g.drawImage(image, 472 + 7 * ir, 100 + 16 * ir, 542 + 7 * ir, 200 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 315, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 630, null);
                    } else if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Diamonds")) {
                        g.drawImage(image, 472 + 7 * ir, 100 + 16 * ir, 542 + 7 * ir, 200 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 630, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 945, null);
                    } else if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Clubs")) {
                        g.drawImage(image, 472 + 7 * ir, 100 + 16 * ir, 542 + 7 * ir, 200 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 945, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 1260, null);
                    }
                }
            } else {
                for (int ir = 0; ir < Game.ds.get(0).hand.cardsInHand.size(); ir++) {

                    if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Hearts")) {
                        g.drawImage(image, 465 + 7 * ir, 84 + 16 * ir, 535 + 7 * ir, 184 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 0, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 315, null);
                    } else if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Spades")) {
                        g.drawImage(image, 465 + 7 * ir, 84 + 16 * ir, 535 + 7 * ir, 184 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 315, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 630, null);
                    } else if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Diamonds")) {
                        g.drawImage(image, 465 + 7 * ir, 84 + 16 * ir, 535 + 7 * ir, 184 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 630, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 945, null);
                    } else if (Game.ds.get(0).hand.cardsInHand.get(ir).suit.equals("Clubs")) {
                        g.drawImage(image, 465 + 7 * ir, 84 + 16 * ir, 535 + 7 * ir, 184 + 16 * ir, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord()) * 225, 945, (Game.ds.get(0).hand.cardsInHand.get(ir).retrieveRankCord() + 1) * 225, 1260, null);
                    }
                }
            }

        }


        URL fontUrl = new URL("https://www.WebpagePublicity.com/free-fonts/n/News%20Gothic%20Bold%20Italic%20BT.ttf");
        Font importedfont = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()); // Takes the font file from the webpage
        importedfont = importedfont.deriveFont(Font.PLAIN,24);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(importedfont);

        g.setFont(importedfont); // Sets the current pen's font to the new one we just added

        g.setColor(new Color(215, 215, 215, 255));

        g.drawString("Current Balance - "+ Game.playersAtTable.get(0).name + ":", 33,45);
        g.drawString(Game.playersAtTable.get(0).bank + "", 33,73);

        g.drawString("Hand Value - " + Game.playersAtTable.get(0).name + ":", 33,115);
        g.drawString(Game.playersAtTable.get(0).hand.calculatedTotalHandValue() + "", 33,143);

        if (Game.ds.get(0).displayFull == true) { // Display's the dealer's hand value ONLY if the second card isn't hidden
            g.drawString("Current Balance - "+ Game.ds.get(0).name + ":", 689,45);
            g.drawString(Game.ds.get(0).hand.calculatedTotalHandValue() + "", 689,73);
        }

        g.dispose();
        bufferStrategy.show();
    }

    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("DrawImage");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }


}
package resources.P4MorseCodeTranslator;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MorseCodeTranslatorGUI extends JFrame implements KeyListener { // Listening to key presses (typing)
    private MorseCodeController morseCodeController;

    // textInputArea - user input (text to be translated) , morseCode Area - translated text into morse code
    private JTextArea textInputArea, morseCodeArea;

    public MorseCodeTranslatorGUI() {
        super("Morse Code Translator"); // It adds text to the title bar

        setSize(new Dimension(540, 760)); // Sets the size of the frame to be 540x760 pixels
        setResizable(false); // Prevents GUI from being able to be resized
        setLayout(null); // Letting the layout to null allows us to manually position and set the size of the components in our GUI

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits program when closing the GUI

        getContentPane().setBackground(Color.decode("#264653")); // Set the background color of the content pane to dark teal
        setLocationRelativeTo(null); // Places the GUI in the center of the screen when ran

        morseCodeController = new MorseCodeController();

        addGuiComponents();
    }
        private void addGuiComponents(){
            JLabel titleLabel = new JLabel("Morse Code Translator"); // Title label
            titleLabel.setFont(new Font("Dialog", Font.BOLD,36)); // Changes the font size for the label and the font size
            titleLabel.setForeground(Color.WHITE); // Changes the font color of the text to white
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centers text (relative to its container's width)
            titleLabel.setBounds(0,0,540,100); // Sets the x,y position and the width and height dimensions to make sure that the title aligns to the center of the GUI, we made it the same width

            JLabel textInputLabel = new JLabel("Text:- ");
            textInputLabel.setFont(new Font("Dialog", Font.BOLD,16));
            textInputLabel.setForeground(Color.WHITE);
            textInputLabel.setBounds(20,100,200,30);

            textInputArea = new JTextArea();
            textInputArea.setFont(new Font("Dialog", Font.PLAIN,18));
            textInputArea.addKeyListener(this); // Listening to key presses whenever we are typing in this text area
            textInputArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Empty space around the borders
            textInputArea.setLineWrap(true); // Makes it so that words wrap to the next line after reaching the end of the text area
            textInputArea.setWrapStyleWord(true); // Makes it so that when the words do get wrap, the word doesn't split off

            // Adds scrolling ability to input text area
            JScrollPane textInputScroll = new JScrollPane(textInputArea);
            textInputScroll.setBounds(20,132,484,236);

            // Morse Code input
            JLabel morseCodeInputLabel = new JLabel("Morse Code");
            morseCodeInputLabel.setFont(new Font("Dialog", Font.PLAIN,16));
            morseCodeInputLabel.setForeground(Color.WHITE);
            morseCodeInputLabel.setBounds(20,390,200,30);

            morseCodeArea = new JTextArea();
            morseCodeArea.setFont(new Font("Dialog",Font.PLAIN,18));
            morseCodeArea.setEditable(false);
            morseCodeArea.setLineWrap(true);
            morseCodeArea.setWrapStyleWord(true);
            morseCodeArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            // Adds scrolling ability to morse code text area
            JScrollPane morseCodeScroll = new JScrollPane(morseCodeArea);
            morseCodeScroll.setBounds(20,430,484,236);

            // Play sound button
            JButton playSoundButton = new JButton("Play Sound");
            playSoundButton.setBounds(210,680,100,30);
            playSoundButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Disable the play button (prevents the sound from getting interrupted)
                    playSoundButton.setEnabled(false);

                    Thread playMorseCodeThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Attempt to play the morse code sound
                            try{
                                String[] morseCodeMessage = morseCodeArea.getText().split("");
                                morseCodeController.playSound(morseCodeMessage);
                            }catch(LineUnavailableException lineUnavailableException){
                                lineUnavailableException.printStackTrace();
                            }catch(InterruptedException interruptedException){
                                interruptedException.printStackTrace();
                            }finally{
                                // Enable play sound button
                                playSoundButton.setEnabled(true);
                            }
                        }
                    });
                    playMorseCodeThread.start();
                }
            });

            // Adds to GUI
            add(titleLabel);
            add(textInputLabel);
            add(textInputScroll);
            add(morseCodeInputLabel);
            add(morseCodeScroll);
            add(playSoundButton);
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Ignore shift key press
        if(e.getKeyCode() != KeyEvent.VK_SHIFT){
            String inputText = textInputArea.getText(); // Retrieve text input

            morseCodeArea.setText(morseCodeController.translateToMorse(inputText)); // Update the GUI with translated text
        }

    }
}

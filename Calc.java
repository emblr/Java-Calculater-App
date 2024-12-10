import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
//i used java swing as the GUI since it reminded me of Tkinter in python

public class Calc extends JFrame{
    JButton[] numButtons = new JButton[16];
    String[] buttonLabels = {"1","2","3","C","4","5","6","/","7","8","9","*","0","+","=","-"};
    JPanel NumJPanel = new JPanel();// a panel to seperate the location and placement of the buttons to the the textfield
    JTextField textField = new JTextField();// this is to displayu the numbers the user types and the result
    Font NumFont = new Font(null);// the font of the numbers you can click on
    Double num1, num2, result;
    String operater;
    
    


    public Calc(){
        NumFont = new Font(Font.MONOSPACED, Font.BOLD, 30);
        this.setTitle("Calculator");// title of the app
        this.setSize(350, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.black);
        this.setResizable(false);
        
        NumJPanel.setBounds(25, 125, 300, 350); //this creates a rectangle in where the butttons will be placed on
        NumJPanel.setLayout(new GridLayout(4,4,0,75));
        NumJPanel.setBackground(Color. black);//changing to a different color shows the rectangle

        
        textField.setBounds(25, 50, 300, 50);
        textField.setEditable(false);//prevents the user from typing anythinmg in the textfield
        
        
        
        
        int x = 0;// the x and y coordinates of the buttons
        int y = 25;

        //this entire for loop is what places the buttons into a 4x4 grid and adds the functionality to the buttons
        //did leave out the decimal button and the delete button
        for(int i = 0; i < numButtons.length; i++){
            numButtons[i] = new JButton(buttonLabels[i]);//giving the buttons their respective values from a list
            
            final int index = i;
            
            numButtons[i].setBackground(Color.ORANGE);
            
            numButtons[i].setOpaque(true);
            numButtons[i].setBorderPainted(false);
            numButtons[i].setSize(25, 25);
            numButtons[i].setBounds(x, y, 73, 75);
            numButtons[i].setFont(NumFont);
            
            x += 75;
            if(i == 3){
                y +=76;
                x = 0;
            }
            if(i == 7){
                y += 76;
                x = 0;
            }
            if(i == 11){
                y+=76;
                x = 0;
            }
            NumJPanel.add(numButtons[i]);
            
            //addActionListerner is what makes the buttons clickable and adds the functionality to the buttons
            //this part of the code has to be inside the lambda function to work
            numButtons[i].addActionListener(e -> {
                String buttonText = numButtons[index].getText();
                if(buttonText.equals("C")){
                    textField.setText("");
                }else if(buttonText.equals("+") || buttonText.equals("/") || buttonText.equals("-") || buttonText.equals("*")){
                    num1 = Double.valueOf(textField.getText());//getting the first number
                    operater = buttonText;
                    textField.setText("");// i clear out the tecxtfeild so its easier for me to split the 2 numbers apart
                }else if(buttonText.equals("=")){
                    num2 = Double.valueOf(textField.getText());
                    switch(operater){
                        case "+":
                            result = num1 + num2;
                            
                            break;
                        case "/":
                            result = num1/num2;
                            
                            break;
                        case "-":
                            result = num1 - num2;
                            
                            break;
                        case "*":
                            result = num1*num2;
                            
                            break;
                    }
                    textField.setText(String.valueOf(result));
                    //this is where the history is written to a file
                    try {
                        writeHistory(Calc_History(num1, num2, operater, result));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    num1 = result;
                }else{
                    textField.setText(textField.getText() + buttonText);
                    
                }
            });
            
            
        }
            this.setVisible(true);//makes the app visible
            this.add(NumJPanel);//adds the panel to the frame
            this.add(textField);//adds the textfield to the frame
        }

        //returns what the program is gpiong to write to the history file
        String Calc_History(Double num1,Double num2,String operater,Double result) throws IOException{
            return num1 + " " + operater + " " + num2 + " = " + result;
        }

        //writes the history to a file
        public static void writeHistory(String history) throws IOException{
            try{
                PrintWriter writer = new PrintWriter(new FileWriter("History.txt", true));
                writer.println(history);
                writer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    
    
    
    

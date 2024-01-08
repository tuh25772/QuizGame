import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{

    //Array to hold questions
    String[] questions = {
                            "Which of these U.S. states does NOT border Canada?",
                            "Which of these countries was NOT a part of the Soviet Union?",
                            "Which of these countries was NEVER part of the British Empire?",
                            "Which of these cities is NOT in India?"
                         };

    //2D array to hold options
    String[][] options = {
                                {"Alaska", "Indiana", "Maine", "Minnesota"},
                                {"Belarus", "Georgia", "Poland", "Ukraine"},
                                {"Ireland", "Kenya", "New Zealand", "Thailand"},
                                {"Bangalore", "Bhopal", "Chennai", "Dhaka"}
                        };

    //Array to hold correct answers
    char[] answers = {
                        'B',
                        'C',
                        'D',
                        'D'
                    };

    //Hold guesses
    char guess;

    //Hold answer
    char answer;

    //Index
    int index;

    //Hold correct num of guesses
    int correct_guesses = 0;

    //total num of questions
    int total_questions = questions.length;

    //hold results
    int results;

    //hold timer
    int seconds = 10;

            //GUI components
    JFrame frame = new JFrame();
    JTextField textField = new JTextField();        //Hold correct question
    JTextArea textArea = new JTextArea();          //Hold current question
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
            //Label Answer Stuff
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel()   ;
            //Label for Timer
    JLabel time_label = new JLabel();
            //Label seconds countdown
    JLabel seconds_left = new JLabel();
            //Calculate results
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();


    //start timer every 1 second
    Timer delay = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0){
                displayAnswer();    //display answer and disable all answers
            }
        }
    });




    //Constructor
    public Quiz(){

        //Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 600);
        frame.getContentPane().setBackground(new Color(0xEC9EC0));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //Hold num questions
                //bounds of text
        textField.setBounds(0, 0, 650, 70);
                //BG of txt
        textField.setBackground(new Color(25, 25, 25));
                //Foreground color
        textField.setForeground(new Color(25, 255, 0));
                //Font and size
        textField.setFont(new Font("Bodoni", Font.PLAIN, 25));
                //set border
        textField.setBorder(BorderFactory.createBevelBorder(1));
                //horizontal allignment
        textField.setHorizontalAlignment(JTextField.CENTER);
                //not editable
        textField.setEditable(false);


        //Text Area stuff
        textArea.setBounds(0, 50, 650, 65);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(25, 255, 0));
        textArea.setFont(new Font("Bodoni", Font.PLAIN, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        //Button A
        buttonA.setBounds(30,120, 100, 100);
        buttonA.setFont(new Font("Bodoni", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");
        buttonA.setForeground(new Color(0xA91B60));

        //Button B
        buttonB.setBounds(30, 225, 100, 100);
        buttonB.setFont(new Font("Bodoni", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");
        buttonB.setForeground(new Color(0xA91B60));


        //Button C
        buttonC.setBounds(30,330, 100, 100);
        buttonC.setFont(new Font("Bodoni", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");
        buttonC.setForeground(new Color(0xA91B60));


        //Button D
        buttonD.setBounds(30,435, 100, 100);
        buttonD.setFont(new Font("Bodoni", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");
        buttonD.setForeground(new Color(0xA91B60));

        //Answer labels
        answer_labelA.setBounds(140, 120, 500, 100);
        answer_labelA.setBackground(new Color(50, 50, 50));
        answer_labelA.setForeground(Color.BLUE);
        answer_labelA.setFont(new Font("Bodoni", Font.PLAIN, 35));

        answer_labelB.setBounds(140, 225, 500, 100);
        answer_labelB.setBackground(new Color(50, 50, 50));
        answer_labelB.setForeground(Color.BLUE);
        answer_labelB.setFont(new Font("Bodoni", Font.PLAIN, 35));

        answer_labelC.setBounds(140, 330, 500, 100);
        answer_labelC.setBackground(new Color(50, 50, 50));
        answer_labelC.setForeground(Color.BLUE);
        answer_labelC.setFont(new Font("Bodoni", Font.PLAIN, 35));

        answer_labelD.setBounds(140, 435, 500, 100);
        answer_labelD.setBackground(new Color(50, 50, 50));
        answer_labelD.setForeground(Color.BLUE);
        answer_labelD.setFont(new Font("Bodoni", Font.PLAIN, 35));


        //Countdown timer
        seconds_left.setBounds(550, 474, 100, 100);
        seconds_left.setBackground(new Color(25, 25, 25));
        seconds_left.setForeground(Color.RED);
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 55));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));  //converts int to string

        //num of questions right
        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);    //dont edit

        //display label for percentage right
        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        //****end of frame****

                //add timer to frame
        frame.add(seconds_left);
                //add answer labels
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
                // add buttons to frame
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
                //add text area to frame
        frame.add(textArea);
                //add textfield to frame
        frame.add(textField);
        frame.setVisible(true);


        //call next question
        nextQuestion();

    }

    //Method to go to another question
    public void nextQuestion(){
        if(index>=total_questions){
            results();
        }
        else{
            textField.setText("Question " + (index + 1));   // retrieve next question
            textArea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);       //first element of array
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);


            // start timer
            delay.start();
        }
    }

    //Implement method
    @Override
    public void actionPerformed(ActionEvent e) {

        //disable buttons temp
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);


        //determine which button is being pressed and check to see if it is a matching answer

            //button A
        if(e.getSource()==buttonA){
            answer = 'A';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
            //button B
        if(e.getSource()==buttonB){
            answer = 'B';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }

            //button C
        if(e.getSource()==buttonC){
            answer = 'C';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }

            //button D
        if(e.getSource()==buttonD){
            answer = 'D';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }

        //Display answer call method
        displayAnswer();

    }

    //Method to display correct answer
    public void displayAnswer(){

            //stop timer
        delay.stop();

            //disable other buttons
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

            //change font to red if answer is incorrect
        if(answers[index] != 'A')
            answer_labelA.setForeground(Color.RED);
        if(answers[index] != 'B')
            answer_labelB.setForeground(Color.RED);
        if(answers[index] != 'C')
            answer_labelC.setForeground(Color.RED);
        if(answers[index] != 'D')
            answer_labelD.setForeground(Color.RED);

        //add delay
        Timer delay = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //go back to original color
                answer_labelA.setForeground(Color.BLUE);
                answer_labelB.setForeground(Color.BLUE);
                answer_labelC.setForeground(Color.BLUE);
                answer_labelD.setForeground(Color.BLUE);

                //reset answer
                answer = ' ';
                seconds = 10;       // original timer start for new question
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);

                // go to next question
                index++;

                //call next question method
                nextQuestion();


            }
        });

        //start timer
        delay.setRepeats(false);
        delay.start();

    }

    //Method to show final results
    public void results(){

        //disable buttons
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        //calculate results
        results = (int)((correct_guesses/(double)total_questions)*100);

        //change text field
        textField.setText("RESULTS!");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        //display correct guesses
        number_right.setText(correct_guesses + "/" + total_questions);

        //display percentage
        percentage.setText(results + "%");

        //add to frame
        frame.add(percentage);
        frame.add(number_right);

    }
}


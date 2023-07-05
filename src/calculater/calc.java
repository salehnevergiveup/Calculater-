package calculater;
import java.awt.Dimension;
import javax.swing.*; 
import java.awt.event.*;
import java.awt.GridLayout;


public class calc extends JFrame{
    private JTextField resultField = new JTextField();
    private JButton[] list =   {
    new JButton("<-"),
    new JButton("C"),
    new JButton("+"),
    new JButton("7"),
    new JButton("8"),
    new JButton("9"),
    new JButton("-"),
    new JButton("4"),
    new JButton("5"),
    new JButton("6"),
    new JButton("*"),
    new JButton("1"),
    new JButton("2"),
    new JButton("3"),
    new JButton("/"),
    new JButton("0"),
    new JButton("."),
    new JButton("="),
    new JButton(""),
    };
    private JPanel buttonsContainer = new JPanel(); 
    private JPanel Container = new JPanel(); 
    private JRadioButton  on =  new JRadioButton("on");
    private JRadioButton off = new JRadioButton("off");
    private ButtonGroup group = new  ButtonGroup();
    private JPanel Radio = new JPanel(); 
    private  String operation; 
    private  double value1,  value2 , result; 
    
    
    public calc() { 
        this.setSize(250 , 380); 
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        group.add(on);
        group.add(off);
        on.setFocusable(false);
        off.setFocusable(false);
        startClose(true);
        Radio.setLayout(new GridLayout(2,1));
        Radio.add(on);
        Radio.add(off);
        buttonsContainer.add(Radio);
        resultField.setPreferredSize(new Dimension(200, 40));
        resultField.setFocusable(false);
        Container.add(resultField);
        Container.add(buttonsContainer);
        buttonsContainer.setLayout(new GridLayout(5,4,  10 ,10));
        this.add(Container);
        //Desgin 
        for(int i  = 0 ; i <  list.length ; i++){ 
            buttonsContainer.add(list[i]);
            list[i].setFocusable(false);
            
        }
        list[18].setEnabled(false);
           
        for(int i  = 0 ; i <  list.length ; i++){ 
             JButton  b = list[i]; 
             list[i].addActionListener(
             new  ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     //general 
                     b.setFocusable(false);
                     //numbers function
                     numbersButtons(b);
                     //remove  back  
                     removeBack(b);
                     //clear  
                     delete(b);
                     //calculations 
                     calculation( b);
                 }
                }
             );
             off.addActionListener(
               new  ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    startClose(false);
                 }
               }
             );
             on.addActionListener(
               new  ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     startClose(true);
                 }
               }
             );
        }
    }
    public  void  EmptyTextField() { 
        resultField.setText("");
    }
    
    public  void  numbersButtons (JButton button)  { 
        switch(button.getText()) { 
            case"1":
            case"2":
            case"3":
            case"4":
            case"5":
            case"6":
            case"7":
            case"8":
            case"9":
            case"0": 
                resultField.setText( resultField.getText() + button.getText());
                break; 
            case ".": 
                if(!resultField.getText().contains("."))
                 resultField.setText(resultField.getText() + button.getText());
                break;
        }
    }
    
    public void delete(JButton button){ 
        if(button.getText().equals("C")) resultField.setText("");
    }
    
    public  void  removeBack(JButton button){ 
        if(button.getText().equals("<-")){  
             String [] str=  resultField.getText().split(""); 
             String  store = ""; 
             if(str.length > 0) { 

                 for(int i = 0;  i <  str.length-1 ; i++) { 
                     store += str[i];
                 }
                 
                 resultField.setText(store);
             }
        }
    }
    
    public void  startClose(boolean v) { 
        for(JButton button :  list){ 
            button.setEnabled(v);
        }  
        if(!v) { 
            EmptyTextField();
        }
        this.value1 = 0; 
        this.value2 = 0; 
        this.result = 0;
        resultField.setEnabled(v);
        list[18].setEnabled(false);
        on.setEnabled(!v);
        off.setEnabled(v);
    } 
    
    public void calculation(JButton button){ 
       try { 
        if(button.getText().equals("+")) { 
            value1 = Double.parseDouble(resultField.getText()); 
            operation = "+"; 
            EmptyTextField();
        } 
        if(button.getText().equals("-")) { 
            value1 = Double.parseDouble(resultField.getText()); 
            operation = "-"; 
            EmptyTextField();
        } 
        if(button.getText().equals("/")) { 
            value1 = Double.parseDouble(resultField.getText()); 
            operation = "/"; 
            EmptyTextField();
        } 
        if(button.getText().equals("*")) { 
            value1 = Double.parseDouble(resultField.getText()); 
            operation = "*"; 
            EmptyTextField();
            
        } 
        if(button.getText().equals("=")) { 
            if(!operation.isEmpty()) {
            value2  =  Double.parseDouble(resultField.getText());
            switch(operation){ 
                case "+": 
                    result = value1 + value2;
                    break; 
                case "-": 
                    result = value1 - value2;
                    break; 
                case "*": 
                    result = value1 * value2; 
                    break; 
                case "/": 
                    if(value2 > 0){ 
                        result = value1/value2; 
                    }else result= 0;
                    
                    break;     
            } 
              resultField .setText(String.valueOf(result));
              value1 =  result; 
            } 
        } 
       } catch(Exception e) {};
    }        
      
}

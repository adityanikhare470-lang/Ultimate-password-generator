import java.awt.*;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.*;

public class UltimatePasswordGenerator extends JFrame {

    JTextField resultField;
    JCheckBox upper, lower, numbers, symbols;
    JLabel strengthLabel;
    JSlider lengthSlider;
    JProgressBar strengthBar;
    JButton copyBtn, saveBtn;

    UltimatePasswordGenerator() {

        setTitle("Ultimate Password Generator");
        setSize(500,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(25,25,25));

        JLabel title = new JLabel("Secure Password Generator");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setBounds(120,20,300,30);

        JLabel lengthLabel = new JLabel("Password Length");
        lengthLabel.setForeground(Color.WHITE);
        lengthLabel.setBounds(40,80,150,25);

        lengthSlider = new JSlider(4,20,10);
        lengthSlider.setBounds(180,80,250,40);

        upper = new JCheckBox("Uppercase");
        lower = new JCheckBox("Lowercase");
        numbers = new JCheckBox("Numbers");
        symbols = new JCheckBox("Symbols");

        upper.setBounds(40,130,120,25);
        lower.setBounds(180,130,120,25);
        numbers.setBounds(320,130,120,25);
        symbols.setBounds(40,160,120,25);

        upper.setBackground(new Color(25,25,25));
        lower.setBackground(new Color(25,25,25));
        numbers.setBackground(new Color(25,25,25));
        symbols.setBackground(new Color(25,25,25));

        upper.setForeground(Color.WHITE);
        lower.setForeground(Color.WHITE);
        numbers.setForeground(Color.WHITE);
        symbols.setForeground(Color.WHITE);

        resultField = new JTextField();
        resultField.setBounds(100,200,300,30);

        copyBtn = new JButton("Copy");
        copyBtn.setBounds(120,240,100,30);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(260,240,100,30);

        strengthBar = new JProgressBar(0,100);
        strengthBar.setBounds(100,290,300,25);

        strengthLabel = new JLabel("Strength");
        strengthLabel.setForeground(Color.WHITE);
        strengthLabel.setBounds(220,320,100,30);

        panel.add(title);
        panel.add(lengthLabel);
        panel.add(lengthSlider);
        panel.add(upper);
        panel.add(lower);
        panel.add(numbers);
        panel.add(symbols);
        panel.add(resultField);
        panel.add(copyBtn);
        panel.add(saveBtn);
        panel.add(strengthBar);
        panel.add(strengthLabel);

        add(panel);

        lengthSlider.addChangeListener(e -> generatePassword());

        copyBtn.addActionListener(e -> {
            resultField.selectAll();
            resultField.copy();
            JOptionPane.showMessageDialog(this,"Password Copied!");
        });

        saveBtn.addActionListener(e -> savePassword());

        setVisible(true);
    }

    void generatePassword() {

        String chars="";

        if(upper.isSelected())
            chars+="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if(lower.isSelected())
            chars+="abcdefghijklmnopqrstuvwxyz";

        if(numbers.isSelected())
            chars+="0123456789";

        if(symbols.isSelected())
            chars+="@#$%&*";

        if(chars.equals(""))
            return;

        int length=lengthSlider.getValue();

        Random rand=new Random();
        String password="";

        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(chars.length());
            password+=chars.charAt(index);
        }

        resultField.setText(password);

        checkStrength(password);
    }

    void checkStrength(String password)
    {
        int score=password.length()*5;

        strengthBar.setValue(score);

        if(score<40)
        {
            strengthLabel.setText("Weak");
            strengthBar.setForeground(Color.RED);
        }
        else if(score<70)
        {
            strengthLabel.setText("Medium");
            strengthBar.setForeground(Color.ORANGE);
        }
        else
        {
            strengthLabel.setText("Strong");
            strengthBar.setForeground(Color.GREEN);
        }
    }

    void savePassword()
    {
        try{
            FileWriter fw=new FileWriter("passwords.txt",true);
            fw.write(resultField.getText()+"\n");
            fw.close();

            JOptionPane.showMessageDialog(this,"Password Saved!");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args)
    {
        new UltimatePasswordGenerator();
    }
}

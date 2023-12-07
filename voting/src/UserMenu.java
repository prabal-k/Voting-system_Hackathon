import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;




public class UserMenu {
    JFrame frame = new JFrame();
    JButton voteButton = new JButton();
    // JButton button2 = new JButton("Vote Eligibility");
    JButton partiesButton = new JButton();

    JButton countButton = new JButton();

    JButton backButton = new JButton();

    JLabel titleLabel = new JLabel("Nepal Election Commission");

    JLabel partiesLabel = new JLabel("Parties");
    JLabel voteLabel = new JLabel("Vote");

    JLabel countLabel = new JLabel("Vote Count");


    //Constructor
    UserMenu()
    {

        //Buttons and Labels
        voteButton.setBounds(150,200,100,100);
        voteButton.setFocusPainted(false);
        voteButton.setBorderPainted(false);
        voteButton.setBackground(new Color(0,0,0,0));
        voteButton.setOpaque(false);


        partiesButton.setBounds(350,200,  100,100);
        partiesButton.setFocusPainted(false);
        partiesButton.setBackground(new Color(0,0,0,0));
        partiesButton.setBorderPainted(false);
        partiesButton.setOpaque(false);

        countButton.setBounds(250, 350, 100, 100);
        countButton.setFocusPainted(false);
        countButton.setBorderPainted(false);
        countButton.setBackground(new Color(0,0,0,0));
        countButton.setOpaque(false);

        backButton.setBounds(2,20,80,40);
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(0,0,0,0));
        backButton.setBorderPainted(false);
        backButton.setOpaque(false);

        titleLabel.setBounds(180, 50, 250, 40);
        Font labelFont = titleLabel.getFont();
        titleLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));

        partiesLabel.setBounds(380,170,50,50);

        voteLabel.setBounds(190,170,50,50);

        countLabel.setBounds(270, 330, 100, 50);


        //Adding Image to a Button
        try {
            Image voteImg = ImageIO.read(getClass().getResource("Images\\vote.png"));
            voteButton.setIcon(new ImageIcon(voteImg));

            Image partiesImg = ImageIO.read(getClass().getResource("Images\\parties.png"));
            partiesButton.setIcon(new ImageIcon(partiesImg));

            Image backImg = ImageIO.read(getClass().getResource("Images\\back3.png"));
            backButton.setIcon(new ImageIcon(backImg));

            Image countImg = ImageIO.read(getClass().getResource("Images\\count.png"));
            countButton.setIcon(new ImageIcon(countImg));

        } catch (Exception ex) {
            System.out.println(ex);
        }


        //adding elements to Frame
        frame.add(titleLabel );
        frame.add(voteButton);
        frame.add(partiesButton);
        frame.add(countButton);
        frame.add(partiesLabel);
        frame.add(voteLabel);
        frame.add(backButton);
        frame.add(countLabel);


        //Frame setting
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(false);
        frame.getContentPane().setBackground(new Color(135,206,250));
        frame.setLocationRelativeTo(null);
    }
}

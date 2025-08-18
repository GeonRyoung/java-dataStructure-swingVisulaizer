package datastructure.link.linkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

public class LinkedVisualizer extends JFrame {

    private LinkedList<Integer> link;
    private static final int INITIAL_SIZE = 5;
    private LinkedPanel linkedPanel;
    private JButton initButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JTextField indexField;
    private JTextField valueField;

    public LinkedVisualizer(){
        setTitle("LinkedList");
        setSize(800,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        link = new LinkedList<>();
        initializeLinked();

        linkedPanel = new LinkedPanel();
        add(linkedPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));

        JPanel buttonPanel = new JPanel();
        initButton = new JButton("초기화");
        insertButton = new JButton("삽입");
        deleteButton = new JButton("삭제");
        buttonPanel.add(initButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("인덱스"));
        indexField = new JTextField(3);
        inputPanel.add(indexField);
        inputPanel.add(new JLabel("값"));
        valueField = new JTextField(3);
        inputPanel.add(valueField);

        controlPanel.add(buttonPanel);
        controlPanel.add(inputPanel);

        add(controlPanel,BorderLayout.SOUTH);

        initButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeLinked();
                linkedPanel.repaint();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int index = Integer.parseInt(indexField.getText());
                    int value = Integer.parseInt(valueField.getText());

                    if(index >= 0 && index <= link.size()){
                        link.add(index, value);
                        linkedPanel.repaint();
                    } else{
                        JOptionPane.showMessageDialog(LinkedVisualizer.this
                                ,"유효한 엔덱스를 입력해주세요.");
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(LinkedVisualizer.this
                    , "인덱스와 값에 숫자를 입력해주세요.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int index = Integer.parseInt(indexField.getText());

                    if(index >= 0 && index < link.size()){
                        link.remove(index);
                        linkedPanel.repaint();
                    } else{
                        JOptionPane.showMessageDialog(LinkedVisualizer.this
                        ,"유효한 인덱스를 입력해주세요.");
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(LinkedVisualizer.this
                    , "인덱스에 숫자를 입력해주세요.");
                }
            }
        });
    }

    private void initializeLinked(){
        link.clear();
        Random random = new Random();
        for(int i = 0 ; i < INITIAL_SIZE; i++){
            link.add(random.nextInt(100) + 1);
        }
    }

    private class LinkedPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int circleWidth = 50, circleHeight = 50, startX = 30, startY = 60, padding = 20;
            for(int i = 0 ;i < link.size(); i++) {
                int currentX = startX + i * (circleWidth + padding);
                g.setColor(Color.WHITE);
                g.fillOval(currentX, startY, circleWidth, circleHeight);
                g.setColor(Color.BLACK);
                g.drawOval(currentX, startY, circleWidth, circleHeight);
                String value = String.valueOf(link.get(i));
                FontMetrics fm = g.getFontMetrics();
                int stringWidth = fm.stringWidth(value);
                int stringX = currentX + (circleWidth - stringWidth) / 2;
                g.drawString(value, stringX, startY + 30);
                String index = "[" + i + "]";
                int indexWidth = fm.stringWidth(index);
                int indexX = currentX + (circleWidth - indexWidth) / 2;
                g.drawString(index, indexX, startY + circleHeight + 15);

                if(i < link.size() -1 ){
                    int arrowY = startY + circleHeight / 2;
                    int lineStartX =currentX + circleWidth;
                    int lineEndX = lineStartX + padding;

                    g.drawLine(lineStartX, arrowY, lineEndX, arrowY);
                    g.drawLine(lineEndX, arrowY, lineEndX - 5, arrowY - 5);
                    g.drawLine(lineEndX, arrowY, lineEndX - 5, arrowY + 5);
                }
            }
        }
    }

    public static void main(String[] args) {
        // main 메소드도 익명 내부 클래스 방식으로 수정
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LinkedVisualizer().setVisible(true);
            }
        });
    }

}

package link.arraylist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ArrayVisualizer extends JFrame {

    private ArrayList<Integer> array;
    private static final int INITIAL_SIZE = 5;
    private ArrayPanel arrayPanel;
    private JButton initButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JTextField indexField;
    private JTextField valueField;

    public ArrayVisualizer(){
        setTitle("ArrayList");
        setSize(800,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        array = new ArrayList<>(INITIAL_SIZE);
        initializeArray();

        arrayPanel = new ArrayPanel();
        add(arrayPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));

        JPanel buttonPanel = new JPanel();
        initButton = new JButton("배열 초기화");
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

        add(controlPanel, BorderLayout.SOUTH);

        initButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeArray();
                arrayPanel.repaint();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(indexField.getText());
                    int value = Integer.parseInt(valueField.getText());

                    if (index >= 0 && index <= array.size()) {
                        array.add(index, value);
                        arrayPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(ArrayVisualizer.this
                                , "유효한 인덱스를 입력해주세요.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ArrayVisualizer.this
                            , "인덱스와 값에 숫자를 입력해주세요.");
                }
            }
         });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int index = Integer.parseInt(indexField.getText());

                    if (index >= 0 && index <= array.size()) {
                        array.remove(index);
                        arrayPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(ArrayVisualizer.this
                                , "유효한 인덱스를 입력해주세요.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ArrayVisualizer.this, "인덱스에 숫자를 입력해주세요.");
                }
            }
        });
    }

    private void initializeArray() {
        array.clear();
        Random random = new Random();
        for(int i = 0 ;i < INITIAL_SIZE; i++){
            array.add(random.nextInt(100) + 1);
        }
    }

    private class ArrayPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int boxWidth = 50, boxHeight = 50, startX = 30, startY = 60, padding = 20;
            for(int i = 0 ;i < array.size(); i++){
                int currentX = startX + i * (boxWidth + padding);
                g.setColor(Color.WHITE);
                g.fillRect(currentX, startY, boxWidth, boxHeight);
                g.setColor(Color.BLACK);
                g.drawRect(currentX, startY, boxWidth, boxHeight);
                String value = String.valueOf(array.get(i));
                FontMetrics fm = g.getFontMetrics();
                int stringWidth = fm.stringWidth(value);
                int stringX = currentX + (boxWidth - stringWidth) / 2;
                g.drawString(value, stringX,startY + 30);
                String index = "[" + i + "]";
                int indexWidth = fm.stringWidth(index);
                int indexX = currentX + (boxWidth - indexWidth) / 2;
                g.drawString(index, indexX, startY + boxHeight + 15);
            }

        }
    }

    public static void main(String[] args) {
        // main 메소드도 익명 내부 클래스 방식으로 수정
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArrayVisualizer().setVisible(true);
            }
        });
    }
}

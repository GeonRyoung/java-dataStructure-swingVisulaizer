package datastructure.deque;

import datastructure.map.MapVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class DequeVisualizer extends JFrame {
    Deque<Integer> buckets = new ArrayDeque<>();

    private DequePanel dequePanel;
    private JButton pushButton;
    private JButton popButton;
    private JButton offerButton;
    private JButton pollButton;
    private JTextField valueTextField;

    public DequeVisualizer(){
        setTitle("DequeVisualizer");
        setSize(800,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        dequePanel = new DequePanel();
        add(dequePanel,BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));

        JPanel insertPanel = new JPanel();
        pushButton = new JButton("push");
        insertPanel.add(pushButton);
        insertPanel.add(new JLabel("VALUE"));
        valueTextField = new JTextField(3);
        insertPanel.add(valueTextField);
        offerButton = new JButton("offer");
        insertPanel.add(offerButton);

        JPanel removePanel = new JPanel();
        popButton = new JButton("pop");
        pollButton = new JButton("poll");
        removePanel.add(popButton);
        removePanel.add(pollButton);

        controlPanel.add(insertPanel);
        controlPanel.add(removePanel);
        add(controlPanel,BorderLayout.SOUTH);

        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(valueTextField.getText());
                    buckets.push(value);
                    dequePanel.repaint(); // dequePanel 인스턴스 사용
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DequeVisualizer.this, "유효한 숫자를 입력하세요.");
                }
            }
        });

        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buckets.pop();
                    dequePanel.repaint();
                } catch (java.util.NoSuchElementException ex) {
                    JOptionPane.showMessageDialog(DequeVisualizer.this
                            , "deque에 값이 없습니다.");
                }
            }
        });

        offerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(valueTextField.getText());
                    buckets.offer(value);
                    dequePanel.repaint(); // dequePanel 인스턴스 사용
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DequeVisualizer.this
                            , "유효한 숫자를 입력하세요.");
                }
            }
        });

        pollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buckets.poll();
                    dequePanel.repaint();
                } catch (java.util.NoSuchElementException ex) {
                    JOptionPane.showMessageDialog(DequeVisualizer.this, "deque에 값이 없습니다.");
                }
            }
        });
    }

    private class DequePanel extends JPanel{

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            int boxSize = 40;
            int currentX = 30;
            int currentY = 50;

            Iterator<Integer> iterator = buckets.iterator();
            while (iterator.hasNext()) {
                Integer value = iterator.next();

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(currentX, currentY, boxSize, boxSize);
                g.setColor(Color.BLACK);
                g.drawRect(currentX, currentY, boxSize, boxSize);

                String text = String.valueOf(value);
                int textWidth = g.getFontMetrics().stringWidth(text);
                g.drawString(text, currentX + (boxSize - textWidth) / 2, currentY + boxSize / 2 + 5);

                currentX += boxSize + 10;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DequeVisualizer().setVisible(true);
            }
        });
    }
}


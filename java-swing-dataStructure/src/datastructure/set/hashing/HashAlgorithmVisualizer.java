package datastructure.set.hashing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class HashAlgorithmVisualizer extends JFrame {

    private LinkedList<Object>[] buckets;
    private static final int INITIAL_SIZE = 5;
    private int capacity = 5;

    private HashPanel hashPanel;
    private JButton searchButton;
    private JButton hashConvertorButton;
    private JButton capacityButton;
    private JTextField valueField;
    private JTextField capacityField;
    private JTextField searchField;

    public HashAlgorithmVisualizer(){
        setTitle("HashAlgorithm");
        setSize(800,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeBuckets(INITIAL_SIZE);

        hashPanel = new HashPanel();
        add(hashPanel,BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3,1));

        JPanel capacityPanel = new JPanel();
        capacityPanel.add(new JLabel("배열 크기"));
        capacityField = new JTextField(3);
        capacityPanel.add(capacityField);
        capacityButton = new JButton("배열 생성");
        capacityPanel.add(capacityButton);

        JPanel valuePanel = new JPanel();
        valuePanel.add(new JLabel("값"));
        valueField = new JTextField(3);
        valuePanel.add(valueField);
        hashConvertorButton = new JButton("추가");
        valuePanel.add(hashConvertorButton);

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("인덱스"));
        searchField = new JTextField(3);
        searchPanel.add(searchField);
        searchButton = new JButton("조회");
        searchPanel.add(searchButton);

        controlPanel.add(capacityPanel);
        controlPanel.add(valuePanel);
        controlPanel.add(searchPanel);

        add(controlPanel, BorderLayout.SOUTH);

        hashConvertorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buckets == null){
                    JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                    , "먼저 배열 크기를 입력하세요.");
                    return;
                }
                try{
                    Object value = valueField.getText();
                    if(buckets[hashIndex(value)].isEmpty()){
                        add(buckets, value);
                        valueField.setText("");
                        hashPanel.repaint();
                    }else {
                        JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                        , "해싱 충돌이 일어났습니다.");
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                    , "올바른 숫자를 입력해주세요.");
                }
            }
        });

        capacityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    capacity = Integer.parseInt(capacityField.getText());
                    if (capacity > 0) {
                        initializeBuckets(capacity);
                        hashPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                                , "배열 크기는 1 이상이어야 합니다.");
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                            , "올바른 숫자 값을 입력해주세요.");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Object searchValue = searchField.getText();
                    boolean isExist = contains(buckets, searchValue);
                    if(isExist){
                        JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                        , "숫자 " + searchValue + "가 존재합니다.");
                    } else{
                        JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                                , "숫자 " + searchValue + "가 존재하지 않습니다..");
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(HashAlgorithmVisualizer.this
                    , "올바른 숫자를 입력해주세요.");
                }
            }
        });
    }

    private void initializeBuckets(int capacity) {
        buckets = new LinkedList[capacity];
        for(int i = 0; i < capacity; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    private int hashIndex(int value){
        return value % capacity;
    }

    private int hashIndex(Object value){
        int hashCode = value.hashCode();
        return hashCode % capacity;
    }

    private void add(LinkedList<Object>[] buckets, Object value){
        int hashIndex =hashIndex(value);
        LinkedList<Object> bucket = buckets[hashIndex]; // O(1)
        if(!bucket.contains(value)){ //O(n)
            bucket.add(value);
        }
    }

    private boolean contains(LinkedList<Object>[] buckets, Object searchValue){
        int hashIndex = hashIndex(searchValue);
        LinkedList<Object> bucket = buckets[hashIndex];
        return bucket.contains(searchValue);
    }

    private class HashPanel extends JPanel{

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int boxWidth = 60, boxHeight = 60, startX = 30, startY = 60;

            for(int i = 0 ;i < capacity; i++){
                int currentX = startX + i * (boxWidth);
                g.setColor(Color.WHITE);
                g.fillRect(currentX, startY, boxWidth, boxHeight);
                g.setColor(Color.BLACK);
                g.drawRect(currentX, startY, boxWidth, boxHeight);

                LinkedList<Object> bucket = buckets[i];
                String value = "";
                if(!bucket.isEmpty()) {
                    value = String.valueOf(bucket);
                }

                FontMetrics fm = g.getFontMetrics();
                int stringWidth = fm.stringWidth(value);
                g.drawString(value, currentX + (boxWidth - stringWidth) / 2,  startY + 35);
            }
        }
    }

    public static void main(String[] args) {
        // main 메소드도 익명 내부 클래스 방식으로 수정
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HashAlgorithmVisualizer().setVisible(true);
            }
        });
    }


}

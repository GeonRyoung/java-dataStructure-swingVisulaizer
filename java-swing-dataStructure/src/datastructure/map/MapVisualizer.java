package datastructure.map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MapVisualizer extends JFrame {

    private LinkedHashMap<String,String> buckets = new LinkedHashMap<>();
    private MapPanel mapPanel;
    private JButton initButton;
    private JTextField keyField;
    private JTextField valueField;

    MapVisualizer(){
        setTitle("MapVisualizer");
        setSize(800,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mapPanel = new MapPanel();
        add(mapPanel,BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("KEY"));
        keyField = new JTextField(3);
        inputPanel.add(keyField);

        inputPanel.add(new JLabel("VALUE"));
        valueField = new JTextField(3);
        inputPanel.add(valueField);

        initButton = new JButton("생성");

        controlPanel.add(inputPanel);
        controlPanel.add(initButton);

        add(controlPanel, BorderLayout.SOUTH);

        initButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key =  keyField.getText();
                String value = valueField.getText();
                if( key.isEmpty()){
                    JOptionPane.showMessageDialog(MapVisualizer.this
                    , "KEY 값을 입력하세요");
                } else if( value.isEmpty()){
                    JOptionPane.showMessageDialog(MapVisualizer.this
                            , "VALUE 값을 입력하세요");
                }
                buckets.put(key, value);
                mapPanel.repaint();
            }
        });
    }

    private class MapPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int boxSize = 60;
            int x = 20;
            int y = 20;

            g.setColor(Color.WHITE);
            g.fillRect(x, y, boxSize, boxSize);
            g.fillRect(x, y+60, boxSize, boxSize);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, boxSize, boxSize);
            g.drawRect(x, y+60, boxSize, boxSize);


            g.drawString("Key" , x + 10, y + 30);
            g.drawString("Value" , x + 10, y + 90);
            x += boxSize;

            Set<Map.Entry<String, String>> entries = buckets.entrySet();
            for (Map.Entry<String, String> entry : entries) {

                g.setColor(Color.WHITE);
                g.fillRect(x, y, boxSize, boxSize);
                g.fillRect(x, y+60, boxSize, boxSize);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, boxSize, boxSize);
                g.drawRect(x, y+60, boxSize, boxSize);


                g.drawString(entry.getKey(), x + 10, y + 30);
                g.drawString(entry.getValue(), x + 10, y + 90);

                x += boxSize; //
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MapVisualizer().setVisible(true);
            }
        });
    }
}

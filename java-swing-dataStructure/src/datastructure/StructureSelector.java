package datastructure;

import datastructure.link.arraylist.ArrayVisualizer;
import datastructure.link.linkedList.LinkedVisualizer;
import datastructure.set.hashing.HashAlgorithmVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StructureSelector extends JFrame {

    private JButton array;
    private JButton linkedList;
    private JButton hashAlgorithm;

    StructureSelector(){
        setTitle("StructureSelector");
        setSize(800,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 80));

        array = new JButton("ArrayList");
        array.setPreferredSize(new Dimension(100,100));
        this.add(array);
        linkedList = new JButton("LinkedList");
        linkedList.setPreferredSize(new Dimension(100,100));
        this.add(linkedList);
        hashAlgorithm = new JButton("HashAlgorithm");
        hashAlgorithm.setPreferredSize(new Dimension(100,100));
        this.add(hashAlgorithm);

        array.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new ArrayVisualizer().setVisible(true);
                    }
                });
                StructureSelector.this.dispose();
            }
        });

        linkedList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new LinkedVisualizer().setVisible(true);
                    }
                });
                StructureSelector.this.dispose();
            }
        });

        hashAlgorithm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new HashAlgorithmVisualizer().setVisible(true);
                    }
                });
                StructureSelector.this.dispose();
            }
        });
    }


    public static void main(String[] args) {
        // main 메소드도 익명 내부 클래스 방식으로 수정
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StructureSelector().setVisible(true);
            }
        });
    }
}

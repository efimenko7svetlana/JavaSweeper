package Interface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersFrame extends JFrame {

    public static JButton start;
    public JLabel label;
    public static JTextField fieldName;

    public PlayersFrame() {
        super("Ввод игрока");
        // Задаём менеджер расположения для этой панели
        GridLayout gl = new GridLayout(2, 2, 5, 10);
        this.setLayout(gl);

        label = new JLabel("Введите имя игрока:");
        this.fieldName = new JTextField(20);

        JLabel result = new JLabel(" ");
        this.start = new JButton("Играть");
        // Добавляем компоненты в панель
        this.add(label);
        this.add(fieldName);
        this.add(result);
        this.add(start);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JavaSweeper js = new JavaSweeper();
                PlayersFrame.this.dispose();
            }
        });
    }

    //настройка фрейма
    public static void frameSetting() {
        PlayersFrame plframe = new PlayersFrame();

        //plframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plframe.setVisible(true);
        plframe.setSize(300, 100);
        plframe.setLocationRelativeTo(null);

    }

    public static String getFieldName() {
        return fieldName.getText();
    }

}

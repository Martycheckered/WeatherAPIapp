package com.myprojects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameDrawing {
    final static String   TITLE_MESSAGE = "Weather now";

    public FrameDrawing () {
        JFrame jframe = getJFrame();
        JPanel jPanel= getJPanel(jframe);

        jframe.add(jPanel);


        jPanel.revalidate();
    }


    static JFrame getJFrame () {
        JFrame jframe = new JFrame();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jframe.setBounds(dimension.width/2-250, dimension.height/2-75, 500,150);
        jframe.setTitle("Weather APP");

        ImageIcon imageIcon = new ImageIcon("src/main/resources/images/android_openweathermap.png");
        Image image = imageIcon.getImage();
        jframe.setIconImage(image);
        return jframe;
    }

    static JPanel getJPanel (JFrame jframe) {
        JPanel jPanel= new JPanel();
        Font font = new Font("Arial", Font.BOLD, 15);
        jPanel.setFont(font);
        jPanel.add(new JLabel("Enter city name to get actual weather: "));

        JTextField field = new JTextField(20);
        jPanel.add(field);

        field.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                String inputCityName = field.getText();
                String jsonResponce = HttpClient.throwRequest(inputCityName);

                UIManager UI=new UIManager();
                UI.put("OptionPane.background", Color.LIGHT_GRAY);
                UI.put("Panel.background", Color.pink);
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));

                if (jsonResponce.equals("Incorrect result of API request")) {
                    JOptionPane.showMessageDialog(jframe,
                            jsonResponce, TITLE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    String parsedJSON = Serialize.parseJSONtoPOJO(jsonResponce);
                    final ImageIcon icon =
                            new ImageIcon(("src/main/resources/images/"+Serialize.getIconNameFromJSON(jsonResponce)+".png"));

                    JOptionPane.showMessageDialog(jframe,
                            parsedJSON, TITLE_MESSAGE, JOptionPane.INFORMATION_MESSAGE, icon);

                }


            }
        });

        return  jPanel;
    }




}

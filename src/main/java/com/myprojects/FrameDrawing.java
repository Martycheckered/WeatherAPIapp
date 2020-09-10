package com.myprojects;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojects.WeatherAPP_POJOs.RequestPOJO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class FrameDrawing {

    public FrameDrawing () {
        JFrame jframe = getJFrame();
        JPanel jPanel= new JPanel();
        jframe.add(jPanel);

        Font font = new Font("Arial", Font.BOLD, 15);
        jPanel.setFont(font);
        jPanel.add(new JLabel("Enter city name to get actual weather: "));

        JTextField field = new JTextField(20);
        jPanel.add(field);

        // ImageIcon  wpIcon = new ImageIcon("android_openweathermap.png");


        //final ImageIcon icon ;

        final  String   TITLE_message = "Weather now";

        field.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                String inputCityName = field.getText();
                String jsonRequest = HttpClientExample.throwRequest(inputCityName);

                UIManager UI=new UIManager();
                UI.put("OptionPane.background", Color.LIGHT_GRAY);
                UI.put("Panel.background", Color.pink);
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));

                if (jsonRequest.equals("Incorrect result of API request")) {
                    JOptionPane.showMessageDialog(jframe,
                            jsonRequest, TITLE_message, JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    String parsedJSON = Serialize.parseJSONtoPOJO(jsonRequest);
                    final ImageIcon icon =
                            new ImageIcon(("src/main/resources/images/"+Serialize.getIconNameFromJSON(jsonRequest)+".png"));

                    JOptionPane.showMessageDialog(jframe,
                            parsedJSON, TITLE_message, JOptionPane.INFORMATION_MESSAGE, icon);

                }



            }
        });
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






}

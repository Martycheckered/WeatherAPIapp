package com.myprojects;

import com.myprojects.Services.Serializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameDrawer extends  JFrame {
    final static String   TITLE_MESSAGE = "Weather now";
     private ImageIcon imageIcon = new ImageIcon("src/main/resources/images/android_openweathermap.png");
      JTextField field = new JTextField(20);
     public static String iconName;

    public FrameDrawer () {

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(dimension.width/2-250, dimension.height/2-75, 500,150);
        this.setTitle("Weather APP");

        Image image = imageIcon.getImage();
        this.setIconImage(image);

        JPanel jPanel= getJPanel(this);
        this.add(jPanel);

        jPanel.revalidate();
    }


     JPanel getJPanel (FrameDrawer frameDrawer) {
        JPanel jPanel= new JPanel();
        Font font = new Font("Arial", Font.BOLD, 15);
        jPanel.setFont(font);
        jPanel.add(new JLabel("Enter city name to get actual weather: "));


        jPanel.add(field);

        field.addActionListener(new MyFieldListener(frameDrawer));

        return  jPanel;
    }

 class MyFieldListener implements ActionListener {

     private FrameDrawer frameDrawer;

     public MyFieldListener(FrameDrawer frameDrawer) {
         this.frameDrawer = frameDrawer;
     }


     public void actionPerformed(ActionEvent e) {
         // Отображение введенного текста
         String inputCityName = field.getText();
         String jsonResponce = HttpClient.throwRequest(inputCityName);

         UIManager UI = new UIManager();
         UI.put("OptionPane.background", Color.LIGHT_GRAY);
         UI.put("Panel.background", Color.pink);
         UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));

         Serializer serializer = Serializer.getInstance();

         if (jsonResponce.equals("Incorrect result of API request")) {
             JOptionPane.showMessageDialog(frameDrawer,
                     jsonResponce, TITLE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
         }
         else {
             String parsedJSON = serializer.parseJSON(jsonResponce);
             final ImageIcon icon =
                     new ImageIcon(("src/main/resources/images/"+ iconName+".png"));

             JOptionPane.showMessageDialog(frameDrawer,
                     parsedJSON, TITLE_MESSAGE, JOptionPane.INFORMATION_MESSAGE, icon);

         }
     }
 }




}

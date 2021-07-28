package javaeems.chapter5.libraryclient;

import javax.swing.*;
import java.awt.*;
import javax.json.JsonObject;

public class BookListRenderer extends JLabel implements ListCellRenderer<Object> {

     @Override
     public Component getListCellRendererComponent(
       JList<?> list,           
       Object value,            
       int index,               
       boolean isSelected,      
       boolean cellHasFocus)  {
         JsonObject jsono = (JsonObject) value;
         this.setText(jsono.getString("title"));
         
         if (isSelected) {
             setBackground(list.getSelectionBackground());
             setForeground(list.getSelectionForeground());
         } else {
             setBackground(list.getBackground());
             setForeground(list.getForeground());
         }
         setEnabled(list.isEnabled());
         setFont(list.getFont());
         setOpaque(true);
         return this;
     }
 }
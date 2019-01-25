package kz.kaznitu.lessons.spm;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import kz.kaznitu.lessons.mod.Registration;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.sql.*;


@Controller
public class listener {
    private static String card;
    boolean status=true;
    public static String SS="";




   public void pauseRead()  {
        if (status == false) {
            System.out.println("попало");
            status=true;

            if (serialPort != null || serialPort.isOpened ()) {
                System.out.println("Не пустой!");
                try {
                    serialPort.purgePort (1);
                    serialPort.purgePort (2);
                    serialPort.closePort();
                    System.out.println("Закрыт");
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }else{System.out.println("для начало норм");   }

    }

  /*public void pauseRead(){
      serialPort = new SerialPort (config.COM);
      if (serialPort != null || serialPort.isOpened ()) {
          try {
              serialPort.closePort();
              System.out.println("Закрыт");
          } catch (SerialPortException ex) {
              System.out.println(ex);
          }
      }
  }*/




     static void   codeRead() throws IOException{  //<================================================================== Проверить на карту от АНВИЗ
        if(SS.indexOf("M")>-1&&SS.indexOf(')')>0){
            SS=SS.substring(SS.indexOf("M"), SS.indexOf(')')+1);



            try    {

                Class.forName(config.myDriver);
                Connection conn = DriverManager.getConnection(config.myUrl, config.db_username, config.db_password);
                Statement st =  conn.createStatement();


                String sql = "SELECT * FROM users";
                ResultSet rs = st.executeQuery(sql);
                String ss="";
                while(rs.next()){
                    ss=rs.getString("card");
                    if(SS.equals(ss)){
                        System.out.println("Данная карта уже была добавлена ранее.");
                        //card.setText("Данная карта уже была добавлена ранее.");
                      //  JOptionPane.showMessageDialog(null,"Данная карта уже была добавлена ранее. \n Обратитесь к администратору.");
                        break;}
                   // System.out.println(rs.getInt("id")+" "+rs.getString("name"));
                }
                if(!SS.equals(ss)){
                    System.out.println(SS);
                    listener listener1 =new listener();
                    listener1.fetchById(SS);
                    //Registration registration = new Registration();
                    //registration.GN = SS;
                    card = SS;


                    //cardStatus=true;
                    //card.setText(SS);

                    //name.setEnabled(cardStatus);
                    //id.setEnabled(cardStatus);
                    //inst.setEnabled(cardStatus);
                    //caf.setEnabled(cardStatus);
                    //stud.setEnabled(cardStatus);
                    //stuff.setEnabled(cardStatus);
                    //spec.setEnabled(cardStatus);
                    //accept.setEnabled(cardStatus);
                    //chooser.setEnabled(cardStatus);
                    //photo.setEnabled(cardStatus);


                    String sql1 = "INSERT INTO users(card, surname, name) " +
                            "VALUES ('"+card+"', '','')";
                    st.executeUpdate(sql1);
                    st.close();
                }



            } catch (Exception e){
                System.err.println("Got an exception! 111111 ");
                System.err.println(e.getMessage());
            }

        }

        else {System.out.println("Ваша карта не поддерживается, обратитесь администратору");}

    }

    static void printS(String s){
        SS=SS+s;
        //System.out.println(SS+"что то там");
        if(checkStr(SS)){
            try {
                codeRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SS="";}
    }

    static boolean checkStr(String s){
        for(int i=0;i<s.length()-3;i++){
            if(s.substring(i, i+4).equals("card")){return true;}}
        return false;}


    private static SerialPort serialPort; /*Создаем объект типа SerialPort*/

    public void startListener(){
        status=false;
        System.out.println(status+" статус");
        serialPort = new SerialPort (config.COM); /*Передаем в конструктор суперкласса имя порта с которым будем работать*/
        try {
            serialPort.openPort (); /*Метод открытия порта*/
            serialPort.setParams (SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); /*Задаем основные параметры протокола UART*/
            serialPort.setEventsMask (SerialPort.MASK_RXCHAR); /*Устанавливаем маску или список события на которые будет происходить реакция. В данном случае это приход данных в буффер порта*/
            serialPort.addEventListener (new EventListener ()); /*Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс*/
        }
        catch (SerialPortException ex) {
            System.out.println (ex);

        }
    }


    public static class EventListener implements SerialPortEventListener { /*Слушатель срабатывающий по появлению данных на COM-порте*/
        String s="";
        public void serialEvent (SerialPortEvent event) {

            if (event.isRXCHAR () && event.getEventValue () > 0){ /*Если происходит событие установленной маски и количество байтов в буфере более 0*/
                try {
                    String data = serialPort.readString (event.getEventValue()); /*Создаем строковую переменную  data, куда и сохраняем данные*/
                    printS(data);
                    //System.out.println (data);


                }
                catch (SerialPortException ex) {
                    System.out.println (ex);
                }

            }
        }
    }



    public Registration fetchById(String string){
        Registration registration = new Registration();
        registration.setListenerId(string);
        return registration;
    }





}

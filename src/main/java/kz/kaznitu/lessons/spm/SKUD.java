package kz.kaznitu.lessons.spm;
import jssc.SerialPort;  /*Импорт классов библиотеки jssc*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JOptionPane;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import kz.kaznitu.lessons.spm.config;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.*;

/*class UserInfo{
    static String card[]=new String[6];
    static String name[]=new String[6];
    static String id[]=new String[6];
    static String inst[]=new String[6];
    static String caf[]=new String[6];
    static String rule[]=new String[6];
    static String spec[]=new String[6];
    static String photo[]=new String[6];

    static String time[]=new String[6];

}*/
/*
class RLUI extends Frame{
    RLUI(){
        setTitle("Система контроля доступа и учета времени. Кафедра КиПИ.");
        setSize(900,600);
        setLayout(null);
        setLocation(150,100);
        setResizable(false);
        addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
        setVisible(true);
    }
    public void paint(Graphics g){
        g.setColor(Color.black);

        g.drawLine(450, 0, 450, 600);
        g.drawLine(0, 200, 900, 200);
        g.drawLine(0, 400, 900, 400);
        //for(int i=0;i<6;i=i+2){

        for(int i=0;i<6;i++){
            int x=0,y=0;

            if	(i==3||i==4||i==5)x=450;
            if 	(i==1||i==4)y=190;
            if  (i==2||i==5)y=388;

            g.drawRect(10+x,30+y, 120, 160);
            g.setFont(new Font("Arial", Font.BOLD,12));
            g.drawString((i+1)+"", 430+x, 40+y);

            g.drawString("ФИО : "+SKUD.users.name[i], 140+x, 50+y);
            g.drawString("ID : "+SKUD.users.id[i], 140+x, 70+y);
            g.drawString("Институт : "+SKUD.users.inst[i], 140+x, 90+y);
            g.drawString("Кафедра : "+SKUD.users.caf[i], 140+x, 110+y);
            g.drawString("Специальность : "+SKUD.users.spec[i], 140+x, 130+y);
            g.drawString("Время: "+SKUD.users.time[i], 140+x, 150+y);

            Graphics2D g2 = (Graphics2D) g;
            Image img1 = Toolkit.getDefaultToolkit().getImage(SKUD.users.photo[i]);
            g2.drawImage(img1, 10+x, 30+y, 120, 160, this);
            g2.finalize();

        }
    }
}*/

@Controller
public class SKUD{  /*Класс чтения из порта*/


    private  static String card;
    private  static String currentTime;
    private  static long iid1;

    //static UserInfo users=new UserInfo();
    boolean status=true;


    private static String SS="";
    private static int t = 0;
   // static RLUI objrl;





    public void pauseRead1()  {
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





    static void printUser(String s) throws IOException{

	   /*FileInputStream fin = new FileInputStream("D:/users.txt");
	    BufferedReader br = new BufferedReader(new InputStreamReader(fin));*/

        try    {

            Class.forName(config.myDriver);
            Connection conn = DriverManager.getConnection(config.myUrl, config.db_username, config.db_password);
            Statement st =  conn.createStatement();


            String sql = "SELECT * FROM users";
            ResultSet rs = st.executeQuery(sql);
            String ss="";
            long iid;
            while(rs.next()){
                if(s.equals(rs.getString("card"))){ss=rs.getString("card"); iid=rs.getLong("id_user");
                    /*for(int i=5;i>0;i--){
                        users.card[i]=users.card[i-1];
                        users.name[i]=users.name[i-1];
                        users.id[i]=users.id[i-1];
                        users.inst[i]=users.inst[i-1];
                        users.caf[i]=users.caf[i-1];
                        users.rule[i]=users.rule[i-1];
                        users.spec[i]=users.spec[i-1];
                        users.photo[i]=users.photo[i-1];
                        users.time[i]=users.time[i-1];
                    }

                    users.card[0]=rs.getString("card");
                    users.name[0]=rs.getString("name");
                    users.id[0]=rs.getString("id");
                    users.inst[0]=rs.getString("inst");
                    users.caf[0]=rs.getString("caf");
                    users.rule[0]=rs.getString("rule");
                    users.spec[0]=rs.getString("spec");
                    users.photo[0]=rs.getString("photo");
                       */
                    card = ss;
                    iid1 = iid;
                    System.out.println(card + "считывающиеся карта");
                    long curTime = System.currentTimeMillis();
                    Date curDate = new Date(curTime);
                    String curStringDate = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss").format(curDate);
                    currentTime=curStringDate;
                    System.out.println(currentTime + "Current time");
                }
            }


            if(!s.equals(ss)){
               // JOptionPane.showMessageDialog(null, "Не зарегистрированная карта! "+s);
                System.out.println("Не зарегистрированная карта!");
            }

            sql = "INSERT INTO timestamp(card, time_in, id_user) " +
                    "VALUES ('"+card+"', '"+currentTime+"', '"+iid1+"')";
            st.executeUpdate(sql);
            st.close();
        } catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());      }



       /* objrl.invalidate();
        objrl.validate();
        objrl.repaint();*/




    }

    static void codeRead(){
        if(SS.indexOf("M")>-1&&SS.indexOf(')')>0){

            try{
                printUser(SS.substring(SS.indexOf("M"), SS.indexOf(')')+1));}
            catch(IOException e){System.out.println(e);}
        }
        else {
          //  JOptionPane.showMessageDialog(null, "Ваша карта не поддерживается, обратитесь администратору");
        System.out.println("Ваша карта не поддерживается, обратитесь администратору");
        }
    }

    static void printS(String s){
        SS=SS+s;
        if(checkStr(SS)){
            //System.out.println("!"+SS+"!!");
            codeRead();
            SS="";}
    }

    static boolean checkStr(String s){
        for(int i=0;i<s.length()-3;i++){
            if(s.substring(i, i+4).equals("card")){return true;}}
        return false;}

    private static SerialPort serialPort; /*Создаем объект типа SerialPort*/

    public void startListener1(){
        status = false;
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

    //public static void main (String[] args) {  /* Точка входа в программу*/    }

    private static class EventListener implements SerialPortEventListener { /*Слушатель срабатывающий по появлению данных на COM-порте*/
        String s="";
        public void serialEvent (SerialPortEvent event) {

            if (event.isRXCHAR () && event.getEventValue () > 0){ /*Если происходит событие установленной маски и количество байтов в буфере более 0*/
                try {
                    String data = serialPort.readString (event.getEventValue()); /*Создаем строковую переменную  data, куда и сохраняем данные*/
                    //System.out.print(data);
                    printS(data);

                }
                catch (SerialPortException ex) {
                    System.out.println (ex);
                }

            }
        }
    }
}
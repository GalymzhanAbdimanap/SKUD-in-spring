package kz.kaznitu.lessons.mod;

import kz.kaznitu.lessons.spm.listener;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Registration {
    //listener listener21 = new listener();
    public String GN;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 3,max = 50, message = "Error")
    private String card;

    @NotNull
    @Size(min = 3,max = 50, message = "Error")
    private String surname;


    @NotNull
    @Size(min = 3,max = 50, message = "Error")
    private String name;

    @NotNull
    @Size(min = 0, max = 50, message = "Error")
    private String cafedra;

    @NotNull
    @Size(min = 0, max = 50, message = "Error")
    private String institut;

    @NotNull
    @Size(min = 0, max = 50, message = "Error")
    private String login;

    @NotNull
    @Size(min = 0, max = 50, message = "Error")
    private String password;

    @NotNull
    @Size(min = 0, max = 2, message = "Error")
    private String status;

    public String ListenerId;



    public Registration(){
        super();
    }

    public Registration(int id, String card, String surname, String name, String cafedra, String institut, String login, String password, String status, String ListenerId){
        this.id=id;
        this.card=card;
        this.surname=surname;
        this.name=name;
        this.cafedra=cafedra;
        this.institut=institut;
        this.login=login;
        this.password=password;
        this.status=status;
        this.ListenerId=ListenerId;
    }

    public Registration(String card, String surname, String name, String cafedra, String institut, String login, String password, String status, String ListenerId){
        this.id=id;
        this.card=card;
        this.surname=surname;
        this.name=name;
        this.cafedra=cafedra;
        this.institut=institut;
        this.login=login;
        this.password=password;
        this.status=status;
        this.ListenerId= ListenerId;
    }
   /* public Registration(String label, String description, String description2, String email){
        this.label=label;
        this.description=description;
        this.description2=description2;
        this.email=email;
    }*/

    public int getId(){
        return id;
    }
    public void setId(){
        this.id=id;
    }

    public String getCard() {
        return card;
    }
    public void setCard(String card){
        this.card=card;
    }


    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }


    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getCafedra() {
        return cafedra;
    }
    public void setCafedra(String cafedra){
        this.cafedra=cafedra;
    }

    public String getInstitut() {
        return institut;
    }
    public void setInstitut(String institut){
        this.institut=institut;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login){
        this.login=login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }

    public String getListenerId(){
        return ListenerId;
    }
    public void setListenerId(String ListenerId){
        System.out.println(ListenerId + "asd");
        this.ListenerId=ListenerId;
        GN=ListenerId;
       // System.out.println(GN +" empty");

    }





    @Override
    public String toString() {
        setListenerId(ListenerId);
        System.out.println(ListenerId+" otvet");
        return ListenerId + " qwerty";
    }
}


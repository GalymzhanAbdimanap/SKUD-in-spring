package kz.kaznitu.lessons.mod;

import kz.kaznitu.lessons.spm.listener;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "timestamp")
public class Timestamp {
    //listener listener21 = new listener();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;



    //@NotNull
   // @Size(min = 1,max = 50, message = "Error")
    private String card;


    //@Size(min = 1,max = 50, message = "Error")
    private String time_in;

    /*@JoinColumn(name = "id", nullable = false)
    private Users user;
*/
    @ManyToOne
    @JoinColumn(name = "id_user")
    private  Users users;
    public Timestamp(){
        super();
    }

 /*   public Users(long id, String card, String surname, String name){
        this.id=id;
        this.card=card;
        this.surname=surname;
        this.name=name;
        }*/

    public Timestamp(long id,String card, String time_in){
        this.id=id;
        this.card=card;
        this.time_in=time_in;
        //this.users=users;
    }

    public Timestamp(String card, String time_in){
        this.card=card;
        this.time_in=time_in;
       // this.users = users;
    }


   /* public Registration(String label, String description, String description2, String email){
        this.label=label;
        this.description=description;
        this.description2=description2;
        this.email=email;
    }*/

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }


    public String getCard() {
        return card;
    }
    public void setCard(String card){
        this.card=card;
    }


    public String getTime_in() {
        return time_in;
    }
    public void setTime_in(String time_in){
        this.time_in=time_in;
    }


    public Users getUsers(){return users;}

    public void setUsers(Users users){this.users=users;}


}



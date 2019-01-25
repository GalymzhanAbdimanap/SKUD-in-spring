package kz.kaznitu.lessons.mod;

import kz.kaznitu.lessons.spm.listener;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Institut {
    //listener listener21 = new listener();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    //@NotNull
    @Size(min = 1,max = 50, message = "Error")
    private String name;



    public Institut(){
        super();
    }

 /*   public Users(long id, String card, String surname, String name){
        this.id=id;
        this.card=card;
        this.surname=surname;
        this.name=name;
        }*/

    public Institut(String name){
        this.name=name;
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


    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }


}



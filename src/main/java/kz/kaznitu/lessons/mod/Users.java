package kz.kaznitu.lessons.mod;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class  Users {
    //listener listener21 = new listener();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id_user;
    //@NotNull
    @Size(min = 3,max = 50, message = "Error")
    private String card;

    //@NotNull
    //@Size(min = 3,max = 50, message = "Error")
    private String surname;


    //@NotNull
    @Size(min = 3,max = 50, message = "Error")
    private String name;

    @Size(min = 3,max = 50, message = "Error")
    private String inst;


    @Size(min = 3,max = 50, message = "Error")
    private String caf;

    @Size(min = 3,max = 50, message = "Error")
    private String login;

    @Size(min = 3,max = 50, message = "Error")
    private String password;

    //@Size(min = 3,max = 50, message = "Error")
    private int active;





    @OneToMany(mappedBy = "users")
    private List<Timestamp> timestamps;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

  /*  @OneToOne(mappedBy = "user")
    private List<User> user;
    *//*
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
*/
    /*@OneToMany(mappedBy = "users")
    private Collection<Timestamp> userTimestamps = new ArrayList<Timestamp>();
*/
    public Users(){
        super();
    }

    public Users(long id_user, String card, String surname, String name, String inst, String caf, List<Timestamp> timestamps, String login, String password, int active){
        this.id_user=id_user;
        this.card=card;
        this.surname=surname;
        this.name=name;
        this.inst=inst;
        this.caf=caf;
        this.timestamps = timestamps;
        this.login=login;
        this.password=password;
        this.active=active;
        //this.user = user;
        }

    public Users(String card, String surname, String name, String inst, String caf, List<Timestamp> timestamps, String login, String password, int active){
        //this.id=id;
        this.card=card;
        this.surname=surname;
        this.name=name;
        this.inst=inst;
        this.caf=caf;
        this.timestamps = timestamps;
        this.login=login;
        this.password=password;
        this.active=active;
        //this.user = user;

    }


    //public Users(String inst){this.inst=inst;}
   /* public Registration(String label, String description, String description2, String email){
        this.label=label;
        this.description=description;
        this.description2=description2;
        this.email=email;
    }*/

    public long getId_user(){
        return id_user;
    }
    public void setId_user(long id_user){
        this.id_user=id_user;
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


    public String getInst() {
        return inst;
    }
    public void setInst(String inst){
        this.inst=inst;
    }


    public String getCaf() {
        return caf;
    }
    public void setCaf(String caf){
        this.caf=caf;
    }

    public  List<Timestamp> getTimestamps(){return timestamps;}

    public  void  setTimestamps(List<Timestamp> timestamps){this.timestamps = timestamps;}

  /*  public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
*/


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
   /* public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/

    /*public  boolean hasTimestamp(Timestamp timestamp){
        for (Timestamp containedTimestamp : getTimestamps()){
            if(containedTimestamp.getId()==timestamp.getId()){
                return true;
            }
        }
        return false;
    }*/


}


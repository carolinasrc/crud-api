package anacsoares.crudapi.entities;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_TABLE")
public class PersonEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public PersonEntity() {

    }

    public PersonEntity(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

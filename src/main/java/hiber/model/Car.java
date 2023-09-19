package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars1")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String model;

    @Column
    int serial;
    @OneToOne(mappedBy = "car",cascade = CascadeType.ALL)
    User owner;
    public Car(){}
    public Car(String model, int serial){
        this.model = model;
        this.serial = serial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return this.model + "-" + this.serial;
    }
}

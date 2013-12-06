package entity;

import common.ArrayObtained;

import javax.persistence.*;

@Entity
@Table(name = "uslugi")
public class ServiceData implements ArrayObtained {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USLUGI")
    private long serviceId;

    @Column(name = "NAZWA")
    private String name;

    @Column(name = "CENA")
    private float price;

    @Column(name = "typ")
    private String type;

    @Override
    public Object[] getArray() {
        return new Object[]{serviceId, name, price, type};
    }

    public long getServiceId() {
        return serviceId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}

package gui.guestBook;

import dto.cantor.CustomerData;

import java.awt.*;

public class CustomerSpecification implements Specification {

    private static CustomerSpecification specification = new CustomerSpecification();

    public static CustomerSpecification customerSpecification() {
        return specification;
    }

    private CustomerSpecification() {
    }

    @Override
    public String getTable() {
        return "klienci";
    }

    @Override
    public Rectangle geDataTableBounds() {
        return new Rectangle(0, 320, 1200, 150);
    }

    @Override
    public Rectangle getServiceTableBounds() {
        return new Rectangle(0, 480, 1200, 150);
    }

    @Override
    public Class<CustomerData> getClientDtoClass() {
        return CustomerData.class;
    }

    @Override
    public String getPrimaryId() {
        return "IDK_PESEL";
    }
}

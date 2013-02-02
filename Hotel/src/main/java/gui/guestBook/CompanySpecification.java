package gui.guestBook;

import dto.cantor.CompanyData;

import java.awt.*;

public class CompanySpecification implements Specification {

    private static CompanySpecification specification = new CompanySpecification();

    public static CompanySpecification companySpecification() {
        return specification;
    }

    private CompanySpecification() {
    }

    @Override
    public String getTable() {
        return "firmy";
    }

    @Override
    public Rectangle geDataTableBounds() {
        return new Rectangle(0, 340, 1200, 150);
    }

    @Override
    public Rectangle getServiceTableBounds() {
        return new Rectangle(0, 500, 1200, 150);
    }

    @Override
    public Class<CompanyData> getClientDtoClass() {
        return CompanyData.class;
    }

    @Override
    public String getPrimaryId() {
        return "IDF_KRS";
    }
}

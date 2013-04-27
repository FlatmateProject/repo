package springExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CantorTableCreator;

@Component
public class SimpleComponent {

    @Autowired
    private CantorTableCreator creator;

    public CantorTableCreator getCreator() {
        return creator;
    }

    public void setCreator(CantorTableCreator creator) {
        this.creator = creator;
    }
}

package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.reservation.Reservation;

@Component
public class ReservationPanel extends TabComponent {

    @Autowired
    private Reservation reservation;

    @Override
    public void create() {

    }

    @Override
    public void addEvents() {

    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}

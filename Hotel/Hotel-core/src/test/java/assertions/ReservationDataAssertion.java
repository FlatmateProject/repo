package assertions;

import entity.ReservationData;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class ReservationDataAssertion extends GenericAssert<ReservationDataAssertion, ReservationData> {

    private ReservationDataAssertion(ReservationData actual) {
        super(ReservationDataAssertion.class, actual);
    }

    public static ReservationDataAssertion assertThat(ReservationData actual) {
        return new ReservationDataAssertion(actual);
    }

    public ReservationDataAssertion hasReservationId(long reservationId) {
        Assertions.assertThat(actual.getReservationId()).isEqualTo(reservationId);
        return this;
    }
}

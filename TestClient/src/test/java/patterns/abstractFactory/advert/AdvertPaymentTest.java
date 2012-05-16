package patterns.abstractFactory.advert;

import org.junit.Test;

import patterns.abstractFactory.ConfirmationHandle;
import patterns.abstractFactory.PaymentHandler;
import patterns.abstractFactory.PaymentsFactory;
import patterns.abstractFactory.model.AdvertEntity;
import patterns.abstractFactory.model.OBJECT_STATUS;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdvertPaymentTest {
	
	@Test
	public void testShouldPayAdvert() {
		//given
		PaymentsFactory paymentFactory = new AdvertPaymentsFactory();
		AdvertEntity advert = new AdvertEntity();
		PaymentsRequest request = new PaymentsRequest(advert, 1000);
		
		//when
		PaymentHandler handler = paymentFactory.createPaymentHandler();
		PaymentsResult result =  handler.executePayment(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.TODO, result.getPaymentStatus());
		assertEquals(advert.getId(), ((AdvertEntity)result.getObject()).getId());
		assertEquals(OBJECT_STATUS.SET_TO_WORK, advert.getStatus());
	}

	@Test
	public void testShouldConfirmPaymentAdvert(){
		//given
		PaymentsFactory paymentFactory = new AdvertPaymentsFactory();
		AdvertEntity advert = new AdvertEntity();
		PaymentsRequest request = new PaymentsRequest(advert, 1000);
		
		//when
		ConfirmationHandle handler = paymentFactory.createConfirmationHandler();
		PaymentsResult result = handler.executeConfirmation(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.DONE, result.getPaymentStatus());
		assertEquals(advert.getId(), ((AdvertEntity)result.getObject()).getId());
		assertEquals(OBJECT_STATUS.WORK, advert.getStatus());
	}
}

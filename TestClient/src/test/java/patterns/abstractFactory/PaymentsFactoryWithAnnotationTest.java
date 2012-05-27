package patterns.abstractFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterns.abstractFactory.CancelConfirmationHandler;
import patterns.abstractFactory.CancelPaymentHandler;
import patterns.abstractFactory.ConfirmationHandle;
import patterns.abstractFactory.PaymentHandler;
import patterns.abstractFactory.PaymentsFactory;
import patterns.abstractFactory.model.OBJECT_STATUS;
import patterns.abstractFactory.model.ObjectEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;

@RunWith(JUnitParamsRunner.class)
public class PaymentsFactoryWithAnnotationTest {
	
	public PaymentsGiven given;
	
	@Before
	public void setUp() {
		given = new PaymentsGiven();
	}
	
	@Test
	@Parameters( { "ADVERT, 1000", "PUBLICITY, 1000" })
	public void testShouldPayAdvert(String objectType, long amount) {
		//given
		PaymentsFactory factory = given.getPaymentsFactory(objectType);
		ObjectEntity object = given.getEntity(objectType);
		PaymentsRequest request = new PaymentsRequest(object, amount);
		
		//when
		PaymentHandler handler = factory.createPaymentHandler();
		PaymentsResult result =  handler.executePayment(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.TODO, result.getPaymentStatus());
		assertEquals(object.getId(), result.getObject().getId());
		assertEquals(OBJECT_STATUS.SET_TO_WORK, object.getStatus());
		assertEquals(request.getAmount(), result.getAmount());
	}

	@Test
	@Parameters( { "ADVERT, 1000", "PUBLICITY, 1000" })
	public void testShouldConfirmPaymentAdvert(String objectType, long amount){
		//given
		PaymentsFactory factory = given.getPaymentsFactory(objectType);
		ObjectEntity advert = given.getEntity(objectType);
		PaymentsRequest request = new PaymentsRequest(advert, 1000);
		
		//when
		ConfirmationHandle handler = factory.createConfirmationHandler();
		PaymentsResult result = handler.executeConfirmation(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.DONE, result.getPaymentStatus());
		assertEquals(advert.getId(), result.getObject().getId());
		assertEquals(OBJECT_STATUS.WORK, advert.getStatus());
		assertEquals(request.getAmount(), result.getAmount());
	}
	
	@Test
	@Parameters( { "ADVERT, 1000", "PUBLICITY, 1000" })
	public void testShouldCancelPaymentAdvert(String objectType, long amount){
		//given
		PaymentsFactory factory = given.getPaymentsFactory(objectType);
		ObjectEntity advert = given.getEntity(objectType);
		PaymentsRequest request = new PaymentsRequest(advert, 1000);
		
		//when
		CancelPaymentHandler handler = factory.createCancelPaymentHandler();
		PaymentsResult result = handler.cancelPayment(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.CANCELED, result.getPaymentStatus());
		assertEquals(advert.getId(), result.getObject().getId());
		assertEquals(OBJECT_STATUS.NEW, advert.getStatus());
		assertEquals(request.getAmount(), result.getAmount());
	}
	
	@Test
	@Parameters( { "ADVERT, 1000", "PUBLICITY, 1000" })
	public void testShouldCancelConfirmationAdvert(String objectType, long amount){
		//given
		PaymentsFactory factory = given.getPaymentsFactory(objectType);
		ObjectEntity advert = given.getEntity(objectType);
		PaymentsRequest request = new PaymentsRequest(advert, 1000);
		
		//when                               
		CancelConfirmationHandler handler = factory.createCancelConfirmationHandler();
		PaymentsResult result = handler.cancelConfirmation(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.CANCELED, result.getPaymentStatus());
		assertEquals(advert.getId(), result.getObject().getId());
		assertEquals(OBJECT_STATUS.NEW, advert.getStatus());
		assertEquals(request.getAmount(), result.getAmount());
	}
}

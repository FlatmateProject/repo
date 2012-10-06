package patterns.abstractFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import patterns.abstractFactory.advert.AdvertPaymentsFactory;
import patterns.abstractFactory.model.AdvertEntity;
import patterns.abstractFactory.model.OBJECT_STATUS;
import patterns.abstractFactory.model.ObjectEntity;
import patterns.abstractFactory.model.PublicityEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;
import patterns.abstractFactory.publicity.PublicityPaymentsFactory;

public class PaymentsFactoryWithMethodTest {
	
	@Test(dataProvider = "complexValues")
	public void testShouldPayAdvert(PaymentsFactory factory, ObjectEntity object) {
		//given
		PaymentsRequest request = new PaymentsRequest(object, 1000);
		
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

	@Test(dataProvider = "complexValues")
	public void testShouldConfirmPaymentAdvert(PaymentsFactory factory, ObjectEntity object) {
		//given
		PaymentsRequest request = new PaymentsRequest(object, 1000);
		
		//when
		ConfirmationHandle handler = factory.createConfirmationHandler();
		PaymentsResult result = handler.executeConfirmation(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.DONE, result.getPaymentStatus());
		assertEquals(object.getId(), result.getObject().getId());
		assertEquals(OBJECT_STATUS.WORK, object.getStatus());
		assertEquals(request.getAmount(), result.getAmount());
	}
	
	@Test(dataProvider = "complexValues")
	public void testShouldCancelPaymentAdvert(PaymentsFactory factory, ObjectEntity object) {
		//given
		PaymentsRequest request = new PaymentsRequest(object, 1000);
		
		//when
		CancelPaymentHandler handler = factory.createCancelPaymentHandler();
		PaymentsResult result = handler.cancelPayment(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.CANCELED, result.getPaymentStatus());
		assertEquals(object.getId(), result.getObject().getId());
		assertEquals(OBJECT_STATUS.NEW, object.getStatus());
		assertEquals(request.getAmount(), result.getAmount());
	}
	
	@Test(dataProvider = "complexValues")
	public void testShouldCancelConfirmationAdvert(PaymentsFactory factory, ObjectEntity object) {
		//given
		PaymentsRequest request = new PaymentsRequest(object, 1000);
		
		//when                               
		CancelConfirmationHandler handler = factory.createCancelConfirmationHandler();
		PaymentsResult result = handler.cancelConfirmation(request);
		
		//then
		assertNotNull(result);
		assertEquals(PAYMENTS_STATUS.CANCELED, result.getPaymentStatus());
		assertEquals(object.getId(), result.getObject().getId());
		assertEquals(OBJECT_STATUS.NEW, object.getStatus());
		assertEquals(request.getAmount(), result.getAmount());
	}
	
	@DataProvider
	public Object[][] complexValues() {
		return new Object[][] { 
		{ new AdvertPaymentsFactory(), new AdvertEntity() },//
		{ new PublicityPaymentsFactory(), new PublicityEntity() },//
		};
	}
}

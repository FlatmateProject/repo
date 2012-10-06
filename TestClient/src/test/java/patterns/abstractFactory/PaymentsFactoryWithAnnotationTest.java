package patterns.abstractFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import patterns.abstractFactory.model.OBJECT_STATUS;
import patterns.abstractFactory.model.OBJECT_TYPE;
import patterns.abstractFactory.model.ObjectEntity;
import patterns.abstractFactory.payments.PAYMENTS_STATUS;
import patterns.abstractFactory.payments.PaymentsRequest;
import patterns.abstractFactory.payments.PaymentsResult;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class PaymentsFactoryWithAnnotationTest {
	
	public PaymentsGiven given;
	
	@BeforeMethod
	public void setUp() {
		given = new PaymentsGiven();
	}
	
	@Test(dataProvider = "parameterIntTestProvider")
	public void testShouldPayAdvert(OBJECT_TYPE objectType, long amount) {
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

	@Test(dataProvider = "parameterIntTestProvider")
	public void testShouldConfirmPaymentAdvert(OBJECT_TYPE objectType, long amount){
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
	
	@Test(dataProvider = "parameterIntTestProvider")
	public void testShouldCancelPaymentAdvert(OBJECT_TYPE objectType, long amount){
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
	
	@Test(dataProvider = "parameterIntTestProvider")
	public void testShouldCancelConfirmationAdvert(OBJECT_TYPE objectType, long amount){
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
	
	@DataProvider(name = "parameterIntTestProvider")
	public Object[][] parameterIntTestProvider() {
		return new Object[][]{
				{ OBJECT_TYPE.ADVERT,    1000 },//
				{ OBJECT_TYPE.PUBLICITY, 1000 }//
		};
	}
}

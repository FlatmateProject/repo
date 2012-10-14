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

import static patterns.abstractFactory.PaymentResultAssertion.assertThat;

public class PaymentsFactoryWithAnnotationTest {
	
	PaymentsGiven given;

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
        assertThat(result).isNotNull()//
            .isPaymentStatus(PAYMENTS_STATUS.TODO)//
            .isAmount(amount)//
            .isObjectId(object.getId())//
            .isObjectStatus(OBJECT_STATUS.SET_TO_WORK);
	}

	@Test(dataProvider = "parameterIntTestProvider")
	public void testShouldConfirmPaymentAdvert(OBJECT_TYPE objectType, long amount){
		//given
		PaymentsFactory factory = given.getPaymentsFactory(objectType);
		ObjectEntity object = given.getEntity(objectType);
		PaymentsRequest request = new PaymentsRequest(object, 1000);
		
		//when
		ConfirmationHandle handler = factory.createConfirmationHandler();
		PaymentsResult result = handler.executeConfirmation(request);
		
		//then
        assertThat(result).isNotNull()//
                .isPaymentStatus(PAYMENTS_STATUS.DONE)//
                .isAmount(amount)//
                .isObjectId(object.getId())//
                .isObjectStatus(OBJECT_STATUS.WORK);
	}
	
	@Test(dataProvider = "parameterIntTestProvider")
	public void testShouldCancelPaymentAdvert(OBJECT_TYPE objectType, long amount){
		//given
		PaymentsFactory factory = given.getPaymentsFactory(objectType);
		ObjectEntity object = given.getEntity(objectType);
		PaymentsRequest request = new PaymentsRequest(object, 1000);
		
		//when
		CancelPaymentHandler handler = factory.createCancelPaymentHandler();
		PaymentsResult result = handler.cancelPayment(request);
		
		//then
        assertThat(result).isNotNull()//
                .isPaymentStatus(PAYMENTS_STATUS.CANCELED)//
                .isAmount(amount)//
                .isObjectId(object.getId())//
                .isObjectStatus(OBJECT_STATUS.NEW);
	}

	@Test(dataProvider = "parameterIntTestProvider")
	public void testShouldCancelConfirmationAdvert(OBJECT_TYPE objectType, long amount){
		//given
		PaymentsFactory factory = given.getPaymentsFactory(objectType);
		ObjectEntity object = given.getEntity(objectType);
		PaymentsRequest request = new PaymentsRequest(object, 1000);

		//when
		CancelConfirmationHandler handler = factory.createCancelConfirmationHandler();
		PaymentsResult result = handler.cancelConfirmation(request);

		//then
        assertThat(result).isNotNull()//
                .isPaymentStatus(PAYMENTS_STATUS.CANCELED)//
                .isAmount(amount)//
                .isObjectId(object.getId())//
                .isObjectStatus(OBJECT_STATUS.NEW);
	}
	
	@DataProvider(name = "parameterIntTestProvider")
	public Object[][] parameterIntTestProvider() {
		return new Object[][]{
				{ OBJECT_TYPE.ADVERT,    1000 },//
				{ OBJECT_TYPE.PUBLICITY, 1000 }//
		};
	}
}

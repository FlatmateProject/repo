package patterns.decorator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class IntegerElement {

	private int value;

	private int quantity = 1;

	public IntegerElement(Integer value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void increase() {
		quantity++;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof IntegerElement == false) {
			return false;
		}
		IntegerElement object = (IntegerElement) obj;
		return new EqualsBuilder()//
				.append(value, object.value)//
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()//
		.append(value)//
		.toHashCode();
	}

	public double calculateRatio() {
		return value * quantity;
	}
}

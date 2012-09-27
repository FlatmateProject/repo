package service.statictic.templates;

public class HotelRoomsRaportTemplateBuilder implements RaportTemplateBuilder {

	
	private String header = "Raportu z wykorzystania pokoi z klasy";
	
	private String body   = "";
	
	private String DEFAULT_FOOT   = "W danym miesiącu nie wprowadzano danych z zakresu.\n";
	
	private String foot   = DEFAULT_FOOT;
	
	@Override
	public void createHeader(Object... args) {
		header = String.format("Raportu z wykorzystania pokoi z klasy %s za miesiąc %s w roku %s \n", args);

	}

	@Override
	public void appendBodyBlock(Object... args) {
		body += String.format("\tPokój nr: %d (%d).\n"
				+ "\tzyski: %.2fzł\n"
				+ "\tliczba meldunków: %d\n"
				+ "\tprzychód jednostkowy: %.2fzł\n", args);
	}

	@Override
	public void createFoot(Object... args) {
		if(isShownLegend(args)){
		foot = String.format("Legenda \n"
				+ " Slupek pierwsz przedstawia zyski\n"
				+ " Slupek drugi przedstawia przychód jednostkowy\n"
				+ " (na jedno zameldowanie)\n");
		} else {
			foot = DEFAULT_FOOT;
		}
	}

	@Override
	public String build() {
		String result = header + body + foot;
		body = "";
		return result;
	}
	
	private boolean isShownLegend(Object... args) {
		int numberOfElements = (Integer) (args != null && args.length > 0 ? args[0] : 0);
		return numberOfElements > 0;
	}
}

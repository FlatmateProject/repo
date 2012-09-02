package service.statictic.templates;

public class HotelRoomRaportTemplateBuilder implements RaportTemplateBuilder {

	private String header = "Raportu z wykorzystania pokoi z klasy";
	private String body   = "";
	private String foot   = "W danym miesiącu nie wprowadzano danych z zakresu.\n";
	
	@Override
	public void createHeader(Object... args) {
		header = String.format("Raportu z wykorzystania pokoi z klasy %s za miesiąc %s w roku %s \n", args);

	}

	@Override
	public void appendBodyBlock(Object... args) {
		String.format("\tPokój nr: % (%d).\n"
				+ "\tzyski: %.2fzł\n"
				+ "\tliczba meldunków: %d\n"
				+ "\tprzychód jednostkowy: %.2fzł\n", args);
	}

	@Override
	public void createFoot(Object... args) {
		int numberOfElements = (Integer) (args != null && args.length > 0 ? args[0] : 0);
		if(numberOfElements > 0){
		foot = String.format("Legenda \n"
				+ " Slupek pierwsz przedstawia zyski\n"
				+ " Slupek drugi przedstawia przychód jednostkowy\n"
				+ " (na jedno zameldowanie)\n");
		}
	}

	@Override
	public String build() {
		return header + body + foot;
	}

}

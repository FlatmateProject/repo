package service;

public enum ERROR_MESSAGE {

	LIMIT_NEGATIVE_VALUE("Limit mniejszy niż zero o o o o"),
    EMPTY_WORD("Słow nie możę być puste"),
	WORD_NOT_FOUND("Szukane słowo nie istnieje"),
	EMPTY_TRANSLATIONS("Lista tłumaczeń nie może być pusta"),
    EMPTY_EXAMPLES("Lista przykładów nie może być pusta"),
	CREATE_SERVICE_ERROR("Nie udało sie utworzyc uslugi"),
	EXECUTE_SERVICE_ERROR("Błąd wykonania usługi"),
	SERVICE_TRANSACTION_ERROR("Błąd w transakcji"),
	HIBERNATE_ERROR("Hibernate error: "), 
	WORD_IS_NULL("Objekt nie może być nullem");
	
	ERROR_MESSAGE(String message){
		this.message = message;
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message += message;
	}
	
	public String toString() {
		return getMessage(); 
	}
	
}

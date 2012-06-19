package patterns.localfactorymethod;

public class FactoryMethod {

	public enum LANGUAGE {
		Polish(new AbstractMessage() {
			{
				message = "Witaj świecie";
			}

			@Override
			public String append(String message) {
				this.message += " \"" + message + "\"";
				return this.message;
			}
		}), //
		English(new AbstractMessage() {
			{
				message = "Hello world";
			}

			@Override
			public String append(String message) {
				this.message += " '" + message + "'";
				return this.message;
			}
		}), //
		Spanish(new AbstractMessage() {
			{
				message = "Hola mundo";
			}

			@Override
			public String append(String message) {
				this.message += " ?" + message + "?";
				return this.message;
			}
		});

		private LANGUAGE(AbstractMessage message) {
			this.message = message;
		}

		private AbstractMessage message;

		public String append(String m) {
			return message.append(m);
		}
	}

	private LANGUAGE language;

	public FactoryMethod(LANGUAGE language) {
		this.language = language;
	}

	public String append(String m) {
		return language.append(m);
	}
}

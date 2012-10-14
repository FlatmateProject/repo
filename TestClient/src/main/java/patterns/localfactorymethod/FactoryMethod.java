package patterns.localfactorymethod;

public class FactoryMethod {

    public enum LANGUAGE {
		Polish(new AbstractMessage("Witaj świecie") {

			@Override
			public String append(String message) {
				this.message += " \"" + message + "\"";
				return this.message;
			}
		}), //
		English(new AbstractMessage("Hello world") {

            @Override
			public String append(String message) {
				this.message += " '" + message + "'";
				return this.message;
			}
		}), //
		Spanish(new AbstractMessage("Hola mundo") {

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

	private FactoryMethod(LANGUAGE language) {
		this.language = language;
	}

	public String append(String m) {
		return language.append(m);
	}

    public static FactoryMethod getMethod(LANGUAGE language) {
        return new FactoryMethod(language);
    }
}

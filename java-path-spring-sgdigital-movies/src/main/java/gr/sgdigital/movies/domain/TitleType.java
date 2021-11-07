package gr.sgdigital.movies.domain;

public enum TitleType {
	UNKNOWN ("Unknown"),
	MOVIE ("Movie"),
	SERIE ("TV Series");

	private final String name;

	TitleType (String name) {
		this.name = name;
	}

	public String getName () {
		return this.name;
	}
}




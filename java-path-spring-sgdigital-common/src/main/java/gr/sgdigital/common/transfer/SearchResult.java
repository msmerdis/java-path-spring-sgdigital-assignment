package gr.sgdigital.common.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SearchResult {
	private String searchToken;
	private Object searchValue;

	public SearchResult () {
	}

	public SearchResult (String token, Object value) {
		this.searchToken = token;
		this.searchValue = value;
	}
}




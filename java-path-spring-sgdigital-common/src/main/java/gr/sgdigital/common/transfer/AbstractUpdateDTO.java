package gr.sgdigital.common.transfer;

import javax.validation.constraints.NotNull;

public abstract class AbstractUpdateDTO<Entity, Key> implements AbstractCreateDTO<Entity> {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Unique identifier is required for an update.")
	protected Key Id;

	public Key getId() {
		return Id;
	}

	public void setId(Key id) {
		Id = id;
	}
}




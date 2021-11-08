package gr.sgdigital.common.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import gr.sgdigital.common.transfer.EmbeddableResponseDTO;
import lombok.Getter;
import lombok.ToString;

/**
 * This class a base entity for non composite keys
 */
@Getter
@ToString
@MappedSuperclass
public abstract class EmbeddableEntity<
	Key extends Object,
	Entity extends EmbeddableEntity<Key, Entity, SimpleDTO, DetailDTO>,
	SimpleDTO extends EmbeddableResponseDTO<Entity>,
	DetailDTO extends EmbeddableResponseDTO<Entity>
> extends AbstractEntity <Key, Entity, SimpleDTO, DetailDTO> {
	@EmbeddedId
	protected Key id;

	public EmbeddableEntity (Class<SimpleDTO> simpleView, Class<DetailDTO> detailView) {
		super (simpleView, detailView);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())
			return false;

		@SuppressWarnings("unchecked")
		Entity other = (Entity) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}




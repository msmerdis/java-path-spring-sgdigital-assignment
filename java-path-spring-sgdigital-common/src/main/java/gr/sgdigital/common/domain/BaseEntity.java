package gr.sgdigital.common.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import lombok.Getter;
import lombok.ToString;

/**
 * This class a base entity for non composite keys
 */
@Getter
@ToString
@MappedSuperclass
public abstract class BaseEntity<
	Key,
	Entity extends BaseEntity<Key, Entity, SimpleDTO, DetailDTO>,
	SimpleDTO extends BaseResponseDTO<Entity>,
	DetailDTO extends BaseResponseDTO<Entity>
> extends AbstractEntity <Key, Entity, SimpleDTO, DetailDTO> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	protected Key id;

	public BaseEntity (Class<SimpleDTO> simpleView, Class<DetailDTO> detailView) {
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




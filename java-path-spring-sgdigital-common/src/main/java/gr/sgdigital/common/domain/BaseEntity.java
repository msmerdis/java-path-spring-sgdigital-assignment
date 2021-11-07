package gr.sgdigital.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import lombok.Getter;
import lombok.ToString;

/**
 * This class holds all common attributes a category in which products are organised.
 */
@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<
	Key,
	Entity extends BaseEntity<Key, Entity, SimpleDTO, DetailDTO>,
	SimpleDTO extends BaseResponseDTO<Entity>,
	DetailDTO extends BaseResponseDTO<Entity>
> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	protected Key id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	@CreatedDate
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	@LastModifiedDate
	protected Date updatedDate;

	private Class<SimpleDTO> simpleView;
	private Class<DetailDTO> detailView;

	public BaseEntity (Class<SimpleDTO> simpleView, Class<DetailDTO> detailView) {
		this.simpleView = simpleView;
		this.detailView = detailView;
	}

	@SuppressWarnings("unchecked")
	public SimpleDTO simpleView () {
		SimpleDTO view;

		try {
			view = simpleView.getDeclaredConstructor().newInstance();
			view.updateFromEntity((Entity)this);
		} catch (Exception e) {
			e.printStackTrace();
			view = null;
		}

		return view;
	}

	@SuppressWarnings("unchecked")
	public DetailDTO detailView () {
		DetailDTO view;

		try {
			view = detailView.getDeclaredConstructor().newInstance();
			view.updateFromEntity((Entity)this);
		} catch (Exception e) {
			e.printStackTrace();
			view = null;
		}

		return view;
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




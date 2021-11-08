package gr.sgdigital.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import gr.sgdigital.common.transfer.AbstractResponseDTO;
import lombok.Getter;
import lombok.ToString;

/**
 * This class holds all common attributes an entity should have
 */
@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<
	Key,
	Entity extends AbstractEntity<Key, Entity, SimpleDTO, DetailDTO>,
	SimpleDTO extends AbstractResponseDTO<Entity>,
	DetailDTO extends AbstractResponseDTO<Entity>
> {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	@CreatedDate
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	@LastModifiedDate
	protected Date updatedDate;

	@Transient private Class<SimpleDTO> simpleView;
	@Transient private Class<DetailDTO> detailView;

	public AbstractEntity (Class<SimpleDTO> simpleView, Class<DetailDTO> detailView) {
		this.simpleView = simpleView;
		this.detailView = detailView;
	}

	@SuppressWarnings("unchecked")
	public SimpleDTO simpleView () throws Exception {
		SimpleDTO view;

		view = simpleView.getDeclaredConstructor().newInstance();
		view.updateFromEntity((Entity)this);

		return view;
	}

	@SuppressWarnings("unchecked")
	public DetailDTO detailView () throws Exception {
		DetailDTO view;

		view = detailView.getDeclaredConstructor().newInstance();
		view.updateFromEntity((Entity)this);

		return view;
	}
}




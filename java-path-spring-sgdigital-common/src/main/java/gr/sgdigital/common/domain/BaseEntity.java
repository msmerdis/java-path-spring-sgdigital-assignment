package gr.sgdigital.common.domain;

import java.io.Serializable;
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

/**
 * This class holds all common attributes a category in which products are organised.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<I> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	protected I id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	@CreatedDate
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	@LastModifiedDate
	protected Date updatedDate;

	public I getId() {
		return id;
	}

	public void setId(I id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
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
		BaseEntity<I> other = (BaseEntity<I>) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [id=" + id + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
}




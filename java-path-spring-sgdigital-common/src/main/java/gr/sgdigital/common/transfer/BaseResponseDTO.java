package gr.sgdigital.common.transfer;

import java.io.Serializable;

import gr.sgdigital.common.domain.BaseEntity;

public abstract class BaseResponseDTO<Entity extends BaseEntity<?,?,?,?>> implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract void updateFromEntity (Entity baseEntity);
}




package gr.sgdigital.common.transfer;

import java.io.Serializable;

import gr.sgdigital.common.domain.AbstractEntity;

public abstract class AbstractResponseDTO<Entity extends AbstractEntity<?,?,?,?>> implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract void updateFromEntity (Entity baseEntity);
}




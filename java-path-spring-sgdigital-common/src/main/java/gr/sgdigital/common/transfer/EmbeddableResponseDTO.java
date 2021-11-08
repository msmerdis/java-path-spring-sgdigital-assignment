package gr.sgdigital.common.transfer;

import gr.sgdigital.common.domain.EmbeddableEntity;

public abstract class EmbeddableResponseDTO<Entity extends EmbeddableEntity<?,?,?,?>> extends AbstractResponseDTO<Entity> {
	private static final long serialVersionUID = 1L;
}




package gr.sgdigital.common.transfer;

import java.io.Serializable;

public interface AbstractCreateDTO<Entity> extends Serializable {
	void updateEntity (Entity entity);
}




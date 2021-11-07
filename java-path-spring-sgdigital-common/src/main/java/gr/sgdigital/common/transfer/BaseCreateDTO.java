package gr.sgdigital.common.transfer;

import java.io.Serializable;

public interface BaseCreateDTO<Entity> extends Serializable {
	void updateEntity (Entity entity);
}




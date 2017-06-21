package com.picc.common.handler;

import java.io.Serializable;

public interface TaskHandler extends Serializable {

	public void execute() throws Exception;

}

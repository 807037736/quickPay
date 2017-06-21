package com.picc.common.service.facade;

import java.io.IOException;
import java.net.URISyntaxException;

public interface YpSmsService {

	public String sendOperation(String text,String mobile) throws IOException, URISyntaxException;
}

package com.generallycloud.nio.component;

import com.generallycloud.nio.common.Logger;
import com.generallycloud.nio.common.LoggerFactory;

public class LoggerSEListener implements SessionEventListener{

	private Logger logger = LoggerFactory.getLogger(LoggerSEListener.class);
	
	public void sessionOpened(SocketSession session) {
		logger.info("session opened:{}",session);
	}

	public void sessionClosed(SocketSession session) {
		logger.info("session closed:{}",session);
	}

	public void sessionIdled(SocketSession session,long lastIdleTime, long currentTime) {
//		logger.debug("session Idled:{}",session);
	}
}

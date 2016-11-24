package com.generallycloud.nio.balance;

import com.generallycloud.nio.common.Logger;
import com.generallycloud.nio.common.LoggerFactory;
import com.generallycloud.nio.component.SEListenerAdapter;
import com.generallycloud.nio.component.SocketSession;

public class FrontReverseAcceptorSEListener extends SEListenerAdapter {

	private Logger			logger	= LoggerFactory.getLogger(FrontReverseAcceptorSEListener.class);

	private FrontContext	context;

	public FrontReverseAcceptorSEListener(FrontContext context) {
		this.context = context;
	}

	public void sessionOpened(SocketSession session) {
		logger.info("负载服务器来自 " + session + " 已建立连接.");
		context.getFrontRouter().addRouterSession((SocketSession) session);
	}

	public void sessionClosed(SocketSession session) {
		logger.info("负载服务器来自 " + session + " 已断开连接.");
		context.getFrontRouter().removeRouterSession((SocketSession) session);
	}
}

/*
 * Copyright 2015-2017 GenerallyCloud.com
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package com.generallycloud.nio.container.rtp;

import java.util.Map;

import com.generallycloud.nio.component.SocketSession;
import com.generallycloud.nio.configuration.ServerConfiguration;
import com.generallycloud.nio.container.AbstractPluginContext;
import com.generallycloud.nio.container.ApplicationContext;
import com.generallycloud.nio.container.configuration.Configuration;
import com.generallycloud.nio.container.rtp.server.RTPCreateRoomServlet;
import com.generallycloud.nio.container.rtp.server.RTPJoinRoomServlet;
import com.generallycloud.nio.container.rtp.server.RTPLeaveRoomServlet;
import com.generallycloud.nio.container.rtp.server.RTPRoomFactory;
import com.generallycloud.nio.container.rtp.server.RTPSessionAttachment;
import com.generallycloud.nio.container.rtp.server.RTPSessionEventListener;
import com.generallycloud.nio.container.service.FutureAcceptorService;

public class RTPContext extends AbstractPluginContext {
	
private ServerConfiguration socketChannelConfig;
	
	private ServerConfiguration datagramChannelConfig;

	public ServerConfiguration getSocketChannelConfig() {
		return socketChannelConfig;
	}

	public void setSocketChannelConfig(ServerConfiguration socketChannelConfig) {
		this.socketChannelConfig = socketChannelConfig;
	}

	public ServerConfiguration getDatagramChannelConfig() {
		return datagramChannelConfig;
	}

	public void setDatagramChannelConfig(ServerConfiguration datagramChannelConfig) {
		this.datagramChannelConfig = datagramChannelConfig;
	}

	private RTPRoomFactory		rtpRoomFactory	= new RTPRoomFactory();
	private static RTPContext	instance		;

	public static RTPContext getInstance() {
		return instance;
	}

	@Override
	public void configFutureAcceptor(Map<String, FutureAcceptorService> acceptors) {

		putServlet(acceptors, new RTPJoinRoomServlet());
		putServlet(acceptors, new RTPCreateRoomServlet());
		putServlet(acceptors, new RTPLeaveRoomServlet());
	}

	@Override
	public void initialize(ApplicationContext context, Configuration config) throws Exception {

		context.addSessionEventListener(new RTPSessionEventListener());
		
		instance = this;
	}
	
	public RTPSessionAttachment getSessionAttachment(SocketSession session){
		return (RTPSessionAttachment) session.getAttachment(this.getPluginIndex());
	}

	public RTPRoomFactory getRTPRoomFactory() {
		return rtpRoomFactory;
	}

	@Override
	public void destroy(ApplicationContext context, Configuration config) throws Exception {
		instance = null;
	}

}

package com.google.gwt.sample.SE_group9.client;

import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("data")
public interface GreetingService extends RemoteService {

	
	String [][] importData (String path);

	public Integer getNumberOfColumns (String path);
	public Integer getNumberOfLines (String path);
	
}

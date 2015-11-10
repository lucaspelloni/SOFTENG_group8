package com.google.gwt.sample.SE_group9.client;

import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	
	void importData(String path, AsyncCallback<String[][]> callback);
	void getNumberOfLines(String path, AsyncCallback<Integer> callback);
	void getNumberOfColumns(String path, AsyncCallback<Integer> callback);

}

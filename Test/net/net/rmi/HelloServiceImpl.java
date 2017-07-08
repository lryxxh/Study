/**
 * HelloServiceImpl.java
 * Created by liurenyong at 2013-9-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author liurenyong 2013-9-24
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService, Remote{
	
	ExecutorService threadPool = Executors.newFixedThreadPool(3);
    
    /**
     * 
     * @throws RemoteException
     * @author liurenyong 2013-9-24
     */
    protected HelloServiceImpl() throws RemoteException {
        super();
    }
    
    Future future = null;

    @SuppressWarnings("unchecked")
	@Override
    public String say(final String word) throws IOException {
//        int a = 100;
//        int b = 0;
//        try{
//        System.out.println(a / b);
//        }catch (Exception e) {
//            try {
//                throw new Exception(".......");
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    	try {
//			future = threadPool.submit(new Callable() {
//				/* (non-Javadoc)
//				 * @see java.util.concurrent.Callable#call()
//				 */
//				@Override
//				public Object call() throws Exception {
//					for(int i =0;i<10000;i++) {
//						System.out.println(Thread.currentThread().isInterrupted()+ ""+ future.isCancelled() + " " +i + " hello " + word + " " + Thread.currentThread().getName());
//					}
//					
//					test();
//			        return "hello " + word;
//				}
//
//				private void test() {
//					for(int i =0;i<10000;i++) {
//						System.out.println(Thread.currentThread().isInterrupted()+ ""+future.isCancelled() + "-----------------" + i);
//					}
//				}
//			});
//			if(!future.isCancelled()) {
//				return future.get().toString();
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
    	return "Hello " +word  +" " + world;
    	
    }
    String world = "ÕÅÈý";
    public void cancelTask() throws IOException{
    	if(future != null) {
    		future.cancel(true);
    	}
    	System.out.println("---task cancel");
    }
	@Override
	public void set(String word) throws IOException {
		this.world = word;		
	}


}

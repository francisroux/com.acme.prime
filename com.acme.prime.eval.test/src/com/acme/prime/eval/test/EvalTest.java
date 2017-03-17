package com.acme.prime.eval.test;
import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import com.acme.prime.eval.api.Eval;

public class EvalTest {

    private final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    
    <T> T getService(Class<T> clazz) throws Exception {
    	ServiceTracker<T,T> st = new ServiceTracker<>(context, clazz, null);
    	st.open();
    	return st.waitForService(1000);
    }
    
    <T> T getService(Class<T> clazz, String filterStr) throws Exception {
    	Filter filter = context.createFilter(filterStr);
    	ServiceTracker<T,T> st = new ServiceTracker<>(context, filter, null);
    	st.open();
    	return st.waitForService(1000);
    }
    
    @Test
    public void testEval() throws Exception {
    	Assert.assertNotNull(context);
    	Assert.assertNotNull(getService(Eval.class));
    }
    
    @Test
    public void trig() throws Exception {
    	String serviceFilter = "(osgi.identity=com.acme.prime.eval.parsii.provider)";
    	Assert.assertSame(1, (int)getService(Eval.class, serviceFilter).eval("sin(1)*sin(1)+cos(1)*cos(1)"));
    }
}
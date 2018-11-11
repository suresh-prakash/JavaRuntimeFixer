package com.jrf.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class JavaRuntimeFixerAgent {
	public static void agentmain(String args, Instrumentation instrumentation) {
		try {
			addTransformers(args, instrumentation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addTransformers(String args, Instrumentation instrumentation) 
		throws ClassNotFoundException, UnmodifiableClassException {

		instrumentation.addTransformer(new JavaRuntimeFixApplier(), true);
			
		if (!instrumentation.isRetransformClassesSupported()) {
			System.out.println("Class retransformation is not supported in this version of JVM");
			return;
		}

		Class<?> clazz = Class.forName("com.jrf.sampleapp.SimpleCalculator");
		instrumentation.retransformClasses(clazz);
	}
}

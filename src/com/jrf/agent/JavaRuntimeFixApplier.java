package com.jrf.agent;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavaRuntimeFixApplier implements ClassFileTransformer {
	public byte[] transform(ClassLoader loader, String className,
			Class classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) {
		byte[] byteCode = classfileBuffer;

		try {
			byteCode = retransform(className, byteCode);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return byteCode;
	}

	private byte[] retransform(String className, byte[] original) throws Exception {
		byte[] modifiedByteCode = original;

		switch(className) {
			case "com/jrf/sampleapp/SimpleCalculator":
				ClassPool classPool = ClassPool.getDefault();
				CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(modifiedByteCode));
				CtMethod method = ctClass.getDeclaredMethod("divide", 
					new CtClass[] {CtClass.longType, CtClass.longType});
	
				method.insertBefore("if ($2 == 0) {" +
							"return \"Cannot divide by zero\";" +
						    "}");
	
				modifiedByteCode = ctClass.toBytecode();
				ctClass.detach();
				break;
		}

		return modifiedByteCode;
	}
}

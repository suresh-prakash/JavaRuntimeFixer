package com.jrf.loader;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import java.io.*;

public class JavaRuntimeFixLoader {
	public static void main(String[] args) throws AgentInitializationException, 
		AgentLoadException, AttachNotSupportedException, IOException {

		if (args.length != 2) {
			System.out.println("Usage: java JavaRuntimeFixLoader <agent_path_relative_to_the_app_to_be_instrumented> <jvm_pid>");
			System.exit(-1);
		}
		
		VirtualMachine jvm = VirtualMachine.attach(args[1]);
		jvm.loadAgent(args[0]);
		jvm.detach();
	}
}

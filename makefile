instrument: bin/com/jrf/loader/JavaRuntimeFixLoader.class bin/agent.jar
	java -cp bin:lib/tools.jar com/jrf/loader/JavaRuntimeFixLoader ../bin/agent.jar `ps -ef | grep [c]om/jrf/sampleapp/SimpleCalculator | awk '{print $$2}'`

bin/agent.jar: src/MANIFEST.MF bin/com/jrf/agent/JavaRuntimeFixApplier.class bin/com/jrf/agent/JavaRuntimeFixerAgent.class
	jar cmf src/MANIFEST.MF bin/agent.jar bin/com/jrf/agent/JavaRuntimeFixApplier.class bin/com/jrf/agent/JavaRuntimeFixerAgent.class lib/javassist.jar

bin/com/jrf/agent/JavaRuntimeFixApplier.class: src/com/jrf/agent/JavaRuntimeFixApplier.java
	javac -cp lib/javassist.jar -d bin/ src/com/jrf/agent/JavaRuntimeFixApplier.java

bin/com/jrf/agent/JavaRuntimeFixerAgent.class: src/com/jrf/agent/JavaRuntimeFixerAgent.java bin/com/jrf/agent/JavaRuntimeFixApplier.class
	javac -cp bin:lib/javassist.jar -d bin/ src/com/jrf/agent/JavaRuntimeFixerAgent.java

bin/com/jrf/loader/JavaRuntimeFixLoader.class: src/com/jrf/loader/JavaRuntimeFixLoader.java
	javac -cp lib/tools.jar -d bin/ src/com/jrf/loader/JavaRuntimeFixLoader.java

sampleapp:
	make -C example

clean:
	rm -rf bin/*
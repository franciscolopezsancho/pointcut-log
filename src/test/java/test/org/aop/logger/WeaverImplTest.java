package test.org.aop.logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.aop.logger.annotation.LoggingDebug;
import org.aop.logger.annotation.LoggingError;
import org.aop.logger.annotation.LoggingInfo;
import org.aop.logger.annotation.LoggingOff;
import org.aop.logger.annotation.LoggingTrace;
import org.aop.logger.annotation.LoggingWarn;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Test;

public class WeaverImplTest {

	String log4jProperties = "src/test/java/log4j.properties";

	@Test
	public void logTrace() throws IOException, InterruptedException {
		// something should be written in log (@see log4j.properties file)
		FileAppender fa = new FileAppender();
		fa.setName("FileLogger1");
		fa.setFile("aop.trace.log");
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.TRACE);
		fa.setAppend(true);
		fa.activateOptions();
		Logger.getRootLogger().addAppender(fa);

		
		
		traceecart();
		debuggubed();
		infoofin();
		warnnraw();
		errorrorre();
		offffo();
		withNoAnnotation();

		File result = new File("aop.trace.log");
		String writtenLog = FileUtils.readFileToString(result, StandardCharsets.UTF_8);
		assertTrue(writtenLog.contains("traceecart()"));
		assertTrue(writtenLog.contains("debuggubed()"));
		assertTrue(writtenLog.contains("infoofin()"));
		assertTrue(writtenLog.contains("warnnraw()"));
		assertTrue(writtenLog.contains("errorrorre()"));
		assertFalse(writtenLog.contains("offffo()"));
		assertTrue(writtenLog.contains("withNoAnnotation()"));



		result.delete();

	}

	@Test
	public void logInfo() throws IOException, InterruptedException {
		// something should be written in log (@see log4j.properties file)
		FileAppender fa = new FileAppender();
		fa.setName("FileLogger2");
		fa.setFile("aop.info.log");
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.INFO);
		fa.setAppend(true);
		fa.activateOptions();

		// add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(fa);
		// repeat with all other desired appenders

		traceecart();
		debuggubed();
		infoofin();
		warnnraw();
		errorrorre();
		offffo();
		withNoAnnotation();

		File result = new File("aop.info.log");
		String writtenLog = FileUtils.readFileToString(result, StandardCharsets.UTF_8);
		assertFalse(writtenLog.contains("traceecart()"));
		assertFalse(writtenLog.contains("debuggubed()"));
		assertTrue(writtenLog.contains("infoofin()"));
		assertTrue(writtenLog.contains("warnnraw()"));
		assertTrue(writtenLog.contains("errorrorre()"));
		assertFalse(writtenLog.contains("offffo()"));
		assertFalse(writtenLog.contains("withNoAnnotation()"));

		

		result.delete();

	}

	@Test
	public void logDegub() throws IOException, InterruptedException {
		FileAppender fa = new FileAppender();
		fa.setName("FileLogger3");
		fa.setFile("aop.debug.log");
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.DEBUG);
		fa.setAppend(true);
		fa.activateOptions();

		// add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(fa);

		traceecart();
		debuggubed();
		infoofin();
		warnnraw();
		errorrorre();
		offffo();
		withNoAnnotation();

		File result = new File("aop.debug.log");
		String writtenLog = FileUtils.readFileToString(result, StandardCharsets.UTF_8);
		assertFalse(writtenLog.contains("traceecart()"));
		assertTrue(writtenLog.contains("debuggubed()"));
		assertTrue(writtenLog.contains("infoofin()"));
		assertTrue(writtenLog.contains("warnnraw()"));
		assertTrue(writtenLog.contains("errorrorre()"));
		assertFalse(writtenLog.contains("offffo()"));
		assertTrue(writtenLog.contains("withNoAnnotation()"));




		result.delete();

	}

	@Test
	public void logWarn() throws IOException, InterruptedException {
		FileAppender fa = new FileAppender();
		fa.setName("FileLogger4");
		fa.setFile("aop.warn.log");
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.WARN);
		fa.setAppend(true);
		fa.activateOptions();

		// add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(fa);

		traceecart();
		debuggubed();
		infoofin();
		warnnraw();
		errorrorre();
		offffo();
		withNoAnnotation();

		File result = new File("aop.warn.log");
		String writtenLog = FileUtils.readFileToString(result, StandardCharsets.UTF_8);
		assertFalse(writtenLog.contains("traceecart()"));
		assertFalse(writtenLog.contains("debuggubed()"));
		assertFalse(writtenLog.contains("infoofin()"));
		assertTrue(writtenLog.contains("warnnraw()"));
		assertTrue(writtenLog.contains("errorrorre()"));
		assertFalse(writtenLog.contains("offffo()"));
		assertFalse(writtenLog.contains("withNoAnnotation()"));


		result.delete();

	}

	@Test
	public void logError() throws IOException, InterruptedException {
		FileAppender fa = new FileAppender();
		fa.setName("FileLogger5");
		fa.setFile("aop.error.log");
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.ERROR);
		fa.setAppend(true);
		fa.activateOptions();

		// add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(fa);
		traceecart();
		debuggubed();
		infoofin();
		warnnraw();
		errorrorre();
		offffo();
		withNoAnnotation();
		
		File result = new File("aop.error.log");
		String writtenLog = FileUtils.readFileToString(result, StandardCharsets.UTF_8);
		assertFalse(writtenLog.contains("traceecart()"));
		assertFalse(writtenLog.contains("debuggubed()"));
		assertFalse(writtenLog.contains("infoofin()"));
		assertFalse(writtenLog.contains("warnnraw()"));
		assertTrue(writtenLog.contains("errorrorre()"));
		assertFalse(writtenLog.contains("offffo()"));
		assertFalse(writtenLog.contains("withNoAnnotation()"));


		result.delete();

	}

	@LoggingTrace
	public void traceecart() {

	}

	@LoggingDebug
	public void debuggubed() {

	}

	@LoggingInfo
	public void infoofin() {

	}

	@LoggingWarn
	public void warnnraw() {

	}

	@LoggingError
	public void errorrorre() {

	}

	@LoggingOff
	public void offffo() {

	}
	
	public void withNoAnnotation(){
		
	}
}

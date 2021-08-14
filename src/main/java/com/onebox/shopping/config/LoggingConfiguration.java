package com.onebox.shopping.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import net.logstash.logback.appender.LogstashUdpSocketAppender;
import net.logstash.logback.layout.LogstashLayout;

@Configuration
public class LoggingConfiguration {

	private final Logger log = LoggerFactory.getLogger(LoggingConfiguration.class);

	private LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

	@Value("${application.logstash.enabled}")
	private boolean logstashEnabled;

	@Value("${application.logstash.host}")
	private String logstashHost;

	@Value("${application.logstash.port}")
	private int logstashPort;

	@Value("${application.logstash.queusize}")
	private int logstashQueuSize;

	private final String appName;
	private final String serverPort;

	public LoggingConfiguration(@Value("${spring.application.name}") String appName,
			@Value("${server.port}") String serverPort) {
		this.appName = appName;
		this.serverPort = serverPort;

		if (logstashEnabled) {
			addLogstashAppender(context);

			// Add context listener
			LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener();
			loggerContextListener.setContext(context);
			context.addListener(loggerContextListener);
		}
	}

	public void addLogstashAppender(LoggerContext context) {
		log.info("Initializing Logstash logging");

		LogstashUdpSocketAppender logstashAppender = new LogstashUdpSocketAppender();
		logstashAppender.setName("LOGSTASH");
		logstashAppender.setContext(context);
		String customFields = "{\"app_name\":\"" + appName + "\",\"app_port\":\"" + serverPort + "\"}";

		logstashAppender.setSyslogHost(logstashHost);
		logstashAppender.setPort(logstashPort);
		
		LogstashLayout layout = new LogstashLayout();
		layout.setCustomFields(customFields);
		logstashAppender.setLayout(layout);

		// UDP limit size
		logstashAppender.setMaxMessageSize(7500);

		logstashAppender.start();

		// Wrap the appender in an Async appender for performance
		AsyncAppender asyncLogstashAppender = new AsyncAppender();
		asyncLogstashAppender.setContext(context);
		asyncLogstashAppender.setName("ASYNC_LOGSTASH");
		asyncLogstashAppender.setQueueSize(logstashQueuSize);
		asyncLogstashAppender.addAppender(logstashAppender);
		asyncLogstashAppender.start();

		context.getLogger("ROOT").addAppender(asyncLogstashAppender);
	}

	/**
	 * Logback configuration is achieved by configuration file and API. When
	 * configuration file change is detected, the configuration is reset. This
	 * listener ensures that the programmatic configuration is also re-applied
	 * after reset.
	 */
	class LogbackLoggerContextListener extends ContextAwareBase implements LoggerContextListener {

		@Override
		public boolean isResetResistant() {
			return true;
		}

		@Override
		public void onStart(LoggerContext context) {
			addLogstashAppender(context);
		}

		@Override
		public void onReset(LoggerContext context) {
			addLogstashAppender(context);
		}

		@Override
		public void onStop(LoggerContext context) {
			// Nothing to do.
		}

		@Override
		public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level) {
			// Nothing to do.
		}
	}

}

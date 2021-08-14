package com.onebox.shopping.rest.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiModelProperty;

public class LoggerVM {

	@NotNull
	@ApiModelProperty(value = "The log classname", required = true, example = "org.springframework.boot")
	private String name;

	@NotNull
	@ApiModelProperty(value = "The log level", required = true, example = "INFO", allowableValues = "{ERROR, DEBUG, INFO, WARN, TRACE}")
	private String level;

	public LoggerVM(Logger logger) {
		this.name = logger.getName();
		this.level = logger.getEffectiveLevel().toString();
	}

	@JsonCreator
	public LoggerVM() {
		// Empty public constructor used by Jackson.
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "LoggerVM{" + "name='" + name + '\'' + ", level='" + level + '\'' + '}';
	}
}

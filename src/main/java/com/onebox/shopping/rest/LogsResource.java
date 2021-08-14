package com.onebox.shopping.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.shopping.rest.model.LoggerVM;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;

@RestController
@RequestMapping("/management")
@Api(value = "/management/logs")
@SwaggerDefinition(tags = {
		@io.swagger.annotations.Tag(name = "Logs Resource", description = "Change logs functionality")
})
public class LogsResource {
	
	@GetMapping("/logs")
    @ApiOperation(value = "Find logs")
    public List<LoggerVM> getList() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        return context.getLoggerList()
            .stream()
            .map(LoggerVM::new)
            .collect(Collectors.toList());
    }

    @PutMapping("/logs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update logs level")
    public void changeLevel(@RequestBody LoggerVM jsonLogger) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger(jsonLogger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));
    }

    @PutMapping("/logs-all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update all logs level")
    public void changeAllLevels(@RequestBody LoggerVM jsonLogger) {
    	LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggers = context.getLoggerList();

        for (Logger logger : loggers) {
        	context.getLogger(logger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));
        }
    }

}

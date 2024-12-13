package mx.axa.gestiondocumental.monitoreo.controller;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import mx.axa.gestiondocumental.monitoreo.bean.EventSchema;
import mx.axa.gestiondocumental.monitoreo.config.ReadConfig;
import mx.axa.gestiondocumental.monitoreo.service.EcmEventOrchestration;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
	
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */    	
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
EcmEventOrchestration ecmEventOrchestration = new EcmEventOrchestration();
String result = ecmEventOrchestration.executionOrchestration();
        // Parse query parameter
        final String query = request.getQueryParameters().get("name");
        final String name = request.getBody().orElse(query);

        if (name == null) {
        	context.getLogger().info("Please pass a name");
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a nam:"+result).build();
        } else {
        	context.getLogger().info("Hi, " + name);
            return request.createResponseBuilder(HttpStatus.OK).body("Hi, " + name+" & "+result).build();
        }
        
    }
    @FunctionName("eventGridMonitorTrackingECM00")
    public void logEvent(
      @EventGridTrigger(
        name = "event"
      )
      EventSchema event,
      final ExecutionContext context) {
        context.getLogger().info("Event content: ");
        context.getLogger().info("Subject: " + event.subject);
        context.getLogger().info("Time: " + event.eventTime); // automatically converted to Date by the runtime
        context.getLogger().info("Id: " + event.id);
        context.getLogger().info("Data: " + event.data);
    }
    public static void main(String[] args) {
		System.out.println("Hi");
		EcmEventOrchestration ecmEventOrchestration = new EcmEventOrchestration();
		System.out.println(ecmEventOrchestration.executionOrchestration());
	}
}

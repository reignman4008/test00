package mx.axa.gestiondocumental.monitoreo.service;

import mx.axa.gestiondocumental.monitoreo.config.ReadConfig;
import mx.axa.gestiondocumental.monitoreo.controller.ECMRestClient;
import mx.axa.gestiondocumental.monitoreo.util.ConvierteBase64Util;

public class EcmEventOrchestration {
	private ReadConfig config;

	public EcmEventOrchestration() {
		config = new ReadConfig();
	}

	public String executionOrchestration() {
		String result;
		String result2;
//		Se ejecuta el primer rest client
		ECMRestClient eCMRestClient= new ECMRestClient();
		result = eCMRestClient.invokeOauth((String)config.getDpConfig().get("token-url"));
//		De la cadena se cambia a Base64		
		System.out.println("Cadena base64"+ConvierteBase64Util.encodeToBase64(result));
		
//		se ejecuta el segundo rest client
		result2 = eCMRestClient.invokeService((String)config.getDpConfig().get("service-url"), result);

		return result2;
	}

	public static void main(String[] args) {
		EcmEventOrchestration ecmEventOrchestration = new EcmEventOrchestration();
		System.out.println(ecmEventOrchestration.executionOrchestration());
	}
}

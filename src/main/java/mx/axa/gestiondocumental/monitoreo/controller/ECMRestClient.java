package mx.axa.gestiondocumental.monitoreo.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import mx.axa.gestiondocumental.monitoreo.bean.Product;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpHeaders;
import java.io.IOException;

public class ECMRestClient {
	public String invokeOauth(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Status Code: " + response.statusCode());
			System.out.println("Response Body: " + response.body());

			ObjectMapper mapper = new ObjectMapper();
			List<Product> products = mapper.readValue(response.body(),
					mapper.getTypeFactory().constructCollectionType(List.class, Product.class));

			products.forEach(System.out::println);
			return products.get(2).getId();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String invokeService(String url, String parameter) {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url+parameter)).GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Status Code: " + response.statusCode());
			System.out.println("Response Body: " + response.body());

			ObjectMapper mapper = new ObjectMapper();
			List<Product> products = mapper.readValue(response.body(),
					mapper.getTypeFactory().constructCollectionType(List.class, Product.class));

			products.forEach(System.out::println);
			return products.get(0).getName();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
}

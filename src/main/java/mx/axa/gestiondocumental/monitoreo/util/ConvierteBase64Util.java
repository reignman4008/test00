package mx.axa.gestiondocumental.monitoreo.util;

import java.util.Base64;

public class ConvierteBase64Util {

	public static String encodeToBase64(String input) {
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException("La cadena de entrada no puede ser nula ni vacía");
		}

		// Convertimos la cadena a un arreglo de bytes
		byte[] inputBytes = input.getBytes();

		// Usamos la clase Base64 para codificar los bytes
		String encodedString = Base64.getEncoder().encodeToString(inputBytes);

		return encodedString;
	}

	public static void main(String[] args) {
		String original = "Hola Mundo";
		String encoded = encodeToBase64(original);
		System.out.println("Cadena original: " + original);
		System.out.println("Cadena codificada en Base64: " + encoded);
	}
	
}

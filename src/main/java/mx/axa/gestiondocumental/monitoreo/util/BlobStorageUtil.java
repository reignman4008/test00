package mx.axa.gestiondocumental.monitoreo.util;

	import com.azure.storage.blob.BlobClient;
	import com.azure.storage.blob.BlobClientBuilder;

	import java.io.ByteArrayInputStream;
	import java.io.IOException;
	import java.nio.charset.StandardCharsets;

	public class BlobStorageUtil {

	    public static void uploadToAzureBlob(String connectionString, String containerName, String blobName, String content) {
	        try {
	            // Crear un BlobClient para manejar el blob
	            BlobClient blobClient = new BlobClientBuilder()
	                    .connectionString(connectionString)
	                    .containerName(containerName)
	                    .blobName(blobName)
	                    .buildClient();

	            // Convertir el contenido a bytes
	            byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
	            ByteArrayInputStream inputStream = new ByteArrayInputStream(contentBytes);

	            // Subir el archivo al Blob Storage
	            blobClient.upload(inputStream, contentBytes.length, true);
	            System.out.println("Archivo subido correctamente al blob: " + blobName);

	        } catch (Exception e) {
	            System.err.println("Error al subir el archivo al Azure Blob Storage: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) throws IOException {
	        // Conexión al almacenamiento de Azure (reemplazar con tu cadena de conexión)
	        String connectionString = "DefaultEndpointsProtocol=https;AccountName=tuAccountName;AccountKey=tuAccountKey;EndpointSuffix=core.windows.net";

	        // Nombre del contenedor y del blob
	        String containerName = "mi-contenedor";
	        String blobName = "mi-archivo.txt";

	        // Contenido del archivo
	        String content = "Hola desde Java 17 y Azure Blob Storage!";

	        // Subir el archivo al Azure Blob Storage
	        uploadToAzureBlob(connectionString, containerName, blobName, content);
	    }
	}




package mx.axa.gestiondocumental.monitoreo.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class ReadConfig {
	private Map<String, Object> blobConfig;
	private Map<String, Object> dpConfig;
	
	public ReadConfig() {
		// Ruta del archivo YAML
		String yamlFilePath = "config.yml";

			// Crear instancia de SnakeYAML
			Yaml yaml = new Yaml();
			 try (InputStream inputStream = ReadConfig.class.getClassLoader().getResourceAsStream(yamlFilePath)) {
		            if (inputStream == null) {
		                throw new IllegalArgumentException("Archivo config.yml no encontrado en classpath");
		            }
			// Cargar el contenido del YAML como un mapa
			Map<String, Object> data = yaml.load(inputStream);

			// Acceder a las propiedades
			blobConfig = (Map<String, Object>) data.get("blobstorage");
			dpConfig = (Map<String, Object>) data.get("datapower");
		} catch (Exception e) {
			System.err.println("Error al leer el archivo YAML: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Ruta del archivo YAML
		String yamlFilePath = "config.yml";

		try (InputStream inputStream = new FileInputStream(yamlFilePath)) {
			// Crear instancia de SnakeYAML
			Yaml yaml = new Yaml();

			// Cargar el contenido del YAML como un mapa
			Map<String, Object> data = yaml.load(inputStream);

			// Acceder a las propiedades
			Map<String, Object> appConfig = (Map<String, Object>) data.get("app");
			String appName = (String) appConfig.get("name");
			String appVersion = (String) appConfig.get("version");

			Map<String, Object> serverConfig = (Map<String, Object>) data.get("server");
			int serverPort = (int) serverConfig.get("port");
			String serverHost = (String) serverConfig.get("host");

			// Imprimir las propiedades
			System.out.println("App Name: " + appName);
			System.out.println("App Version: " + appVersion);
			System.out.println("Server Port: " + serverPort);
			System.out.println("Server Host: " + serverHost);

		} catch (Exception e) {
			System.err.println("Error al leer el archivo YAML: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Map<String, Object> getBlobConfig() {
		return blobConfig;
	}

	public void setBlobConfig(Map<String, Object> blobConfig) {
		this.blobConfig = blobConfig;
	}

	public Map<String, Object> getDpConfig() {
		return dpConfig;
	}

	public void setDpConfig(Map<String, Object> dpConfig) {
		this.dpConfig = dpConfig;
	}
	
}

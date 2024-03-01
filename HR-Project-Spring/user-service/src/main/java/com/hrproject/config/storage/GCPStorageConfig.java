
package com.hrproject.config.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GCPStorageConfig {

  @Bean
  public Storage gcpStorage() throws IOException {
	// Google Cloud Storage'a erişim için kimlik doğrulama yapılandırması
	ClassPathResource resource = new ClassPathResource("data-gearbox-405211-e4f80a385e4f.json"); // Service Account Key dosyanın adını kullan
	InputStream input = resource.getInputStream();

	Storage storage = StorageOptions.newBuilder()
									.setCredentials(GoogleCredentials.fromStream(input))
									.build()
									.getService();

	return storage;
  }
}

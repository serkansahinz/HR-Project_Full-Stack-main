package com.hrproject.utility;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class FileService {
  private final Storage gcpStorage;

  @Autowired
  public FileService(Storage gcpStorage) {
	this.gcpStorage = gcpStorage;
  }

  public String uploadFile(MultipartFile avatarFile) {


	String bucketName = "hrproject_team3"; // Kullanılacak bucket'ın adını gir
	BlobId blobId = BlobId.of(bucketName,avatarFile.getOriginalFilename());
	BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
								.setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER))))
								.build();

	try {
	  gcpStorage.create(blobInfo,avatarFile.getBytes());
	  String avatarUrl = "https://storage.googleapis.com/" + bucketName + "/" + avatarFile.getOriginalFilename();
	  return avatarUrl;
	} catch (IOException e) {
	  e.printStackTrace();
	  return null;
	}
  }
}

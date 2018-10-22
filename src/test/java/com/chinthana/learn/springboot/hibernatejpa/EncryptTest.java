package com.chinthana.learn.springboot.hibernatejpa;

import java.nio.ByteBuffer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootHibernateJpaApplication.class)
public class EncryptTest {
	private static String keyArn;
	private static String data;

	@Test
	public void testEncrypt() {
		keyArn = "arn:aws:kms:us-east-1:675888047069:key/110d813b-f3ea-4b24-9d4d-7beef315dcf6";
		data = "DataToEncrypt";
		
		
		//Creating AWSKMS client
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIRKBEVPMOCONHB2Q", "8uk3AL4gFbJwa7kisbegZhbXHPoQs+Z36njwFdZU");
		AWSKMS kmsClient = AWSKMSClientBuilder.standard()
		                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-east-1")
		                        .build();		
		
		// Generate a data key		
		GenerateDataKeyRequest dataKeyRequest = new GenerateDataKeyRequest();
		dataKeyRequest.setKeyId("alias/cmk-learn");
		dataKeyRequest.setKeySpec("AES_128");

		GenerateDataKeyResult dataKeyResult = kmsClient.generateDataKey(dataKeyRequest);

		ByteBuffer plaintextKey = dataKeyResult.getPlaintext();

		ByteBuffer encryptedKey = dataKeyResult.getCiphertextBlob();
		
		System.out.println("#####################################" + plaintextKey);
		
		
		String keyId = "arn:aws:kms:us-west-2:111122223333:key/1234abcd-12ab-34cd-56ef-1234567890ab";
		ByteBuffer plaintext = ByteBuffer.wrap(data.getBytes());

		EncryptRequest req = new EncryptRequest().withKeyId("alias/cmk-learn").withPlaintext(plaintext);
		ByteBuffer ciphertext = kmsClient.encrypt(req).getCiphertextBlob();
		
		
		DecryptRequest req1 = new DecryptRequest().withCiphertextBlob(ciphertext);
		ByteBuffer plainText = kmsClient.decrypt(req1).getPlaintext();
		
		System.out.println(plainText.array().toString());
		
		
	}
}
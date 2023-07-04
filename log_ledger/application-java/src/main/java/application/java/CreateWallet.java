import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.sdk.identity.X509Identity;

public class CreateWallet {

	public static void main(String[] args) {
		String walletPath = "./wallet";
		String certPath = "/home/ogn/denemeler/log_project/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/signcerts/Admin@org1.example.com-cert.pem";
		String keyPath = "/home/ogn/denemeler/log_project/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/keystore/priv_sk";
		String identityLabel = "Admin@org1.example.com";
		String mspId = "Org1MSP";

		try {
			Wallet wallet = Wallets.newFileSystemWallet(Paths.get(walletPath));

			Path certFile = Paths.get(certPath);
			byte[] certData = Files.readAllBytes(certFile);
			X509Identity identity = new X509Identity(mspId, certData, loadPrivateKey(keyPath));

			wallet.put(identityLabel, identity);

			System.out.println("Cüzdan oluşturuldu ve kullanıcı kimlik bilgileri eklendi.");
		} catch (Exception e) {
			System.err.println("Hata: " + e.getMessage());
		}
	}

	private static byte[] loadPrivateKey(String keyPath) throws Exception {
		Path keyFile = Paths.get(keyPath);
		return Files.readAllBytes(keyFile);
	}
}

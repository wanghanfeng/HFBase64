import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Base64;

public class Main {

	public static void main(String[] args) throws IOException {
		HFBase64 encoder = new HFBase64();
		
		File file = new File("/Users/apple/Documents/其他/Resource/Picture/Mine/whf.jpg");
		File outFile = new File("/Users/apple/Documents/其他/Resource/Picture/Mine/outBase64.txt");
		File outFile2 = new File("/Users/apple/Documents/其他/Resource/Picture/Mine/outBase64_2.jpg");
		
		FileInputStream fs = new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(outFile);
		FileOutputStream os2 = new FileOutputStream(outFile2);
		
		byte[] buf = new byte[1024];
		while (fs.read(buf) != -1) {
			System.out.print(encoder.enCode(buf));
			os.write(encoder.enCode(buf).getBytes());
			os2.write(encoder.deCode(encoder.enCode(buf)));
//			os2.write(Base64.getDecoder().decode(encoder.enCode(buf)));
		}
		fs.close();
//		fs = new FileInputStream(file);
////		os.write("\n".getBytes());
//		while (fs.read(buf) != -1) {
//			System.out.print(Base64.getEncoder().encodeToString(buf));
//			os2.write(Base64.getEncoder().encodeToString(buf).getBytes());
//		}
		
		
		fs.close();
		os.close();
		os2.close();
//		Base64.getEncoder().encode(buf);
	}

}

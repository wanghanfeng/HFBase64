
public class HFBase64 {

	private static String encodingTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	
	public HFBase64() {
		
	}
	
	public String enCode(byte[] datas) {
		if (datas == null) {
			return null;
		}
		System.out.println(""+encodingTable.charAt(12));
		System.out.println(""+encodingTable.charAt(33));
		System.out.println(""+encodingTable.charAt(17));
		System.out.println(""+encodingTable.charAt(46));
		System.out.println(""+encodingTable.charAt(9));
		System.out.println(""+encodingTable.charAt(30));
		String ciphertext = "";
		Integer byteIndex = 0;
		while (byteIndex < datas.length) {
			Integer index = 0;
			byte[] temByte = new byte[3];
			byte temp;
			while (index < 3 && byteIndex < datas.length)
				temByte[index++] = datas[byteIndex++];
			ciphertext += encodingTable.charAt((temByte[0] & 0xfc) >> 2);
			ciphertext += encodingTable.charAt(((temByte[0] & 0x03) << 4) | ((temByte[1] & 0xf0) >> 4));
			if (index > 1) {
				ciphertext += encodingTable.charAt(((temByte[1] & 0x0f) << 2) | (temByte[2] & 0xc0) >> 6);
			} else {
				ciphertext += '=';
			}
			
			if (index > 2) {
				ciphertext += encodingTable.charAt((temByte[2] & 0x3f));
			} else {
				ciphertext += '=';
			}
		}
		
		return ciphertext;
	}
	
	public byte[] deCode(String datas) {
		if (datas == null) {
			return null;
		}
		Integer len = (datas.length()) / 4 * 3;
		byte[] plainBytes = new byte[len];
		
		Integer charCount = 0;
		Integer index = 0;
		
		while (charCount < datas.length()) {
			
			plainBytes[index++] = (byte) ((this.convertByte(datas.charAt(charCount)) << 2) | (this.convertByte(datas.charAt(++charCount))) >> 4);
			if (datas.charAt(charCount + 1) == '=') {
//				plainBytes[--index] = (byte) (this.convertByte(datas.charAt(charCount)) >> 4);
				break;
			}
			plainBytes[index++] = (byte) ((this.convertByte(datas.charAt(charCount)) << 4) | (this.convertByte(datas.charAt(++charCount))) >> 2);
			if (datas.charAt(charCount + 1) == '=') {
//				plainBytes[--index] = (byte) (this.convertByte(datas.charAt(charCount)) >> 2);
				break;
			}
			plainBytes[index++] = (byte) ((this.convertByte(datas.charAt(charCount)) << 6) | (this.convertByte(datas.charAt(++charCount))));
			charCount++;
		}
		
		byte[] newBytes = new byte[index];
		for (int i = 0; i < index; i++) {
			newBytes[i] = plainBytes[i];
		}
		
		return newBytes;
	}
	
	private byte convertByte(char EncodeChar) {
		byte index = (byte) encodingTable.indexOf(EncodeChar);
		return index;
	}
}

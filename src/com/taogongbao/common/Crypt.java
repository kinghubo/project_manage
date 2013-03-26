/*--------------------------------------------------------------------------------------------------
 *Name:		Crypt.java
 *Directory:		/com/jgoal/misc/
 *Author:		Annia qian
 *Description:	common methods of Crypt
 *Amend record
 *Date		Version		Member		Description
 *DD/MM/YYYY	1.0		Firstname Lastname	Createtion
 *--------------------------------------------------------------------------------------------------
 */
package com.taogongbao.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

// import javax.mail.internet.MimeUtility;

public class Crypt {

	private static String serial = "iongloba";

	private static String conserial = "";// "jgoal_annia";

	private static String Algorithm = "DES"; //

	// DES,DESede,Blowfish

	public static String StrEnCrypt(String para_Source) throws Exception {
		String enStr = DESEnCryptA(para_Source, serial);
		enStr = StringUnit.Byte2Hex(enStr);
		return enStr;
	}

	public static String StrEnCrypt(String para_Source, String key)
			throws Exception {
		String enStr = DESEnCryptA(para_Source, key);
		enStr = StringUnit.Byte2Hex(enStr);
		return enStr;
	}

	public static String StrDeCrypt(String para_Source) throws Exception {

		String enStr = StringUnit.Hex2Str(para_Source);
		enStr = DESDeCryptA(enStr, serial);
		return enStr;
	}

	public static String StrDeCrypt(String para_Source, String key)
			throws Exception {

		String enStr = StringUnit.Hex2Str(para_Source);
		enStr = DESDeCryptA(enStr, key);
		return enStr;
	}

	public static String DESEnCrypt(String para_Source) throws Exception {
		return DESEnCryptA(para_Source, serial);
	}

	public static String DESEnCrypt(String para_Source, String key)
			throws Exception {
		return DESEnCryptA(para_Source, key);
	}

	private static String DESEnCryptA(String para_Source, String curserial)
			throws Exception {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		byte result[] = Secret_Encode(para_Source.getBytes(StringUnit.CharSet),
				curserial.getBytes(), "DES");
		enStr = new String(result, StringUnit.CharSet);
		return enStr;
	}

	public static String DESDeCrypt(String para_Source) throws Exception {
		return DESDeCryptA(para_Source, serial);
	}

	public static String DESDeCrypt(String para_Source, String key)
			throws Exception {
		return DESDeCryptA(para_Source, key);
	}

	private static String DESDeCryptA(String para_Source, String curserial)
			throws Exception {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		byte result[] = Secret_Decode(para_Source.getBytes(StringUnit.CharSet),
				curserial.getBytes(), "DES");
		enStr = new String(result, StringUnit.CharSet);
		return enStr;
	}

	public static String Blowfish_EnCrypt(String para_Source) throws Exception {
		return Blowfish_EnCryptA(para_Source, serial);
	}

	public static String Blowfish_EnCrypt(String para_Source, String key)
			throws Exception {
		return Blowfish_EnCryptA(para_Source, key);
	}

	private static String Blowfish_EnCryptA(String para_Source, String curserial)
			throws Exception {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		byte result[] = Secret_Encode(para_Source.getBytes(StringUnit.CharSet),
				curserial.getBytes(), "Blowfish");
		enStr = new String(result, StringUnit.CharSet);
		return enStr;
	}

	public static String Blowfish_DeCrypt(String para_Source) throws Exception,
			UnsupportedEncodingException, InvalidKeyException {
		return Blowfish_DeCryptA(para_Source, serial);
	}

	public static String Blowfish_DeCrypt(String para_Source, String key)
			throws Exception, UnsupportedEncodingException, InvalidKeyException {
		return Blowfish_DeCryptA(para_Source, key);
	}

	private static String Blowfish_DeCryptA(String para_Source, String curserial)
			throws Exception {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		byte result[] = Secret_Decode(para_Source.getBytes(StringUnit.CharSet),
				curserial.getBytes(), "Blowfish");
		enStr = new String(result, StringUnit.CharSet);
		return enStr;
	}

	public void FileEncrypt(String sourcefile, String destfile) {
		FileEncrypt(sourcefile, destfile, serial);
	}

	public void FileDecrypt(String sourcefile, String destfile) {
		FileEncrypt(sourcefile, destfile, serial);
	}

	public void FileEncrypt(String sourcefile, String destfile, String cryptstr) {
		FileEncrypt(sourcefile, destfile, cryptstr, 0);
	}

	public void FileDecrypt(String sourcefile, String destfile, String cryptstr) {
		FileEncrypt(sourcefile, destfile, cryptstr, 1);
	}

	private void FileEncrypt(String sourcefile, String destfile,
			String cryptstr, int mode) {
		try {
			DataInputStream dataIS = new DataInputStream(new FileInputStream(
					sourcefile));
			DataOutputStream dataOS = new DataOutputStream(
					new FileOutputStream(destfile));
			int data = dataIS.read();
			int serialInt = cryptstr.hashCode();
			for (; data != -1; data = dataIS.read()) {
				if (mode == 0)
					dataOS.write(data + serialInt);
				else
					dataOS.write(data - serialInt);
			}
			dataIS.close();
			dataOS.close();
		} catch (Exception e) {

		}
	}

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	public static byte[] getKey() throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
		SecretKey deskey = keygen.generateKey();
		//logger.debug("Genrate Key:" + StringUnit.Byte2Hex(deskey.getEncoded()));
		return deskey.getEncoded();
	}

	private static byte[] Secret_Encode(byte[] input, byte[] curkey,
			String crypt_Type) throws Exception {
		try {
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(curkey,
					crypt_Type);
			Cipher c1 = Cipher.getInstance(crypt_Type);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] DestByte = c1.doFinal(input);
			return DestByte;
		} catch (Exception E) {
			//logger.warn("Secret_Encode() Error:" + E.getMessage());
			return null;
		}
	}

	private static byte[] Secret_Decode(byte[] input, byte[] curkey,
			String crypt_Type) throws Exception {
		try {
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(curkey,
					crypt_Type);
			Cipher c1 = Cipher.getInstance(crypt_Type);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			byte[] SourceByte = c1.doFinal(input);
			return SourceByte;
		} catch (Exception E) {
			//logger.warn("Secret_Encode() Error:" + E.getMessage());
			return null;
		}

	}

	public static String MD5(String para_Source) throws Exception {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		byte result[] = MD5A(para_Source.getBytes(StringUnit.CharSet));
		enStr = new String(result, StringUnit.CharSet);
		enStr = StringUnit.Byte2Hex(enStr);
		return enStr;
	}

	private static byte[] MD5A(byte[] input) throws Exception {
		java.security.MessageDigest alg = java.security.MessageDigest
				.getInstance("MD5"); // or "SHA-1"
		alg.update(input);
		byte[] digest = alg.digest();
		return digest;
	}

	public static String getConSerial() throws Exception {
		if (conserial == null)
			setConSerial();
		return conserial;
	}

	public static void setConSerial() throws Exception {
		conserial = StrDeCrypt("23F27BA8780069AB5EC02761D3390786");
	}

	public static void main(String[] args) throws Exception {
		String str = "admin";
		str = Crypt.StrEnCrypt(str);
		System.out.println(str);
		str = Crypt.StrDeCrypt("FC0DF2100B37634B");
		System.out.println(str);
	}
}
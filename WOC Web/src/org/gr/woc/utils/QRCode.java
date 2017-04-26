package org.gr.woc.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QRCode {
	public void QREncode(String path, String content, String filename, int width, int height){
		MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
		Map<EncodeHintType, String> hints=new HashMap<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			BitMatrix bitMatrix=multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			File file=new File(path, filename);
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public String QRDecode(String path){
		Result result=null;
		try {
			MultiFormatReader multiFormatReader=new MultiFormatReader();
			File file=new File(path);
			BufferedImage img=ImageIO.read(file);
			LuminanceSource source=new BufferedImageLuminanceSource(img);
			Binarizer binarizer=new HybridBinarizer(source);
			BinaryBitmap binaryBitmap=new BinaryBitmap(binarizer);
			@SuppressWarnings("rawtypes")
			Map hints=new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			result=multiFormatReader.decode(binaryBitmap, hints);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result.toString();
		
	}
}

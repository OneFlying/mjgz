package com.yf.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarCodeUtil {

	public static void creatBarCode(String content){
		
		try {
            //Create the barcode bean
            //Code39Bean bean = new Code39Bean();
            Code128Bean bean = new Code128Bean();
            final int dpi = 150;
             
            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(2.0f / dpi)); //makes the narrow bar 
                                                             //width exactly one pixel
            //bean.setWideFactor(3);
            bean.doQuietZone(false);
             
            //Open output file
            File outputFile = new File("e:\\out.png");
            OutputStream out = new FileOutputStream(outputFile);
            try {
                //Set up the canvas provider for monochrome JPEG output 
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
             
                //Generate the barcode
                bean.generateBarcode(canvas, content);
                        
                //Signal end of generation
                canvas.finish();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }	
	}
	
	public static String creatTestBarCode(HttpServletRequest request,String content){
		
		String respath="";
		
		try {
            //Create the barcode bean
            //Code39Bean bean = new Code39Bean();
            Code128Bean bean = new Code128Bean();
            final int dpi = 150;
             
            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(2.0f / dpi)); //makes the narrow bar 
                                                             //width exactly one pixel
            //bean.setWideFactor(3);
            bean.doQuietZone(false);
             
            String path = request.getSession().getServletContext().getRealPath("")+"/barcodeimg";
            respath = path+"\\"+content+".png";
            //Open output file
            File outputFile = new File(path,content+".png");
            OutputStream out = new FileOutputStream(outputFile);
            try {
                //Set up the canvas provider for monochrome JPEG output 
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
             
                //Generate the barcode
                bean.generateBarcode(canvas, content);
                        
                //Signal end of generation
                canvas.finish();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }	
		
		return respath;
	}
}

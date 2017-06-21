package com.picc.tm.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 工具类，生成验证码图片
 *
 */
public class SecurityImage{
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityImage.class);
    
    /**
     * 生成验证码图片
     * @param securityCode   验证码字符
     * @return  BufferedImage  图片
     */
    public static BufferedImage createImage(String securityCode){
        
        //验证码长度
        int codeLength=securityCode.length();
        //字体大小
        int fSize = 15;
        int fWidth = fSize + 1;
        //图片宽度
        int width = codeLength * fWidth + 6 ;
        //图片高度
        int height = fSize * 2 + 1;
        
        //图片
        BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g=image.createGraphics();
        
        //设置背景色
        g.setColor(Color.WHITE);
        //填充背景
        g.fillRect(0, 0, width, height);
        
        //设置边框颜色
        g.setColor(Color.LIGHT_GRAY);
        //边框字体样式
        g.setFont(new Font("Arial", Font.BOLD, height - 2));
        //绘制边框
        g.drawRect(0, 0, width - 1, height -1);
        
        
//        //绘制噪点
        Random rand = new Random();
//        //设置噪点颜色
//        g.setColor(Color.LIGHT_GRAY);
//        for(int i = 0;i < codeLength * 6;i++){
//            int x = rand.nextInt(width);
//            int y = rand.nextInt(height);
//            //绘制1*1大小的矩形
//            g.drawRect(x, y, 2, 2);
//        }
        
        //绘制验证码
//        int codeY = height - 10;  
//        //设置字体颜色和样式
//        g.setColor(new Color(19,148,246));
//        g.setFont(new Font("Georgia", Font.BOLD, fSize));
//        for(int i = 0; i < codeLength;i++){
//            g.drawString(String.valueOf(securityCode.charAt(i)), i * 16 + 5, codeY);
//        }
        int w = (g.getFontMetrics()).stringWidth(securityCode);
        int a = (g.getFontMetrics()).getMaxAscent();
        int x = 0, y = 0;
     // 设置文字出现位置为中央 
        x = width / 2 - w / 2;
        y = height / 2 + a / 2 - 6;
		// 文字变形设置
		AffineTransform fontAT = new AffineTransform();
		int xp = x - 2;
		// 每个文字都变形
		for (int c = 0; c < securityCode.length(); c++) {
			// 产生弧度
			int rotate = rand.nextInt(25);
			fontAT.rotate(rand.nextBoolean() ? Math.toRadians(rotate) : -Math
					.toRadians(rotate / 2));
			Font fx = new Font(new String[] { "Times New Roman", "Verdana",
					"arial" }[rand.nextInt(2)], rand.nextInt(5),
					15 + rand.nextInt(16)).deriveFont(fontAT);
			g.setFont(fx);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			Random random = new Random();
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			// 用随机产生的颜色将验证码绘制到图像中。
			g.setColor(new Color(red, green, blue));
			String ch = String.valueOf(securityCode.charAt(c));
			int ht = rand.nextInt(3);
			// 打印字并移动位置
			g.drawString(ch, xp, y + (rand.nextBoolean() ? -ht : ht));
			// 移动指针.
			xp += g.getFontMetrics().stringWidth(ch) + 2;
		}
        //关闭资源
        g.dispose();
        
        return image;
    }
    
    /**
     * 返回验证码图片的流格式
     * @param securityCode  验证码
     * @return ByteArrayInputStream 图片流
     */
    public static ByteArrayInputStream getImageAsInputStream(String securityCode){
        
        BufferedImage image = createImage(securityCode);
        return convertImageToStream(image);
    }
    
    /**
     * 将BufferedImage转换成ByteArrayInputStream
     * @param image  图片
     * @return ByteArrayInputStream 流
     */
    private static ByteArrayInputStream convertImageToStream(BufferedImage image){
        
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
        try {
            jpeg.encode(image);
            byte[] bts = bos.toByteArray();
            inputStream = new ByteArrayInputStream(bts);
        } catch (ImageFormatException e) {
            e.printStackTrace();
            logger.error("", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
        return inputStream;
    }
}
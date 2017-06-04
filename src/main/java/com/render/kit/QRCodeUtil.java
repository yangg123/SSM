package com.render.kit;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class QRCodeUtil {// px必须是45的倍数
        public static boolean getQrCodeImgByContents(String contents, int px, int avatarPx, String format, File logoPath, File qrPath) {
            int width = px;
            int height = px;
            try {
                Qrcode qrcode = new Qrcode();
                // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
                qrcode.setQrcodeErrorCorrect('Q');
                qrcode.setQrcodeEncodeMode('B');
                // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
                qrcode.setQrcodeVersion(15);

                BufferedImage bufImg = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_BGR);

                Graphics2D gs = bufImg.createGraphics();
                gs.setBackground(Color.WHITE);
                gs.clearRect(0, 0, width, height);
                gs.setColor(Color.BLACK);
                //偏移量
                int pixoff = 25;

                byte[] contentBytes = contents.getBytes("UTF-8");
                //原先是120，我改成140试试看
                if (contentBytes.length != 0 && contentBytes.length < 160) {
                    boolean[][] codeOut = qrcode.calQrcode(contentBytes);
                    System.out.println("codeOut.length=" + codeOut.length);
                    int howBig = width / codeOut.length;
                    for (int i = 0; i < codeOut.length; i++) {
                        for (int j = 0; j < codeOut.length; j++) {
                            if (codeOut[j][i]) {
                                gs.fillRect(j * howBig + pixoff, i * howBig + pixoff, howBig, howBig);
                            }
                        }
                    }
                } else {
                    System.out.println("大小超出限制");
                }
                if (logoPath != null && logoPath.exists()) {
                    Image img = ImageIO.read(logoPath);//实例化一个Image对象。
                    int offset = (width - avatarPx) / 2;
                    gs.drawImage(img, offset, offset, avatarPx, avatarPx, null);
                }

                gs.dispose();
                bufImg.flush();
                if (qrPath.exists()) qrPath.delete();
                ImageIO.write(bufImg, format, qrPath);
                System.out.println("二维码生成成功！");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}

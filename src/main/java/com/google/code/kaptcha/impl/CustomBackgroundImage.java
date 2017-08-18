package com.google.code.kaptcha.impl;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.util.Configurable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static com.google.code.kaptcha.Constants.CUSTOM_BACKGROUND_IMAGE_PATH;

/**
 * 自定义验证码背景图片
 *
 * 优先检查配置中kaptcha.background.image配置的图片路径, 如未配置, 则检查系统环境变量 `kaptchaBG` 的值
 *
 * 例如:如图片在 `resource/imge/kaptchaBG.jpg` ,则配置为:
 *
 * <--自定义背景处理类-->
 * <prop key="kaptcha.background.impl">com.google.code.kaptcha.impl.CustomBackgroundImage</prop>
 * <--自定义背景路径-->
 * <prop key="kaptcha.background.image">image/kaptchaBG.jpg</prop>
 *
 */
public class CustomBackgroundImage extends Configurable implements BackgroundProducer {

    private static final String DEFAULT_CUSTOM_IMAGE_PATH = "kaptchaBG.jpg";

    private static final String PATH_SYSTEM_ENV_KEY = "kaptchaBG";

    @Override
    public BufferedImage addBackground(BufferedImage textImage) {
        BufferedImage backgroundImage;
        try {
            backgroundImage = getBackgroundImage(getCustomBackgroundImagePath(DEFAULT_CUSTOM_IMAGE_PATH));
        } catch (Exception e) {
            return textImage;
        }

        drawTextImageOnBackgroundImage(textImage, backgroundImage);

        return backgroundImage;
    }

    private void drawTextImageOnBackgroundImage(BufferedImage textImage, BufferedImage backgroundImage) {
        Graphics2D bgi_g2d = (Graphics2D) backgroundImage.getGraphics();
        bgi_g2d.drawImage(textImage, 0, 0, null, null);
        bgi_g2d.dispose();
    }

    private BufferedImage getBackgroundImage(String _imagePath) throws IOException {
        InputStream _image = getClass().getResourceAsStream("/" + _imagePath);
        return ImageIO.read(_image);
    }

    private String getCustomBackgroundImagePath(String defaultVal){
        String _imagePath = getConfig().getProperties().getProperty(CUSTOM_BACKGROUND_IMAGE_PATH);
        if ("".equals(_imagePath) || null == _imagePath) {
            _imagePath = System.getProperty(PATH_SYSTEM_ENV_KEY, defaultVal);
        }
        return _imagePath;
    }
}

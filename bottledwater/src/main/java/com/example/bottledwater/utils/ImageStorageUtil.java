package com.example.bottledwater.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageStorageUtil {
    public static final String IMAGE_DIRECTORY = "src/main/resources/images"; // 修改文件夹路径

    static {
        File directory = new File(IMAGE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static void storeAndResizeImage(byte[] imageData, String filename, int width, int height) throws IOException {
        File file = new File(IMAGE_DIRECTORY + File.separator + filename);
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));

        // Resize the image to the specified dimensions
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(resizedImage, 0, 0, null);

        // Save the resized image to the file
        ImageIO.write(bufferedImage, "jpg", file);
    }

    public static byte[] loadImage(String filename) throws IOException {
        File file = new File(IMAGE_DIRECTORY + File.separator + filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }

        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }



}

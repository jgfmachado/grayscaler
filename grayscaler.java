import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Scratch {

    private static String filePath = null;
    private static String fileName = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Paste the path or drag the image here, and press ENTER.");
        filePath = scanner.nextLine();

        File fileImage = new File(filePath);
        fileName = fileImage.getName();

        BufferedImage image = readImage(fileImage);

        convertImageToGrayscale(image);

        writeImage(image, filePath
                .replace(String.format(fileName), String.format("grayscale_%s", fileName)));
    }

    private static BufferedImage readImage(File imageFile) {
        try {
            if (filePath == null) {
                throw new RuntimeException("Image path is not set");
            }
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeImage(BufferedImage image, String filePath) {
        try {
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void convertImageToGrayscale(BufferedImage image) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color coloredPixel = new Color(image.getRGB(j, i));
                int grayscaleValue = (int) Math.round(
                        coloredPixel.getRed()*0.3 + coloredPixel.getGreen()*0.59 + coloredPixel.getBlue()*0.11);
                Color grayscalePixel =  new Color(grayscaleValue, grayscaleValue, grayscaleValue);
                image.setRGB(j, i, grayscalePixel.getRGB());
            }
        }
    }

}
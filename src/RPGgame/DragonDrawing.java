package RPGgame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//этот код скопипастил с инета.
public class DragonDrawing {
	public static void main(String[] args) throws IOException {
		// допустим, что один символ занимает пространство 21×8 пикселей

	}

	public static char[][] readImage(String path, int scH, int scW) throws IOException {
		BufferedImage image = ImageIO.read(new File(path));
		int height = image.getHeight() / scH;
		int width = image.getWidth() / scW;
		char[][] chars = new char[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// масштабируем изображение и складываем цвета
				int colorRGB = 0;
				for (int k = 0; k < scH; k++)
					for (int p = 0; p < scW; p++)
						colorRGB += image.getRGB(j * scW + p, i * scH + k);
				// получаем среднее значение цвета
				Color color = new Color(colorRGB / (scH * scW));
				// складываем R+G+B значения цвета и считаем среднюю яркость
				int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
				// получаем символ по значению яркости
				chars[i][j] = getDensity(brightness);
			}
		}
		return chars;
	}

	static final String DENSITY =
			"@QB#NgWM8RDHdOKq9$6khEPXwmeZaoS2yjufF]}{tx1zv7lciL/\\|?*>r^;:_\"~,'.-`";

	static char getDensity(int value) {
		// поскольку количество символов меньше 255, будем использовать проценты
		int charValue = (int) Math.round(DENSITY.length() / 255.0 * value);
		charValue = Math.max(charValue, 0);
		charValue = Math.min(charValue, DENSITY.length() - 1);
		return DENSITY.charAt(charValue);
	}

	static void writeToFile(String path, char[][] chars) throws IOException {
		FileWriter writer = new FileWriter(path);
		for (char[] row : chars) {
			String str = String.valueOf(row);
			writer.append(str).write("\n");
			System.out.println(str); // вывод
		}
		writer.flush();
		writer.close();
	}
}
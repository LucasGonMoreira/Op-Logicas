package ImagePath.par4;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String img1 = "src/ImagePath/par4/imagem6.png";
        String img2 = "src/ImagePath/par4/imagem8.png";
        BufferedImage imagem1 = ImageIO.read(new File(img1));
        BufferedImage imagem2 = ImageIO.read(new File(img2));

        int width = imagem1.getWidth();
        int height = imagem1.getHeight();

        BufferedImage imagemNova1 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagemNova2 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagemNova3 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagemNova4 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);

        int pixelMinImg1 = 255;
        int pixelMaxImg1 = 0;

        int pixelMinImg2 = 255;
        int pixelMaxImg2 = 0;


        for (int linha=0;linha<width;linha++){
            for (int coluna=0; coluna<height; coluna++){

                int rgb1 = imagem1.getRGB(linha,coluna);
                int rgb2 = imagem2.getRGB(linha,coluna);

                Color cor1 = new Color(rgb1);
                Color cor2 = new Color(rgb2);

                if (cor1.getRed()>pixelMaxImg1){
                    pixelMaxImg1 = cor1.getRed();
                } else if (cor1.getRed()<pixelMinImg1) {
                    pixelMinImg1 = cor1.getRed();
                }

                if (cor2.getRed()>pixelMaxImg2){
                    pixelMaxImg2=cor2.getRed();
                }else if (cor2.getRed()<pixelMinImg2){
                    pixelMinImg2 = cor2.getRed();
                }
            }
        }

        for (int linha=0;linha<width;linha++){
            for (int coluna=0;coluna<height;coluna++){

                int rgb1 = imagem1.getRGB(linha,coluna);
                int rgb2 = imagem2.getRGB(linha,coluna);

                Color cor1 = new Color(rgb1);
                Color cor2 = new Color(rgb2);

                int red1 = cor1.getRed();
                int red2 = cor2.getRed();

                int r1 = 255/ (pixelMaxImg1 - pixelMinImg1) * (red1 - pixelMinImg1);
                int r2 = 255/ (pixelMaxImg2 - pixelMinImg2) * (red2 - pixelMinImg2);

                imagem1.setRGB(linha,coluna,new Color(r1,r1,r1).getRGB());
                imagem2.setRGB(linha,coluna,new Color(r2,r2,r2).getRGB());

                imagemNova1.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)+imagem2.getRGB(linha,coluna));
                imagemNova2.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)|imagem2.getRGB(linha,coluna));
                imagemNova3.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)&imagem2.getRGB(linha,coluna));
                imagemNova4.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)^imagem2.getRGB(linha,coluna));

            }
        }

        ImageIO.write(imagemNova1,"jpg", new File("src/ImagePath/par4/ImagemNovaSoma.jpg"));
        ImageIO.write(imagemNova2,"jpg", new File("src/ImagePath/par4/ImagemNovaOR.jpg"));
        ImageIO.write(imagemNova3,"jpg", new File("src/ImagePath/par4/ImagemNovaAND.jpg"));
        ImageIO.write(imagemNova4,"jpg", new File("src/ImagePath/par4/ImagemNovaXOR.jpg"));
    }
}

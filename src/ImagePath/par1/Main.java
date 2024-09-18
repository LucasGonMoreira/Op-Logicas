package ImagePath.par1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String img = "src/ImagePath/par1/imagem1.jpg";
        BufferedImage imagem1 = ImageIO.read(new File(img));

        String img2 = "src/ImagePath/par1/imagem8.png";
        BufferedImage imagem2 = ImageIO.read(new File(img2));

        int width = imagem1.getWidth();
        int height = imagem1.getHeight();

        BufferedImage imagemNova1 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagemNova2 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagemNova3 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagemNova4 = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);

        int pixelMin = 255;
        int pixelMax = 0;

        int pixelMin2 = 255;
        int pixelMax2 = 0;
        for (int linha=0;linha<width;linha++){
            for (int coluna=0; coluna<height; coluna++) {

                int rgb1 = imagem1.getRGB(linha, coluna);
                Color cor1 = new Color(rgb1);

                if (cor1.getRed()>pixelMax){
                    pixelMax= cor1.getRed();
                } else if (cor1.getRed()<pixelMin) {
                    pixelMin=cor1.getRed();
                }

                int rgb2 = imagem2.getRGB(linha,coluna);
                Color cor2 = new Color(rgb2);

                if (cor2.getRed()>pixelMax2){
                    pixelMax2 = cor2.getRed();
                } else if (cor2.getRed()<pixelMin) {
                    pixelMin2=cor2.getRed();
                }
            }
        }



        for (int linha=0; linha<width; linha++) {
            for (int coluna = 0; coluna < height; coluna++) {

                int rgb = imagem1.getRGB(linha,coluna);
                Color cor = new Color(rgb);

                int red = cor.getRed();

                int r = (255 / (pixelMax - pixelMin)) * (red - pixelMin);

                Color newCor = new Color(r,r,r);
                imagem1.setRGB(linha,coluna,newCor.getRGB());


                int rgb2 = imagem2.getRGB(linha,coluna);
                Color cor2 = new Color(rgb2);

                int red2 = cor2.getRed();
                int r2 = (255 / (pixelMax2 - pixelMin2)) * (red2 - pixelMin2);

                Color novaCor = new Color(r2,r2,r2);
                imagem2.setRGB(linha,coluna,novaCor.getRGB());


                imagemNova1.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)+imagem2.getRGB(linha,coluna));
                imagemNova2.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)|imagem2.getRGB(linha,coluna));
                imagemNova3.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)&imagem2.getRGB(linha,coluna));
                imagemNova4.setRGB(linha,coluna, imagem1.getRGB(linha,coluna)^imagem2.getRGB(linha,coluna));
            }
        }


        ImageIO.write(imagemNova1,"jpg", new File("src/ImagePath/par1/ImagemNovaSoma.jpg"));
        ImageIO.write(imagemNova2,"jpg", new File("src/ImagePath/par1/ImagemNovaOR.jpg"));
        ImageIO.write(imagemNova3,"jpg", new File("src/ImagePath/par1/ImagemNovaAND.jpg"));
        ImageIO.write(imagemNova4,"jpg", new File("src/ImagePath/par1/ImagemNovaXOR.jpg"));
    }
}
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDF2Images
{
    public static void main(String[] args) throws IOException
    {
        // 设置 pdf 文件路径
        String path = null;
        // 设置 pdf 文件名，不文件需要后缀
        String pdf = null;

        String root = path + "/" + pdf;

        try (PDDocument pdDocument = PDDocument.load(new File(root + ".pdf")))
        {
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
            int pageCount = pdDocument.getPages().getCount();
            // 循环导出 pdf 所有页面
            for (int i = 0; i < pageCount; i++)
            {
                // 设置还原比例为 3.0
                BufferedImage bufferedImage = pdfRenderer.renderImage(i, 3.0f);
                ImageIO.write(bufferedImage, "png", new File(root + i + ".png"));
            }
        }
    }
}
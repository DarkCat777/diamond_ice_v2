package controller.qr;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.*;

public class QRGenerator {
    public static final String NOMBRE_IMG = "temporal_QR.jpg";

    private static QRGenerator instancia_qr_generator;

    private QRGenerator() {

    }

    public static QRGenerator getQRGenerator() {
        if (instancia_qr_generator == null) {
            instancia_qr_generator = new QRGenerator();
        }
        return instancia_qr_generator;
    }


    public void GenerateCodeQR(String text) throws QRGeneratorException {
        ByteArrayOutputStream out = QRCode.from(text).withSize(1024, 1024).to(ImageType.JPG).stream();
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(QRGenerator.NOMBRE_IMG);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(out.toByteArray());
        } catch (FileNotFoundException e) {
            throw new QRGeneratorException("No se ha podido crear o encontrar el archivo.");
        } catch (IOException e) {
            throw new QRGeneratorException("No se ha escribir el archivo.");
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new QRGeneratorException("No se puede cerrar la escritura el archivo de la Imagen generada.");
                }
            }
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new QRGeneratorException("No se puede cerrar el archivo de la Imagen generada.");
        }
    }
}

package hello.ftp.ftpclient;

import hello.ftp.exceptions.ErrorMessage;
import hello.ftp.exceptions.FTPErrors;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;



@Service
public class FTPServiceImpl implements FTPService {


    @Value("${ftp1.username}") String user;
    @Value("${ftp1.password}") String pw;
    @Value("${ftp1.host}") String host;
    @Value("${ftp1.port}") int port;
    FTPClient ftpconnection;

    private Logger logger = LoggerFactory.getLogger(FTPServiceImpl.class);


    @Override
    public void connectToFTP() throws FTPErrors {

        ftpconnection = new FTPClient();
        ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;

        try {
            ftpconnection.connect(this.host);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-1, "No fue posible conectarse al FTP a través del host=" + host);
            throw new FTPErrors(errorMessage);
        }

        reply = ftpconnection.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {

            try {
                ftpconnection.disconnect();
            } catch (IOException e) {
                ErrorMessage errorMessage = new ErrorMessage(-2, "No fue posible conectarse al FTP, el host=" + host + " entregó la respuesta=" + reply);
                logger.error(errorMessage.toString());
                throw new FTPErrors(errorMessage);
            }
        }

        try {
            ftpconnection.login(this.user, this.pw);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-3, "El usuario=" + this.user + ", y el pass=**** no fueron válidos para la autenticación.");
            logger.error(errorMessage.toString());
            throw new FTPErrors(errorMessage);
        }

        try {
            ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-4, "El tipo de dato para la transferencia no es válido.");
            logger.error(errorMessage.toString());
            throw new FTPErrors(errorMessage);
        }

        ftpconnection.enterLocalPassiveMode();
    }

    @Override
    public void uploadFileToFTP(byte[] entrada, String ftpHostDir , String serverFilename) throws FTPErrors {
        connectToFTP();
        try {
            InputStream input = new ByteArrayInputStream(entrada);
            this.ftpconnection.storeFile(ftpHostDir + serverFilename, input);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
            logger.error(errorMessage.toString());
            throw new FTPErrors(errorMessage);
        }

    }


    @Override
    public byte[] downloadFileFromFTP(String rutaPath) throws FTPErrors {
        connectToFTP();
        InputStream inputStream =null;
        byte[] archivo=null;
        try {

            inputStream = ftpconnection.retrieveFileStream(rutaPath);
            archivo = readAllBytes(inputStream);
        } catch (FileNotFoundException e) {
            ErrorMessage errorMessage = new ErrorMessage(-6, "No se pudo obtener la referencia a la carpeta relativa donde guardar, verifique la ruta y los permisos.");
            logger.error(errorMessage.toString());
            throw new FTPErrors(errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return archivo;
    }

    @Override
    public void disconnectFTP() throws FTPErrors {
        if (this.ftpconnection.isConnected()) {
            try {
                this.ftpconnection.logout();
                this.ftpconnection.disconnect();
            } catch (IOException f) {
                throw new FTPErrors( new ErrorMessage(-8, "Ha ocurrido un error al realizar la desconexión del servidor FTP"));
            }
        }
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int bufLen = 4 * 0x400; // 4KB
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;

        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                    outputStream.write(buf, 0, readLen);

                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null) inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
    }
}

package hello.ftp.ftpclient;

import hello.ftp.exceptions.FTPErrors;


public interface FTPService {
     void connectToFTP() throws FTPErrors;
     void uploadFileToFTP(byte[] imput , String ftpHostDir , String serverFilename) throws FTPErrors;
     public byte[] downloadFileFromFTP(String rutaPath) throws FTPErrors;
     void disconnectFTP() throws FTPErrors;

}

package hello.services.Impl;
import hello.commons.util.Metodo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Map;

@Service
public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private Metodo metodoUtil;
    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseEntity<?> sendMailHTML(
            String email,
            String subject,
            String template,
            @Nullable Map<String, Object> templateAttributes,
            @Nullable Map<String, byte []> imagesBase64){
        sendHTML(email,subject,template,templateAttributes,imagesBase64);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public void sendHTML(
            String email,
            String subject,
            String template ,
            Map<String, Object> templateAttributes,
            Map<String, byte []> imagesBase64) {

        final Context context = new Context();
        if(!metodoUtil.isEmpty(templateAttributes))
            context.setVariables(templateAttributes);
        String html = templateEngine.process(template, context);
        //save(html);
        sendPreparedMail(email, subject, html, true,imagesBase64);
    }

    private synchronized void sendPreparedMail(
            String to,
            String subject,
            String text,
            Boolean isHtml,
            @Nullable Map<String, byte []> imagesBase64
    ) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            if(!metodoUtil.isEmpty(imagesBase64))
                setImages(helper,imagesBase64);
            javaMailSender.send(mail);
        } catch (Exception e) {
            LOGGER.error("Problem with sending email to: {}, error message: {}", to, e.getMessage());
        }
    }

    private void setImages(
            MimeMessageHelper helper,
//    		Map<String, String> imagesBase64
            Map<String, byte []> imagesBase64
    ) {
        for(String key: imagesBase64.keySet()) {
            File image=new File(key+".png");
//			imagen(image,Base64.decodeBase64(imagesBase64.get(key)));
            imagen(image,imagesBase64.get(key));
            try {
                helper.addInline(key,image);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void save(String html) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("mail.html")));
            writer.write(html);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void imagen(File temp, byte[] logo) {
        try {
            OutputStream os = new FileOutputStream(temp);
            os.write(logo);
            os.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

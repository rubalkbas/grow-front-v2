package com.nezztech.generic.email.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

//import javax.activation.DataSource;
import javax.imageio.ImageIO;
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ITTIVA
 * @version 1.0
 * @since 2025
 *
 */
@Slf4j
@Service
public class NotificacionCorreoService {

	@Autowired
	private JavaMailSender correoJ;

	public void envioCorreo(Date fechaP, String uuid, Long idRequisicion, String correoEmisor, String nombreEmpresaReceptoria, String logoEmpresaReceptoria, List<String> correosDestinatarios) throws MessagingException, IOException, URISyntaxException {

		log.info("-");
		log.info("INICIO DE ENVIO DE CORREO !!!");

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String fecha = formatter.format(fechaP);
        
        log.info("fecha : "+fecha);
        log.info("uuid : "+uuid);
        log.info("idRequisicion : "+idRequisicion);
        log.info("correoEmisor : "+correoEmisor);
        log.info("nombreEmpresaReceptoria : "+nombreEmpresaReceptoria);
        log.info("logoEmpresaReceptoria base 64 : "+logoEmpresaReceptoria);
        
        
        saveImageFromBase64(logoEmpresaReceptoria, "logo.jpg");

//		String correoEnvio = correoEmisor;
//        String correoEnvio = "teacndroid@gmail.com";
		String[] destinatariosArray = correosDestinatarios.toArray(new String[0]);

		String asunto = "Envío de la factura #SAR-"+ idRequisicion +" con colio fiscal" + uuid;

		MimeMessage message = correoJ.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		File imgFileL = new File(getClass().getClassLoader().getResource("img/fondoIttiva.jpg").toURI());

	    // Leer la imagen desde el archivo
	    BufferedImage imageL = ImageIO.read(imgFileL);

	    // Convertir la imagen a un array de bytes
	    ByteArrayOutputStream byteArrayOutputStreamL = new ByteArrayOutputStream();
	    ImageIO.write(imageL, "jpg", byteArrayOutputStreamL); 


		// Ruta de la imagen guardada
	    File imgFile = new File(getClass().getClassLoader().getResource("img/logo.jpg").toURI());

	    // Leer la imagen desde el archivo
	    BufferedImage image = ImageIO.read(imgFile);

	    // Convertir la imagen a un array de bytes
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    ImageIO.write(image, "jpg", byteArrayOutputStream);    
		
		String html2 = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Bloque con Imagen, Nombre y Párrafos</title>\r\n"
				+ "    <link href=\"https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap\" rel=\"stylesheet\">\r\n"
				+ "\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            display: flex;\r\n"
				+ "            justify-content: center;\r\n"
				+ "            align-items: center;\r\n"
				+ "            min-height: 100vh;\r\n"
				+ "            margin: 0;\r\n"
				+ "            background-color: #f9f9f9;\r\n"
				+ "        }\r\n"
				+ "        .main-container {\r\n"
				+ "            width: 600px;\r\n"
				+ "            background-color: #fff;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);\r\n"
				+ "            padding: 20px;\r\n"
				+ "        }\r\n"
				+ "        .block-button {\r\n"
				+ "            display: block;\r\n"
				+ "            width: 100%;\r\n"
				+ "            height: 90px;\r\n"
				+ "            padding: 15px 0;\r\n"
				+ "            font-size: 18px;\r\n"
				+ "            color: #fff;\r\n"
				+ "            background-color: #007bff;\r\n"
				+ "            border: none;\r\n"
				+ "            cursor: pointer;\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            text-transform: uppercase;\r\n"
				+ "        }\r\n"
				+ "        .block-button:hover {\r\n"
				+ "            background-color: #0056b3;\r\n"
				+ "        }\r\n"
				+ "        .image-container {\r\n"
				+ "            display: flex;\r\n"
				+ "            align-items: center;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            text-align: left;\r\n"
				+ "        }\r\n"
				+ "        .image-container img {\r\n"
				+ "            width: 120px; /* Aumentamos ligeramente el tamaño de la imagen */\r\n"
				+ "            height: 120px; /* Ajustamos la altura proporcionalmente */\r\n"
				+ "            object-fit: cover;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            margin-right: 20px;\r\n"
				+ "        }\r\n"
				+ "        .image-container span {\r\n"
				+ "            flex-grow: 1;\r\n"
				+ "            margin-top: 30px;\r\n"
				+ "            margin-left: 40px;\r\n"
				+ "            font-size: 20px; /* Aumentamos el tamaño de la fuente sutilmente */\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            color: #007bff;\r\n"
				+ "        }\r\n"
				+ "        .message {\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            color: #555;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            background-color: #f5f5f5;\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        .additional-paragraphs {\r\n"
				+ "            margin-top: 20px;\r\n"
				+ "        }\r\n"
				+ "        .additional-paragraphs p {\r\n"
				+ "            font-size: 14px;\r\n"
				+ "            color:#020258;\r\n"
				+ "            line-height: 1.6;\r\n"
				+ "            margin: 10px 0;\r\n"
				+ "            text-align: justify;\r\n"
				+ "        }\r\n"
				+ "        .text-image-container {\r\n"
				+ "            display: flex;\r\n"
				+ "            align-items: center;\r\n"
				+ "            justify-content: space-between;\r\n"
				+ "            background-color: #fff;\r\n"
				+ "            border: 1px solid #ddd;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);\r\n"
				+ "            padding: 20px;\r\n"
				+ "            margin-top: 20px;\r\n"
				+ "        }\r\n"
				+ "        .text-content {\r\n"
				+ "            flex: 2;\r\n"
				+ "            font-size: 14px;\r\n"
				+ "            color: #007bff;\r\n"
				+ "            line-height: 1.6;\r\n"
				+ "            margin-right: 20px;\r\n"
				+ "            text-align: justify;\r\n"
				+ "        }\r\n"
				+ "        .image-content {\r\n"
				+ "            flex: 1;\r\n"
				+ "        }\r\n"
				+ "        .image-content img {\r\n"
				+ "            width: 150px;\r\n"
				+ "            height: 100px;\r\n"
				+ "            margin-left: 200px;\r\n"
				+ "            object-fit: cover;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .logoFooter {\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            color: #007bff;\r\n"
				+ "            text-align: left;\r\n"
				+ "            font-weight: 600; /* Texto más grueso, pero sutil */\r\n"
				+ "            letter-spacing: 4px; /* Espaciado sutil entre letras */\r\n"
				+ "            font-stretch: 85%; /* Reduce el ancho de las letras */\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"main-container\">\r\n"
				+ "        <div class=\"block-container\">\r\n"
				+ "            <a href=\"http://localhost:8095/api/pdf-requision/download/pdfCorreo/"+ idRequisicion +"\" style=\"display: block; width: 100%; padding: 15px 0; font-size: 18px; color: #fff; background-color: #007bff; text-align: center; text-decoration: none; font-weight: bold; text-transform: uppercase; border-radius: 5px;\">\r\n"
				//+ "            <a href=\"https://toliman.gob.mx:8095/api/pdf-requision/download/pdfCorreo/"+ idRequisicion +"\" style=\"display: block; width: 100%; padding: 15px 0; font-size: 18px; color: #fff; background-color: #007bff; text-align: center; text-decoration: none; font-weight: bold; text-transform: uppercase; border-radius: 5px;\">\r\n"
				+ "                DESCARGA TU FACTURA\r\n"
				+ "            </a>            \r\n"
				+ "            \r\n"
				+ "            <div class=\"image-container\">\r\n"
				+ "                <img src='cid:signature' alt=\"Imagen de ejemplo\">\r\n"
				+ "                <span>"+ nombreEmpresaReceptoria +"</span>\r\n"
				+ "            </div>\r\n"
				+ "\r\n"
				+ "            <div class=\"message\">\r\n"
				+ "                ¡HOLA MUNICIPIO DE TOLIMAN QUERETARO!\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "\r\n"
				+ "        <div class=\"additional-paragraphs\">\r\n"
				+ "            <p>Adjunto al correo encontrarás la factura #SAR-"+ idRequisicion +" con fecha "+ fecha +" y folio fiscal "+ uuid +".</p>\r\n"
				+ "            <p>Para cualquier duda puedes ponerte en contacto con nosotros en el teléfono 4271121584 o si lo deseas puedes escribirnos al correo contabiliza.afy@gmail.com.</p>\r\n"
				+ "            <p>Atentamente,</p>\r\n"
				+ "            <p>"+ nombreEmpresaReceptoria + "</p>\r\n"
				+ "        </div>\r\n"
				+ "\r\n"
				+ "        <div class=\"text-image-container\">\r\n"
				+ "            <div class=\"text-content\">\r\n"
				+ "                <p>Tu factura se genero desde</p>\r\n"
				+ "                <p class=\"logoFooter\">CONTPAQ VENDE</p>\r\n"
				+ "            </div>\r\n"
				+ "            <div class=\"image-content\">\r\n"
				+ "        <img src='cid:signatureL'  \r\n"
	  		    + "            alt=\"Logo\" width=\"500\" height=\"120\" style=\"object-fit: contain;\">\r\n"
				+ "            </div>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n"
				+ "";

		try {

			helper.setTo(destinatariosArray);
			helper.setSubject(asunto);
			helper.setText(html2, true);
			helper.setFrom("contabiliza.afy@gmail.com");

			 byte[] signatureBytes = byteArrayOutputStream.toByteArray();
		        
		     DataSource dataSource = new ByteArrayDataSource(signatureBytes, "image/jpeg");  
		        
		     byte[] signatureBytesL = byteArrayOutputStreamL.toByteArray();
		        
		     DataSource dataSourceL = new ByteArrayDataSource(signatureBytesL, "image/jpeg");
		        
		      helper.addInline("signature", dataSource ); 
		      helper.addInline("signatureL", dataSourceL ); 
			  correoJ.send(message);
				
			 if (imgFile.exists()) {
				 imgFile.delete();
			}

			log.info("-");
			log.info("EL CORREO FUE ENVIADO EXITOSAMENTE !!!");
			log.info("-");

		} catch (MessagingException e) {
			e.printStackTrace();
			log.info("ERROR AL ENVIAR EL CORREO : " + e.getMessage());

		}
	}
	
	// Método para guardar la imagen a partir de un string base64
	public void saveImageFromBase64(String base64Image, String fileName) throws IOException {
	    byte[] imageBytes = Base64.getDecoder().decode(base64Image);
	    
	    // Ruta al directorio de recursos
	    String resourceDir = getClass().getClassLoader().getResource("img").getPath();
	    File file = new File(resourceDir + "/" + fileName);

	    // Guardar el archivo
	    try (FileOutputStream fos = new FileOutputStream(file)) {
	        fos.write(imageBytes);
	    }catch (IOException e) {
	        e.printStackTrace();
	    }
	}  
	
	
}

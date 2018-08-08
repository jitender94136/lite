package in.flexsol.service.mail;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class SendGridEmailServiceImpl implements EmailService { 
	
	    private static final Logger logger = LoggerFactory.getLogger(SendGridEmailServiceImpl.class);
		private SendGrid sendGridClient;
	
		@Value("${SENDGRID_API_KEY}")
		String sendGridApiKey;
		
		
		//@Autowired
		public SendGridEmailServiceImpl(/*SendGrid sendGridClient*/) {
			this.sendGridClient =  new SendGrid(sendGridApiKey);
		}
		
		@Override
		public void sendText(String from, String to, String subject, String body) {
			 Response response = sendEmail(from, to, subject, new Content("text/plain", body));
			 if(response != null) {
				 logger.info("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: " + response.getHeaders());
			 }
		}
		
		@Override 
		public void sendHTML(String from, String to, String subject, String body) {
			 Response response = sendEmail(from, to, subject, new Content("text/html", body));
			 if(response != null) {
			 	logger.info("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: " + response.getHeaders());
			 } 	
	    }
		
		private Response sendEmail(String from, String to, String subject, Content content) {
			 Mail mail = new Mail(new Email(from), subject, new Email(to), content);
			 mail.setReplyTo(new Email("jitendersingh@flexsol.in"));
			 Request request = new Request();
			 Response response = null;
			 try {
				  request.setMethod(Method.POST);
				  request.setEndpoint("mail/send");
				  request.setBody(mail.build());
				  response = this.sendGridClient.api(request);
			 } catch (IOException ex) {
				  logger.info("Exception occurred in sendgridemailservice client "+ex.getMessage());
			 }
			 return response;
		}
	
	}
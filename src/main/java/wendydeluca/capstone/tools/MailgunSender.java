package wendydeluca.capstone.tools;




import kong.unirest.Unirest;
import kong.unirest.JsonNode;
import kong.unirest.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wendydeluca.capstone.entities.User;



@Component
public class MailgunSender {
    private String apiKey;
    private String domainName;

    public MailgunSender(@Value("${mailgun.apikey}") String apiKey, @Value("${mailgun.domainname}") String domainName){
        this.apiKey = apiKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail(User recipient){
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/"+ this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "wendydeluca96@hotmail.com")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Successfully registered!")
                .queryString("text", "Congratulations " + recipient.getName() + " for your registration!")
                .asJson();

        System.out.println(response.getBody());
    }

}

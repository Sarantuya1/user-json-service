package com.example.json.soap;

import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.http.*;

@Component
public class SoapClient {

    private static final String SOAP_URL = "http://localhost:8081/ws";

    public boolean validateToken(String token) {
        String soap = """
            <?xml version="1.0"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:auth="http://example.com/soap">
              <soapenv:Body>
                <auth:ValidateTokenRequest>
                  <auth:token>""" + token + """
                </auth:token>
                </auth:ValidateTokenRequest>
              </soapenv:Body>
            </soapenv:Envelope>
            """;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SOAP_URL))
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "ValidateToken")
                .POST(HttpRequest.BodyPublishers.ofString(soap))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().contains("<valid>true</valid>");
        } catch (Exception e) {
            System.out.println("SOAP холбогдсонгүй: " + e.getMessage());
            return false;
        }
    }
}
package mx.axity.com.webapi.rest.client.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import dnd.axity.com.CreatePlayerRequest;
import dnd.axity.com.CreatePlayerResponse;

@Endpoint
public class PlayerEndpoint {
  private static final Logger logger = LoggerFactory.getLogger(PlayerEndpoint.class);
  private static final String NAMESPACE_URI = "com.axity.dnd";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreatePlayerRequest")
  @ResponsePayload
  public CreatePlayerResponse createPlayer(@RequestPayload CreatePlayerRequest request) {
    
    logger.info("Ingresó a createPlayer");
    
    CreatePlayerResponse response = new CreatePlayerResponse();
    response.setPlayerId(1);
    
    return response;
  }

}

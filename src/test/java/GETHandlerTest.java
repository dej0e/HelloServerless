import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import lambda.GETHandler;
import lambda.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(Util.class)
public class GETHandlerTest {

    @Mock
    private APIGatewayProxyRequestEvent  event;
    @Mock
    private Context context;

    @Test
    public void getHandlerTestSuccess(){
        GETHandler getHandler = new GETHandler();
        getHandler.handleRequest(event,context);

    }

}

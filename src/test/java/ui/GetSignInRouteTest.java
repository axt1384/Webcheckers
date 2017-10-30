package ui;

import com.webcheckers.ui.GetSignInRoute;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the GetSignInRoute to make sure the proper html page for signing in is generated.
 * This route is simple and should create no error messages - that is done in post sign in route.
 *
 * @author Anorwen - - - edc8230@rit.edu
 */
public class GetSignInRouteTest {

    private GetSignInRoute CuT;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;

    private static final String TITLE = "Welcome!";
    private static final String NO_ERROR = "";
    private static final String IN_USE = "<p>The username 'ReadyPlayer1' is already in use.";
    private static final String NO_INPUT = "<p>Please enter a username with at least one character.<p>";
    private static final String INVALID_INPUT = "<p>The username \" is not allowed.<p>";


    /**
     * Setup new mock objects before tests.
     */
    @Before
    public void setup() {
        this.request = mock(Request.class);
        this.session = mock(Session.class);
        this.response = mock(Response.class);
        when(request.session()).thenReturn(this.session);
        this.engine = mock(TemplateEngine.class);

        this.CuT = new GetSignInRoute(this.engine);
    }

    /**
     * Test that the Get view will create a sign in page with no errors.
     */
    @Test
    public void new_sign_in() {

        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        this.CuT.handle(this.request, this.response);

        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);

        // Analyze the content passed into the render method
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Welcome!", vm.get("title")); // Should be the Title
        assertEquals("", vm.get("signInMessage")); // Should be no Error
    }

}

package dream.development;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TestInputValues extends FxRobot {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.Locale");

    private TextField txtNumExpressions;
    private TextArea txtAreaResult;

    @Before
    public void setUp() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
        txtNumExpressions = GuiTest.find("#txtNumExpressions");
        txtAreaResult = GuiTest.find("#txtAreaResult");
    }

    @Test
    public void testDefaultNumExpressionsInput() {
        assertThat(txtNumExpressions.getText(), is("1"));
    }

    @Test
    public void testEmptyInput() {
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText(), is(resourceBundle.getString("main.inputError")));
    }

    @Test
    public void testEmptyNumExpressionsInput() {
        clickOn("#txtNumExpressions").eraseText(1);
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText(), is(resourceBundle.getString("main.numExpressionsError")));
    }

    @Test
    public void testInvalidNumExpressionsInput() {
        clickOn("#txtNumExpressions").eraseText(1).write("a");
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText(), is(resourceBundle.getString("main.numExpressionsInvalid")));
    }

    @Test
    public void testLeaveMaxNumExpressionsInput() {
        clickOn("#txtNumExpressions").write("01");
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText(), is(resourceBundle.getString("main.numExpressionsError")));
    }

    @Test
    public void testZeroNumExpressionsInput() {
        clickOn("#txtNumExpressions").eraseText(1).write("0");
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText(), is(resourceBundle.getString("main.numExpressionsError")));
    }

    @Test
    public void testBelowZeroNumExpressionsInput() {
        clickOn("#txtNumExpressions").eraseText(1).write("-1");
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText(), is(resourceBundle.getString("main.numExpressionsInvalid")));
    }

    @Test
    public void testMaxNumExpressionsInput() {
        clickOn("#txtNumExpressions").write("00");
        clickOn("#txtAreaInput").write("(a+(b*c))");
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText().replace("\n",""), is("abc*+"));
    }

    @Test
    public void testSizeExpressionsInput() {
        clickOn("#txtAreaInput").write("(a+(b*c))\n");
        clickOn("#txtAreaInput").write("(a+(b*c))");
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText().replace("\n",""), is("abc*+"));
    }

    @Test
    public void testSizeExpressionsInput2() {
        clickOn("#txtNumExpressions").eraseText(1).write("2");
        clickOn("#txtAreaInput").write("(a+(b*c))");
        clickOn("#btnLaunch");
        assertThat(txtAreaResult.getText().replace("\n",""), is("abc*+"));
    }

    @AfterClass
    public static void afterClass() {
        Platform.exit();
    }

}

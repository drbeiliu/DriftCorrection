
import mmcorej.CMMCore;
import org.micromanager.MMStudioMainFrame;
import org.micromanager.api.ScriptInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dell
 */
public class liveDriftCorrection implements org.micromanager.api.MMPlugin{
    
    public static final String menuName = "Live Drift Correction";
    public static final String tooltipDescription = 
            "Using Beads for Stage Drift Correction";

    private ScriptInterface app_;
    private CMMCore core_;
    private liveDriftCorrectionGUI ldcGui_;
    private MMStudioMainFrame gui_;
    
    
    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setApp(ScriptInterface app) {
        app_ = app;
        if(app_ == null)
            return;
        core_ = app_.getMMCore();
        ldcGui_ = new liveDriftCorrectionGUI(core_, app_);
        ldcGui_.setVisible(true);
         
    }

    @Override
    public void show() {
        //gui_.showMessage("Hello world");
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCopyright() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

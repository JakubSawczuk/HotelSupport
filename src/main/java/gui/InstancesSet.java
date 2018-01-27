package gui;

import gui.addobject.AddClientWindow;
import gui.addobject.AddInvoiceWindow;
import gui.searchobject.EditRoomWindow;
import gui.searchobject.SearchClientWindow;

/**
 * Created by Kuba on 2018-01-17.
 */
public class InstancesSet {

    private static volatile BasicWindow instanceBasicWindow = null;
    private static volatile LogInWindow instanceLogInWindow = null;
    private static volatile SearchClientWindow instanceShowClientWindow = null;
    private static volatile AddClientWindow instanceAddClientWindow = null;
    private static volatile AddInvoiceWindow instanceAddInvoiceWindow = null;
    private static volatile EditRoomWindow instanceEditRoomWindow = null;

    private InstancesSet() {
    }

    public static LogInWindow getInstanceLogInWindow() {
        if (instanceLogInWindow == null) {
            synchronized (LogInWindow.class) {
                if (instanceLogInWindow == null) {
                    instanceLogInWindow = new LogInWindow();
                }
            }
        }
        return instanceLogInWindow;
    }

    public static BasicWindow getInstanceBasicWindow() {
        if (instanceBasicWindow == null) {
            synchronized (BasicWindow.class) {
                if (instanceBasicWindow == null) {
                    instanceBasicWindow = new BasicWindow();
                }
            }
        }
        return instanceBasicWindow;
    }

    public static SearchClientWindow getInstanceSearchClientWindow() {
        if (instanceShowClientWindow == null) {
            synchronized (SearchClientWindow.class) {
                if (instanceShowClientWindow == null) {
                    instanceShowClientWindow = new SearchClientWindow();
                }
            }
        }
        return instanceShowClientWindow;
    }

    public static AddClientWindow getInstanceAddClientWindow() {
        if (instanceAddClientWindow == null) {
            synchronized (AddClientWindow.class) {
                if (instanceAddClientWindow == null) {
                    instanceAddClientWindow = new AddClientWindow();
                }
            }
        }
        return instanceAddClientWindow;
    }

    public static AddInvoiceWindow getInstanceAddInvoiceWindow() {
        if (instanceAddInvoiceWindow == null) {
            synchronized (AddInvoiceWindow.class) {
                if (instanceAddInvoiceWindow == null) {
                    instanceAddInvoiceWindow = new AddInvoiceWindow();
                }
            }
        }
        return instanceAddInvoiceWindow;
    }

    public static EditRoomWindow getInstanceEditRoomWindow() {
        if (instanceEditRoomWindow == null) {
            synchronized (EditRoomWindow.class) {
                if (instanceEditRoomWindow == null) {
                    instanceEditRoomWindow = new EditRoomWindow();
                }
            }
        }
        return instanceEditRoomWindow;
    }



}

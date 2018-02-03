package gui;

import gui.addobject.AddClientWindow;
import gui.addobject.AddInvoiceWindow;
import gui.searchobject.EditRoomWindow;
import gui.searchobject.SearchClientWindow;
import timewithrest.DataByRESTful;

/**
 * Created by Kuba on 2018-01-17.
 */
public class InstancesSet {

    private static volatile BasicWindow instanceBasicWindow = null;
    private static volatile SearchClientWindow instanceShowClientWindow = null;
    private static volatile AddClientWindow instanceAddClientWindow = null;
    private static volatile AddInvoiceWindow instanceAddInvoiceWindow = null;
    private static volatile EditRoomWindow instanceEditRoomWindow = null;
    private static volatile DataByRESTful instanceDataByRESTful = null;

    private InstancesSet() {
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

    public static DataByRESTful getInstanceDataByRESTful() {
        if (instanceDataByRESTful == null) {
            synchronized (DataByRESTful.class) {
                if (instanceDataByRESTful == null) {
                    instanceDataByRESTful = new DataByRESTful();
                }
            }
        }
        return instanceDataByRESTful;
    }



}

package bus;

import dto.Report;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface ReportBUS extends Remote {
    Report getAllReportByTime(LocalDateTime start, LocalDateTime end, String type) throws RemoteException;
}

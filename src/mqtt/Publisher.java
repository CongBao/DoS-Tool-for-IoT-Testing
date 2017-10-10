package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {

	public static void main(String[] args) throws MqttException {
		if (args.length < 4) {
			System.out.println("Usage: java -jar Publisher.jar [URL] [port] [topic] [content]");
		} else {
			String url = args[0];
			String port = args[1];
			String topic = args[2];
			String content = args[3];
			MqttClient client = new MqttClient("tcp://" + url + ":" + port, "Client_Publisher");
			client.setCallback(new MqttCallback() {

				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("Delivery complete: " + token.isComplete());

				}

				@Override
				public void connectionLost(Throwable cause) {
					System.out.println("Disconnected.");
				}
			});
			client.connect();
			client.publish(topic, content.getBytes(), 2, true);
		}
	}

}

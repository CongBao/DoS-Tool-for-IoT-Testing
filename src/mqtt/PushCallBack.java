package mqtt;

import java.util.Date;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallBack implements MqttCallback {

	private int time = 0;
	private static long lastSec = new Date().getTime();

	public static void setTime() {
		lastSec = new Date().getTime();
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("Disconnected.");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("Delivery complete: " + token.isComplete());
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Message topic: " + topic);
		System.out.println("Message QoS: " + message.getQos());
		System.out.println("Message content: " + new String(message.getPayload(), "ISO-8859-1"));
		System.out.println("Times: " + ++time);
		System.out.println("Duration: " + (new Date().getTime() - lastSec));
		System.out.println();
	}
}

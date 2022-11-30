package client.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Message {
	private int messageType;
	private int requestId;
	private String objectReference;
	private String method;
	private String arguments;

	public Message(int messageType, int requestId, String objectReference, String method, String arguments) {
		this.messageType = messageType;
		this.requestId = requestId;
		this.objectReference = objectReference;
		this.method = method;
		this.arguments = arguments;
	}

	public static Message buildFromJson(String json) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(json.replaceAll("\\p{C}", ""));

		return new Message(
				((Long) jsonObject.get("messageType")).intValue(),
				((Long) jsonObject.get("requestId")).intValue(),
				(String) jsonObject.get("objectReference"),
				(String) jsonObject.get("method"),
				(String) jsonObject.get("arguments")
		);
	}

	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("messageType", messageType);
		obj.put("requestId", requestId);
		obj.put("objectReference", objectReference);
		obj.put("method", method);
		obj.put("arguments", arguments);
		return obj.toJSONString();
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getObjectReference() {
		return objectReference;
	}

	public void setObjectReference(String objectReference) {
		this.objectReference = objectReference;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}
}

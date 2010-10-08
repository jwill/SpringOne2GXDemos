package websocketchat
import org.json.*
import org.jwebsocket.api.*
import org.jwebsocket.client.token.*

class WebsocketChatController {
    // these will be injected by Griffon
    def model
    def view

    void mvcGroupInit(Map args) {
        // this method is called after model and view are injected
    }
    
    def openConnection = {
		model.client = new BaseTokenClient()
		model.client.open(model.endpoint)
		def listener = [
			processToken:{aEvent, aToken ->
				println "token"+aToken
				def json = new JSONObject(aToken.toString())
				println json.getString("type")
				switch(json.getString("type")) {
					case "welcome":
						model.msgEventList.add("Logged in.")
					break
					case "event":
						if (json.getString("name").equals("login")) 
							model.msgEventList.add(json.getString("username") + " logged in.")
						else if (json.getString("name").equals("logout"))
							model.msgEventList.add(json.getString("username") + " logged out.")
					break
					case "broadcast":
						def sender = json.getString("sender")
						def msg = json.getString("data")
						model.msgEventList.add("$sender: $msg")
					break
				}
				// if this is a broadcast message
				if (json.has("data") && json.getString("type").equals("broadcast")) {
					
				}
			}, processOpened:{aEvent -> println aEvent}, 
			processPacket:{evt, pkt -> 
		}] as WebSocketClientTokenListener
		
		model.client.addTokenClientListener(listener)
    }
    
    def login = {
		if (!model.client)
			openConnection()
			
		def username = System.getProperty("user.name")
		println model.client.login(username, '')
		model.msgEventList.add("Logging in...")
    }
    
    def setEndpoint = { url ->
		model.endpoint = url
    }
    
    def closeConnection = {
		model.client.disconnect()
    }
    
    def sendMessage = { message ->
		model.client?.broadcastText(message)
		model.msgEventList.add("Me: "+message)
    }
    
    def sendQuestion = { question ->
		sendMessage("Question:"+question)
    }

    /*
    def action = { evt = null ->
    }
    */
}

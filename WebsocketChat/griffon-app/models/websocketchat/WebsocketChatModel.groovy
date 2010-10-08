package websocketchat
import org.jwebsocket.api.*
import groovy.beans.Bindable
import org.jwebsocket.client.token.*

import ca.odell.glazedlists.*
import ca.odell.glazedlists.swing.*

class WebsocketChatModel {
   // @Bindable String propName
   def client
   def endpoint = "ws://localhost:8787"
   EventList msgEventList = new BasicEventList()
   EventListModel msgModel = new EventListModel(msgEventList)
}
